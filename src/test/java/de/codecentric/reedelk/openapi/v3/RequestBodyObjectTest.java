package de.codecentric.reedelk.openapi.v3;

import de.codecentric.reedelk.openapi.Fixture;
import de.codecentric.reedelk.openapi.v3.model.Example;
import de.codecentric.reedelk.openapi.v3.model.MediaTypeObject;
import de.codecentric.reedelk.openapi.v3.model.RequestBodyObject;
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
