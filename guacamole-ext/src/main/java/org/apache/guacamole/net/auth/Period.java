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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Period {
    private String startTime;
    private String endTime;
    private String validFrom;
    private String validUntil;

    public Period() {}
    
    public Period(String startTime, String endTime, String validFrom, String validUntil) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getValidFrom() {
        return validFrom;
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

    public void setvalidFrom(String validFrom) {
        this.validFrom = validFrom;
    }

    public void setValidUntil(String validUntil) {
        this.validUntil = validUntil;
    }

    public boolean isWithinPeriod() {
        Date now = new Date();
        SimpleDateFormat dsd = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat tsd = new SimpleDateFormat("HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        try {
            Date validFrom = dsd.parse(this.validFrom);
            cal.setTime(dsd.parse(this.validUntil));
            cal.add(Calendar.DATE, 1);
            Date validUntil = cal.getTime();
            Date startTime = tsd.parse(this.startTime);
            Date endTime = tsd.parse(this.endTime);
            return now.after(validFrom) && now.before(validUntil) && now.after(startTime) && now.before(endTime);
        } catch (Exception e) {
            return false;
        }
    }

}
