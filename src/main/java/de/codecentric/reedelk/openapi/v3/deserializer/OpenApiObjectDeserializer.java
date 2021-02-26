package de.codecentric.reedelk.openapi.v3.deserializer;

import de.codecentric.reedelk.openapi.commons.AbstractDeserializer;
import de.codecentric.reedelk.openapi.v3.DeserializerContext;
import de.codecentric.reedelk.openapi.v3.model.*;

import java.util.Map;

public class OpenApiObjectDeserializer extends AbstractDeserializer<OpenApiObject> {

    @Override
    public OpenApiObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        OpenApiObject openApiObject = new OpenApiObject();

        if (serialized.containsKey(OpenApiObject.Properties.OPEN_API.value())) {
            openApiObject.setOpenapi(getString(serialized, OpenApiObject.Properties.OPEN_API.value()));
        }

        // Info
        mapApiModel(serialized, OpenApiObject.Properties.INFO.value(), InfoObject.class, context)
                .ifPresent(openApiObject::setInfo);

        // Tags
        mapListApiModel(OpenApiObject.Properties.TAGS.value(), serialized,
                source -> context.deserialize(TagObject.class, source))
                .ifPresent(openApiObject::setTags);

        // Servers
        mapListApiModel(OpenApiObject.Properties.SERVERS.value(), serialized,
                source -> context.deserialize(ServerObject.class, source))
                .ifPresent(openApiObject::setServers);

        // Paths
        mapApiModel(serialized, OpenApiObject.Properties.PATHS.value(), PathsObject.class, context)
                .ifPresent(openApiObject::setPaths);

        // Components
        mapApiModel(serialized, OpenApiObject.Properties.COMPONENTS.value(), ComponentsObject.class, context)
                .ifPresent(openApiObject::setComponents);

        return openApiObject;
    }
}
