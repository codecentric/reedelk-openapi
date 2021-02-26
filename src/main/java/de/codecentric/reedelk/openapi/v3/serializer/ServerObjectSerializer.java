package de.codecentric.reedelk.openapi.v3.serializer;

import de.codecentric.reedelk.openapi.commons.AbstractSerializer;
import de.codecentric.reedelk.openapi.commons.NavigationPath;
import de.codecentric.reedelk.openapi.commons.Precondition;
import de.codecentric.reedelk.openapi.v3.SerializerContext;
import de.codecentric.reedelk.openapi.v3.model.ServerObject;

import java.util.LinkedHashMap;
import java.util.Map;

import static de.codecentric.reedelk.openapi.v3.model.ServerObject.Properties;

public class ServerObjectSerializer extends AbstractSerializer<ServerObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, NavigationPath navigationPath, ServerObject input) {
        Precondition.checkNotNull(input.getUrl(), Properties.URL.value());

        Map<String, Object> serverObject = new LinkedHashMap<>();
        set(serverObject, Properties.URL.value(), input.getUrl());
        set(serverObject, Properties.DESCRIPTION.value(), input.getDescription());

        if (input.getVariables() != null) {
            Map<String, Map<String,Object>> serializedServerVariableMap = new LinkedHashMap<>();
            input.getVariables().forEach((variableName, serverVariableObject) -> {
                NavigationPath currentNavigationPath = navigationPath
                        .with(NavigationPath.SegmentKey.VARIABLES)
                        .with(NavigationPath.SegmentKey.VARIABLE_NAME, variableName);
                Map<String, Object> serializedServerVariableObject = context.serialize(currentNavigationPath, serverVariableObject);
                serializedServerVariableMap.put(variableName, serializedServerVariableObject);
            });
            serverObject.put(Properties.VARIABLES.value(), serializedServerVariableMap);
        }

        return serverObject;
    }
}
