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

package org.apache.guacamole.auth.ssl.conf;

import jakarta.naming.InvalidNameException;
import jakarta.naming.ldap.LdapName;
import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.GuacamoleServerException;
import org.apache.guacamole.properties.GuacamoleProperty;

/**
 * A GuacamoleProperty whose value is an LDAP name, such as a distinguished
 * name.
 */
public abstract class LdapNameGuacamoleProperty implements GuacamoleProperty<LdapName> {

    @Override
    public LdapName parseValue(String value) throws GuacamoleException {

        if (value == null)
            return null;

        try {
            return new LdapName(value);
        }
        catch (InvalidNameException e) {
            throw new GuacamoleServerException("Value \"" + value
                + "\" is not a valid LDAP name.", e);
        }

    }

}
