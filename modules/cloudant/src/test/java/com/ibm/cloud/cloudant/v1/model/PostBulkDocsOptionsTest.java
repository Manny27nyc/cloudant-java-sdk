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

import com.ibm.cloud.cloudant.v1.model.Attachment;
import com.ibm.cloud.cloudant.v1.model.BulkDocs;
import com.ibm.cloud.cloudant.v1.model.Document;
import com.ibm.cloud.cloudant.v1.model.DocumentRevisionStatus;
import com.ibm.cloud.cloudant.v1.model.PostBulkDocsOptions;
import com.ibm.cloud.cloudant.v1.model.Revisions;
import com.ibm.cloud.cloudant.v1.utils.TestUtilities;
import com.ibm.cloud.sdk.core.service.model.FileWithMetadata;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Unit test class for the PostBulkDocsOptions model.
 */
public class PostBulkDocsOptionsTest {
  final HashMap<String, InputStream> mockStreamMap = TestUtilities.createMockStreamMap();
  final List<FileWithMetadata> mockListFileWithMetadata = TestUtilities.creatMockListFileWithMetadata();

  @Test
  public void testPostBulkDocsOptions() throws Throwable {
    Attachment attachmentModel = new Attachment.Builder()
      .contentType("testString")
      .data(TestUtilities.createMockByteArray("This is a mock byte array value."))
      .digest("testString")
      .encodedLength(Long.valueOf("0"))
      .encoding("testString")
      .follows(true)
      .length(Long.valueOf("0"))
      .revpos(Long.valueOf("1"))
      .stub(true)
      .build();
    assertEquals(attachmentModel.contentType(), "testString");
    assertEquals(attachmentModel.data(), TestUtilities.createMockByteArray("This is a mock byte array value."));
    assertEquals(attachmentModel.digest(), "testString");
    assertEquals(attachmentModel.encodedLength(), Long.valueOf("0"));
    assertEquals(attachmentModel.encoding(), "testString");
    assertEquals(attachmentModel.follows(), Boolean.valueOf(true));
    assertEquals(attachmentModel.length(), Long.valueOf("0"));
    assertEquals(attachmentModel.revpos(), Long.valueOf("1"));
    assertEquals(attachmentModel.stub(), Boolean.valueOf(true));

    Revisions revisionsModel = new Revisions.Builder()
      .ids(new ArrayList<String>(Arrays.asList("testString")))
      .start(Long.valueOf("1"))
      .build();
    assertEquals(revisionsModel.ids(), new ArrayList<String>(Arrays.asList("testString")));
    assertEquals(revisionsModel.start(), Long.valueOf("1"));

    DocumentRevisionStatus documentRevisionStatusModel = new DocumentRevisionStatus.Builder()
      .rev("testString")
      .status("available")
      .build();
    assertEquals(documentRevisionStatusModel.rev(), "testString");
    assertEquals(documentRevisionStatusModel.status(), "available");

    Document documentModel = new Document.Builder()
      .attachments(new HashMap<String, Attachment>() { { put("foo", attachmentModel); } })
      .conflicts(new ArrayList<String>(Arrays.asList("testString")))
      .deleted(true)
      .deletedConflicts(new ArrayList<String>(Arrays.asList("testString")))
      .id("testString")
      .localSeq("testString")
      .rev("testString")
      .revisions(revisionsModel)
      .revsInfo(new ArrayList<DocumentRevisionStatus>(Arrays.asList(documentRevisionStatusModel)))
      .add("foo", "testString")
      .build();
    assertEquals(documentModel.getAttachments(), new HashMap<String, Attachment>() { { put("foo", attachmentModel); } });
    assertEquals(documentModel.getConflicts(), new ArrayList<String>(Arrays.asList("testString")));
    assertEquals(documentModel.isDeleted(), Boolean.valueOf(true));
    assertEquals(documentModel.getDeletedConflicts(), new ArrayList<String>(Arrays.asList("testString")));
    assertEquals(documentModel.getId(), "testString");
    assertEquals(documentModel.getLocalSeq(), "testString");
    assertEquals(documentModel.getRev(), "testString");
    assertEquals(documentModel.getRevisions(), revisionsModel);
    assertEquals(documentModel.getRevsInfo(), new ArrayList<DocumentRevisionStatus>(Arrays.asList(documentRevisionStatusModel)));
    assertEquals(documentModel.get("foo"), "testString");

    BulkDocs bulkDocsModel = new BulkDocs.Builder()
      .docs(new ArrayList<Document>(Arrays.asList(documentModel)))
      .newEdits(true)
      .build();
    assertEquals(bulkDocsModel.docs(), new ArrayList<Document>(Arrays.asList(documentModel)));
    assertEquals(bulkDocsModel.newEdits(), Boolean.valueOf(true));

    PostBulkDocsOptions postBulkDocsOptionsModel = new PostBulkDocsOptions.Builder()
      .db("testString")
      .bulkDocs(bulkDocsModel)
      .body(TestUtilities.createMockStream("This is a mock file."))
      .build();
    assertEquals(postBulkDocsOptionsModel.db(), "testString");
    assertEquals(postBulkDocsOptionsModel.bulkDocs(), bulkDocsModel);
    assertEquals(IOUtils.toString(postBulkDocsOptionsModel.body()), IOUtils.toString(TestUtilities.createMockStream("This is a mock file.")));
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testPostBulkDocsOptionsError() throws Throwable {
    new PostBulkDocsOptions.Builder().build();
  }

}
