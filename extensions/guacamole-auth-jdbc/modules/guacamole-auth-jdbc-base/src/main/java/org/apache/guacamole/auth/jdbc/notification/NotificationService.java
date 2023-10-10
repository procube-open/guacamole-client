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

import org.apache.guacamole.auth.jdbc.base.ModeledDirectoryObjectMapper;
import org.apache.guacamole.auth.jdbc.base.ModeledDirectoryObjectService;
import org.apache.guacamole.net.auth.AuthenticatedUser;
import org.apache.guacamole.net.auth.Notification;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;

public class NotificationService extends ModeledDirectoryObjectService<ModeledNotification, Notification, NotificationModel> {
    
    @Inject
    NotificationMapper notificationMapper;

    @Override
    protected ModeledDirectoryObjectMapper<NotificationModel> getObjectMapper() {
        return notificationMapper;
    }

    @Override
    protected ModeledNotification getObjectInstance(AuthenticatedUser user, NotificationModel object) {
        return ModeledNotification(object);
    }

}
