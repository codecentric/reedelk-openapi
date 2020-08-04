package com.reedelk.openapi.v3.serializer;

import com.reedelk.openapi.commons.AbstractSerializer;
import com.reedelk.openapi.commons.NavigationPath;
import com.reedelk.openapi.commons.Precondition;
import com.reedelk.openapi.v3.SerializerContext;
import com.reedelk.openapi.v3.model.LicenseObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class LicenseObjectSerializer extends AbstractSerializer<LicenseObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, NavigationPath navigationPath, LicenseObject input) {
        Precondition.checkNotNull("name", input.getName());

        Map<String, Object> map = new LinkedHashMap<>();
        set(map, "name", input.getName());
        set(map, "url", input.getUrl());
        return map;
    }
}
