package com.reedelk.openapi.v3.serializer;

import com.reedelk.openapi.AbstractSerializer;
import com.reedelk.openapi.v3.SerializerContext;
import com.reedelk.openapi.v3.model.TagObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class TagObjectSerializer extends AbstractSerializer<TagObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, TagObject input) {
        Map<String, Object> map = new LinkedHashMap<>();
        set(map, "name", input.getName());
        set(map, "description", input.getDescription());

        if (input.getExternalDocs() != null) {
            Map<String, Object> serializedExternalDocs = context.serialize(input.getExternalDocs());
            map.put("externalDocs", serializedExternalDocs);
        }
        return map;
    }
}
