<!-- This section applied from common template, do not edit in language specific repository KNOWN_ISSUES file -->
# Limitations, Restrictions, and Known Issues

## All Cloudant SDKs

### Path elements containing the `+` character

Path elements containing the `+` character in the SDKs are not interoperable with Apache CouchDB and Cloudant.
* This is because standard URL encoding libraries following the [RFC3986 URI specification](https://tools.ietf.org/html/rfc3986#section-3.3) do not encode this character in path elements.
* Apache CouchDB violates the specification by treating the `+` in path elements as a space character (see https://github.com/apache/couchdb/issues/2235).
* Path elements include database names, all document names, and index and view names.
* It is possible to workaround for document names with a `+` in the ID (e.g. `docidwith+char`) by using:
    * For reading: use the `post` all docs operation and the `key` or `keys` parameter with a value of the document ID including the `+`.
    * For writing: use the `post` document operation or `post` bulk docs operation with the value of the document ID including the `+`.
* There is no pre-encoding workaround because the result is a double encoding e.g. using `%2b` in the path element ends up being double encoded as `%252b`.

### Views

#### Objects as keys

Using JSON objects as keys (e.g. `start_key`, `end_key`, `key`, `keys`)
can cause inconsistent results because the ordering of the members of the JSON
object after serialization is not guaranteed.

### Search

#### Cannot use `drilldown` parameters

Drilldown parameters cannot be used for search queries with server versions:
* CouchDB versions < 3.2.0
* Cloudant (Classic) <= 8158

### Changes

#### Terminated connections

When using the `post` changes operation the connection may intermittently terminate with an early `EOF` when using
server versions:
* CouchDB versions < 3.2.0
* Cloudant (Classic) <= 8169

### Documents

#### Attachments

The `atts_since` parameter is not supported when retrieving a document.
The workaround is to call `POST /{db}/_bulk_get` using the `atts_since` field under the `docs` request body. See the [alternative example request for `atts_since` using the `/_bulk_get` endpoint](https://cloud.ibm.com/apidocs/cloudant#postbulkget) in our API Docs.
Example JSON request body:
```json
{
  "docs": [{"id": "order00058", "atts_since": "1-99b02e08da151943c2dcb40090160bb8"}]
}
```

#### Open revisions

The `open_revs` parameter is not supported when retrieving a document.
If you want to retrieve documents with all leaf revisions (`open_revs=all`), the workaround is to call `POST /{db}/_bulk_get` using the `id` field within the `docs` array request body.
See the [alternative example request for `open_revs=all` using the `/_bulk_get` endpoint](https://cloud.ibm.com/apidocs/cloudant#postbulkget) in our API Docs.
Example JSON request body:
```json
{
  "docs": [{"id": "order00067"}]
}
```

If you want to retrieve documents of specified leaf revisions (e.g. `open_revs=["3-917fa2381192822767f010b95b45325b", "4-a5be949eeb7296747cc271766e9a498b"]`), the workaround is to call `POST /{db}/_bulk_get` using the same `id` value for each unique `rev` value within of the `docs` array request body.
See the [default example request using the `/_bulk_get` endpoint](https://cloud.ibm.com/apidocs/cloudant#postbulkget) in our API Docs.
Example JSON request body:
```json
{
  "docs": [
    {
      "id": "order00067",
      "rev": "3-917fa2381192822767f010b95b45325b"
    },
    {
      "id": "order00067",
      "rev": "4-a5be949eeb7296747cc271766e9a498b"
    }
  ]
}
```

### Monitoring, Authorization, and CORS

The server (Cloudant (Classic) <= 8169) incorrectly processes gzip compressed request bodies for the following endpoints:
| Endpoint                              | HTTP operation |
|---------------------------------------|----------------|
|`/_api/v2/user/activity_tracker/events`|`POST`          |
|`/_api/v2/user/capacity/throughput`    |`PUT`           |
|`/_api/v2/api_keys`                    |`POST`          |
|`/_api/v2/db/{db}/_security`           |`PUT`           |
|`/_api/v2/user/config/cors`            |`PUT`           |

The workaround is to [disable request body compression](#disabling-request-body-compression).

### Replication

The server (Cloudant (Classic) <= 8169) incorrectly processes gzip compressed request bodies for `_replicate` endpoint.
The workaround is to [disable request body compression](#disabling-request-body-compression).

The `basic` property of `ReplicationDatabaseAuth` is not available in CouchDB < 3.2.0 and Cloudant <= 8914. For those versions use the `headers` property to add a header with a key of `Authorization` and a value of `Basic <base64 encoded credentials>`.

### Cloudant on Transaction Engine

Whilst most SDK methods will work with _Cloudant on Transaction Engine_ there are some limitations.
It should be noted that not all existing API options are applicable to _Cloudant on Transaction Engine_
and new API options added in _Cloudant on Transaction Engine_ are not yet available in the SDKs. Please
consult the Cloudant documentation for further information.

### Compression

* Manually setting an `Accept-Encoding` header on requests will disable the transparent gzip decompression of response bodies from the server.
* Manually setting a `Content-Encoding` header on requests will disable the transparent gzip compression of request bodies to the server.

<!-- End common section -->

<!-- Template substitution for language specific content -->
<!-- ## SPLIT MARKER ## -->
## Cloudant SDK for Java

### Authentication
#### Reflective serializer and modularization
* When using IAM authentication on Java 9+ a warning will be emitted:
    ```
    WARNING: An illegal reflective access operation has occurred
    WARNING: Illegal reflective access by com.google.gson.internal.reflect.UnsafeReflectionAccessor (.../gson/2.8.5/f645ed69d595b24d4cf8b3fbb64cc505bede8829/gson-2.8.5.jar) to field java.lang.Throwable.detailMessage
    ```

  The warning is emitted after processing the following line in `com.ibm.cloud.sdk.core.util`:
  ```java
  return GsonSingleton.getGsonWithoutPrettyPrinting().fromJson(reader, valueType);
  ```

### Search
#### Analyzer definitions should be in object format

* In order to be able to deserialize a design document with a search
  index analyzer into the model object the analyzer must be stored in the design
  document described in object format, not string format e.g.
  ```json
  {
    "analyzer": {
      "name": "keyword"
    }
  }
  ```
  not
  ```json
  {
    "analyzer": "keyword"
  }
  ```
  Note that analyzers created using the Java object models will use the object
  format. As such the issue will only manifest when trying to read a design document created from another source with this exception:
  ```
  Expected BEGIN_OBJECT but was STRING
  ```

### Changes
#### Cannot automatically use the server default heartbeat interval

* It is not possible to specify `heartbeat=true` to use the server side default heartbeat interval. The workaround is to specify a numerical heartbeat interval.

### Geospatial queries
#### Legacy format responses

* When using `legacy` format responses the document ID is not available in the response model in the expected place `GeoJsonFeature#getId()`.
    * The workaround is to use `GeoJsonFeature#getProperties().get('id')`.

#### Coordinates of GeoJSON geometry objects

* Casting is required to unpack the `coordinates` of a `GeoJsonGeometry`.

#### GeoJSON feature properties

* The `properties` field of a GeoJSON feature object is accessed by using `GeoJsonFeature#getXProperties()` not `GeoJsonFeature#getProperties()` which is the set of additional properties on the GeoJSON feature object (i.e. peers of the `properties` field).

#### Error if GeoJSON feature contains no properties

* It is not currently possible to deserialize a GeoJSON feature that has no properties into the model classes. (`Expected BEGIN_OBJECT but was BEGIN_ARRAY`)
    * The workaround is to request the query as a stream `Cloudant#getGeoAsStream` and use custom deserialization.

### Session authentication

Session authentication does not work with compressed request bodies when the server is CouchDB <= 3.1.1 or Cloudant <= 8192. [Disabling gzip compression](#disabling-request-body-compression) for requests is required when using session authentication with these server versions.


### Disabling request body compression

```java
import com.ibm.cloud.cloudant.v1.Cloudant;

Cloudant client = Cloudant.newInstance("YOUR_SERVICE_NAME");
client.enableGzipCompression(false);
...
```
