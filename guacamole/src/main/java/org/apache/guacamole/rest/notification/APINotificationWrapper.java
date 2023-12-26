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

package org.apache.guacamole.rest.notification;

import java.util.Date;

import org.apache.guacamole.net.auth.Notification;

public class APINotificationWrapper implements Notification {

    /**
     * The wrapped APINotification.
     */
    APINotification apiNotification;

    /**
     * Creates a new APINotificationWrapper which wraps the given APINotification,
     * exposing its properties through the Notification interface.
     *
     * @param apiNotification
     *     The APINotification to wrap.
     */
    public APINotificationWrapper(APINotification apiNotification) {
        this.apiNotification = apiNotification;
    }

    @Override
    public String getIdentifier() {
        return apiNotification.getIdentifier();
    }

    @Override
    public void setIdentifier(String identifier) {
        apiNotification.setIdentifier(identifier);
    }

    @Override
    public String getIdmIdentifier() {
        return apiNotification.getIdmIdentifier();
    }

    @Override
    public void setIdmIdentifier(String idmIdentifier) {
        apiNotification.setIdmIdentifier(idmIdentifier);
    }

    @Override
    public Date getTimestamp() {
        return apiNotification.getTimestamp();
    }

    @Override
    public void setTimestamp(Date timestamp) {
        apiNotification.setTimestamp(timestamp);
    }

    @Override
    public String getTitle() {
        return apiNotification.getTitle();
    }

    @Override
    public void setTitle(String title) {
        apiNotification.setTitle(title);
    }

    @Override
    public String getMessage() {
        return apiNotification.getMessage();
    }

    @Override
    public void setMessage(String message) {
        apiNotification.setMessage(message);
    }
    
}
