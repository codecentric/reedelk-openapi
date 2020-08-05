package com.reedelk.openapi.v3.serializer;

import com.reedelk.openapi.commons.AbstractSerializer;
import com.reedelk.openapi.commons.NavigationPath;
import com.reedelk.openapi.commons.Precondition;
import com.reedelk.openapi.v3.SerializerContext;
import com.reedelk.openapi.v3.model.ServerObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class ServerObjectSerializer extends AbstractSerializer<ServerObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, NavigationPath navigationPath, ServerObject input) {
        Precondition.checkNotNull(input.getUrl(), "url");

        Map<String, Object> serverObject = new LinkedHashMap<>();
        set(serverObject, "url", input.getUrl());
        set(serverObject, "description", input.getDescription());

        if (input.getVariables() != null) {
            Map<String, Map<String,Object>> serializedServerVariableMap = new LinkedHashMap<>();
            input.getVariables().forEach((variableName, serverVariableObject) -> {
                NavigationPath currentNavigationPath = navigationPath
                        .with(NavigationPath.SegmentKey.VARIABLES)
                        .with(NavigationPath.SegmentKey.VARIABLE_NAME, variableName);
                Map<String, Object> serializedServerVariableObject = context.serialize(currentNavigationPath, serverVariableObject);
                serializedServerVariableMap.put(variableName, serializedServerVariableObject);
            });
            serverObject.put("variables", serializedServerVariableMap);
        }

        return serverObject;
    }
}
