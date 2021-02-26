package de.codecentric.reedelk.openapi.v3.serializer;

import de.codecentric.reedelk.openapi.commons.AbstractSerializer;
import de.codecentric.reedelk.openapi.commons.NavigationPath;
import de.codecentric.reedelk.openapi.v3.SerializerContext;
import de.codecentric.reedelk.openapi.v3.model.HeaderObject;

import java.util.LinkedHashMap;
import java.util.Map;

import static de.codecentric.reedelk.openapi.v3.model.HeaderObject.Properties;

public class HeaderObjectSerializer extends AbstractSerializer<HeaderObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, NavigationPath navigationPath, HeaderObject input) {
        Map<String, Object> map = new LinkedHashMap<>();

        set(map, Properties.DESCRIPTION.value(), input.getDescription());
        if (input.getStyle() != null) set(map, Properties.STYLE.value(), input.getStyle().name());

        if (input.getSchema() != null) {

            NavigationPath currentNavigationPath = navigationPath
                    .with(NavigationPath.SegmentKey.SCHEMA);

            Map<String, Object> serializedSchema = context.serialize(currentNavigationPath, input.getSchema());
            set(map, Properties.SCHEMA.value(), serializedSchema);
        }

        set(map, Properties.EXAMPLE.value(), input.getExample());
        set(map, Properties.EXPLODE.value(), input.getExplode());
        set(map, Properties.DEPRECATED.value(), input.getDeprecated());
        set(map, Properties.ALLOW_RESERVED.value(), input.getAllowReserved());
        return map;
    }
}
