package de.codecentric.reedelk.openapi.v3.deserializer;

import de.codecentric.reedelk.openapi.commons.AbstractDeserializer;
import de.codecentric.reedelk.openapi.v3.DeserializerContext;
import de.codecentric.reedelk.openapi.v3.model.ServerObject;
import de.codecentric.reedelk.openapi.v3.model.ServerVariableObject;

import java.util.Map;

public class ServerObjectDeserializer extends AbstractDeserializer<ServerObject> {

    @Override
    public ServerObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        ServerObject serverObject = new ServerObject();
        serverObject.setUrl(getString(serialized, ServerObject.Properties.URL.value()));
        serverObject.setDescription(getString(serialized, ServerObject.Properties.DESCRIPTION.value()));

        // Variables
        mapKeyApiModel(ServerObject.Properties.VARIABLES.value(), serialized,
                (key, source) -> context.deserialize(ServerVariableObject.class, source))
                .ifPresent(serverObject::setVariables);

        return serverObject;
    }
}
