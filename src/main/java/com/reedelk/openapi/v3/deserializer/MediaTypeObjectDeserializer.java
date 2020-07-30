package com.reedelk.openapi.v3.deserializer;

import com.reedelk.openapi.commons.AbstractDeserializer;
import com.reedelk.openapi.v3.DeserializerContext;
import com.reedelk.openapi.v3.model.Example;
import com.reedelk.openapi.v3.model.MediaTypeObject;
import com.reedelk.openapi.v3.model.Schema;

import java.util.Map;

public class MediaTypeObjectDeserializer extends AbstractDeserializer<MediaTypeObject> {

    @Override
    public MediaTypeObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        MediaTypeObject mediaTypeObject = new MediaTypeObject();

        if (serialized.containsKey("schema")) {
            Map<String, Object> schemaDefinition = getMap(serialized, "schema");
            Schema schemaObject = context.deserialize(Schema.class, schemaDefinition);
            mediaTypeObject.setSchema(schemaObject);
        }

        if (serialized.containsKey("example")) {
            String exampleData = (String) serialized.get("example");
            Example example = new Example(exampleData);
            mediaTypeObject.setExample(example);
        }

        return mediaTypeObject;
    }
}
