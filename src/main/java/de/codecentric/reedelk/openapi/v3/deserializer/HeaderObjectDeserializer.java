package de.codecentric.reedelk.openapi.v3.deserializer;

import de.codecentric.reedelk.openapi.commons.AbstractDeserializer;
import de.codecentric.reedelk.openapi.v3.DeserializerContext;
import de.codecentric.reedelk.openapi.v3.model.HeaderObject;
import de.codecentric.reedelk.openapi.v3.model.ParameterStyle;
import de.codecentric.reedelk.openapi.v3.model.Schema;

import java.util.Map;

public class HeaderObjectDeserializer extends AbstractDeserializer<HeaderObject> {

    @Override
    public HeaderObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        HeaderObject headerObject = new HeaderObject();
        headerObject.setDescription(getString(serialized, HeaderObject.Properties.DESCRIPTION.value()));

        // Header Style
        String styleValue = getString(serialized, HeaderObject.Properties.STYLE.value());
        if (styleValue != null) {
            headerObject.setStyle(ParameterStyle.valueOf(styleValue));
        }

        // Schema
        mapApiModel(serialized, HeaderObject.Properties.SCHEMA.value(), Schema.class, context)
                .ifPresent(headerObject::setSchema);

        headerObject.setExample(getString(serialized, HeaderObject.Properties.EXAMPLE.value()));
        headerObject.setExplode(getBoolean(serialized, HeaderObject.Properties.EXPLODE.value()));
        headerObject.setDeprecated(getBoolean(serialized, HeaderObject.Properties.DEPRECATED.value()));
        headerObject.setAllowReserved(getBoolean(serialized, HeaderObject.Properties.ALLOW_RESERVED.value()));
        return headerObject;
    }
}
