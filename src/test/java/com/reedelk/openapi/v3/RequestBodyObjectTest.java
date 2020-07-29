package com.reedelk.openapi.v3;

import com.reedelk.openapi.Fixture;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class RequestBodyObjectTest extends AbstractOpenApiSerializableTest {

    @Test
    void shouldCorrectlySerializeRequestBodyWithAllProperties() {
        // Given
        MediaTypeObject mediaTypeObject = new MediaTypeObject();
        mediaTypeObject.setExample(new Example("{\"id\":\"Dog\",\"name\":\"John\"}"));

        Map<String, MediaTypeObject> contentTypeMediaType = new HashMap<>();
        contentTypeMediaType.put("application/json", mediaTypeObject);

        RequestBodyObject requestBody = new RequestBodyObject();
        requestBody.setDescription("My request body");
        requestBody.setRequired(true);
        requestBody.setContent(contentTypeMediaType);

        // Expect
        assertSerializeJSON(requestBody, Fixture.RequestBodyObject.WithAllProperties);
    }

    @Test
    void shouldCorrectlySerializeRequestBodyWithDefaults() {
        // Given
        RequestBodyObject requestBody = new RequestBodyObject();

        // Expect
        assertSerializeJSON(requestBody, Fixture.RequestBodyObject.WithDefault);
    }
}
