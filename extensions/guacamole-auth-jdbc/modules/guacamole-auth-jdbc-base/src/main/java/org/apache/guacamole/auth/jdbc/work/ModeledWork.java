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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.auth.jdbc.base.ModeledDirectoryObject;
import org.apache.guacamole.form.DateField;
import org.apache.guacamole.form.Form;
import org.apache.guacamole.form.TimeField;
import org.apache.guacamole.net.auth.Period;
import org.apache.guacamole.net.auth.Work;
import org.apache.guacamole.net.auth.WorkConnection;
import org.apache.guacamole.net.auth.WorkUser;
import org.apache.guacamole.net.auth.permission.ObjectPermission;
import org.apache.guacamole.net.auth.permission.ObjectPermissionSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

public class ModeledWork extends ModeledDirectoryObject<WorkModel> implements Work {

    /**
     * Logger for this class.
     */
    private static final Logger logger = LoggerFactory.getLogger(ModeledWork.class);

    @Inject
    private WorkService workService;

    /**
     * All possible attributes of work objects organized as
     * individual, logical forms.
     */
    public static final Collection<Form> ATTRIBUTES = Collections.unmodifiableCollection(Arrays.asList());

    /**
     * The names of all attributes which are explicitly supported by this
     * extension's Work objects.
     */
    public static final Set<String> ATTRIBUTE_NAMES =
            Collections.unmodifiableSet(new HashSet<String>(Arrays.asList()));

    @Override
    public String getName() {
        return getModel().getName();
    }

    @Override
    public void setName(String name) {
        getModel().setName(name);
    }

    @Override
    public String getIdmIdentifier() {
        return getModel().getIdmIdentifier();
    }

    @Override
    public void setIdmIdentifier(String idmIdentifier) {
        getModel().setIdmIdentifier(idmIdentifier);
    }

    /**
     * Returns whether this work is disabled.
     *
     * @return
     *     true if this work is disabled, false otherwise.
     */
    @Override
    public Boolean isDisabled() {
        return getModel().isDisabled();
    }

    /**
     * Sets whether this work is disabled.
     *
     * @param disabled
     *     true if this work is disabled, false otherwise.
     */
    @Override
    public void setDisabled(Boolean disabled) {
        getModel().setDisabled(disabled);
    }

    @Override
    public Map<String, String> getAttributes() {
        
        // Include any defined arbitrary attributes
        Map<String, String> attributes = super.getAttributes();
        
        return attributes;   
    }

    @Override
    public void setAttributes(Map<String, String> attributes) {

        // Set arbitrary attributes
        super.setAttributes(attributes);
        
    }

    /**
     * Parses the given string into a corresponding date. The string must
     * follow the standard format used by date attributes, as defined by
     * DateField.FORMAT and as would be produced by DateField.format().
     *
     * @param dateString
     *     The date string to parse, which may be null.
     *
     * @return
     *     The date corresponding to the given date string, or null if the
     *     provided date string was null or blank.
     *
     * @throws ParseException
     *     If the given date string does not conform to the standard format
     *     used by date attributes.
     */
    private Date parseDate(String dateString)
    throws ParseException {

        // Return null if no date provided
        java.util.Date parsedDate = DateField.parse(dateString);
        if (parsedDate == null)
            return null;

        // Convert to SQL Date
        return new Date(parsedDate.getTime());

    }

    /**
     * Parses the given string into a corresponding time. The string must
     * follow the standard format used by time attributes, as defined by
     * TimeField.FORMAT and as would be produced by TimeField.format().
     *
     * @param timeString
     *     The time string to parse, which may be null.
     *
     * @return
     *     The time corresponding to the given time string, or null if the
     *     provided time string was null or blank.
     *
     * @throws ParseException
     *     If the given time string does not conform to the standard format
     *     used by time attributes.
     */
    private Time parseTime(String timeString)
    throws ParseException {

        // Return null if no time provided
        java.util.Date parsedDate = TimeField.parse(timeString);
        if (parsedDate == null)
            return null;

        // Convert to SQL Time 
        return new Time(parsedDate.getTime());

    }

