package com.reedelk.openapi.v3;

import com.reedelk.openapi.Fixture;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

class HeaderObjectTest extends AbstractOpenApiSerializableTest {

    @Test
    void shouldCorrectlySerializeHeaderWithAllPropertiesAndInlineSchema() {
        // Given
        HeaderObject header = new HeaderObject();
        header.setAllowReserved(true);
        header.setDeprecated(true);
        header.setExplode(true);
        header.setExample("my header value");
        header.setDescription("My header description");
        header.setStyle(ParameterStyle.spaceDelimited);
        header.setSchema(new SchemaDefault(new JSONObject("{\n" +
                "    \"type\": \"array\",\n" +
                "    \"items\": {\"type\": \"string\"}\n" +
                "  }").toMap()));

        // Expect
        assertSerializeJSON(header, Fixture.HeaderObject.WithAllPropertiesAndDefaultSchema);
    }

    @Test
    void shouldCorrectlySerializeHeaderWithReferenceSchema() {
        // Given
        HeaderObject header = new HeaderObject();
        header.setAllowReserved(true);
        header.setDeprecated(true);
        header.setExplode(true);
        header.setExample("my header value");
        header.setDescription("My header description");
        header.setStyle(ParameterStyle.spaceDelimited);
        header.setSchema(new SchemaDefault("#/components/schemas/Pet"));

        // Expect
        assertSerializeJSON(header, Fixture.HeaderObject.WithAllPropertiesAndReferenceSchema);
    }

    @Test
    void shouldSerializeEmptyObjectWhenNoProperties() {
        // Given
        HeaderObject header = new HeaderObject();

        // Expect
        assertSerializeJSON(header, Fixture.HeaderObject.WithDefaults);
    }
}
