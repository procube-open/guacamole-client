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

package org.apache.guacamole.rest.work;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.net.auth.Work;
import org.apache.guacamole.protocol.GuacamoleConfiguration;
import org.apache.guacamole.rest.sharingprofile.APISharingProfile;

/**
 * A simple work to expose through the REST endpoints.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value=Include.NON_NULL)
public class APIWork {

    /**
     * The name of this work.
     */
    private String name;
    
    /**
     * The identifier of this work.
     */
    private String identifier;
    
    /**
     * The identifier of the parent work group for this work.
     */
    private String parentIdentifier;

    /**
     * The protocol of this work.
     */
    private String protocol;
    
    /**
     * Map of all associated parameter values, indexed by parameter name.
     */
    private Map<String, String> parameters;
    
    /**
     * Map of all associated attributes by attribute identifier.
     */
    private Map<String, String> attributes;

    /**
     * All associated sharing profiles. If sharing profiles are not being
     * queried, this may be omitted.
     */
    private Collection<APISharingProfile> sharingProfiles;

    /**
     * The count of currently active works using this work.
     */
    private int activeWorks;

    /**
     * The date and time that this work was last used, or null if this
     * work has never been used or this information is unavailable.
     */
    private Date lastActive;

    /**
     * Create an empty APIWork.
     */
    public APIWork() {}
    
    /**
     * Create an APIWork from a Work record. Parameters for the
     * work will not be included.
     *
     * @param work The work to create this APIWork from.
     * @throws GuacamoleException If a problem is encountered while
     *                            instantiating this new APIWork.
     */
    public APIWork(Work work) 
            throws GuacamoleException {

        // Set work information
        this.name = work.getName();
        this.identifier = work.getIdentifier();
        this.parentIdentifier = work.getParentIdentifier();
        this.activeWorks = work.getActiveWorks();
        this.lastActive = work.getLastActive();
        
        // Set protocol from configuration
        GuacamoleConfiguration configuration = work.getConfiguration();
        this.protocol = configuration.getProtocol();

        // Associate any attributes
        this.attributes = work.getAttributes();

    }

    /**
     * Returns the name of this work.
     * @return The name of this work.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of this work.
     * @param name The name of this work.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Returns the unique identifier for this work.
     * @return The unique identifier for this work.
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Sets the unique identifier for this work.
     * @param identifier The unique identifier for this work.
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
    
    /**
     * Returns the unique identifier for this work.
     * @return The unique identifier for this work.
     */
    public String getParentIdentifier() {
        return parentIdentifier;
    }

    /**
     * Sets the parent work group identifier for this work.
     * @param parentIdentifier The parent work group identifier 
     *                         for this work.
     */
    public void setParentIdentifier(String parentIdentifier) {
        this.parentIdentifier = parentIdentifier;
    }

    /**
     * Returns the parameter map for this work.
     * @return The parameter map for this work.
     */
    public Map<String, String> getParameters() {
        return parameters;
    }

    /**
     * Sets the parameter map for this work.
     * @param parameters The parameter map for this work.
     */
    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    /**
     * Returns the protocol for this work.
     * @return The protocol for this work.
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * Sets the protocol for this work.
     * @param protocol protocol for this work.
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    /**
     * Returns the number of currently active works using this
     * work.
     *
     * @return
     *     The number of currently active usages of this work.
     */
    public int getActiveWorks() {
        return activeWorks;
    }

    /**
     * Set the number of currently active works using this work.
     *
     * @param activeWorks
     *     The number of currently active usages of this work.
     */
    public void setActiveUsers(int activeWorks) {
        this.activeWorks = activeWorks;
    }

    /**
     * Returns a map of all attributes associated with this work. Each
     * entry key is the attribute identifier, while each value is the attribute
     * value itself.
     *
     * @return
     *     The attribute map for this work.
     */
    public Map<String, String> getAttributes() {
        return attributes;
    }

    /**
     * Sets the map of all attributes associated with this work. Each
     * entry key is the attribute identifier, while each value is the attribute
     * value itself.
     *
     * @param attributes
     *     The attribute map for this work.
     */
    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    /**
     * Returns a collection of all associated sharing profiles, or null if
     * sharing profiles have not been queried.
     *
     * @return
     *     A collection of all associated sharing profiles, or null if sharing
     *     profiles have not been queried.
     */
    public Collection<APISharingProfile> getSharingProfiles() {
        return sharingProfiles;
    }

    /**
     * Sets the collection of all associated sharing profiles to the given
     * collection, which may be null if sharing profiles have not been queried.
     *
     * @param sharingProfiles
     *     The collection containing all sharing profiles associated with this
     *     work, or null if sharing profiles have not been queried.
     */
    public void setSharingProfiles(Collection<APISharingProfile> sharingProfiles) {
        this.sharingProfiles = sharingProfiles;
    }

    /**
     * Returns the date and time that this work was last used, or null if
     * this work has never been used or this information is unavailable.
     *
     * @return
     *     The date and time that this work was last used, or null if this
     *     work has never been used or this information is unavailable.
     */
    public Date getLastActive() {
        return lastActive;
    }

    /**
     * Sets the date and time that this work was last used.
     *
     * @param lastActive
     *     The date and time that this work was last used, or null if this
     *     work has never been used or this information is unavailable.
     */
    public void setLastActive(Date lastActive) {
        this.lastActive = lastActive;
    }

}
