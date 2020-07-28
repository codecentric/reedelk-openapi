package com.reedelk.openapi.v3;

import com.reedelk.openapi.OpenApiSerializableAbstract;

import java.util.LinkedHashMap;
import java.util.Map;

public class SchemaObject extends OpenApiSerializableAbstract {

    private Schema schema;

    public Schema getSchema() {
        return schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }

    @Override
    public Map<String,Object> serialize() {
        Map<String, Object> map = new LinkedHashMap<>();
        set(map, schema);
        return map;
    }

    @Override
    public void deserialize(Map<String, Object> serialized) {
        schema = new Schema();
        schema.deserialize(serialized);
    }
}
