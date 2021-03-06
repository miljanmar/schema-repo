/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.schemarepo.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Properties;

import org.junit.Test;
import org.schemarepo.config.Config;
import org.schemarepo.json.GsonJsonUtil;

/**
 * Tests {@link RESTRepositoryClient} configured to rethrow any unexpected remote exception
 * encountered while talking to the server.
 */
public class TestRESTRepositoryClientRethrowExceptions extends AbstractTestRESTRepositoryClient<RESTRepositoryClient> {

  @Override
  protected RESTRepositoryClient createClient(String repoUrl) {
    return new RESTRepositoryClient(repoUrl, new GsonJsonUtil(), false);
  }

  @Test
  public void testGetStatus() {
    assertTrue(repo.getStatus().startsWith("OK"));
  }

  @Test
  public void testGetConfig() {
    Properties properties = repo.getConfiguration(false);
    assertEquals("localhost", properties.getProperty(Config.JETTY_HOST));
    assertNull(properties.getProperty(Config.JETTY_PATH));
    properties = repo.getConfiguration(true);
    assertEquals("localhost", properties.getProperty(Config.JETTY_HOST));
    assertEquals("/schema-repo", properties.getProperty(Config.JETTY_PATH));
  }

}
