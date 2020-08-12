package com.reedelk.openapi.v3.serializer;

import com.reedelk.openapi.commons.AbstractSerializer;
import com.reedelk.openapi.commons.NavigationPath;
import com.reedelk.openapi.commons.Precondition;
import com.reedelk.openapi.v3.SerializerContext;
import com.reedelk.openapi.v3.model.ServerVariableObject;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.reedelk.openapi.v3.model.ServerVariableObject.Properties;

public class ServerVariableObjectSerializer extends AbstractSerializer<ServerVariableObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, NavigationPath navigationPath, ServerVariableObject input) {
        Precondition.checkNotNull(Properties.DEFAULT_VALUE.value(), input.getDefaultValue());

        Map<String, Object> map = new LinkedHashMap<>();
        setList(map, Properties.ENUM_VALUES.value(), input.getEnumValues());
        set(map, Properties.DEFAULT_VALUE.value(), input.getDefaultValue());
        set(map, Properties.DESCRIPTION.value(), input.getDescription());
        return map;
    }
}
