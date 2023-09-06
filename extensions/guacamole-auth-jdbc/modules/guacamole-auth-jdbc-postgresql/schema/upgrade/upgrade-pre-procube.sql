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

CREATE TABLE guacamole_work (

  work_id      serial       NOT NULL,
  work_idm_id  varchar(10)  NOT NULL,
  work_name    varchar(128) NOT NULL,

  PRIMARY KEY (work_id)
);

--
-- Table of work permissions. Each work permission grants a user or
-- user group specific access to a work.
--

CREATE TABLE guacamole_work_permission (

  entity_id     integer NOT NULL,
  work_id       integer NOT NULL,
  permission    guacamole_object_permission_type NOT NULL,

  PRIMARY KEY (entity_id, work_id, permission),

  CONSTRAINT guacamole_work_permission_ibfk_1
    FOREIGN KEY (work_id)
    REFERENCES guacamole_work (work_id) ON DELETE CASCADE,

  CONSTRAINT guacamole_work_permission_entity
    FOREIGN KEY (entity_id)
    REFERENCES guacamole_entity (entity_id) ON DELETE CASCADE

);

CREATE INDEX guacamole_work_permission_work_id
    ON guacamole_work_permission(work_id);

CREATE INDEX guacamole_work_permission_entity_id
    ON guacamole_work_permission(entity_id);

--
-- Table of arbitrary work attributes. Each attribute is simply a
-- name/value pair associated with a work. Arbitrary attributes are
-- defined by other extensions. Attributes defined by this extension will be
-- mapped to properly-typed columns of a specific table.
--

CREATE TABLE guacamole_work_attribute (

  work_id   integer       NOT NULL,
  attribute_name  varchar(128)  NOT NULL,
  attribute_value varchar(4096) NOT NULL,

  PRIMARY KEY (work_id, attribute_name),

  CONSTRAINT guacamole_work_attribute_ibfk_1
    FOREIGN KEY (work_id)
    REFERENCES guacamole_work (work_id) ON DELETE CASCADE

);

CREATE INDEX guacamole_work_attribute_work_id
    ON guacamole_work_attribute(work_id);

--
-- Table of work parameters. Each parameter is simply a name/value pair
-- associated with a work.
--

CREATE TABLE guacamole_work_parameter (

  work_id         integer       NOT NULL,
  parameter_name  varchar(128)  NOT NULL,
  parameter_value varchar(4096) NOT NULL,

  PRIMARY KEY (work_id, parameter_name),

  CONSTRAINT guacamole_work_parameter_ibfk_1
    FOREIGN KEY (work_id)
    REFERENCES guacamole_work (work_id) ON DELETE CASCADE

);

CREATE INDEX guacamole_work_parameter_work_id
    ON guacamole_work_parameter(work_id);
