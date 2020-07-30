package com.reedelk.openapi.v3;

import com.reedelk.openapi.Fixture;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class ResponseObjectTest extends AbstractOpenApiSerializableTest {

    @Test
    void shouldCorrectlySerializeResponseWithAllProperties() {
        // Given
        MediaTypeObject jsonMediaType = new MediaTypeObject();
        jsonMediaType.setExample(new Example("{\"id\":\"Dog\",\"name\":\"John\"}"));

        MediaTypeObject xmlMediaType = new MediaTypeObject();
        xmlMediaType.setExample(new Example("<note><to>Tove</to><from>Jani</from><heading>Reminder</heading><body>Don't forget me this weekend!</body></note>"));

        Map<String, MediaTypeObject> contentTypeMediaType = new HashMap<>();
        contentTypeMediaType.put("application/json", jsonMediaType);
        contentTypeMediaType.put("text/xml", xmlMediaType);

        HeaderObject header1 = new HeaderObject();
        header1.setDescription("My header 1");
        header1.setStyle(ParameterStyle.simple);
        header1.setSchema(new SchemaDefault(new JSONObject("{\n" +
                "        \"type\": \"string\"\n" +
                "    }").toMap()));

        HeaderObject header2 = new HeaderObject();
        header2.setDescription("My header 2");
        header2.setStyle(ParameterStyle.simple);
        header2.setSchema(new SchemaDefault(new JSONObject("{\n" +
                "        \"type\": \"string\"\n" +
                "    }").toMap()));

        Map<String, HeaderObject> headers = new HashMap<>();
        headers.put("x-my-header1", header1);
        headers.put("x-my-header2", header2);

        ResponseObject response = new ResponseObject();
        response.setDescription("My response description");
        response.setContent(contentTypeMediaType);
        response.setHeaders(headers);

        // Expect
        assertSerializeJSON(response, Fixture.ResponseObject.WithAllProperties);
    }

    @Test
    void shouldCorrectlySerializeResponseBodyWithDefaults() {
        // Given
        ResponseObject response = new ResponseObject();

        // Expect
        assertSerializeJSON(response, Fixture.ResponseObject.WithDefault);
    }
}
