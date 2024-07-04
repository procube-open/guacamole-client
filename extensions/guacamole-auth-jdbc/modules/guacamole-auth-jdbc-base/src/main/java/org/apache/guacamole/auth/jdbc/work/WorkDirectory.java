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
import java.util.Collections;
import java.util.Set;

import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.auth.jdbc.base.JDBCDirectory;
import org.apache.guacamole.net.auth.Work;

import com.google.inject.Inject;

public class WorkDirectory extends JDBCDirectory<Work> {

    /**
     * Service for managing work objects.
     */
    @Inject
    private WorkService workService;

    @Override
    public Work get(String identifier) throws GuacamoleException {
        return workService.retrieveObject(getCurrentUser(), identifier);
    }

    @Override
    public Collection<Work> getAll(Collection<String> identifiers) throws GuacamoleException {
        Collection<ModeledWork> objects = workService.retrieveObjects(getCurrentUser(), identifiers);
        return Collections.<Work>unmodifiableCollection(objects);
    }

    @Override
    public Set<String> getIdentifiers() throws GuacamoleException {
        return workService.getIdentifiers(getCurrentUser());
    }

    @Override
    public void add(Work object) throws GuacamoleException {
        workService.createObject(getCurrentUser(), object);
    }

    @Override
    public void update(Work object) throws GuacamoleException {
        ModeledWork work = (ModeledWork) object;
        workService.updateObject(getCurrentUser(), work);
    }

    @Override
    public void remove(String identifier) throws GuacamoleException {
        workService.deleteObject(getCurrentUser(), identifier);
    }
    
}
