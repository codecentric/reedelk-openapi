package de.codecentric.reedelk.openapi.v3.deserializer;

import de.codecentric.reedelk.openapi.commons.AbstractDeserializer;
import de.codecentric.reedelk.openapi.v3.DeserializerContext;
import de.codecentric.reedelk.openapi.v3.model.Example;
import de.codecentric.reedelk.openapi.v3.model.ExampleObject;
import de.codecentric.reedelk.openapi.v3.model.MediaTypeObject;
import de.codecentric.reedelk.openapi.v3.model.Schema;

import java.util.HashMap;
import java.util.Map;

public class MediaTypeObjectDeserializer extends AbstractDeserializer<MediaTypeObject> {

    @SuppressWarnings("unchecked")
    @Override
    public MediaTypeObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        MediaTypeObject mediaTypeObject = new MediaTypeObject();

        // Schema
        mapApiModel(serialized, MediaTypeObject.Properties.SCHEMA.value(), Schema.class, context)
                .ifPresent(mediaTypeObject::setSchema);

        // Example: (we keep the raw string)
        if (serialized.containsKey(MediaTypeObject.Properties.EXAMPLE.value())) {
            // It could be a string, or a JSON (or YAML object)
            Object exampleData = serialized.get(MediaTypeObject.Properties.EXAMPLE.value());
            String exampleDataAsString = deserializeExampleDataAsString(exampleData);
            Example example = new Example(exampleDataAsString);
            mediaTypeObject.setExample(example);
        }

        // Examples
        if (serialized.containsKey(MediaTypeObject.Properties.EXAMPLES.value())) {
            Map<String, ExampleObject> deserializedExamples = new HashMap<>();
            Map<String, Object> serializedExamples = (Map<String, Object>) serialized.get(MediaTypeObject.Properties.EXAMPLES.value());
            serializedExamples.keySet().forEach(exampleId ->
                    mapApiModel(serializedExamples, exampleId, ExampleObject.class, context)
                    .ifPresent(exampleObject -> deserializedExamples.put(exampleId, exampleObject)));
            mediaTypeObject.setExamples(deserializedExamples);
        }

        return mediaTypeObject;
    }
}
