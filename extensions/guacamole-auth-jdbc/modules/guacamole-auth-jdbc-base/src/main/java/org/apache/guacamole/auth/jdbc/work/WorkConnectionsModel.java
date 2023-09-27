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

package org.apache.guacamole.auth.jdbc.work;

public class WorkConnectionsModel {
    
    /**
     * The unique identifier associated for work.
     */
    private String workIdentifier;

    /**
     * The unique identifier associated for connection.
     */
    private String connectionIdentifier;

    /**
     * Creates a new, empty WorkConnectionsModel.
     */
    public WorkConnectionsModel() {
    }

    /**
     * Returns the unique identifier associated for work.
     * 
     * @return
     *     The unique identifier associated for work.
     */
    public String getWorkIdentifier() {
        return workIdentifier;
    }

    /**
     * Sets the unique identifier associated for work.
     * 
     * @param workIdentifier
     *     The unique identifier associated for work.
     */
    public void setWorkIdentifier(String workIdentifier) {
        this.workIdentifier = workIdentifier;
    }

    /**
     * Returns the unique identifier associated for connection.
     * 
     * @return
     *     The unique identifier associated for connection.
     */
    public String getConnectionIdentifier() {
        return connectionIdentifier;
    }

    /**
     * Sets the unique identifier associated for connection.
     * 
     * @param connectionIdentifier
     *     The unique identifier associated for connection.
     */
    public void setConnectionIdentifier(String connectionIdentifier) {
        this.connectionIdentifier = connectionIdentifier;
    }

}
