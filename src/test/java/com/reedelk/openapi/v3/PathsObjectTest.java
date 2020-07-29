package com.reedelk.openapi.v3;

import com.reedelk.openapi.Fixture;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class PathsObjectTest extends AbstractOpenApiSerializableTest {

    @Test
    void shouldCorrectlySerializePathsWithDefault() {
        // Given
        PathsObject paths = new PathsObject();

        // Expect
        assertSerializeJSON(paths, Fixture.PathsObject.WithDefaultPaths);
    }

    @Test
    void shouldCorrectlySerializePathsWithOperationObjectForPath() {
        // Given
        MediaTypeObject successResponse1 = new MediaTypeObject();
        successResponse1.setExample(new Example("{ \"name\": \"Mark\" }"));

        Map<String, MediaTypeObject> contentTypeMediaTypeMap = new HashMap<>();
        contentTypeMediaTypeMap.put("application/json", successResponse1);

        ResponseObject response200 = new ResponseObject();
        response200.setDescription("200 Response");
        response200.setContent(contentTypeMediaTypeMap);

        ResponseObject response401 = new ResponseObject();
        response401.setDescription("401 Response");

        OperationObject operationObject = new OperationObject();
        operationObject.setDescription("My response description");
        operationObject.setOperationId("myOperationId");
        operationObject.setSummary("My summary");

        Map<String, ResponseObject> statusCodeResponseMap = new HashMap<>();
        statusCodeResponseMap.put("200", response200);
        statusCodeResponseMap.put("401", response401);
        operationObject.setResponses(statusCodeResponseMap);
        operationObject.setTags(Arrays.asList("tag1", "tag2"));

        Map<RestMethod, OperationObject> methodOperation = new HashMap<>();
        methodOperation.put(RestMethod.GET, operationObject);

        PathsObject paths = new PathsObject();
        paths.getPaths().put("/mypath", methodOperation);

        // Expect
        assertSerializeJSON(paths, Fixture.PathsObject.WithOperation);
    }
}
