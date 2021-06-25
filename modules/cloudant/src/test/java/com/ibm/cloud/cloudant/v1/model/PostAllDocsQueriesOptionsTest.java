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

import com.ibm.cloud.cloudant.v1.model.AllDocsQuery;
import com.ibm.cloud.cloudant.v1.model.PostAllDocsQueriesOptions;
import com.ibm.cloud.cloudant.v1.utils.TestUtilities;
import com.ibm.cloud.sdk.core.service.model.FileWithMetadata;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Unit test class for the PostAllDocsQueriesOptions model.
 */
public class PostAllDocsQueriesOptionsTest {
  final HashMap<String, InputStream> mockStreamMap = TestUtilities.createMockStreamMap();
  final List<FileWithMetadata> mockListFileWithMetadata = TestUtilities.creatMockListFileWithMetadata();

  @Test
  public void testPostAllDocsQueriesOptions() throws Throwable {
    AllDocsQuery allDocsQueryModel = new AllDocsQuery.Builder()
      .attEncodingInfo(true)
      .attachments(true)
      .conflicts(true)
      .descending(true)
      .includeDocs(true)
      .inclusiveEnd(true)
      .limit(Long.valueOf("0"))
      .skip(Long.valueOf("0"))
      .updateSeq(true)
      .endkey("testString")
      .key("testString")
      .keys(new ArrayList<String>(Arrays.asList("testString")))
      .startkey("testString")
      .build();
    assertEquals(allDocsQueryModel.attEncodingInfo(), Boolean.valueOf(true));
    assertEquals(allDocsQueryModel.attachments(), Boolean.valueOf(true));
    assertEquals(allDocsQueryModel.conflicts(), Boolean.valueOf(true));
    assertEquals(allDocsQueryModel.descending(), Boolean.valueOf(true));
    assertEquals(allDocsQueryModel.includeDocs(), Boolean.valueOf(true));
    assertEquals(allDocsQueryModel.inclusiveEnd(), Boolean.valueOf(true));
    assertEquals(allDocsQueryModel.limit(), Long.valueOf("0"));
    assertEquals(allDocsQueryModel.skip(), Long.valueOf("0"));
    assertEquals(allDocsQueryModel.updateSeq(), Boolean.valueOf(true));
    assertEquals(allDocsQueryModel.endkey(), "testString");
    assertEquals(allDocsQueryModel.key(), "testString");
    assertEquals(allDocsQueryModel.keys(), new ArrayList<String>(Arrays.asList("testString")));
    assertEquals(allDocsQueryModel.startkey(), "testString");

    PostAllDocsQueriesOptions postAllDocsQueriesOptionsModel = new PostAllDocsQueriesOptions.Builder()
      .db("testString")
      .queries(new ArrayList<AllDocsQuery>(Arrays.asList(allDocsQueryModel)))
      .build();
    assertEquals(postAllDocsQueriesOptionsModel.db(), "testString");
    assertEquals(postAllDocsQueriesOptionsModel.queries(), new ArrayList<AllDocsQuery>(Arrays.asList(allDocsQueryModel)));
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testPostAllDocsQueriesOptionsError() throws Throwable {
    new PostAllDocsQueriesOptions.Builder().build();
  }

}
