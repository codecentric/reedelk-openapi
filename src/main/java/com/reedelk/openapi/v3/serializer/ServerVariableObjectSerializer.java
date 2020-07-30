package com.reedelk.openapi.v3.serializer;

import com.reedelk.openapi.commons.AbstractSerializer;
import com.reedelk.openapi.commons.Precondition;
import com.reedelk.openapi.v3.SerializerContext;
import com.reedelk.openapi.v3.model.ServerVariableObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class ServerVariableObjectSerializer extends AbstractSerializer<ServerVariableObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, ServerVariableObject input) {
        Precondition.checkNotNull("default", input.getDefaultValue());

        Map<String, Object> map = new LinkedHashMap<>();
        set(map, "default", input.getDefaultValue());
        set(map, "description", input.getDescription());
        setList(map, "enum", input.getEnumValues());
        return map;
    }
}
