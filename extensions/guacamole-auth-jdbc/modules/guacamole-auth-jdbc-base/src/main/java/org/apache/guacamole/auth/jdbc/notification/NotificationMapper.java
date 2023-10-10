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

package org.apache.guacamole.auth.jdbc.notification;

import java.util.Collection;
import java.util.Set;

import org.apache.guacamole.auth.jdbc.base.ModeledDirectoryObjectMapper;
import org.apache.ibatis.annotations.Param;

public interface NotificationMapper extends ModeledDirectoryObjectMapper<NotificationModel> {

    /**
     * Selects the identifiers of all objects, regardless of whether they
     * are readable by any particular user. This should only be called on
     * behalf of a system administrator. If identifiers are needed by a non-
     * administrative user who must have explicit read rights, use
     * selectReadableIdentifiers() instead.
     *
     * @return
     *     A Set containing all identifiers of all objects.
     */
    Set<String> selectIdentifiers();
    
    /**
     * Selects all objects which have the given identifiers. If an identifier
     * has no corresponding object, it will be ignored. This should only be
     * called on behalf of a system administrator. If objects are needed by a
     * non-administrative user who must have explicit read rights, use
     * selectReadable() instead.
     *
     * @param identifiers
     *     The identifiers of the objects to return.
     *
     * @return 
     *     A Collection of all objects having the given identifiers.
     */
    Collection<NotificationModel> select(@Param("identifiers") Collection<String> identifiers);

    /**
     * Inserts the given object into the database. If the object already
     * exists, this will result in an error.
     *
     * @param object
     *     The object to insert.
     *
     * @return
     *     The number of rows inserted.
     */
    int insert(@Param("object") NotificationModel object);

    /**
     * Deletes the given object into the database. If the object does not 
     * exist, this operation has no effect.
     *
     * @param identifier
     *     The identifier of the object to delete.
     *
     * @return
     *     The number of rows deleted.
     */
    int delete(@Param("identifier") String identifier);

    /**
     * Updates the given existing object in the database. If the object does 
     * not actually exist, this operation has no effect.
     *
     * @param object
     *     The object to update.
     *
     * @return
     *     The number of rows updated.
     */
    int update(@Param("object") NotificationModel object);

}
