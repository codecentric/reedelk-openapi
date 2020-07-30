package com.reedelk.openapi.v3;

import com.reedelk.openapi.Fixture;
import com.reedelk.openapi.v3.model.ComponentsObject;
import com.reedelk.openapi.v3.model.Schema;
import com.reedelk.openapi.v3.model.SchemaObject;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.Map;

class ComponentsObjectTest extends AbstractOpenApiSerializableTest {

    @Test
    void shouldCorrectlySerializeSchema() {
        // Given
        SchemaObject stringSchemaObject = new SchemaObject();
        stringSchemaObject.setSchema(new Schema(new JSONObject("{\n" +
                "      \"type\": \"string\"\n" +
                "    }").toMap()));

        SchemaObject integerSchemaObject = new SchemaObject();
        integerSchemaObject.setSchema(new Schema(new JSONObject("{\n" +
                "      \"type\": \"integer\"\n" +
                "    }").toMap()));


        ComponentsObject componentsObject = new ComponentsObject();

        Map<String, SchemaObject> schemas = componentsObject.getSchemas();
        schemas.put("MyString", stringSchemaObject);
        schemas.put("MyInteger", integerSchemaObject);

        // Expect
        assertSerializeJSON(componentsObject, Fixture.ComponentsObject.WithSampleSchemas);
    }

    @Test
    void shouldCorrectlySerializeWhenNoSchemas() {
        // Given
        ComponentsObject componentsObject = new ComponentsObject();

        // Expect
        assertSerializeJSON(componentsObject, Fixture.ComponentsObject.WithNoSchemas);
    }
}
