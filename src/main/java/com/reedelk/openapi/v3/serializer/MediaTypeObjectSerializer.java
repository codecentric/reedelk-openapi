package com.reedelk.openapi.v3.serializer;

import com.reedelk.openapi.commons.AbstractSerializer;
import com.reedelk.openapi.v3.SerializerContext;
import com.reedelk.openapi.v3.model.MediaTypeObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class MediaTypeObjectSerializer extends AbstractSerializer<MediaTypeObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, MediaTypeObject input) {
        Map<String, Object> map = new LinkedHashMap<>();

        if (input.getSchema() != null) {
            Map<String, Object> serializedSchema = context.serialize(input.getSchema());
            set(map, "schema", serializedSchema);
        }

        if (input.getExample() != null && input.getExample().data() != null) {
            map.put("example", input.getExample().data());
        }
        return map;
    }
}
