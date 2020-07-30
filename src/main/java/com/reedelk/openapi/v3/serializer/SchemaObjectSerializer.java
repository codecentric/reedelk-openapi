package com.reedelk.openapi.v3.serializer;

import com.reedelk.openapi.commons.AbstractSerializer;
import com.reedelk.openapi.v3.SerializerContext;
import com.reedelk.openapi.v3.model.SchemaObject;

import java.util.Map;

public class SchemaObjectSerializer extends AbstractSerializer<SchemaObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, SchemaObject input) {
        return context.serialize(input.getSchema());
    }
}
