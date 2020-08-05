package com.reedelk.openapi.v3.serializer;

import com.reedelk.openapi.commons.AbstractSerializer;
import com.reedelk.openapi.commons.NavigationPath;
import com.reedelk.openapi.v3.SerializerContext;
import com.reedelk.openapi.v3.model.HeaderObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class HeaderObjectSerializer extends AbstractSerializer<HeaderObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, NavigationPath navigationPath, HeaderObject input) {
        Map<String, Object> map = new LinkedHashMap<>();

        set(map, "description", input.getDescription());
        if (input.getStyle() != null) set(map, "style", input.getStyle().name());

        if (input.getSchema() != null) {

            NavigationPath currentNavigationPath = navigationPath
                    .with(NavigationPath.SegmentKey.SCHEMA);

            Map<String, Object> serializedSchema = context.serialize(currentNavigationPath, input.getSchema());
            set(map, "schema", serializedSchema);
        }

        set(map, "example", input.getExample());
        set(map, "explode", input.getExplode());
        set(map, "deprecated", input.getDeprecated());
        set(map, "allowReserved", input.getAllowReserved());
        return map;
    }
}
