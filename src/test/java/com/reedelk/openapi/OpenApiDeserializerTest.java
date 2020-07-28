package com.reedelk.openapi;

import com.reedelk.openapi.v3.*;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class OpenApiDeserializerTest {

    @Test
    void shouldDeserializeFromJSON() {
        // Given
        String json = Fixture.EndToEnd.SAMPLE_JSON.string();

        // When
        OpenApiObject actualOpenApi = OpenApiDeserializer.from(json);

        // Then
        assertEquals(expectedOpenApi, actualOpenApi);
    }

    @Test
    void shouldDeserializeFromYAML() {
        // Given
        String yaml = Fixture.EndToEnd.SAMPLE_YAML.string();

        // When
        OpenApiObject actualOpenApi = OpenApiDeserializer.from(yaml);

        // Then
        assertEquals(expectedOpenApi, actualOpenApi);
    }

    private void assertEquals(OpenApiObject expected, OpenApiObject actual) {
        InfoObject expectedInfo = expected.getInfo();
        InfoObject actualInfo = actual.getInfo();
        assertThat(expectedInfo).isEqualTo(actualInfo);

        List<ServerObject> expectedServers = expected.getServers();
        List<ServerObject> actualServers = actual.getServers();
        assertThat(expectedServers).isEqualTo(actualServers);

        PathsObject expectedPaths = expected.getPaths();
        PathsObject actualPaths = actual.getPaths();
        assertThat(expectedPaths).isEqualTo(actualPaths);
    }

    private static OpenApiObject expectedOpenApi;
    static {
        // Contact Object
        ContactObject expectedContact = new ContactObject();
        expectedContact.setEmail("apiteam@swagger.io");
        // ------------------------------------------------------

        // License Object
        LicenseObject expectedLicense = new LicenseObject();
        expectedLicense.setName("Apache 2.0");
        expectedLicense.setUrl("http://www.apache.org/licenses/LICENSE-2.0.html");
        // ------------------------------------------------------

        // Info Object
        InfoObject expectedInfo = new InfoObject();
        expectedInfo.setDescription("This is a sample server Petstore server.  You can find out more about Swagger at [http://swagger.io](http://swagger.io) or on [irc.freenode.net, #swagger](http://swagger.io/irc/).  For this sample, you can use the api key `special-key` to test the authorization filters.");
        expectedInfo.setVersion("1.0.2");
        expectedInfo.setTitle("Swagger Petstore");
        expectedInfo.setTermsOfService("http://swagger.io/terms/");
        expectedInfo.setContact(expectedContact);
        expectedInfo.setLicense(expectedLicense);
        // ------------------------------------------------------

        // Server Object
        ServerObject expectedServer = new ServerObject();
        expectedServer.setUrl("https://petstore.swagger.io/v2");
        // ------------------------------------------------------

        // PUT Operation Object
        RequestBodyObject putPetRequestBody = new RequestBodyObject();
        putPetRequestBody.set$ref("#/components/requestBodies/Pet");

        ResponseObject responseObject400 = new ResponseObject();
        responseObject400.setDescription("Invalid ID supplied");

        ResponseObject responseObject404 = new ResponseObject();
        responseObject404.setDescription("Pet not found");

        ResponseObject responseObject405 = new ResponseObject();
        responseObject405.setDescription("Validation exception");

        Map<String, ResponseObject> statusResponseMap = new HashMap<>();
        statusResponseMap.put("400", responseObject400);
        statusResponseMap.put("404", responseObject404);
        statusResponseMap.put("405", responseObject405);

        OperationObject putPetOperation = new OperationObject();
        putPetOperation.setSummary("Update an existing pet");
        putPetOperation.setOperationId("updatePet");
        putPetOperation.setRequestBody(putPetRequestBody);
        putPetOperation.setResponses(statusResponseMap);
        putPetOperation.setDescription("");
        putPetOperation.setTags(Collections.singletonList("pet"));
        // ------------------------------------------------------

        // POST Operation Object
        RequestBodyObject postPetRequestBody = new RequestBodyObject();
        postPetRequestBody.set$ref("#/components/requestBodies/Pet");

        ResponseObject postResponseObject405 = new ResponseObject();
        postResponseObject405.setDescription("Invalid input");

        Map<String, ResponseObject> postStatusResponseMap = new HashMap<>();
        postStatusResponseMap.put("405", postResponseObject405);

        OperationObject postPetOperation = new OperationObject();
        postPetOperation.setSummary("Add a new pet to the store");
        postPetOperation.setOperationId("addPet");
        postPetOperation.setRequestBody(postPetRequestBody);
        postPetOperation.setResponses(postStatusResponseMap);
        postPetOperation.setDescription("");
        postPetOperation.setTags(Collections.singletonList("pet"));
        // ------------------------------------------------------

        Map<RestMethod, OperationObject> petOperationMap = new HashMap<>();
        petOperationMap.put(RestMethod.PUT, putPetOperation);
        petOperationMap.put(RestMethod.POST, postPetOperation);

        Map<String, Map<RestMethod, OperationObject>> paths = new HashMap<>();
        paths.put("/pet", petOperationMap);

        // Paths Object
        PathsObject expectedPaths = new PathsObject();
        expectedPaths.setPaths(paths);
        // ------------------------------------------------------

        expectedOpenApi = new OpenApiObject();
        expectedOpenApi.setOpenapi("3.0.0");
        expectedOpenApi.setBasePath("/");
        expectedOpenApi.setInfo(expectedInfo);
        expectedOpenApi.setServers(Collections.singletonList(expectedServer));
        expectedOpenApi.setPaths(expectedPaths);
    }
}
