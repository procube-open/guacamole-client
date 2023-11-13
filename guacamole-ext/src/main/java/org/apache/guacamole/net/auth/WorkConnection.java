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
    String hostname;
    String protocol;
    String remark;
    Date lastActive;

    public WorkConnection() {}

    public WorkConnection(String identifier, String hostname, String remark, String protocol, Date lastActive) {
        this.identifier = identifier;
        this.hostname = hostname;
        this.protocol = protocol;
        this.remark = remark;
        this.lastActive = lastActive;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getHostname() {
        return hostname;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getRemark() {
        return remark;
    }

    public Date getLastActive() {
        return lastActive;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setLastActive(Date lastActive) {
        this.lastActive = lastActive;
    }

}
