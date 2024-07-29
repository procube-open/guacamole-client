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

package org.apache.guacamole.rest.work;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.List;
import java.util.Map;

import org.apache.guacamole.net.auth.Period;
import org.apache.guacamole.net.auth.Work;
import org.apache.guacamole.net.auth.WorkConnection;
import org.apache.guacamole.net.auth.WorkUser;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value=Include.NON_NULL)
public class APIWork {
    private String name;
    private String identifier;
    private String idmIdentifier;
    private Map<String, String> attributes;
    private List<Period> periods;
    private List<WorkConnection> connections;
    private List<WorkUser> users;
    private Boolean isWorker;
    private Boolean isManager;
    private Boolean disabled;

    public APIWork() {}
    public APIWork(Work work) {
        this.name = work.getName();
        this.identifier = work.getIdentifier();
        this.idmIdentifier = work.getIdmIdentifier();
        this.attributes = work.getAttributes();
        this.periods = work.getPeriods();
        this.connections = work.getConnections();
        this.users = work.getUsers();
        this.isWorker = work.isWorker();
        this.isManager = work.isManager();
        this.disabled = work.isDisabled();
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Map<String, String> getAttributes() {
        return this.attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdmIdentifier() {
        return idmIdentifier;
    }
    
    public void setIdmIdentifier(String idmIdentifier) {
        this.idmIdentifier = idmIdentifier;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Period> periods){
        this.periods = periods;
    }

    public List<WorkConnection> getConnections() {
        return connections;
    }

    public void setConnections(List<WorkConnection> connections) {
        this.connections = connections;
    }

    public List<WorkUser> getUsers() {
        return users;
    }

    public void setUsers(List<WorkUser> users){
        this.users = users;
    }

    public Boolean getIsWorker() {
        return isWorker;
    }

    public void setIsWorker(Boolean isWorker) {
        this.isWorker = isWorker;
    }

    public Boolean getIsAdmin() {
        return isManager;
    }

    public void setIsAdmin(Boolean isManager) {
        this.isManager = isManager;
    }

    public Boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

}
