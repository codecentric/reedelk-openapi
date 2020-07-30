package com.reedelk.openapi.v3;

import com.reedelk.openapi.Fixture;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

class ParameterObjectTest extends AbstractOpenApiSerializableTest {

    @Test
    void shouldCorrectlySerializeParameterWithAllProperties() {
        // Given
        ParameterObject parameter = new ParameterObject();
        parameter.setAllowEmptyValue(true);
        parameter.setAllowReserved(true);
        parameter.setDeprecated(true);
        parameter.setRequired(true);
        parameter.setExplode(true);
        parameter.setName("param1");
        parameter.setIn(ParameterLocation.query);
        parameter.setStyle(ParameterStyle.simple);
        parameter.setDescription("My parameter description");
        parameter.setSchema(new SchemaDefault(new JSONObject("{\n" +
                "    \"type\": \"array\",\n" +
                "    \"items\": {\"type\": \"string\"}\n" +
                "  }").toMap()));

        // Expect
        assertSerializeJSON(parameter, Fixture.ParameterObject.WithAllProperties);
    }

    @Test
    void shouldCorrectlySerializeParameterWithDefault() {
        // Given
        ParameterObject parameter = new ParameterObject();
        parameter.setName("myParam");
        parameter.setIn(ParameterLocation.query);
        parameter.setRequired(true);

        // Expect
        assertSerializeJSON(parameter, Fixture.ParameterObject.WithDefault);
    }

    @Test
    void shouldSetRequiredTrueWhenParameterInPath() {
        // Given
        ParameterObject parameter = new ParameterObject();
        parameter.setName("myParam");
        parameter.setIn(ParameterLocation.path);

        // Expect
        assertSerializeJSON(parameter, Fixture.ParameterObject.WithInPath);
    }
}
