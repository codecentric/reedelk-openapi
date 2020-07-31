package com.reedelk.openapi.v3.deserializer;

import com.reedelk.openapi.commons.AbstractDeserializer;
import com.reedelk.openapi.v3.DeserializerContext;
import com.reedelk.openapi.v3.model.ServerObject;
import com.reedelk.openapi.v3.model.ServerVariableObject;

import java.util.Map;

public class ServerObjectDeserializer extends AbstractDeserializer<ServerObject> {

    @Override
    public ServerObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        ServerObject serverObject = new ServerObject();
        serverObject.setUrl(getString(serialized, "url"));
        serverObject.setDescription(getString(serialized, "description"));

        // Variables
        mapKeyApiModel("variables", serialized,
                (key, source) -> context.deserialize(ServerVariableObject.class, source))
                .ifPresent(serverObject::setVariables);

        return serverObject;
    }
}
