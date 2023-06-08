--
-- Licensed to the Apache Software Foundation (ASF) under one
-- or more contributor license agreements.  See the NOTICE file
-- distributed with this work for additional information
-- regarding copyright ownership.  The ASF licenses this file
-- to you under the Apache License, Version 2.0 (the
-- "License"); you may not use this file except in compliance
-- with the License.  You may obtain a copy of the License at
--
--   http://www.apache.org/licenses/LICENSE-2.0
--
-- Unless required by applicable law or agreed to in writing,
-- software distributed under the License is distributed on an
-- "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
-- KIND, either express or implied.  See the License for the
-- specific language governing permissions and limitations
-- under the License.
--

--
-- Work status.
--
CREATE TYPE guacamole_work_status AS ENUM(
    'USER',
    'USER_GROUP'
);

--
-- Table of work.
--
CREATE TABLE guacamole_work {
  work_id         serial                  NOT NULL,
  display_id      varchar(64)             NOT NULL,
  name            varchar(128)            NOT NULL,
  start_time      datetime                NOT NULL,
  end_time        datetime                NOT NULL,
  status          guacamole_work_status   NOT NULL,
  approved_user_id integer                NOT NULL,
  approved_date   datetime                NOT NULL,

  PRIMARY KEY (work_id),

  CONSTRAINT guacamole_work_approved_user
    FOREIGN KEY (approved_user_id)
    REFERENCES guacamole_user (user_id)
    ON DELETE CASCADE

}

--
-- Table of work member.
--
CREATE TABLE guacamole_work_member {
  
  work_id          integer       NOT NULL,
  member_user_id   integer       NOT NULL,

  PRIMARY KEY (work_id, member_user_id),

  -- Parent must be a user group
  CONSTRAINT guacamole_work_member_parent
    FOREIGN KEY (work_id)
    REFERENCES guacamole_work (work_id) ON DELETE CASCADE,

  -- Member may be either a user or a user group (any entity)
  CONSTRAINT guacamole_work_member_user
    FOREIGN KEY (member_user_id)
    REFERENCES guacamole_user (user_id) ON DELETE CASCADE

}

--
-- Table of work connection.
--
CREATE TABLE guacamole_work_connection {

  work_id          integer       NOT NULL,
  connection_id    integer       NOT NULL,

  PRIMARY KEY (work_id, connection_id),

  -- Parent must be a user group
  CONSTRAINT guacamole_work_member_parent
    FOREIGN KEY (work_id)
    REFERENCES guacamole_work (work_id) ON DELETE CASCADE,

  -- Member may be either a user or a user group (any entity)
  CONSTRAINT guacamole_work_connection_con
    FOREIGN KEY (connection_id)
    REFERENCES guacamole_connection (connection_id) ON DELETE CASCADE

}
