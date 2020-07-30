package com.reedelk.openapi.v3;

import com.reedelk.openapi.Fixture;
import com.reedelk.openapi.v3.model.HeaderObject;
import com.reedelk.openapi.v3.model.ParameterStyle;
import com.reedelk.openapi.v3.model.Schema;
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
        header.setSchema(new Schema(new JSONObject("{\n" +
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
        header.setSchema(new Schema("#/components/schemas/Pet"));

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
