/*
  Copyright 2016, Google, Inc.

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
*/

package com.example.pubsub;

import static com.google.common.truth.Truth.assertThat;

import com.google.cloud.pubsub.spi.v1.PublisherClient;
import com.google.pubsub.v1.TopicName;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Tests for quickstart sample.
 */
@RunWith(JUnit4.class)
@SuppressWarnings("checkstyle:abbreviationaswordinname")
public class QuickstartSampleIT {

  private ByteArrayOutputStream bout;
  private PrintStream out;
  private String projectId;

  private void deleteTestTopic(String projectId) throws Exception {
    try (PublisherClient publisherClient = PublisherClient.create()) {
      publisherClient.deleteTopic(TopicName.create(projectId, "my-new-topic"));
    } catch (IOException e) {
      System.err.println("Error deleting topic " + e.getMessage());
    }
  }

  @Before
  public void setUp() {
    bout = new ByteArrayOutputStream();
    out = new PrintStream(bout);
    System.setOut(out);
    projectId = System.getenv("GOOGLE_CLOUD_PROJECT");
    assertThat(projectId).isNotNull();
    try {
      deleteTestTopic(projectId);
    } catch (Exception e) {
      //empty catch block
    }
  }

  @After
  public void tearDown() throws Exception {
    System.setOut(null);
    deleteTestTopic(projectId);
  }

  @Test
  public void testQuickstart() throws Exception {
    QuickstartSample.main(projectId);
    String got = bout.toString();
    assertThat(got).contains("my-new-topic created.");
  }
}
