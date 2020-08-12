package com.reedelk.openapi.v3.serializer;

import com.reedelk.openapi.commons.AbstractSerializer;
import com.reedelk.openapi.commons.NavigationPath;
import com.reedelk.openapi.v3.SerializerContext;
import com.reedelk.openapi.v3.model.MediaTypeObject;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.reedelk.openapi.v3.model.MediaTypeObject.Properties;

public class MediaTypeObjectSerializer extends AbstractSerializer<MediaTypeObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, NavigationPath navigationPath, MediaTypeObject input) {
        Map<String, Object> map = new LinkedHashMap<>();

        if (input.getSchema() != null) {
            NavigationPath currentNavigationPath = navigationPath.with(NavigationPath.SegmentKey.SCHEMA);
            Map<String, Object> serializedSchema = context.serialize(currentNavigationPath, input.getSchema());
            set(map, Properties.SCHEMA.value(), serializedSchema);
        }

        if (input.getExample() != null && input.getExample().data() != null) {
            map.put(Properties.EXAMPLE.value(), input.getExample().data());
        }
        return map;
    }
}
