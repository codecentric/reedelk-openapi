package com.reedelk.openapi.v3.deserializer;

import com.reedelk.openapi.commons.AbstractDeserializer;
import com.reedelk.openapi.commons.Properties;
import com.reedelk.openapi.v3.DeserializerContext;
import com.reedelk.openapi.v3.model.Schema;

import java.util.Map;

public class SchemaDeserializer extends AbstractDeserializer<Schema> {

    @Override
    public Schema deserialize(DeserializerContext context, Map<String, Object> serialized) {
        Schema schema = new Schema();
        if (serialized.containsKey(Properties.$REF)) {
            schema.setSchemaId(getString(serialized, Properties.$REF));
        } else {
            schema.setSchemaData(serialized);
        }
        return schema;
    }
}
