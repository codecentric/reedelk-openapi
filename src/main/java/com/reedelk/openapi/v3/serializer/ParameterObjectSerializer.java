package com.reedelk.openapi.v3.serializer;

import com.reedelk.openapi.AbstractSerializer;
import com.reedelk.openapi.Precondition;
import com.reedelk.openapi.v3.SerializerContext;
import com.reedelk.openapi.v3.model.ParameterLocation;
import com.reedelk.openapi.v3.model.ParameterObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class ParameterObjectSerializer extends AbstractSerializer<ParameterObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, ParameterObject input) {
        Precondition.checkNotNull("name", input.getName());
        Precondition.checkNotNull("in", input.getIn());

        if (!ParameterLocation.path.equals(input.getIn())) {
            Precondition.checkNotNull("required", input.getRequired());
        }

        Map<String, Object> map = new LinkedHashMap<>();
        set(map, "name", input.getName());
        set(map, "description", input.getDescription());
        set(map, "in", input.getIn().name().toLowerCase());
        if (input.getStyle() != null) set(map, "style", input.getStyle().name().toLowerCase());

        map.put("example", input.getExample());

        set(map, "explode", input.getExplode());
        set(map, "deprecated", input.getDeprecated());

        if (input.getSchema() != null) {
            Map<String, Object> serializedSchema = context.serialize(input.getSchema());
            set(map, "schema", serializedSchema);
        }

        // By OpenAPI specification, if the parameter location is "path", this property is REQUIRED
        // and its value MUST be true. Otherwise, the property MAY be included and its default value is false.
        if (ParameterLocation.path.equals(input.getIn())) {
            set(map, "required", Boolean.TRUE);
        } else {
            set(map, "required", input.getRequired());
        }
        set(map, "allowEmptyValue", input.getAllowEmptyValue());
        set(map, "allowReserved", input.getAllowReserved());
        return map;
    }
}
