package com.reedelk.openapi;

import com.reedelk.openapi.v3.*;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class OpenApiDeserializerTest {

    @Test
    void shouldDeserializeFromJSON() {
        // Given
        String json = Fixture.EndToEnd.SAMPLE_JSON.string();

        // When
        OpenApiObject actualOpenApi = OpenApiDeserializer.from(json);

        // Then
        assertThat(expectedOpenApi).isEqualTo(actualOpenApi);
    }

    @Test
    void shouldDeserializeFromYAML() {
        // Given
        String yaml = Fixture.EndToEnd.SAMPLE_YAML.string();

        // When
        OpenApiObject actualOpenApi = OpenApiDeserializer.from(yaml);

        // Then
        assertThat(expectedOpenApi).isEqualTo(actualOpenApi);
    }

    private static OpenApiObject expectedOpenApi;
    static {
        Map<String, Map<RestMethod, OperationObject>> paths = new HashMap<>();
        paths.put("/pet", createPetOperationMap());
        paths.put("/pet/{petId}", createPetByIdOperationMap());
        paths.put("/store/inventory", createStoreInventoryOperationMap());
        paths.put("/user/login", createUserLoginOperationMap());

        // Paths Object
        PathsObject expectedPaths = new PathsObject();
        expectedPaths.setPaths(paths);
        // ------------------------------------------------------

        // Components Object
        ComponentsObject componentsObject = createComponents();

        expectedOpenApi = new OpenApiObject();
        expectedOpenApi.setOpenapi("3.0.0");
        expectedOpenApi.setInfo(createInfo());
        expectedOpenApi.setServers(Collections.singletonList(createServer()));
        expectedOpenApi.setPaths(expectedPaths);
        expectedOpenApi.setComponents(componentsObject);
    }

    private static Map<RestMethod, OperationObject> createUserLoginOperationMap() {
        // ---------------- GET Store Inventory Object ----------------
        ParameterObject usernameParameter = new ParameterObject();
        usernameParameter.setName("username");
        usernameParameter.setIn(ParameterLocation.query);
        usernameParameter.setDescription("The user name for login");
        usernameParameter.setRequired(true);
        usernameParameter.setSchema(new Schema(new JSONObject("{\n" +
                "              \"type\": \"string\",\n" +
                "            }").toMap()));

        ParameterObject passwordParameter = new ParameterObject();
        passwordParameter.setName("password");
        passwordParameter.setIn(ParameterLocation.query);
        passwordParameter.setDescription("The password for login in clear text");
        passwordParameter.setRequired(true);
        passwordParameter.setSchema(new Schema(new JSONObject("{\n" +
                "              \"type\": \"string\",\n" +
                "            }").toMap()));

        Map<String, MediaTypeObject> contentTypeMedia = new HashMap<>();
        contentTypeMedia.put("application/json", createMediaType(new JSONObject("{\n" +
                "                  \"type\": \"string\"\n" +
                "                }").toMap()));
        contentTypeMedia.put("application/xml", createMediaType(new JSONObject("{\n" +
                "                  \"type\": \"string\"\n" +
                "                }").toMap()));

        HeaderObject rateLimit = new HeaderObject();
        rateLimit.setDescription("calls per hour allowed by the user");
        rateLimit.setSchema(new Schema(new JSONObject("{\n" +
                "                  \"type\": \"integer\",\n" +
                "                  \"format\": \"int32\"\n" +
                "                }").toMap()));

        HeaderObject expiresAfter = new HeaderObject();
        expiresAfter.setDescription("date in UTC when token expires");
        expiresAfter.setSchema(new Schema(new JSONObject("{\n" +
                "                  \"type\": \"string\",\n" +
                "                  \"format\": \"date-time\"\n" +
                "                }").toMap()));

        Map<String, HeaderObject> headersMap = new HashMap<>();
        headersMap.put("X-Rate-Limit", rateLimit);
        headersMap.put("X-Expires-After", expiresAfter);

        ResponseObject response200 = createResponseObject("successful operation");
        response200.setContent(contentTypeMedia);
        response200.setHeaders(headersMap);

        Map<String, ResponseObject> getStatusCodeResponseMap = new HashMap<>();
        getStatusCodeResponseMap.put("200", response200);
        getStatusCodeResponseMap.put("400", createResponseObject("Invalid username/password supplied"));

        OperationObject loginOperation = new OperationObject();
        loginOperation.setTags(Collections.singletonList("user"));
        loginOperation.setSummary("Logs user into the system");
        loginOperation.setOperationId("loginUser");
        loginOperation.setResponses(getStatusCodeResponseMap);
        loginOperation.setDescription("");
        loginOperation.setParameters(asList(usernameParameter, passwordParameter));
        // ------------------------------------------------------

        Map<RestMethod, OperationObject> userLoginOperationMap = new HashMap<>();
        userLoginOperationMap.put(RestMethod.GET, loginOperation);
        return userLoginOperationMap;
    }

    private static Map<RestMethod, OperationObject> createStoreInventoryOperationMap() {
        // ---------------- GET Store Inventory Object ----------------
        Map<String, MediaTypeObject> contentTypeMedia = new HashMap<>();
        contentTypeMedia.put("application/json", createMediaType(new JSONObject("{\n" +
                "                  \"type\": \"object\",\n" +
                "                  \"additionalProperties\": {\n" +
                "                    \"type\": \"integer\",\n" +
                "                    \"format\": \"int32\"\n" +
                "                  }\n" +
                "                }").toMap()));

        ResponseObject response200 = createResponseObject("successful operation");
        response200.setContent(contentTypeMedia);

        Map<String, ResponseObject> getStatusCodeResponseMap = new HashMap<>();
        getStatusCodeResponseMap.put("200", response200);

        OperationObject getStoreInventoryOperation = new OperationObject();
        getStoreInventoryOperation.setTags(Collections.singletonList("store"));
        getStoreInventoryOperation.setSummary("Returns pet inventories by status");
        getStoreInventoryOperation.setOperationId("getInventory");
        getStoreInventoryOperation.setResponses(getStatusCodeResponseMap);
        getStoreInventoryOperation.setDescription("Returns a map of status codes to quantities");
        // ------------------------------------------------------

        Map<RestMethod, OperationObject> storeInventoryOperationMap = new HashMap<>();
        storeInventoryOperationMap.put(RestMethod.GET, getStoreInventoryOperation);
        return storeInventoryOperationMap;
    }

    private static Map<RestMethod, OperationObject> createPetByIdOperationMap() {
        // ---------------- GET Operation Object ----------------
        ParameterObject getParameters = new ParameterObject();
        getParameters.setName("petId");
        getParameters.setIn(ParameterLocation.path);
        getParameters.setDescription("ID of pet to return");
        getParameters.setRequired(true);
        getParameters.setSchema(new Schema(new JSONObject("{\n" +
                "              \"type\": \"integer\",\n" +
                "              \"format\": \"int64\"\n" +
                "            }").toMap()));
        Map<String, MediaTypeObject> contentTypeMedia = new HashMap<>();
        contentTypeMedia.put("application/xml", createMediaType("#/components/schemas/Pet"));
        contentTypeMedia.put("application/json", createMediaType("#/components/schemas/Pet"));

        ResponseObject response200 = createResponseObject("successful operation");
        response200.setContent(contentTypeMedia);

        Map<String, ResponseObject> getStatusCodeResponseMap = new HashMap<>();
        getStatusCodeResponseMap.put("400", createResponseObject("Invalid ID supplied"));
        getStatusCodeResponseMap.put("404", createResponseObject("Pet not found"));
        getStatusCodeResponseMap.put("200", response200);

        OperationObject getPetOperation = new OperationObject();
        getPetOperation.setTags(Collections.singletonList("pet"));
        getPetOperation.setSummary("Find pet by ID");
        getPetOperation.setOperationId("getPetById");
        getPetOperation.setResponses(getStatusCodeResponseMap);
        getPetOperation.setDescription("Returns a single pet");
        getPetOperation.setParameters(Collections.singletonList(getParameters));
        // ------------------------------------------------------

        // ---------------- POST Operation Object ----------------
        ParameterObject postParameters = new ParameterObject();
        postParameters.setName("petId");
        postParameters.setIn(ParameterLocation.path);
        postParameters.setDescription("ID of pet that needs to be updated");
        postParameters.setRequired(true);
        postParameters.setSchema(new Schema(new JSONObject("{\n" +
                "              \"type\": \"integer\",\n" +
                "              \"format\": \"int64\"\n" +
                "            }").toMap()));

        Map<String, ResponseObject> postStatusCodeResponseMap = new HashMap<>();
        postStatusCodeResponseMap.put("405", createResponseObject("Invalid input"));

        MediaTypeObject wwwFormUrlEncoded = createMediaType(new JSONObject("{\n" +
                "                \"type\": \"object\",\n" +
                "                \"properties\": {\n" +
                "                  \"name\": {\n" +
                "                    \"description\": \"Updated name of the pet\",\n" +
                "                    \"type\": \"string\"\n" +
                "                  },\n" +
                "                  \"status\": {\n" +
                "                    \"description\": \"Updated status of the pet\",\n" +
                "                    \"type\": \"string\"\n" +
                "                  }\n" +
                "                }\n" +
                "              }").toMap());
        Map<String, MediaTypeObject> postContentTypeMediaTypeMap = new HashMap<>();
        postContentTypeMediaTypeMap.put("application/x-www-form-urlencoded", wwwFormUrlEncoded);
        RequestBodyObject postPetRequestBody = new RequestBodyObject();
        postPetRequestBody.setContent(postContentTypeMediaTypeMap);

        OperationObject postPetOperation = new OperationObject();
        postPetOperation.setTags(Collections.singletonList("pet"));
        postPetOperation.setSummary("Updates a pet in the store with form data");
        postPetOperation.setOperationId("updatePetWithForm");
        postPetOperation.setRequestBody(postPetRequestBody);
        postPetOperation.setResponses(postStatusCodeResponseMap);
        postPetOperation.setDescription("");
        postPetOperation.setParameters(Collections.singletonList(postParameters));
        // ------------------------------------------------------

        Map<RestMethod, OperationObject> petOperationMap = new HashMap<>();
        petOperationMap.put(RestMethod.GET, getPetOperation);
        petOperationMap.put(RestMethod.POST, postPetOperation);
        return petOperationMap;
    }

    private static Map<RestMethod, OperationObject> createPetOperationMap() {
        // ---------------- PUT Operation Object ----------------
        RequestBodyObject putPetRequestBody = new RequestBodyObject();
        putPetRequestBody.set$ref("#/components/requestBodies/Pet");

        Map<String, ResponseObject> putStatusCodeResponseMap = new HashMap<>();
        putStatusCodeResponseMap.put("400", createResponseObject("Invalid ID supplied"));
        putStatusCodeResponseMap.put("404", createResponseObject("Pet not found"));
        putStatusCodeResponseMap.put("405", createResponseObject("Validation exception"));

        OperationObject putPetOperation = new OperationObject();
        putPetOperation.setSummary("Update an existing pet");
        putPetOperation.setOperationId("updatePet");
        putPetOperation.setRequestBody(putPetRequestBody);
        putPetOperation.setResponses(putStatusCodeResponseMap);
        putPetOperation.setDescription("");
        putPetOperation.setTags(Collections.singletonList("pet"));
        // ------------------------------------------------------

        // ---------------- POST Operation Object ----------------
        RequestBodyObject postPetRequestBody = new RequestBodyObject();
        postPetRequestBody.set$ref("#/components/requestBodies/Pet");

        Map<String, ResponseObject> postStatusResponseMap = new HashMap<>();
        postStatusResponseMap.put("405", createResponseObject("Invalid input"));

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
        return petOperationMap;
    }

    private static ComponentsObject createComponents() {
        Map<String, MediaTypeObject> contentTypeMediaTypeMap = new HashMap<>();
        contentTypeMediaTypeMap.put("application/xml", createMediaType("#/components/schemas/Pet"));
        contentTypeMediaTypeMap.put("application/json", createMediaType("#/components/schemas/Pet"));

        RequestBodyObject petRequestBody = new RequestBodyObject();
        petRequestBody.setContent(contentTypeMediaTypeMap);
        petRequestBody.setRequired(true);
        petRequestBody.setDescription("Pet object that needs to be added to the store");

        Map<String, RequestBodyObject> idAndRequestBody = new HashMap<>();
        idAndRequestBody.put("Pet", petRequestBody);

        Map<String, SchemaObject> idAndSchema = new HashMap<>();
        idAndSchema.put("Pet", createSchema(Fixture.Schemas.Pet));
        idAndSchema.put("Tag", createSchema(Fixture.Schemas.Tag));
        idAndSchema.put("Category", createSchema(Fixture.Schemas.Category));

        ComponentsObject componentsObject = new ComponentsObject();
        componentsObject.setRequestBodies(idAndRequestBody);
        componentsObject.setSchemas(idAndSchema);
        return componentsObject;
    }

    private static MediaTypeObject createMediaType(Map<String, Object> schemaData) {
        MediaTypeObject mediaType = new MediaTypeObject();
        mediaType.setSchema(new Schema(schemaData));
        return mediaType;
    }

    private static MediaTypeObject createMediaType(String schemaId) {
        MediaTypeObject mediaType = new MediaTypeObject();
        mediaType.setSchema(new Schema(schemaId));
        return mediaType;
    }

    private static SchemaObject createSchema(Fixture.Schemas schemaData) {
        Schema schema = new Schema(new JSONObject(schemaData.string()).toMap());
        SchemaObject schemaObject = new SchemaObject();
        schemaObject.setSchema(schema);
        return schemaObject;
    }

    private static ContactObject createContact() {
        ContactObject expectedContact = new ContactObject();
        expectedContact.setEmail("apiteam@swagger.io");
        return expectedContact;
    }

    private static LicenseObject createLicense() {
        LicenseObject expectedLicense = new LicenseObject();
        expectedLicense.setName("Apache 2.0");
        expectedLicense.setUrl("http://www.apache.org/licenses/LICENSE-2.0.html");
        return expectedLicense;
    }

    private static InfoObject createInfo() {
        InfoObject expectedInfo = new InfoObject();
        expectedInfo.setDescription("This is a sample server Petstore server.  You can find out more about Swagger at [http://swagger.io](http://swagger.io) or on [irc.freenode.net, #swagger](http://swagger.io/irc/).  For this sample, you can use the api key `special-key` to test the authorization filters.");
        expectedInfo.setVersion("1.0.2");
        expectedInfo.setTitle("Swagger Petstore");
        expectedInfo.setTermsOfService("http://swagger.io/terms/");
        expectedInfo.setContact(createContact());
        expectedInfo.setLicense(createLicense());
        return expectedInfo;
    }

    private static ServerObject createServer() {
        ServerObject expectedServer = new ServerObject();
        expectedServer.setUrl("https://petstore.swagger.io/v2");
        return expectedServer;
    }

    private static ResponseObject createResponseObject(String description) {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setDescription(description);
        return responseObject;
    }
}
