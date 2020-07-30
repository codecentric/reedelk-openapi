package com.reedelk.openapi.v3.serializer;

import com.reedelk.openapi.commons.AbstractSerializer;
import com.reedelk.openapi.v3.SerializerContext;
import com.reedelk.openapi.v3.model.OpenApiObject;
import com.reedelk.openapi.v3.model.TagObject;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class OpenApiObjectSerializer extends AbstractSerializer<OpenApiObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, OpenApiObject input) {
        Map<String, Object> map = new LinkedHashMap<>();

        set(map, "openapi", input.getOpenapi()); // REQUIRED

        Map<String, Object> serializedInfo = context.serialize(input.getInfo());
        set(map, "info", serializedInfo); // REQUIRED

        List<TagObject> tags = input.getTags();
        if (tags != null && !tags.isEmpty()) {
            List<Map<String, Object>> mappedTags = tags
                    .stream()
                    .map(context::serialize)
                    .collect(toList());
            map.put("tags", mappedTags);
        }

        List<Map<String, Object>> mappedServers = input
                .getServers()
                .stream()
                .map(context::serialize)
                .collect(toList());
        map.put("servers", mappedServers);

        Map<String, Object> serializedPaths = context.serialize(input.getPaths());
        set(map, "paths", serializedPaths); // REQUIRED

        Map<String, Object> serializedComponents = context.serialize(input.getComponents());
        set(map, "components", serializedComponents);
        return map;
    }
}
