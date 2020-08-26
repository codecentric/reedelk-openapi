package com.reedelk.openapi.v3.serializer;

import com.reedelk.openapi.commons.AbstractSerializer;
import com.reedelk.openapi.commons.NavigationPath;
import com.reedelk.openapi.v3.SerializerContext;
import com.reedelk.openapi.v3.model.OpenApiObject;
import com.reedelk.openapi.v3.model.TagObject;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.reedelk.openapi.v3.model.OpenApiObject.Properties;
import static java.util.stream.Collectors.toList;

public class OpenApiObjectSerializer extends AbstractSerializer<OpenApiObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, NavigationPath navigationPath, OpenApiObject input) {
        Map<String, Object> map = new LinkedHashMap<>();

        set(map, Properties.OPEN_API.value(), input.getOpenapi()); // REQUIRED

        Map<String, Object> serializedInfo = context.serialize(navigationPath.with(NavigationPath.SegmentKey.INFO), input.getInfo());
        set(map, Properties.INFO.value(), serializedInfo); // REQUIRED

        List<Map<String, Object>> mappedServers = input
                .getServers()
                .stream()
                .map(serverObject -> {
                    NavigationPath navPath = navigationPath
                            .with(NavigationPath.SegmentKey.SERVERS)
                            .with(NavigationPath.SegmentKey.SERVER_URL, serverObject.getUrl());
                    return context.serialize(navPath, serverObject);
                })
                .collect(toList());
        map.put(Properties.SERVERS.value(), mappedServers);

        Map<String, Object> serializedPaths = context.serialize(navigationPath.with(NavigationPath.SegmentKey.PATHS), input.getPaths());
        set(map, Properties.PATHS.value(), serializedPaths); // REQUIRED

        Map<String, Object> serializedComponents = context.serialize(navigationPath.with(NavigationPath.SegmentKey.COMPONENTS), input.getComponents());
        if (serializedComponents != null && !serializedComponents.isEmpty()) {
            set(map, Properties.COMPONENTS.value(), serializedComponents);
        }

        List<TagObject> tags = input.getTags();
        if (tags != null && !tags.isEmpty()) {
            List<Map<String, Object>> mappedTags = tags
                    .stream()
                    .map(tagObject -> {
                        NavigationPath navPath = navigationPath
                                .with(NavigationPath.SegmentKey.TAGS)
                                .with(NavigationPath.SegmentKey.TAG_NAME, tagObject.getName());
                        return context.serialize(navPath, tagObject);
                    })
                    .collect(toList());
            map.put(Properties.TAGS.value(), mappedTags);
        }
        return map;
    }
}
