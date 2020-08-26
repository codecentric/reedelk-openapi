package com.reedelk.openapi.v3;

import com.reedelk.openapi.Fixture;
import com.reedelk.openapi.v3.model.HeaderObject;
import com.reedelk.openapi.v3.model.ParameterStyle;
import com.reedelk.openapi.v3.model.Schema;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HeaderObjectTest extends AbstractOpenApiSerializableTest {

    private HeaderObject header;
    private HeaderObject headerWithReference;

    @BeforeEach
    void setUp() {
        header = new HeaderObject();
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

        headerWithReference = new HeaderObject();
        headerWithReference.setAllowReserved(true);
        headerWithReference.setDeprecated(true);
        headerWithReference.setExplode(true);
        headerWithReference.setExample("my header value");
        headerWithReference.setDescription("My header description");
        headerWithReference.setStyle(ParameterStyle.spaceDelimited);
        headerWithReference.setSchema(new Schema("#/components/schemas/Pet"));
    }

    @Test
    void shouldCorrectlySerializeHeaderWithAllPropertiesAndInlineSchema() {
        // Given
        HeaderObject theHeader = header;

        // Expect
        assertSerializeJSON(theHeader, Fixture.HeaderObject.WithAllPropertiesAndDefaultSchema);
    }

    @Test
    void shouldCorrectlyDeserializeHeaderWithAllPropertiesAndInlineSchema() {
        // Given
        HeaderObject theHeader = header;

        // Expect
        assertDeserializeJSON(theHeader, Fixture.HeaderObject.WithAllPropertiesAndDefaultSchema);
    }

    @Test
    void shouldCorrectlySerializeHeaderWithReferenceSchema() {
        // Given
        HeaderObject theHeader = headerWithReference;

        // Expect
        assertSerializeJSON(theHeader, Fixture.HeaderObject.WithAllPropertiesAndReferenceSchema);
    }

    @Test
    void shouldCorrectlyDeserializeHeaderWithReferenceSchema() {
        // Given
        HeaderObject theHeader = headerWithReference;

        // Expect
        assertDeserializeJSON(theHeader, Fixture.HeaderObject.WithAllPropertiesAndReferenceSchema);
    }

    @Test
    void shouldSerializeEmptyObjectWhenNoProperties() {
        // Given
        HeaderObject header = new HeaderObject();

        // Expect
        assertSerializeJSON(header, Fixture.HeaderObject.WithDefaults);
    }
}
