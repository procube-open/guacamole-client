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

import java.util.List;
import java.util.Map;

import org.apache.guacamole.net.auth.Period;
import org.apache.guacamole.net.auth.Work;
import org.apache.guacamole.net.auth.WorkConnection;
import org.apache.guacamole.net.auth.WorkUser;

public class APIWorkWrapper implements Work {
    
    /**
     * The wrapped APIWork.
     */
    private final APIWork apiWork;

    /**
     * Creates a new APIWorkWrapper which wraps the given APIWork,
     * exposing its properties through the Work interface.
     *
     * @param apiWork
     *     The APIWork to wrap.
     */
    public APIWorkWrapper(APIWork apiWork) {
        this.apiWork = apiWork;
    }

    @Override
    public String getIdentifier() {
        return apiWork.getIdentifier();
    }

    @Override
    public void setIdentifier(String identifier) {
        apiWork.setIdentifier(identifier);
    }

    @Override
    public Map<String, String> getAttributes() {
        return apiWork.getAttributes();
    }

    @Override
    public void setAttributes(Map<String, String> attributes) {
        apiWork.setAttributes(attributes);
    }

    @Override
    public String getName() {
        return apiWork.getName();
    }

    @Override
    public void setName(String name) {
        apiWork.setName(name);
    }

    @Override
    public String getIdmIdentifier() {
        return apiWork.getIdmIdentifier();
    }

    @Override
    public void setIdmIdentifier(String idmIdentifier) {
        apiWork.setIdmIdentifier(idmIdentifier);
    }

    @Override
    public List<Period> getPeriods() {
        return apiWork.getPeriods();
    }

    @Override
    public void setPeriods(List<Period> periods) {
        apiWork.setPeriods(periods);
    }

    @Override
    public List<WorkConnection> getConnections() {
        return apiWork.getConnections();
    }

    @Override
    public void setConnections(List<WorkConnection> connections) {
        apiWork.setConnections(connections);
    }

    @Override
    public List<WorkUser> getUsers() {
        return apiWork.getUsers();
    }

    @Override
    public Boolean isWorker() {
        return apiWork.getIsWorker();
    }

    @Override
    public Boolean isManager() {
        return apiWork.getIsAdmin();
    }

}
