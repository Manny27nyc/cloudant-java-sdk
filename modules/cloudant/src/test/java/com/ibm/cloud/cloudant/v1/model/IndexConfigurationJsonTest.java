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

import com.ibm.cloud.cloudant.v1.model.Analyzer;
import com.ibm.cloud.cloudant.v1.model.IndexConfigurationJson;
import com.ibm.cloud.cloudant.v1.model.IndexDefinitionJson;
import com.ibm.cloud.cloudant.v1.model.IndexTextOperatorDefaultField;
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
 * Unit test class for the IndexConfigurationJson model.
 */
public class IndexConfigurationJsonTest {
  final HashMap<String, InputStream> mockStreamMap = TestUtilities.createMockStreamMap();
  final List<FileWithMetadata> mockListFileWithMetadata = TestUtilities.creatMockListFileWithMetadata();

  @Test
  public void testIndexConfigurationJson() throws Throwable {
    Analyzer analyzerModel = new Analyzer.Builder()
      .name("classic")
      .stopwords(new ArrayList<String>(Arrays.asList("testString")))
      .build();
    assertEquals(analyzerModel.name(), "classic");
    assertEquals(analyzerModel.stopwords(), new ArrayList<String>(Arrays.asList("testString")));

    IndexTextOperatorDefaultField indexTextOperatorDefaultFieldModel = new IndexTextOperatorDefaultField.Builder()
      .analyzer(analyzerModel)
      .enabled(true)
      .build();
    assertEquals(indexTextOperatorDefaultFieldModel.analyzer(), analyzerModel);
    assertEquals(indexTextOperatorDefaultFieldModel.enabled(), Boolean.valueOf(true));

    IndexDefinitionJson indexDefinitionJsonModel = new IndexDefinitionJson.Builder()
      .defaultAnalyzer(analyzerModel)
      .defaultField(indexTextOperatorDefaultFieldModel)
      .indexArrayLengths(true)
      .partialFilterSelector(new HashMap<String, Object>() { { put("foo", "testString"); } })
      .fields(new ArrayList<Map<String, String>>(Arrays.asList(new HashMap<String, String>() { { put("foo", "asc"); } })))
      .build();
    assertEquals(indexDefinitionJsonModel.defaultAnalyzer(), analyzerModel);
    assertEquals(indexDefinitionJsonModel.defaultField(), indexTextOperatorDefaultFieldModel);
    assertEquals(indexDefinitionJsonModel.indexArrayLengths(), Boolean.valueOf(true));
    assertEquals(indexDefinitionJsonModel.partialFilterSelector(), new HashMap<String, Object>() { { put("foo", "testString"); } });
    assertEquals(indexDefinitionJsonModel.fields(), new ArrayList<Map<String, String>>(Arrays.asList(new HashMap<String, String>() { { put("foo", "asc"); } })));

    IndexConfigurationJson indexConfigurationJsonModel = new IndexConfigurationJson.Builder()
      .ddoc("testString")
      .name("testString")
      .type("json")
      .index(indexDefinitionJsonModel)
      .build();
    assertEquals(indexConfigurationJsonModel.ddoc(), "testString");
    assertEquals(indexConfigurationJsonModel.name(), "testString");
    assertEquals(indexConfigurationJsonModel.type(), "json");
    assertEquals(indexConfigurationJsonModel.index(), indexDefinitionJsonModel);

    String json = TestUtilities.serialize(indexConfigurationJsonModel);

    IndexConfigurationJson indexConfigurationJsonModelNew = TestUtilities.deserialize(json, IndexConfigurationJson.class);
    assertTrue(indexConfigurationJsonModelNew instanceof IndexConfigurationJson);
    assertEquals(indexConfigurationJsonModelNew.ddoc(), "testString");
    assertEquals(indexConfigurationJsonModelNew.name(), "testString");
    assertEquals(indexConfigurationJsonModelNew.type(), "json");
    assertEquals(indexConfigurationJsonModelNew.index().toString(), indexDefinitionJsonModel.toString());
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testIndexConfigurationJsonError() throws Throwable {
    new IndexConfigurationJson.Builder().build();
  }

}
