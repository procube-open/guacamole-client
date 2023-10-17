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
import java.util.Set;

import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.auth.jdbc.base.JDBCDirectory;
import org.apache.guacamole.net.auth.Notification;

import com.google.inject.Inject;

public class NotificationDirectory extends JDBCDirectory<Notification> {

    /**
     * Service for managing notification objects.
     */
    @Inject
    private NotificationService notificationService;

    @Override
    public Notification get(String identifier) throws GuacamoleException {
        return notificationService.retrieveObject(getCurrentUser(), identifier);
    }

    @Override
    public Collection<Notification> getAll(Collection<String> identifiers) throws GuacamoleException {
        Collection<ModeledNotification> objects = notificationService.retrieveObjects(getCurrentUser(), identifiers);
        return Collections.<Notification>unmodifiableCollection(objects);
    }

    @Override
    public Set<String> getIdentifiers() throws GuacamoleException {
        return notificationService.getIdentifiers(getCurrentUser());
    }

    @Override
    public void add(Notification object) throws GuacamoleException {
        notificationService.createObject(getCurrentUser(), object);
    }

    @Override
    public void update(Notification object) throws GuacamoleException {
        throw new UnsupportedOperationException("Unimplemented method 'hasCreatePermission'");
    }

    @Override
    public void remove(String identifier) throws GuacamoleException {
        notificationService.deleteObject(getCurrentUser(), identifier);
    }

}
