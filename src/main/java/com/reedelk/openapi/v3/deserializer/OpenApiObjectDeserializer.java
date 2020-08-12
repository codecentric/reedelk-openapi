package com.reedelk.openapi.v3.deserializer;

import com.reedelk.openapi.commons.AbstractDeserializer;
import com.reedelk.openapi.v3.DeserializerContext;
import com.reedelk.openapi.v3.model.*;

import java.util.Map;

import static com.reedelk.openapi.v3.model.OpenApiObject.Properties;

public class OpenApiObjectDeserializer extends AbstractDeserializer<OpenApiObject> {

    @Override
    public OpenApiObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        OpenApiObject openApiObject = new OpenApiObject();

        if (serialized.containsKey(Properties.OPEN_API.value())) {
            openApiObject.setOpenapi(getString(serialized, Properties.OPEN_API.value()));
        }

        // Info
        mapApiModel(serialized, Properties.INFO.value(), InfoObject.class, context)
                .ifPresent(openApiObject::setInfo);

        // Tags
        mapListApiModel(Properties.TAGS.value(), serialized,
                source -> context.deserialize(TagObject.class, source))
                .ifPresent(openApiObject::setTags);

        // Servers
        mapListApiModel(Properties.SERVERS.value(), serialized,
                source -> context.deserialize(ServerObject.class, source))
                .ifPresent(openApiObject::setServers);

        // Paths
        mapApiModel(serialized, Properties.PATHS.value(), PathsObject.class, context)
                .ifPresent(openApiObject::setPaths);

        // Components
        mapApiModel(serialized, Properties.COMPONENTS.value(), ComponentsObject.class, context)
                .ifPresent(openApiObject::setComponents);

        return openApiObject;
    }
}
