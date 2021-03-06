/**
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * <p>Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * <p>http://www.apache.org/licenses/LICENSE-2.0
 *
 * <p>Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.guestbook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

@RunWith(JUnit4.class)
public class GreetingTest {

  @Before
  public void setUp() {
    TestUtils.startDatastore();
  }

  @Test
  public void testSaveGreeting() throws Exception {
    Greeting greeting = new Greeting(null, "Test!");
    greeting.save();

    Guestbook guestbook = new Guestbook(null);
    List<Greeting> greetings = guestbook.getGreetings();
    assertTrue(greetings.size() == 1);
    assertEquals(greeting, greetings.get(0));
  }

  @After
  public void tearDown() {
    TestUtils.stopDatastore();
  }
}
