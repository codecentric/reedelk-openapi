package com.reedelk.openapi.v3.deserializer;

import com.reedelk.openapi.commons.AbstractDeserializer;
import com.reedelk.openapi.v3.DeserializerContext;
import com.reedelk.openapi.v3.model.*;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class OpenApiObjectDeserializer extends AbstractDeserializer<OpenApiObject> {

    @Override
    public OpenApiObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        OpenApiObject openApiObject = new OpenApiObject();

        if (serialized.containsKey("openapi")) {
            openApiObject.setOpenapi(getString(serialized, "openapi"));
        }

        if (serialized.containsKey("info")) {
            Map<String, Object> infoObjectMap = getMap(serialized, "info");
            InfoObject infoObject = context.deserialize(InfoObject.class, infoObjectMap);
            openApiObject.setInfo(infoObject);
        }

        if (serialized.containsKey("tags")) {
            List<Map<String, Object>> tagsList = getList(serialized, "tags");
            List<TagObject> tagObjects = tagsList
                    .stream()
                    .map(objectMap -> context.deserialize(TagObject.class, objectMap))
                    .collect(toList());
            openApiObject.setTags(tagObjects);
        }

        if (serialized.containsKey("servers")) {
            List<Map<String, Object>> serversList = getList(serialized, "servers");
            List<ServerObject> serverObjects = serversList
                    .stream()
                    .map(objectMap -> context.deserialize(ServerObject.class, objectMap))
                    .collect(toList());
            openApiObject.setServers(serverObjects);
        }

        if (serialized.containsKey("paths")) {
            Map<String, Object> pathsObjectMap = getMap(serialized, "paths");
            PathsObject pathsObject = context.deserialize(PathsObject.class, pathsObjectMap);
            openApiObject.setPaths(pathsObject);
        }

        if (serialized.containsKey("components")) {
            Map<String, Object> componentsObjectMap = getMap(serialized, "components");
            ComponentsObject componentsObject = context.deserialize(ComponentsObject.class, componentsObjectMap);
            openApiObject.setComponents(componentsObject);
        }

        return openApiObject;
    }
}
