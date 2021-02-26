package de.codecentric.reedelk.openapi.v3;

import de.codecentric.reedelk.openapi.Fixture;
import de.codecentric.reedelk.openapi.v3.model.*;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

class ComponentsObjectTest extends AbstractOpenApiSerializableTest {

    private ComponentsObject componentsObject;

    @BeforeEach
    void setUp() {
        SchemaObject stringSchemaObject = new SchemaObject();
        stringSchemaObject.setSchema(new Schema(new JSONObject("{\n" +
                "      \"type\": \"string\"\n" +
                "    }").toMap()));

        SchemaObject integerSchemaObject = new SchemaObject();
        integerSchemaObject.setSchema(new Schema(new JSONObject("{\n" +
                "      \"type\": \"integer\"\n" +
                "    }").toMap()));


        componentsObject = new ComponentsObject();
        Map<String, SchemaObject> schemas = componentsObject.getSchemas();
        schemas.put("MyString", stringSchemaObject);
        schemas.put("MyInteger", integerSchemaObject);

        ExampleObject exampleObject = new ExampleObject();
        exampleObject.setValue("{\"name\": \"John\"}");
        exampleObject.setSummary("My summary");
        exampleObject.setDescription("My description");
        exampleObject.setExternalValue("My external value");

        Map<String, ExampleObject> examples = componentsObject.getExamples();
        examples.put("Example1", exampleObject);

        RequestBodyObject requestBodyObject = new RequestBodyObject();
        requestBodyObject.setRequired(true);
        requestBodyObject.setDescription("My request body");

        Example example = new Example("{\"name\": \"Mark\"}");
        MediaTypeObject mediaTypeObject = new MediaTypeObject();
        mediaTypeObject.setExample(example);

        Map<String, MediaTypeObject> content = new LinkedHashMap<>();
        content.put("application/json", mediaTypeObject);
        requestBodyObject.setContent(content);

        Map<String, RequestBodyObject> requestBodies = componentsObject.getRequestBodies();
        requestBodies.put("RequestBody1", requestBodyObject);

        SecuritySchemeObject securitySchemeObject = new SecuritySchemeObject();
        securitySchemeObject.setIn(SecurityKeyLocation.query);
        securitySchemeObject.setType(SecurityType.apiKey);

        Map<String, SecuritySchemeObject> securitySchemes = componentsObject.getSecuritySchemes();
        securitySchemes.put("api_key", securitySchemeObject);
    }

    @Test
    void shouldCorrectlySerializeSchema() {
        // Given
        ComponentsObject theComponents = componentsObject;

        // Expect
        assertSerializeJSON(theComponents, Fixture.ComponentsObject.WithSampleSchemas);
    }

    @Test
    void shouldCorrectlyDeserializeSchema() {
        // Given
        ComponentsObject theComponents = componentsObject;

        // Expect
        assertDeserializeJSON(theComponents, Fixture.ComponentsObject.WithSampleSchemas);
    }

    @Test
    void shouldCorrectlySerializeWhenNoSchemas() {
        // Given
        ComponentsObject componentsObject = new ComponentsObject();

        // Expect
        assertSerializeJSON(componentsObject, Fixture.ComponentsObject.WithNoSchemas);
    }

    @Test
    void shouldCorrectlyDeserializeWhenNoSchemas() {
        // Given
        ComponentsObject componentsObject = new ComponentsObject();

        // Expect
        assertDeserializeJSON(componentsObject, Fixture.ComponentsObject.WithNoSchemas);
    }
}
