package com.reedelk.openapi.v3.deserializer;

import com.reedelk.openapi.commons.AbstractDeserializer;
import com.reedelk.openapi.v3.DeserializerContext;
import com.reedelk.openapi.v3.model.Example;
import com.reedelk.openapi.v3.model.MediaTypeObject;
import com.reedelk.openapi.v3.model.Schema;

import java.util.Map;

import static com.reedelk.openapi.v3.model.MediaTypeObject.Properties;

public class MediaTypeObjectDeserializer extends AbstractDeserializer<MediaTypeObject> {

    @Override
    public MediaTypeObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        MediaTypeObject mediaTypeObject = new MediaTypeObject();

        // Schema
        mapApiModel(serialized, Properties.SCHEMA.value(), Schema.class, context)
                .ifPresent(mediaTypeObject::setSchema);

        // Example (we keep the raw string)
        if (serialized.containsKey(Properties.EXAMPLE.value())) {
            // It could be a string, or a JSON (or YAML object)
            Object exampleData = serialized.get(Properties.EXAMPLE.value());
            if (exampleData instanceof String) {
                Example example = new Example((String) exampleData);
                mediaTypeObject.setExample(example);
            } else {
                // If it is an object must have the type of the
                // OpenAPI document: must be either YAML or JSON.
                String exampleAsString = context.getDataFormat().dump(exampleData);
                Example example = new Example(exampleAsString);
                mediaTypeObject.setExample(example);
            }
        }

        return mediaTypeObject;
    }
}