    @Override
    public List<Period> getPeriods() {
        Collection<WorkPeriodModel> modeledPeriods = getModel().getWorkPeriods();
        if (modeledPeriods == null)
            return new ArrayList<Period>(0);
        List<Period> periods = new ArrayList<Period>(modeledPeriods.size());
        for (WorkPeriodModel modeledPeriod : modeledPeriods) {
            logger.debug(
                "Modeled period: work {} from {} {} until {} {}",
                getModel().getIdentifier(),
                modeledPeriod.getValidFrom().toString(),
                modeledPeriod.getStartTime().toString(),
                modeledPeriod.getValidUntil().toString(),
                modeledPeriod.getEndTime().toString()
            );
            Period period = new Period(
                modeledPeriod.getStartTime().toString(),
                modeledPeriod.getEndTime().toString(),
                modeledPeriod.getValidFrom().toString(),
                modeledPeriod.getValidUntil().toString()
            );
            periods.add(period);
        }
        return periods;
    }

    @Override
    public void setPeriods(List<Period> periods) {
        Collection<WorkPeriodModel> modeledPeriods = new ArrayList<>(periods.size());
        for (Period period : periods) {
            WorkPeriodModel modeledPeriod = new WorkPeriodModel();
            try {
                modeledPeriod.setStartTime(parseTime(period.getStartTime()));
                modeledPeriod.setEndTime(parseTime(period.getEndTime()));
                modeledPeriod.setValidFrom(parseDate(period.getValidFrom()));
                modeledPeriod.setValidUntil(parseDate(period.getValidUntil()));
            }
            catch (ParseException e) {
                logger.error("Unable to parse period: {}", period, e);
            }
            modeledPeriods.add(modeledPeriod);
        }
        getModel().setWorkPeriods(modeledPeriods);
    }

    @Override
    public List<WorkConnection> getConnections() {
        Collection<String> connectionIdentifiers = getModel().getConnectionIdentifiers();
        if (connectionIdentifiers == null) {
            logger.info("No connections found for work: {}", getModel().getIdmIdentifier());
            return new ArrayList<WorkConnection>(0);
        }
        try {
            logger.info("Retrieving connections for work: {}", getModel().getIdmIdentifier());
            List<WorkConnection> connections = workService.getWorkConnections(getCurrentUser(), connectionIdentifiers);
            return connections;
        }
        catch (GuacamoleException e) {
            logger.error("Unable to retrieve connections for work: {}", getModel().getIdentifier(), e);
            return new ArrayList<WorkConnection>(0);
        }
    }

    @Override
    public void setConnections(List<WorkConnection> connections) {
        Collection<String> connectionIdentifiers = new ArrayList<>(connections.size());
        for (WorkConnection connection : connections) {
            connectionIdentifiers.add(connection.getIdentifier());
        }
        getModel().setConnectionIdentifiers(connectionIdentifiers);
    }

    @Override
    public List<WorkUser> getUsers() {
        return new ArrayList<WorkUser>(0);
    }

    @Override
    public Boolean isWorker() {
        try {
            ObjectPermissionSet permissions = workService.getEffectivePermissionSet(getCurrentUser());
            return permissions.hasPermission(ObjectPermission.Type.WORKER, getIdentifier());
        }
        catch(GuacamoleException e) {
            logger.error("Unable to retrieve effective permissions for user: {}", getCurrentUser().getIdentifier(), e);
            return false;
        }
    }

    @Override
    public Boolean isManager() {
        try {
            ObjectPermissionSet permissions = workService.getEffectivePermissionSet(getCurrentUser());
            return permissions.hasPermission(ObjectPermission.Type.MANAGER, getIdentifier());
        }
        catch(GuacamoleException e) {
            logger.error("Unable to retrieve effective permissions for user: {}", getCurrentUser().getIdentifier(), e);
            return false;
        }
    }

}