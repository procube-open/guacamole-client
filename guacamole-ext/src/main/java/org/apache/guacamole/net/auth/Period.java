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

public class Period {
    private String startTime;
    private String endTime;
    private String vaildFrom;
    private String validUntil;

    public Period() {}
    
    public Period(String startTime, String endTime, String vaildFrom, String validUntil) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.vaildFrom = vaildFrom;
        this.validUntil = validUntil;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getVaildFrom() {
        return vaildFrom;
    }

    public String getValidUntil() {
        return validUntil;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setVaildFrom(String vaildFrom) {
        this.vaildFrom = vaildFrom;
    }

    public void setValidUntil(String validUntil) {
        this.validUntil = validUntil;
    }

}
