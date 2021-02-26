package de.codecentric.reedelk.openapi.v3.serializer;

import de.codecentric.reedelk.openapi.commons.AbstractSerializer;
import de.codecentric.reedelk.openapi.commons.NavigationPath;
import de.codecentric.reedelk.openapi.v3.SerializerContext;
import de.codecentric.reedelk.openapi.v3.model.MediaTypeObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class MediaTypeObjectSerializer extends AbstractSerializer<MediaTypeObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, NavigationPath navigationPath, MediaTypeObject input) {
        Map<String, Object> map = new LinkedHashMap<>();

        if (input.getSchema() != null) {
            NavigationPath currentNavigationPath = navigationPath.with(NavigationPath.SegmentKey.SCHEMA);
            Map<String, Object> serializedSchema = context.serialize(currentNavigationPath, input.getSchema());
            set(map, MediaTypeObject.Properties.SCHEMA.value(), serializedSchema);
        }

        if (input.getExample() != null && input.getExample().data() != null) {
            map.put(MediaTypeObject.Properties.EXAMPLE.value(), input.getExample().data());
        }

        if (input.getExamples() != null && !input.getExamples().isEmpty()) {
            NavigationPath currentNavigationPath = navigationPath.with(NavigationPath.SegmentKey.EXAMPLES);
            Map<String,Object> serializedExamples = new LinkedHashMap<>();
            input.getExamples().forEach((exampleId, exampleObject) -> {
                Map<String, Object> serializedExample = context.serialize(currentNavigationPath, exampleObject);
                serializedExamples.put(exampleId, serializedExample);
            });
            map.put(MediaTypeObject.Properties.EXAMPLES.value(), serializedExamples);
        }
        return map;
    }
}
