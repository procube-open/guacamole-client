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

import java.util.Date;

import org.apache.guacamole.auth.jdbc.base.ModeledDirectoryObject;
import org.apache.guacamole.net.auth.Notification;

public class ModeledNotification extends ModeledDirectoryObject<NotificationModel> implements Notification {

    @Override
    public String getIdmIdentifier() {
        return getModel().getIdmIdentifier();
    }

    @Override
    public Date getTimestamp() {
        return getModel().getTimestamp();
    }

    @Override
    public String getTitle() {
        return getModel().getTitle();
    }

    @Override
    public String getMessage() {
        return getModel().getMessage();
    }

    @Override
    public void setIdmIdentifier(String idmIdentifier) {
        getModel().setIdmIdentifier(idmIdentifier);
    }

    @Override
    public void setTimestamp(Date timestamp) {
        getModel().setTimestamp(new Date());
    }

    @Override
    public void setTitle(String title) {
        getModel().setTitle(title);
    }

    @Override
    public void setMessage(String message) {
        getModel().setMessage(message);
    }

}
