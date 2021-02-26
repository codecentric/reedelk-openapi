package de.codecentric.reedelk.openapi.v3.serializer;

import de.codecentric.reedelk.openapi.commons.AbstractSerializer;
import de.codecentric.reedelk.openapi.commons.NavigationPath;
import de.codecentric.reedelk.openapi.commons.Precondition;
import de.codecentric.reedelk.openapi.v3.SerializerContext;
import de.codecentric.reedelk.openapi.v3.model.LicenseObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class LicenseObjectSerializer extends AbstractSerializer<LicenseObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, NavigationPath navigationPath, LicenseObject input) {
        Precondition.checkNotNull(LicenseObject.Properties.NAME.value(), input.getName());

        Map<String, Object> map = new LinkedHashMap<>();
        set(map, LicenseObject.Properties.NAME.value(), input.getName());
        set(map, LicenseObject.Properties.URL.value(), input.getUrl());
        return map;
    }
}
