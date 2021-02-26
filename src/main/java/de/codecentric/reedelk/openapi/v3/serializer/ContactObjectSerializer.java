package de.codecentric.reedelk.openapi.v3.serializer;

import de.codecentric.reedelk.openapi.commons.AbstractSerializer;
import de.codecentric.reedelk.openapi.commons.NavigationPath;
import de.codecentric.reedelk.openapi.v3.SerializerContext;
import de.codecentric.reedelk.openapi.v3.model.ContactObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class ContactObjectSerializer extends AbstractSerializer<ContactObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, NavigationPath navigationPath, ContactObject input) {
        Map<String, Object> map = new LinkedHashMap<>();
        set(map, ContactObject.Properties.NAME.value(), input.getName());
        set(map, ContactObject.Properties.URL.value(), input.getUrl());
        set(map, ContactObject.Properties.EMAIL.value(), input.getEmail());
        return map;
    }
}
