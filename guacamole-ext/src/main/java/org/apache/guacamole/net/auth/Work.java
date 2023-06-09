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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.GuacamoleUnsupportedException;
import org.apache.guacamole.protocol.GuacamoleConfiguration;

/**
 * Represents a pairing of a GuacamoleConfiguration with a unique,
 * human-readable identifier, and abstracts the work process. The
 * backing GuacamoleConfiguration may be intentionally obfuscated or tokenized
 * to protect sensitive configuration information.
 */
public interface Work extends Identifiable, Connectable, Attributes, Nameable {

    /**
     * Returns the unique identifier of the parent WorkGroup for
     * this Work.
     * 
     * @return The unique identifier of the parent WorkGroup for
     * this Work.
     */
    public String getParentIdentifier();

    /**
     * Sets the unique identifier of the parent WorkGroup for
     * this Work.
     * 
     * @param parentIdentifier The unique identifier of the parent 
     * WorkGroup for this Work.
     */
    public void setParentIdentifier(String parentIdentifier);

    /**
     * Returns the GuacamoleConfiguration associated with this Work. Note
     * that because configurations may contain sensitive information, some data
     * in this configuration may be omitted or tokenized.
     *
     * @return The GuacamoleConfiguration associated with this Work.
     */
    public GuacamoleConfiguration getConfiguration();

    /**
     * Sets the GuacamoleConfiguration associated with this Work.
     *
     * @param config The GuacamoleConfiguration to associate with this
     *               Work.
     */
    public void setConfiguration(GuacamoleConfiguration config);

    /**
     * Returns the date and time that this work was last used. If the
     * work was never used, the time that the work was last used is
     * unknown, or this information is not visible to the current user, this
     * may be null.
     *
     * @return
     *     The date and time this work was last used, or null if this
     *     information is unavailable or inapplicable.
     */
    Date getLastActive();

    /**
     * Returns a list of WorkRecords representing the usage history
     * of this Work, including any active users. WorkRecords
     * in this list will be sorted in descending order of end time (active
     * works are first), and then in descending order of start time
     * (newer works are first). If work history tracking is
     * not implemented this method should throw GuacamoleUnsupportedException.
     *
     * @deprecated 
     *     This function has been deprecated in favor of
     *     {@link getWorkHistory}, which returns the work history
     *     as an ActivityRecordSet that can be easily sorted and filtered.
     *     While the getHistory() method is provided for API compatibility,
     *     new implementations should avoid use of this method and, instead,
     *     implement the getWorkHistory() method.
     * 
     * @return
     *     A list of WorkRecrods representing the usage history of this
     *     Work.
     *
     * @throws GuacamoleException
     *     If history tracking is not implemented, if an error occurs while
     *     reading the history of this work, or if permission is
     *     denied.
     */
    @Deprecated
    default List<? extends WorkRecord> getHistory()
            throws GuacamoleException {
        return Collections.unmodifiableList(new ArrayList<>(getWorkHistory().asCollection()));
    }

    /**
     * Returns an ActivityRecordSet containing WorkRecords that
     * represent the usage history of this Work, including any active
     * users. WorkRecords in this list will be sorted in descending order
     * of end time (active works are first), and then in descending order
     * of start time (newer works are first). If work history
     * tracking has not been implemented, or has been implemented using the
     * deprecated {@link getHistory} method, this function should throw
     * GuacamoleUnsupportedExpcetion.
     * 
     * @return
     *     An ActivityRecordSet containing WorkRecords representing the
     *     usage history of this Work.
     * 
     * @throws GuacamoleException
     *     If history tracking is not implemented, if an error occurs while
     *     reading the history of this work, or if permission is
     *     denied.
     */
    default ActivityRecordSet<WorkRecord> getWorkHistory()
            throws GuacamoleException {
        throw new GuacamoleUnsupportedException("This implementation of Work does not provide work history.");
    }
    
    /**
     * Returns identifiers of all readable sharing profiles that can be used to
     * join this work when it is active. The level of access granted to a
     * joining user is dictated by the work parameters associated with
     * the sharing profile, not necessarily the parameters of the primary
     * work being joined.
     *
     * @return
     *     A Set of identifiers representing the sharing profiles for this
     *     work.
     *
     * @throws GuacamoleException
     *     If an error occurs while fetching the sharing profiles for this
     *     work.
     */
    public Set<String> getSharingProfileIdentifiers() throws GuacamoleException;

}
