package com.reedelk.openapi.v3.deserializer;

import com.reedelk.openapi.commons.AbstractDeserializer;
import com.reedelk.openapi.v3.DeserializerContext;
import com.reedelk.openapi.v3.model.ServerVariableObject;

import java.util.List;
import java.util.Map;

import static com.reedelk.openapi.v3.model.ServerVariableObject.Properties;

public class ServerVariableObjectDeserializer extends AbstractDeserializer<ServerVariableObject> {

    @SuppressWarnings("unchecked")
    @Override
    public ServerVariableObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        ServerVariableObject serverVariableObject = new ServerVariableObject();
        serverVariableObject.setDescription(getString(serialized, Properties.DESCRIPTION.value()));
        serverVariableObject.setDefaultValue(getString(serialized, Properties.DEFAULT_VALUE.value()));

        List<String> enumValues = (List<String>) serialized.get(Properties.ENUM_VALUES.value());
        serverVariableObject.setEnumValues(enumValues);
        return serverVariableObject;
    }
}
