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

import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.auth.jdbc.base.ModeledDirectoryObjectMapper;
import org.apache.guacamole.auth.jdbc.base.ModeledDirectoryObjectService;
import org.apache.guacamole.auth.jdbc.permission.ObjectPermissionMapper;
import org.apache.guacamole.auth.jdbc.user.ModeledAuthenticatedUser;
import org.apache.guacamole.net.auth.Notification;
import org.apache.guacamole.net.auth.permission.ObjectPermissionSet;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class NotificationService extends ModeledDirectoryObjectService<ModeledNotification, Notification, NotificationModel> {
    
    @Inject
    private NotificationMapper notificationMapper;

    @Inject
    private Provider<ModeledNotification> notificationProvider;

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
