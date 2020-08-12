package com.reedelk.openapi.v3.deserializer;

import com.reedelk.openapi.commons.AbstractDeserializer;
import com.reedelk.openapi.v3.DeserializerContext;
import com.reedelk.openapi.v3.model.HeaderObject;
import com.reedelk.openapi.v3.model.ParameterStyle;
import com.reedelk.openapi.v3.model.Schema;

import java.util.Map;

import static com.reedelk.openapi.v3.model.HeaderObject.Properties;

public class HeaderObjectDeserializer extends AbstractDeserializer<HeaderObject> {

    @Override
    public HeaderObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        HeaderObject headerObject = new HeaderObject();
        headerObject.setDescription(getString(serialized, Properties.DESCRIPTION.value()));

        // Header Style
        String styleValue = getString(serialized, Properties.STYLE.value());
        if (styleValue != null) {
            headerObject.setStyle(ParameterStyle.valueOf(styleValue));
        }

        // Schema
        mapApiModel(serialized, Properties.SCHEMA.value(), Schema.class, context)
                .ifPresent(headerObject::setSchema);

        headerObject.setExample(getString(serialized, Properties.EXAMPLE.value()));
        headerObject.setExplode(getBoolean(serialized, Properties.EXPLODE.value()));
        headerObject.setDeprecated(getBoolean(serialized, Properties.DEPRECATED.value()));
        headerObject.setAllowReserved(getBoolean(serialized, Properties.ALLOW_RESERVED.value()));
        return headerObject;
    }
}
