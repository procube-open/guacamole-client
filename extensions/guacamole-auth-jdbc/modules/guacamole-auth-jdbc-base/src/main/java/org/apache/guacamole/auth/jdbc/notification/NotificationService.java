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

package org.apache.guacamole.auth.jdbc.notification;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.auth.jdbc.JDBCEnvironment;
import org.apache.guacamole.auth.jdbc.base.ModeledDirectoryObjectMapper;
import org.apache.guacamole.auth.jdbc.base.ModeledDirectoryObjectService;
import org.apache.guacamole.auth.jdbc.permission.ObjectPermissionMapper;
import org.apache.guacamole.auth.jdbc.user.ModeledAuthenticatedUser;
import org.apache.guacamole.net.auth.Notification;
import org.apache.guacamole.net.auth.permission.ObjectPermissionSet;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotificationService extends ModeledDirectoryObjectService<ModeledNotification, Notification, NotificationModel> {
    
    /**
     * Logger for this class.
     */
    private final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    @Inject
    private NotificationMapper notificationMapper;

    @Inject
    private Provider<ModeledNotification> notificationProvider;

    /**
     * The environment of the Guacamole server.
     */
    @Inject
    private JDBCEnvironment environment;

    @Override
    protected ModeledDirectoryObjectMapper<NotificationModel> getObjectMapper() {
        return notificationMapper;
    }

    @Override
    protected ModeledNotification getObjectInstance(ModeledAuthenticatedUser user, NotificationModel object) {
        ModeledNotification notification = notificationProvider.get();
        notification.init(user, object);
        return notification;
    }

    @Override
    protected NotificationModel getModelInstance(ModeledAuthenticatedUser user, Notification object) {
        NotificationModel model = new NotificationModel();
        ModeledNotification notification = getObjectInstance(user, model);

        notification.setIdmIdentifier(object.getIdmIdentifier());
        notification.setTitle(object.getTitle());
        notification.setMessage(object.getMessage());

        return model;

    }
    
    @Override
    public Collection<ModeledNotification> retrieveObjects(ModeledAuthenticatedUser user,
            Collection<String> identifiers) throws GuacamoleException {

        // Ignore invalid identifiers
        List<String> filteredIdentifiers = filterIdentifiers(identifiers);

        // Do not query if no identifiers given
        if (filteredIdentifiers.isEmpty())
            return Collections.<ModeledNotification>emptyList();

        int batchSize = environment.getBatchSize();

        // Process the filteredIdentifiers in batches using Lists.partition() and flatMap
        Collection<NotificationModel> allObjects = Lists.partition(filteredIdentifiers, batchSize).stream()
                .flatMap(chunk -> {
                    Collection<NotificationModel> objects;
                    objects = getObjectMapper().select(chunk);

                    return objects.stream();
                })
                .collect(Collectors.toList());

        // Return collection of requested objects
        return getObjectInstances(user, allObjects);
    }

    @Override
    public Set<String> getIdentifiers(ModeledAuthenticatedUser user) throws GuacamoleException {
        Set<String> identifiers = getObjectMapper().selectIdentifiers();
        logger.debug("getIdentifiers() - Found {} identifiers", identifiers.size());
        logger.debug("getIdentifiers() - Identifiers: {}", identifiers);
        return identifiers;
    }

    @Override
    protected ObjectPermissionMapper getPermissionMapper() {
        throw new UnsupportedOperationException("Unimplemented method 'getPermissionMapper'");
    }

    @Override
    protected boolean hasCreatePermission(ModeledAuthenticatedUser user) throws GuacamoleException {
        throw new UnsupportedOperationException("Unimplemented method 'hasCreatePermission'");
    }

    @Override
    protected ObjectPermissionSet getEffectivePermissionSet(ModeledAuthenticatedUser user) throws GuacamoleException {
        throw new UnsupportedOperationException("Unimplemented method 'getEffectivePermissionSet'");
    }

}
