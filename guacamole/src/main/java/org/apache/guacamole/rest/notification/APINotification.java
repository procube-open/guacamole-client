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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value=Include.NON_NULL)
public class APINotification {
    
    private String identifier;

    private String idmIdentifier;

    private Date starDate;

    private Date endDate;

    private String message;

    public APINotification() {}
    public APINotification(Notification notification) {
        this.identifier = notification.getIdentifier();
        this.idmIdentifier = notification.getIdmIdentifier();
        this.starDate = notification.getStartDate();
        this.endDate = notification.getEndDate();
        this.message = notification.getMessage();
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier= identifier;
    }

    public String getIdmIdentifier() {
        return this.idmIdentifier;
    }

    public void setIdmIdentifier(String idmIdentifier) {
        this.idmIdentifier = idmIdentifier;
    }

    public Date getStartDate() {
        return this.starDate;
    }

    public void setStartDate(Date startDate) {
        this.starDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate= endDate;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message= message;
    }

    @Override
    public String toString()
    {
        return "{" + this.identifier + " " + this.idmIdentifier + " " + this.starDate + " " + this.endDate + this.message + "}";
    }

}
