package com.reedelk.openapi.v3.deserializer;

import com.reedelk.openapi.commons.AbstractDeserializer;
import com.reedelk.openapi.v3.DeserializerContext;
import com.reedelk.openapi.v3.model.Example;
import com.reedelk.openapi.v3.model.MediaTypeObject;
import com.reedelk.openapi.v3.model.Schema;
import org.yaml.snakeyaml.Yaml;

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
                // TODO: This yaml instance ...should be a factory clazz.
                // Must be a YAML or JSON.
                Yaml yaml = new Yaml();
                String exampleAsString = yaml.dump(exampleData);
                Example example = new Example(exampleAsString);
                mediaTypeObject.setExample(example);
            }
        }

        return mediaTypeObject;
    }
}
