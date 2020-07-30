package com.reedelk.openapi.v3.deserializer;

import com.reedelk.openapi.commons.AbstractDeserializer;
import com.reedelk.openapi.v3.DeserializerContext;
import com.reedelk.openapi.v3.model.ServerObject;
import com.reedelk.openapi.v3.model.ServerVariableObject;

import java.util.HashMap;
import java.util.Map;

public class ServerObjectDeserializer extends AbstractDeserializer<ServerObject> {

    @SuppressWarnings("unchecked")
    @Override
    public ServerObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        ServerObject serverObject = new ServerObject();
        serverObject.setUrl(getString(serialized, "url"));
        serverObject.setDescription(getString(serialized, "description"));

        if (serialized.containsKey("variables")) {
            Map<String, ServerVariableObject> serverVariableObjectMap = new HashMap<>();
            Map<String, Map<String, Object>> variablesMap = (Map<String, Map<String, Object>>) serialized.get("variables");
            variablesMap.forEach((serverVariableKey, objectMap) -> {
                ServerVariableObject serverVariableObject = context.deserialize(ServerVariableObject.class, objectMap);
                serverVariableObjectMap.put(serverVariableKey, serverVariableObject);
            });
            serverObject.setVariables(serverVariableObjectMap);
        }

        return serverObject;
    }
}
