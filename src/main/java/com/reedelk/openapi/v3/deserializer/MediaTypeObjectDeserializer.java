package com.reedelk.openapi.v3.deserializer;

import com.reedelk.openapi.commons.AbstractDeserializer;
import com.reedelk.openapi.v3.DeserializerContext;
import com.reedelk.openapi.v3.model.Example;
import com.reedelk.openapi.v3.model.ExampleObject;
import com.reedelk.openapi.v3.model.MediaTypeObject;
import com.reedelk.openapi.v3.model.Schema;

import java.util.HashMap;
import java.util.Map;

import static com.reedelk.openapi.v3.model.MediaTypeObject.Properties;

public class MediaTypeObjectDeserializer extends AbstractDeserializer<MediaTypeObject> {

    @SuppressWarnings("unchecked")
    @Override
    public MediaTypeObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        MediaTypeObject mediaTypeObject = new MediaTypeObject();

        // Schema
        mapApiModel(serialized, Properties.SCHEMA.value(), Schema.class, context)
                .ifPresent(mediaTypeObject::setSchema);

        // Example: (we keep the raw string)
        if (serialized.containsKey(Properties.EXAMPLE.value())) {
            // It could be a string, or a JSON (or YAML object)
            Object exampleData = serialized.get(Properties.EXAMPLE.value());
            String exampleDataAsString = deserializeExampleDataAsString(exampleData);
            Example example = new Example(exampleDataAsString);
            mediaTypeObject.setExample(example);
        }

        // Examples
        if (serialized.containsKey(Properties.EXAMPLES.value())) {
            Map<String, ExampleObject> deserializedExamples = new HashMap<>();
            Map<String, Object> serializedExamples = (Map<String, Object>) serialized.get(Properties.EXAMPLES.value());
            serializedExamples.keySet().forEach(exampleId ->
                    mapApiModel(serializedExamples, exampleId, ExampleObject.class, context)
                    .ifPresent(exampleObject -> deserializedExamples.put(exampleId, exampleObject)));
            mediaTypeObject.setExamples(deserializedExamples);
        }

        return mediaTypeObject;
    }
}
