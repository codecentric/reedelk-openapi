package de.codecentric.reedelk.openapi.v3.deserializer;

import de.codecentric.reedelk.openapi.commons.AbstractDeserializer;
import de.codecentric.reedelk.openapi.v3.DeserializerContext;
import de.codecentric.reedelk.openapi.v3.model.ParameterLocation;
import de.codecentric.reedelk.openapi.v3.model.ParameterObject;
import de.codecentric.reedelk.openapi.v3.model.ParameterStyle;
import de.codecentric.reedelk.openapi.v3.model.Schema;

import java.util.Map;

public class ParameterObjectDeserializer extends AbstractDeserializer<ParameterObject> {

    @Override
    public ParameterObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        ParameterObject parameterObject = new ParameterObject();

        parameterObject.setName(getString(serialized, ParameterObject.Properties.NAME.value()));
        parameterObject.setDescription(getString(serialized, ParameterObject.Properties.DESCRIPTION.value()));

        // Parameter 'in'
        String inValue = getString(serialized, ParameterObject.Properties.PARAMETER_LOCATION.value());
        if (inValue != null) {
            parameterObject.setIn(ParameterLocation.valueOf(inValue));
        }

        // Parameter 'style'
        String styleValue = getString(serialized, ParameterObject.Properties.STYLE.value());
        if (styleValue != null) {
            parameterObject.setStyle(ParameterStyle.valueOf(styleValue));
        }

        // Parameter schema
        mapApiModel(serialized, ParameterObject.Properties.SCHEMA.value(), Schema.class, context)
                .ifPresent(parameterObject::setSchema);

        parameterObject.setExample(getString(serialized, ParameterObject.Properties.EXAMPLE.value()));
        parameterObject.setExplode(getBoolean(serialized, ParameterObject.Properties.EXPLODE.value()));
        parameterObject.setDeprecated(getBoolean(serialized, ParameterObject.Properties.DEPRECATED.value()));
        parameterObject.setRequired(getBoolean(serialized, ParameterObject.Properties.REQUIRED.value()));
        parameterObject.setAllowEmptyValue(getBoolean(serialized, ParameterObject.Properties.ALLOW_EMPTY_VALUE.value()));
        parameterObject.setAllowReserved(getBoolean(serialized, ParameterObject.Properties.ALLOW_RESERVED.value()));
        return parameterObject;
    }
}
