package de.codecentric.reedelk.openapi.v3;

import de.codecentric.reedelk.openapi.Fixture;
import de.codecentric.reedelk.openapi.v3.model.Schema;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

public class SchemaTest extends AbstractOpenApiSerializableTest {

    @Test
    void shouldCorrectlySerializeSchemaWithReference() {
        // Given
        Schema schema = new Schema("#/components/schemas/Pet");

        // Expect
        assertSerializeJSON(schema, Fixture.Schema.WithReference);
    }

    @Test
    void shouldCorrectlySerializeInfoWithRequiredValues() {
        // Given
        Schema schema = new Schema(new JSONObject(
                "{\n" +
                        "  \"type\": \"object\",\n" +
                        "  \"properties\": {\n" +
                        "    \"name\": {\n" +
                        "      \"description\": \"Updated name of the pet\",\n" +
                        "      \"type\": \"string\"\n" +
                        "    },\n" +
                        "    \"status\": {\n" +
                        "      \"description\": \"Updated status of the pet\",\n" +
                        "      \"type\": \"string\"\n" +
                        "    }\n" +
                        "  }\n" +
                        "}\n"
        ).toMap());

        // Expect
        assertSerializeJSON(schema, Fixture.Schema.WithInlineSchema);
    }
}
