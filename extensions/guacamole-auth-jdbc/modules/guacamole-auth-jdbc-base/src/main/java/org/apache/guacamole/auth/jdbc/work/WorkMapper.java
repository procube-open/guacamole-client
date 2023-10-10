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

import org.apache.guacamole.auth.jdbc.base.ModeledDirectoryObjectMapper;
import org.apache.ibatis.annotations.Param;

public interface WorkMapper extends ModeledDirectoryObjectMapper<WorkModel> {

    /**
     * Deletes any Periods currently associated with the given work
     * in the database.
     * 
     * @param object
     *     The work whose periods shold be deleted.
     * 
     * @return
     *     The number of rows deleted.
     */
    int deletePeriods(@Param("object") WorkModel object);

    /**
     * Inserts all Periods associated with the given work.
     * 
     * @param object
     *     The work whose periods should be inserted.
     * 
     * @return
     *     The number of rows inserted.
     */
    int insertPeriods(@Param("object") WorkModel object);

    /**
     * Deletes any connections currently associated with the given work
     * in the database.
     * 
     * @param object
     *     The work whose connections should be deleted.
     * 
     * @return
     *     The number of rows deleted.
     */
    int deleteConnections(@Param("object") WorkModel object);

    /**
     * Inserts all connections associated with the given work.
     * 
     * @param object
     *     The work whose connections should be inserted.
     * 
     * @return
     *     The number of rows inserted.
     */
    int insertConnections(@Param("object") WorkModel object);

}
