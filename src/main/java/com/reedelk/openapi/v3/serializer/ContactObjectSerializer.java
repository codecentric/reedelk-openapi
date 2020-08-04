package com.reedelk.openapi.v3.serializer;

import com.reedelk.openapi.commons.AbstractSerializer;
import com.reedelk.openapi.commons.NavigationPath;
import com.reedelk.openapi.v3.SerializerContext;
import com.reedelk.openapi.v3.model.ContactObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class ContactObjectSerializer extends AbstractSerializer<ContactObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, NavigationPath navigationPath, ContactObject input) {
        Map<String, Object> map = new LinkedHashMap<>();
        set(map, "name", input.getName());
        set(map, "url", input.getUrl());
        set(map, "email", input.getEmail());
        return map;
    }
}
