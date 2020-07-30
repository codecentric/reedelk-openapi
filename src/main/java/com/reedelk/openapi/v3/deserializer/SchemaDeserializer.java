package com.reedelk.openapi.v3.deserializer;

import com.reedelk.openapi.commons.AbstractDeserializer;
import com.reedelk.openapi.v3.DeserializerContext;
import com.reedelk.openapi.v3.model.Schema;

import java.util.Map;

public class SchemaDeserializer extends AbstractDeserializer<Schema> {

    private static final String JSON_PROPERTY_REF = "$ref";

    @Override
    public Schema deserialize(DeserializerContext context, Map<String, Object> serialized) {
        Schema schema = new Schema();
        if (serialized.containsKey(JSON_PROPERTY_REF)) {
            schema.setSchemaId(getString(serialized, JSON_PROPERTY_REF));
        } else {
            schema.setSchemaData(serialized);
        }
        return schema;
    }
}
