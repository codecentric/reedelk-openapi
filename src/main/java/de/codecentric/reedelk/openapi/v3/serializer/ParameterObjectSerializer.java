package de.codecentric.reedelk.openapi.v3.serializer;

import de.codecentric.reedelk.openapi.commons.AbstractSerializer;
import de.codecentric.reedelk.openapi.commons.NavigationPath;
import de.codecentric.reedelk.openapi.commons.Precondition;
import de.codecentric.reedelk.openapi.v3.SerializerContext;
import de.codecentric.reedelk.openapi.v3.model.ParameterLocation;
import de.codecentric.reedelk.openapi.v3.model.ParameterObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class ParameterObjectSerializer extends AbstractSerializer<ParameterObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, NavigationPath navigationPath, ParameterObject input) {
        Precondition.checkNotNull(ParameterObject.Properties.NAME.value(), input.getName());
        Precondition.checkNotNull(ParameterObject.Properties.PARAMETER_LOCATION.value(), input.getIn());

        if (ParameterLocation.path.equals(input.getIn())) {
            Precondition.checkNotNull(ParameterObject.Properties.REQUIRED.value(), input.getRequired());
            Precondition.checkTrue(ParameterObject.Properties.REQUIRED.value(), input.getRequired());
        }

        Map<String, Object> map = new LinkedHashMap<>();
        set(map, ParameterObject.Properties.NAME.value(), input.getName());
        set(map, ParameterObject.Properties.DESCRIPTION.value(), input.getDescription());
        set(map, ParameterObject.Properties.PARAMETER_LOCATION.value(), input.getIn().name().toLowerCase());

        if (input.getStyle() != null) {
            set(map, ParameterObject.Properties.STYLE.value(), input.getStyle().name().toLowerCase());
        }

        if (input.getExample() != null) {
            map.put(ParameterObject.Properties.EXAMPLE.value(), input.getExample());
        }

        set(map, ParameterObject.Properties.EXPLODE.value(), input.getExplode());
        set(map, ParameterObject.Properties.DEPRECATED.value(), input.getDeprecated());

        if (input.getSchema() != null) {
            NavigationPath currentNavigationPath = navigationPath.with(NavigationPath.SegmentKey.SCHEMA);
            Map<String, Object> serializedSchema = context.serialize(currentNavigationPath, input.getSchema());
            set(map, ParameterObject.Properties.SCHEMA.value(), serializedSchema);
        }

        // By OpenAPI specification, if the parameter location is "path", this property is REQUIRED
        // and its value MUST be true. Otherwise, the property MAY be included and its default value is false.
        if (ParameterLocation.path.equals(input.getIn())) {
            set(map, ParameterObject.Properties.REQUIRED.value(), Boolean.TRUE);
        } else {
            set(map, ParameterObject.Properties.REQUIRED.value(), input.getRequired());
        }
        set(map, ParameterObject.Properties.ALLOW_EMPTY_VALUE.value(), input.getAllowEmptyValue());
        set(map, ParameterObject.Properties.ALLOW_RESERVED.value(), input.getAllowReserved());
        return map;
    }
}
