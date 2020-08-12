package com.reedelk.openapi.v3.serializer;

import com.reedelk.openapi.commons.AbstractSerializer;
import com.reedelk.openapi.commons.NavigationPath;
import com.reedelk.openapi.commons.Precondition;
import com.reedelk.openapi.v3.SerializerContext;
import com.reedelk.openapi.v3.model.LicenseObject;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.reedelk.openapi.v3.model.LicenseObject.Properties;

public class LicenseObjectSerializer extends AbstractSerializer<LicenseObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, NavigationPath navigationPath, LicenseObject input) {
        Precondition.checkNotNull(Properties.NAME.value(), input.getName());

        Map<String, Object> map = new LinkedHashMap<>();
        set(map, Properties.NAME.value(), input.getName());
        set(map, Properties.URL.value(), input.getUrl());
        return map;
    }
}
