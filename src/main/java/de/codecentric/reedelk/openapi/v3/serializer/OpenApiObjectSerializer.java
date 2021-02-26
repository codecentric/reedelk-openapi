package de.codecentric.reedelk.openapi.v3.serializer;

import de.codecentric.reedelk.openapi.commons.AbstractSerializer;
import de.codecentric.reedelk.openapi.commons.NavigationPath;
import de.codecentric.reedelk.openapi.v3.SerializerContext;
import de.codecentric.reedelk.openapi.v3.model.OpenApiObject;
import de.codecentric.reedelk.openapi.v3.model.TagObject;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class OpenApiObjectSerializer extends AbstractSerializer<OpenApiObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, NavigationPath navigationPath, OpenApiObject input) {
        Map<String, Object> map = new LinkedHashMap<>();

        set(map, OpenApiObject.Properties.OPEN_API.value(), input.getOpenapi()); // REQUIRED

        Map<String, Object> serializedInfo = context.serialize(navigationPath.with(NavigationPath.SegmentKey.INFO), input.getInfo());
        set(map, OpenApiObject.Properties.INFO.value(), serializedInfo); // REQUIRED

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
        map.put(OpenApiObject.Properties.SERVERS.value(), mappedServers);

        Map<String, Object> serializedPaths = context.serialize(navigationPath.with(NavigationPath.SegmentKey.PATHS), input.getPaths());
        set(map, OpenApiObject.Properties.PATHS.value(), serializedPaths); // REQUIRED

        Map<String, Object> serializedComponents = context.serialize(navigationPath.with(NavigationPath.SegmentKey.COMPONENTS), input.getComponents());
        if (serializedComponents != null && !serializedComponents.isEmpty()) {
            set(map, OpenApiObject.Properties.COMPONENTS.value(), serializedComponents);
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
            map.put(OpenApiObject.Properties.TAGS.value(), mappedTags);
        }
        return map;
    }
}
