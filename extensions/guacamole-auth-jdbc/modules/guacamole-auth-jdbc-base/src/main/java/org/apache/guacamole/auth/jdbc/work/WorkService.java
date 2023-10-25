/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.guacamole.auth.jdbc.work;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.auth.jdbc.base.ModeledDirectoryObjectMapper;
import org.apache.guacamole.auth.jdbc.base.ModeledDirectoryObjectService;
import org.apache.guacamole.auth.jdbc.connection.ConnectionService;
import org.apache.guacamole.auth.jdbc.connection.ModeledConnection;
import org.apache.guacamole.auth.jdbc.permission.ObjectPermissionMapper;
import org.apache.guacamole.auth.jdbc.permission.ObjectPermissionModel;
import org.apache.guacamole.auth.jdbc.permission.WorkPermissionMapper;
import org.apache.guacamole.auth.jdbc.user.ModeledAuthenticatedUser;
import org.apache.guacamole.auth.jdbc.user.UserService;
import org.apache.guacamole.net.auth.Work;
import org.apache.guacamole.net.auth.WorkConnection;
import org.apache.guacamole.net.auth.WorkUser;
import org.apache.guacamole.net.auth.permission.ObjectPermission;
import org.apache.guacamole.net.auth.permission.ObjectPermissionSet;
import org.apache.guacamole.net.auth.permission.SystemPermission;
import org.apache.guacamole.net.auth.permission.SystemPermissionSet;
import org.mybatis.guice.transactional.Transactional;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * Service which provides convenience methods for creating, retrieving, and
 * manipulating works.
 */
public class WorkService extends ModeledDirectoryObjectService<ModeledWork, Work, WorkModel> {

    /**
     * Mapper for accessing work.
     */
    @Inject
    private WorkMapper workMapper;

    /**
     * Mapper for accessing connections.
     */
    @Inject
    private ConnectionService connectionService;

    /**
     * Mapper for manipulating users.
     */
    @Inject
    private UserService userService;

    /**
     * Mapper for manipulating work permissions.
     */
    @Inject
    private WorkPermissionMapper workPermissionMapper;
    
    /**
     * Provider for creating works.
     */
    @Inject
    private Provider<ModeledWork> workProvider;

    @Override
    protected ModeledDirectoryObjectMapper<WorkModel> getObjectMapper() {
        return workMapper;
    }

    @Override
    protected ObjectPermissionMapper getPermissionMapper() {
        return workPermissionMapper;
    }

    @Override
    protected ModeledWork getObjectInstance(ModeledAuthenticatedUser currentUser, WorkModel model)
            throws GuacamoleException {

        ModeledWork work = workProvider.get();
        work.init(currentUser, model);
        return work;

    }

    @Override
    protected WorkModel getModelInstance(ModeledAuthenticatedUser currentUser, Work object) throws GuacamoleException {

        // Create new ModeledWork backed by blank model
        WorkModel model = new WorkModel();
        ModeledWork work = getObjectInstance(currentUser, model);

        // Set model contents through ModeledWork, copying the provided work
        work.setName(object.getName());
        work.setIdmIdentifier(object.getIdmIdentifier());
        work.setAttributes(object.getAttributes());

        return model;
        
    }

    @Override
    protected boolean hasCreatePermission(ModeledAuthenticatedUser user) throws GuacamoleException {

        // Return whether user has explicit work creation permission
        SystemPermissionSet permissionSet = user.getUser().getEffectivePermissions().getSystemPermissions();
        return permissionSet.hasPermission(SystemPermission.Type.CREATE_WORK);

    }

    @Override
    protected ObjectPermissionSet getEffectivePermissionSet(ModeledAuthenticatedUser user) throws GuacamoleException {

        // Return permissions related to works
        return user.getUser().getEffectivePermissions().getWorkPermissions();

    }

    @Override
    @Transactional
    public ModeledWork createObject(ModeledAuthenticatedUser user, Work object) throws GuacamoleException {

        ModeledWork modeledWork = super.createObject(user, object);

        modeledWork.setPeriods(object.getPeriods());
        modeledWork.setConnections(object.getConnections());

        List<WorkUser> workUsers = object.getUsers();
        String workIdentifier = modeledWork.getIdentifier();
        Collection<ObjectPermissionModel> permissions = new ArrayList<>(workUsers.size() * 3);

        for (WorkUser workUser : workUsers) {

            Integer entityID = userService.retrieveObject(user, workUser.getIdentifier()).getModel().getEntityID();

            ObjectPermissionModel readPermission = new ObjectPermissionModel();
            readPermission.setEntityID(entityID);
            readPermission.setObjectIdentifier(workIdentifier);
            readPermission.setType(ObjectPermission.Type.READ);
            permissions.add(readPermission);

            if (workUser.getIsWorker()) {
                ObjectPermissionModel workerPermission = new ObjectPermissionModel();
                workerPermission.setEntityID(entityID);
                workerPermission.setObjectIdentifier(workIdentifier);
                workerPermission.setType(ObjectPermission.Type.WORKER);
                permissions.add(workerPermission);
            }

            if (workUser.getIsManager()) {
                ObjectPermissionModel managerPermission = new ObjectPermissionModel();
                managerPermission.setEntityID(entityID);
                managerPermission.setObjectIdentifier(workIdentifier);
                managerPermission.setType(ObjectPermission.Type.MANAGER);
                permissions.add(managerPermission);
            }

        }

        workMapper.insertPeriods(modeledWork.getModel());
        workMapper.insertConnections(modeledWork.getModel());
        getPermissionMapper().insert(permissions);

        return modeledWork;
    }

    public List<WorkConnection> getWorkConnections(ModeledAuthenticatedUser user, Collection<String> connectionIdentifiers) throws GuacamoleException {
        Collection<ModeledConnection> connectionModels = connectionService.retrieveObjects(user, connectionIdentifiers);
        List<WorkConnection> workConnections = new ArrayList<>(connectionIdentifiers.size());

        for (ModeledConnection connectionModel : connectionModels) {
            WorkConnection workConnection = new WorkConnection(
                connectionModel.getIdentifier(),
                connectionModel.getParentIdentifier(),
                connectionModel.getName(),
                connectionModel.getConfiguration().getProtocol(),
                connectionModel.getLastActive()
            );
            workConnections.add(workConnection);
        }

        return workConnections;
    }

}
