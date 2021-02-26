package de.codecentric.reedelk.openapi.v3;

import de.codecentric.reedelk.openapi.Fixture;
import de.codecentric.reedelk.openapi.v3.model.Example;
import de.codecentric.reedelk.openapi.v3.model.MediaTypeObject;
import de.codecentric.reedelk.openapi.v3.model.Schema;
import org.junit.jupiter.api.Test;

public class MediaTypeObjectTest extends AbstractOpenApiSerializableTest {

    @Test
    void shouldCorrectlySerializeMediaTypeWithSchema() {
        // Given
        MediaTypeObject mediaType = new MediaTypeObject();
        mediaType.setSchema(new Schema("#/components/schemas/Pet"));

        // Expect
        assertSerializeJSON(mediaType, Fixture.MediaTypeObject.WithSchema);
    }

    @Test
    void shouldCorrectlyDeserializeMediaTypeWithSchema() {
        // Given
        MediaTypeObject mediaType = new MediaTypeObject();
        mediaType.setSchema(new Schema("#/components/schemas/Pet"));

        // Expect
        assertDeserializeJSON(mediaType, Fixture.MediaTypeObject.WithSchema);
    }

    @Test
    void shouldCorrectlySerializeMediaTypeWithExample() {
        // Given
        MediaTypeObject mediaType = new MediaTypeObject();
        mediaType.setExample(new Example("{\"id\":\"Dog\",\"name\":\"John\"}"));

        // Expect
        assertSerializeJSON(mediaType, Fixture.MediaTypeObject.WithExample);
    }

    @Test
    void shouldCorrectlyDeserializeMediaTypeWithExample() {
        // Given
        MediaTypeObject mediaType = new MediaTypeObject();
        mediaType.setExample(new Example("{\"id\":\"Dog\",\"name\":\"John\"}"));

        // Expect
        assertDeserializeJSON(mediaType, Fixture.MediaTypeObject.WithExample);
    }

    @Test
    void shouldCorrectlySerializeMediaTypeWithSchemaAndExample() {
        // Given
        MediaTypeObject mediaType = new MediaTypeObject();
        mediaType.setSchema(new Schema("#/components/schemas/Pet"));
        mediaType.setExample(new Example("{\"id\":\"Dog\",\"name\":\"John\"}"));

        // Expect
        assertSerializeJSON(mediaType, Fixture.MediaTypeObject.WithSchemaAndExample);
    }

    @Test
    void shouldCorrectlySerializeMediaTypeDefault() {
        // Given
        MediaTypeObject mediaType = new MediaTypeObject();

        // Expect
        assertSerializeJSON(mediaType, Fixture.MediaTypeObject.WithDefault);
    }
}
