package com.reedelk.openapi.v3;

import com.reedelk.openapi.OpenApiSerializableAbstract;
import com.reedelk.openapi.OpenApiSerializableContext;

import java.util.LinkedHashMap;
import java.util.Map;

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

    public void setSchema(Schema schema, OpenApiSerializableContext context) {
        context.setSchema(schema);
        this.schema = schema;
    }

    @Override
    public Map<String,Object> serialize(OpenApiSerializableContext context) {
        Map<String, Object> map = new LinkedHashMap<>();
        set(map, schema, context);
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
}
