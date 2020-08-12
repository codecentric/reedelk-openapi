package com.reedelk.openapi.v3.deserializer;

import com.reedelk.openapi.commons.AbstractDeserializer;
import com.reedelk.openapi.v3.DeserializerContext;
import com.reedelk.openapi.v3.model.ParameterLocation;
import com.reedelk.openapi.v3.model.ParameterObject;
import com.reedelk.openapi.v3.model.ParameterStyle;
import com.reedelk.openapi.v3.model.Schema;

import java.util.Map;

import static com.reedelk.openapi.v3.model.ParameterObject.Properties;

public class ParameterObjectDeserializer extends AbstractDeserializer<ParameterObject> {

    @Override
    public ParameterObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        ParameterObject parameterObject = new ParameterObject();

        parameterObject.setName(getString(serialized, Properties.NAME.value()));
        parameterObject.setDescription(getString(serialized, Properties.DESCRIPTION.value()));

        // Parameter 'in'
        String inValue = getString(serialized, Properties.PARAMETER_LOCATION.value());
        if (inValue != null) {
            parameterObject.setIn(ParameterLocation.valueOf(inValue));
        }

        // Parameter 'style'
        String styleValue = getString(serialized, Properties.STYLE.value());
        if (styleValue != null) {
            parameterObject.setStyle(ParameterStyle.valueOf(styleValue));
        }

        // Parameter schema
        mapApiModel(serialized, Properties.SCHEMA.value(), Schema.class, context)
                .ifPresent(parameterObject::setSchema);

        parameterObject.setExample(getString(serialized, Properties.EXAMPLE.value()));
        parameterObject.setExplode(getBoolean(serialized, Properties.EXPLODE.value()));
        parameterObject.setDeprecated(getBoolean(serialized, Properties.DEPRECATED.value()));
        parameterObject.setRequired(getBoolean(serialized, Properties.REQUIRED.value()));
        parameterObject.setAllowEmptyValue(getBoolean(serialized, Properties.ALLOW_EMPTY_VALUE.value()));
        parameterObject.setAllowReserved(getBoolean(serialized, Properties.ALLOW_RESERVED.value()));
        return parameterObject;
    }
}
