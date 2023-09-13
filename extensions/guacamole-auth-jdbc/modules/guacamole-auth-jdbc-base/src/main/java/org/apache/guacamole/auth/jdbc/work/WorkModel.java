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

import java.util.Collection;

import org.apache.guacamole.auth.jdbc.base.ObjectModel;

public class WorkModel extends ObjectModel {

    /**
     * The unique identifier associated for IDM with this work.
     */
    private String idmIdentifier;

    /**
     * The human-readable name associated with this work.
     */
    private String name;

    /**
     * The work periods associated with this work.
     */
    private Collection<WorkPeriodModel> workPeriods;

    /**
     * Creates a new, empty WorkModel.
     */
    public WorkModel() {
    }

    /**
     * Returns the unique identifier associated for IDM with this work.
     * 
     * @return
     *     The unique identifier associated for IDM with this work.
     */
    public String getIdmIdentifier() {
        return idmIdentifier;
    }

    /**
     * Sets the unique identifier associated for IDM with this work.
     * 
     * @param idmIdentifier
     *     The unique identifier to associate for IDM with this work.
     */
    public void setIdmIdentifier(String idmIdentifier) {
        this.idmIdentifier = idmIdentifier;
    }

    /**
     * Returns the name associated with this work.
     *
     * @return
     *     The name associated with this work.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name associated with this work.
     *
     * @param name
     *     The name to associate with this work.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the work periods associated with this work.
     *
     * @return
     *     The work periods associated with this work.
     */
    public Collection<WorkPeriodModel> getWorkPeriods() {
        return workPeriods;
    }

    /**
     * Sets the work periods associated with this work.
     *
     * @param workPeriods
     *     The work periods to associate with this work.
     */
    public void setWorkPeriods(Collection<WorkPeriodModel> workPeriods) {
        this.workPeriods = workPeriods;
    }

    @Override
    public String getIdentifier() {

        // If no associated ID, then no associated identifier
        Integer id = getObjectID();
        if (id == null)
            return null;

        // Otherwise, the identifier is the ID as a string
        return id.toString();

    }

    @Override
    public void setIdentifier(String identifier) {
        throw new UnsupportedOperationException("Connection identifiers are derived from IDs. They cannot be set.");
    }

}