package com.reedelk.openapi.v3;

import com.reedelk.openapi.OpenApiSerializableAbstract;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ComponentsObject extends OpenApiSerializableAbstract {

    private Map<String, SchemaObject> schemas = new HashMap<>();

    public Map<String, SchemaObject> getSchemas() {
        return schemas;
    }

    public void setSchemas(Map<String, SchemaObject> schemas) {
        this.schemas = schemas;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new LinkedHashMap<>();
        setMapSerializable(map,"schemas", schemas);
        return map;
    }

    @Override
    public void deserialize(Map<String, Object> serialized) {

    }
}
