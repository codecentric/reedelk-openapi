package de.codecentric.reedelk.openapi.v3.deserializer;

import de.codecentric.reedelk.openapi.commons.AbstractDeserializer;
import de.codecentric.reedelk.openapi.v3.DeserializerContext;
import de.codecentric.reedelk.openapi.v3.model.ServerVariableObject;

import java.util.List;
import java.util.Map;

public class ServerVariableObjectDeserializer extends AbstractDeserializer<ServerVariableObject> {

    @SuppressWarnings("unchecked")
    @Override
    public ServerVariableObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        ServerVariableObject serverVariableObject = new ServerVariableObject();
        serverVariableObject.setDescription(getString(serialized, ServerVariableObject.Properties.DESCRIPTION.value()));
        serverVariableObject.setDefaultValue(getString(serialized, ServerVariableObject.Properties.DEFAULT_VALUE.value()));

        List<String> enumValues = (List<String>) serialized.get(ServerVariableObject.Properties.ENUM_VALUES.value());
        serverVariableObject.setEnumValues(enumValues);
        return serverVariableObject;
    }
}
