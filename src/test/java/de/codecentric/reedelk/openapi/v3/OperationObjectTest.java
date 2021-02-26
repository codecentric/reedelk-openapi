package de.codecentric.reedelk.openapi.v3;

import de.codecentric.reedelk.openapi.Fixture;
import de.codecentric.reedelk.openapi.v3.model.*;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

class OperationObjectTest extends AbstractOpenApiSerializableTest {

    private OperationObject operation;

    @BeforeEach
    void setUp() {
        // Responses
        ResponseObject response1 = new ResponseObject();
        response1.setDescription("Successful response");

        ResponseObject response2 = new ResponseObject();
        response2.setDescription("Error response");

        Map<String, ResponseObject> responseObjectMap = new HashMap<>();
        responseObjectMap.put("200", response1);
        responseObjectMap.put("500", response2);

        // Parameters
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

        // Request Body
        RequestBodyObject requestBody = new RequestBodyObject();
        requestBody.setDescription("My request body");
        requestBody.setRequired(true);

        // Security
        List<Map<String,SecurityRequirementObject>> securityRequirement = new ArrayList<>();
        SecurityRequirementObject securityRequirementObject = new SecurityRequirementObject();
        securityRequirementObject.setScopes(Arrays.asList("write:pets", "read:pets"));
        Map<String, SecurityRequirementObject> securityRequirementObjectMap = new HashMap<>();
        securityRequirementObjectMap.put("petstore_auth", securityRequirementObject);
        securityRequirement.add(securityRequirementObjectMap);

        operation = new OperationObject();
        operation.setTags(Arrays.asList("tag1", "tag2"));
        operation.setSummary("My summary");
        operation.setOperationId("myOperationId");
        operation.setDescription("My operation description");
        operation.setParameters(Arrays.asList(parameter1, parameter2));
        operation.setDeprecated(true);
        operation.setResponses(responseObjectMap);
        operation.setRequestBody(requestBody);
        operation.setSecurity(securityRequirement);
    }

    @Test
    void shouldCorrectlySerializeOperationWithAllProperties() {
        // Given
        OperationObject theOperation = operation;

        // Expect
        assertSerializeJSON(theOperation, Fixture.OperationObject.WithAllProperties);
    }

    @Test
    void shouldCorrectlyDeserializeOperationWithAllProperties() {
        // Given
        OperationObject theOperation = operation;

        // Expect
        assertDeserializeJSON(theOperation, Fixture.OperationObject.WithAllProperties);
    }

    @Test
    void shouldCorrectlySerializeOperationWithDefault() {
        // Given
        OperationObject operation = new OperationObject();

        // Expect
        assertSerializeJSON(operation, Fixture.OperationObject.WithDefault);
    }
}
