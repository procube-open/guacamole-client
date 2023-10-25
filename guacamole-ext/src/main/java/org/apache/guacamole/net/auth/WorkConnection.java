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

import java.util.Date;

public class WorkConnection {
    String identifier;
    String parentIdentifier;
    String name;
    String protocol;
    Date lastActive;

    public WorkConnection() {}

    public WorkConnection(String identifier, String parentIdentifier, String name, String protocol, Date lastActive) {
        this.identifier = identifier;
        this.parentIdentifier = parentIdentifier;
        this.name = name;
        this.protocol = protocol;
        this.lastActive = lastActive;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getParentIdentifier() {
        return parentIdentifier;
    }

    public String getName() {
        return name;
    }

    public String getProtocol() {
        return protocol;
    }

    public Date getLastActive() {
        return lastActive;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setParentIdentifier(String parentIdentifier) {
        this.parentIdentifier = parentIdentifier;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public void setLastActive(Date lastActive) {
        this.lastActive = lastActive;
    }

}
