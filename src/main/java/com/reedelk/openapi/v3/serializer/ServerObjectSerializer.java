package com.reedelk.openapi.v3.serializer;

import com.reedelk.openapi.AbstractSerializer;
import com.reedelk.openapi.Precondition;
import com.reedelk.openapi.v3.SerializerContext;
import com.reedelk.openapi.v3.model.ServerObject;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ServerObjectSerializer extends AbstractSerializer<ServerObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, ServerObject input) {
        Precondition.checkNotNull(input.getUrl(), "url");

        Map<String, Object> serverObject = new LinkedHashMap<>();
        set(serverObject, "url", input.getUrl());
        set(serverObject, "description", input.getDescription());

        if (input.getVariables() != null) {
            Map<String, Map<String,Object>> serializedServerVariableMap = new HashMap<>();
            input.getVariables().forEach((variableName, serverVariableObject) -> {
                Map<String, Object> serializedServerVariableObject = context.serialize(serverVariableObject);
                serializedServerVariableMap.put(variableName, serializedServerVariableObject);
            });
            serverObject.put("variables", serializedServerVariableMap);
        }

        return serverObject;
    }
}
