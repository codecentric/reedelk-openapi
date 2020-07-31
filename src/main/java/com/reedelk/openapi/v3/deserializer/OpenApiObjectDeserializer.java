package com.reedelk.openapi.v3.deserializer;

import com.reedelk.openapi.commons.AbstractDeserializer;
import com.reedelk.openapi.v3.DeserializerContext;
import com.reedelk.openapi.v3.model.*;

import java.util.Map;

public class OpenApiObjectDeserializer extends AbstractDeserializer<OpenApiObject> {

    @Override
    public OpenApiObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        OpenApiObject openApiObject = new OpenApiObject();

        if (serialized.containsKey("openapi")) {
            openApiObject.setOpenapi(getString(serialized, "openapi"));
        }

        // Info
        mapApiModel(serialized, "info", InfoObject.class, context)
                .ifPresent(openApiObject::setInfo);

        // Tags
        mapListApiModel("tags", serialized,
                source -> context.deserialize(TagObject.class, source))
                .ifPresent(openApiObject::setTags);

        // Servers
        mapListApiModel("servers", serialized,
                source -> context.deserialize(ServerObject.class, source))
                .ifPresent(openApiObject::setServers);

        // Paths
        mapApiModel(serialized, "paths", PathsObject.class, context)
                .ifPresent(openApiObject::setPaths);

        // Components
        mapApiModel(serialized, "components", ComponentsObject.class, context)
                .ifPresent(openApiObject::setComponents);

        return openApiObject;
    }
}
