package com.reedelk.openapi.v3.serializer;

import com.reedelk.openapi.commons.AbstractSerializer;
import com.reedelk.openapi.commons.NavigationPath;
import com.reedelk.openapi.commons.Precondition;
import com.reedelk.openapi.v3.SerializerContext;
import com.reedelk.openapi.v3.model.ParameterLocation;
import com.reedelk.openapi.v3.model.ParameterObject;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.reedelk.openapi.v3.model.ParameterObject.Properties;

public class ParameterObjectSerializer extends AbstractSerializer<ParameterObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, NavigationPath navigationPath, ParameterObject input) {
        Precondition.checkNotNull(Properties.NAME.value(), input.getName());
        Precondition.checkNotNull(Properties.PARAMETER_LOCATION.value(), input.getIn());

        if (ParameterLocation.path.equals(input.getIn())) {
            Precondition.checkNotNull(Properties.REQUIRED.value(), input.getRequired());
            Precondition.checkTrue(Properties.REQUIRED.value(), input.getRequired());
        }

        Map<String, Object> map = new LinkedHashMap<>();
        set(map, Properties.NAME.value(), input.getName());
        set(map, Properties.DESCRIPTION.value(), input.getDescription());
        set(map, Properties.PARAMETER_LOCATION.value(), input.getIn().name().toLowerCase());

        if (input.getStyle() != null) {
            set(map, Properties.STYLE.value(), input.getStyle().name().toLowerCase());
        }

        if (input.getExample() != null) {
            map.put(Properties.EXAMPLE.value(), input.getExample());
        }

        set(map, Properties.EXPLODE.value(), input.getExplode());
        set(map, Properties.DEPRECATED.value(), input.getDeprecated());

        if (input.getSchema() != null) {
            NavigationPath currentNavigationPath = navigationPath.with(NavigationPath.SegmentKey.SCHEMA);
            Map<String, Object> serializedSchema = context.serialize(currentNavigationPath, input.getSchema());
            set(map, Properties.SCHEMA.value(), serializedSchema);
        }

        // By OpenAPI specification, if the parameter location is "path", this property is REQUIRED
        // and its value MUST be true. Otherwise, the property MAY be included and its default value is false.
        if (ParameterLocation.path.equals(input.getIn())) {
            set(map, Properties.REQUIRED.value(), Boolean.TRUE);
        } else {
            set(map, Properties.REQUIRED.value(), input.getRequired());
        }
        set(map, Properties.ALLOW_EMPTY_VALUE.value(), input.getAllowEmptyValue());
        set(map, Properties.ALLOW_RESERVED.value(), input.getAllowReserved());
        return map;
    }
}
