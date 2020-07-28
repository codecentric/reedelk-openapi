package com.reedelk.openapi.v3;

import com.reedelk.openapi.OpenApiSerializableAbstract;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchemaObject that = (SchemaObject) o;
        return Objects.equals(schema, that.schema);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schema);
    }

    @Override
    public String toString() {
        return "SchemaObject{" +
                "schema=" + schema +
                '}';
    }
}
