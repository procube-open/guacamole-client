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

import java.sql.Date;
import java.sql.Time;

public class WorkPeriodModel {

    /**
     * The unique identifier associated for work.
     */
    private String workIdentifier;

    /**
     * work start time.
     */
    private Time startTime;

    /**
     * work end time.
     */
    private Time endTime;

    /**
     * work valid from this date.
     */
    private Date validFrom;

    /**
     * work valid until this date.
     */
    private Date validUntil;

    /**
     * Creates a new, empty WorkPeriodModel.
     */
    public WorkPeriodModel() {
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
     *     The unique identifier to associate for work.
     */
    public void setWorkIdentifier(String workIdentifier) {
        this.workIdentifier = workIdentifier;
    }

    /**
     * Returns the work start time.
     *
     * @return
     *     The work start time.
     */
    public Time getStartTime() {
        return startTime;
    }

    /**
     * Sets the work start time.
     *
     * @param startTime
     *     The work start time.
     */
    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    /**
     * Returns the work end time.
     *
     * @return
     *     The work end time.
     */
    public Time getEndTime() {
        return endTime;
    }

    /**
     * Sets the work end time.
     *
     * @param endTime
     *     The work end time.
     */
    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    /**
     * Returns the work valid from this date.
     *
     * @return
     *     The work valid from this date.
     */
    public Date getValidFrom() {
        return validFrom;
    }

    /**
     * Sets the work valid from this date.
     *
     * @param validFrom
     *     The work valid from this date.
     */
    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    /**
     * Returns the work valid until this date.
     *
     * @return
     *     The work valid until this date.
     */
    public Date getValidUntil() {
        return validUntil;
    }

    /**
     * Sets the work valid until this date.
     *
     * @param validUntil
     *     The work valid until this date.
     */
    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

}
