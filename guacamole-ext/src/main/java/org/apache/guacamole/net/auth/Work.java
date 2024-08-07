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

package org.apache.guacamole.net.auth;

import java.util.List;

public interface Work extends Identifiable, Attributes, Nameable {

    /**
     * Returns the unique identifier of IDM for this Work.
     * 
     * @return The unique identifier of IDM for
     * this Connection.
     */
    public String getIdmIdentifier();

    /**
     * Sets the unique identifier of IDM for this Work.
     * 
     * @param idmIdentifier Sets the unique identifier of IDM for this Work.
     */
    public void setIdmIdentifier(String idmIdentifier);

    public Boolean isDisabled();

    public void setDisabled(Boolean disabled);

    public List<Period> getPeriods();

    public void setPeriods(List<Period> periods);

    public List<WorkConnection> getConnections();

    public void setConnections(List<WorkConnection> connections);

    public List<WorkUser> getUsers();

    public Boolean isWorker();

    public Boolean isManager();

}
