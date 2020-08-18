package com.reedelk.openapi.v3;

import com.reedelk.openapi.Fixture;
import com.reedelk.openapi.v3.model.*;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class OperationObjectTest extends AbstractOpenApiSerializableTest {

    @Test
    void shouldCorrectlySerializeOperationWithAllProperties() {
        // Given
        ResponseObject response1 = new ResponseObject();
        response1.setDescription("Successful response");

        ResponseObject response2 = new ResponseObject();
        response2.setDescription("Error response");

        Map<String, ResponseObject> responseObjectMap = new HashMap<>();
        responseObjectMap.put("200", response1);
        responseObjectMap.put("500", response2);

        ParameterObject parameter1 = new ParameterObject();
        parameter1.setName("param1");
        parameter1.setRequired(false);
        parameter1.setIn(ParameterLocation.query);
        parameter1.setSchema(new Schema(new JSONObject("{\"type\": \"string\"}").toMap()));

        ParameterObject parameter2 = new ParameterObject();
        parameter2.setName("param2");
        parameter2.setRequired(true);
        parameter2.setIn(ParameterLocation.path);
        parameter2.setSchema(new Schema(new JSONObject("{\"type\": \"string\"}").toMap()));

        RequestBodyObject requestBody = new RequestBodyObject();
        requestBody.setDescription("My request body");
        requestBody.setRequired(true);

        OperationObject operation = new OperationObject();
        operation.setTags(Arrays.asList("tag1", "tag2"));
        operation.setSummary("My summary");
        operation.setOperationId("myOperationId");
        operation.setDescription("My operation description");
        operation.setParameters(Arrays.asList(parameter1, parameter2));
        operation.setDeprecated(true);
        operation.setResponses(responseObjectMap);
        operation.setRequestBody(requestBody);

        // Expect
        assertSerializeJSON(operation, Fixture.OperationObject.WithAllProperties);
    }

    @Test
    void shouldCorrectlySerializeOperationWithDefault() {
        // Given
        OperationObject operation = new OperationObject();

        // Expect
        assertSerializeJSON(operation, Fixture.OperationObject.WithDefault);
    }
}
