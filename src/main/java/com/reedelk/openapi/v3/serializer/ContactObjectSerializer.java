package com.reedelk.openapi.v3.serializer;

import com.reedelk.openapi.commons.AbstractSerializer;
import com.reedelk.openapi.commons.NavigationPath;
import com.reedelk.openapi.v3.SerializerContext;
import com.reedelk.openapi.v3.model.ContactObject;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.reedelk.openapi.v3.model.ContactObject.Properties;

public class ContactObjectSerializer extends AbstractSerializer<ContactObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, NavigationPath navigationPath, ContactObject input) {
        Map<String, Object> map = new LinkedHashMap<>();
        set(map, Properties.NAME.value(), input.getName());
        set(map, Properties.URL.value(), input.getUrl());
        set(map, Properties.EMAIL.value(), input.getEmail());
        return map;
    }
}
