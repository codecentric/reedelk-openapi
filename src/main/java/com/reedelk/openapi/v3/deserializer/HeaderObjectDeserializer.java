package com.reedelk.openapi.v3.deserializer;

import com.reedelk.openapi.commons.AbstractDeserializer;
import com.reedelk.openapi.v3.DeserializerContext;
import com.reedelk.openapi.v3.model.HeaderObject;
import com.reedelk.openapi.v3.model.ParameterStyle;
import com.reedelk.openapi.v3.model.Schema;

import java.util.Map;

public class HeaderObjectDeserializer extends AbstractDeserializer<HeaderObject> {

    @SuppressWarnings("unchecked")
    @Override
    public HeaderObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        HeaderObject headerObject = new HeaderObject();
        headerObject.setDescription(getString(serialized, "description"));

        // Header Style
        String styleValue = getString(serialized, "style");
        if (styleValue != null) {
            headerObject.setStyle(ParameterStyle.valueOf(styleValue));
        }

        // Schema
        if (serialized.containsKey("schema")) {
            Map<String, Object> schemaMap = (Map<String, Object>) serialized.get("schema");
            Schema schema = context.deserialize(Schema.class, schemaMap);
            headerObject.setSchema(schema);
        }

        headerObject.setExample(getString(serialized, "example"));
        headerObject.setExplode(getBoolean(serialized, "explode"));
        headerObject.setDeprecated(getBoolean(serialized, "deprecated"));
        headerObject.setAllowReserved(getBoolean(serialized, "allowReserved"));
        return headerObject;
    }
}
