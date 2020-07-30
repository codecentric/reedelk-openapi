package com.reedelk.openapi.v3.deserializer;

import com.reedelk.openapi.AbstractDeserializer;
import com.reedelk.openapi.v3.DeserializerContext;
import com.reedelk.openapi.v3.model.Schema;
import com.reedelk.openapi.v3.model.SchemaObject;

import java.util.Map;
import java.util.function.BiConsumer;

public class SchemaObjectDeserializer extends AbstractDeserializer<SchemaObject> {

    @Override
    public SchemaObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        SchemaObject schemaObject = new SchemaObject();
        Schema schema = context.deserialize(Schema.class, serialized);
        String schemaId = schemaObject.getSchemaId();
        schemaObject.setSchemaId(schemaId);
        serialized.forEach(new BiConsumer<String, Object>() {
            @Override
            public void accept(String schemaId, Object schemaData) {

            }
        });

        // TODO: This one?
        return schemaObject;
    }
}
