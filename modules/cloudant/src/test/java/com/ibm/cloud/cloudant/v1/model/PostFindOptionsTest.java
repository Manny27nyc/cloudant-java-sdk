/*
 * (C) Copyright IBM Corp. 2021.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.ibm.cloud.cloudant.v1.model;

import com.ibm.cloud.cloudant.v1.model.PostFindOptions;
import com.ibm.cloud.cloudant.v1.utils.TestUtilities;
import com.ibm.cloud.sdk.core.service.model.FileWithMetadata;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Unit test class for the PostFindOptions model.
 */
public class PostFindOptionsTest {
  final HashMap<String, InputStream> mockStreamMap = TestUtilities.createMockStreamMap();
  final List<FileWithMetadata> mockListFileWithMetadata = TestUtilities.creatMockListFileWithMetadata();

  @Test
  public void testPostFindOptions() throws Throwable {
    PostFindOptions postFindOptionsModel = new PostFindOptions.Builder()
      .db("testString")
      .selector(new HashMap<String, Object>() { { put("foo", "testString"); } })
      .bookmark("testString")
      .conflicts(true)
      .executionStats(true)
      .fields(new ArrayList<String>(Arrays.asList("testString")))
      .limit(Long.valueOf("0"))
      .skip(Long.valueOf("0"))
      .sort(new ArrayList<Map<String, String>>(Arrays.asList(new HashMap<String, String>() { { put("foo", "asc"); } })))
      .stable(true)
      .update("false")
      .useIndex(new ArrayList<String>(Arrays.asList("testString")))
      .r(Long.valueOf("1"))
      .build();
    assertEquals(postFindOptionsModel.db(), "testString");
    assertEquals(postFindOptionsModel.selector(), new HashMap<String, Object>() { { put("foo", "testString"); } });
    assertEquals(postFindOptionsModel.bookmark(), "testString");
    assertEquals(postFindOptionsModel.conflicts(), Boolean.valueOf(true));
    assertEquals(postFindOptionsModel.executionStats(), Boolean.valueOf(true));
    assertEquals(postFindOptionsModel.fields(), new ArrayList<String>(Arrays.asList("testString")));
    assertEquals(postFindOptionsModel.limit(), Long.valueOf("0"));
    assertEquals(postFindOptionsModel.skip(), Long.valueOf("0"));
    assertEquals(postFindOptionsModel.sort(), new ArrayList<Map<String, String>>(Arrays.asList(new HashMap<String, String>() { { put("foo", "asc"); } })));
    assertEquals(postFindOptionsModel.stable(), Boolean.valueOf(true));
    assertEquals(postFindOptionsModel.update(), "false");
    assertEquals(postFindOptionsModel.useIndex(), new ArrayList<String>(Arrays.asList("testString")));
    assertEquals(postFindOptionsModel.r(), Long.valueOf("1"));
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testPostFindOptionsError() throws Throwable {
    new PostFindOptions.Builder().build();
  }

}
