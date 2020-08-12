package com.reedelk.openapi.v3.deserializer;

import com.reedelk.openapi.commons.AbstractDeserializer;
import com.reedelk.openapi.v3.DeserializerContext;
import com.reedelk.openapi.v3.model.Schema;

import java.util.Map;

public class SchemaDeserializer extends AbstractDeserializer<Schema> {

    @Override
    public Schema deserialize(DeserializerContext context, Map<String, Object> serialized) {
        Schema schema = new Schema();
        if (serialized.containsKey(Schema.Properties.$REF.value())) {
            schema.setSchemaId(getString(serialized, Schema.Properties.$REF.value()));
        } else {
            schema.setSchemaData(serialized);
        }
        return schema;
    }
}
