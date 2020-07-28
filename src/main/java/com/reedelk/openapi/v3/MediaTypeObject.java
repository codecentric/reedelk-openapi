package com.reedelk.openapi.v3;

import com.reedelk.openapi.OpenApiSerializableAbstract;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class MediaTypeObject extends OpenApiSerializableAbstract {

    private Example example;
    private Schema schema;

    public Example getExample() {
        return example;
    }

    public void setExample(Example example) {
        this.example = example;
    }

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
        set(map, example);
        return map;
    }

    @Override
    public void deserialize(Map<String, Object> serialized) {
        boolean hasSchema = serialized.containsKey("schema");
        if (hasSchema) {
            schema = new Schema();
            Map<String, Object> schemaDefinition = getMap(serialized, "schema");
            schema.deserialize(schemaDefinition);
        }

        boolean hasExample = serialized.containsKey("example");
        if (hasExample) {
            example = new Example();
            Map<String, Object> exampleDefinition = getMap(serialized, "example");
            example.deserialize(exampleDefinition);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MediaTypeObject that = (MediaTypeObject) o;
        return Objects.equals(example, that.example) &&
                Objects.equals(schema, that.schema);
    }

    @Override
    public int hashCode() {
        return Objects.hash(example, schema);
    }

    @Override
    public String toString() {
        return "MediaTypeObject{" +
                "example=" + example +
                ", schema=" + schema +
                '}';
    }
}
