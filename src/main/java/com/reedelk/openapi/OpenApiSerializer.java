package com.reedelk.openapi;

import com.reedelk.openapi.commons.JsonObjectFactory;
import com.reedelk.openapi.v3.SerializerContext;
import com.reedelk.openapi.v3.serializer.Serializers;
import org.json.JSONObject;
import org.yaml.snakeyaml.Yaml;

import java.util.Map;

class OpenApiSerializer {

    private static final int JSON_INDENT_FACTOR = 2;

    private final Serializers serializers = new Serializers();

    /**
     * Serializes the open API object map to JSON.
     */
    String toJson(OpenApiModel serializable) {
        SerializerContext context =  new SerializerContext(serializers);
        Map<String, Object> serialized = context.serialize(serializable);
        // We use the custom object factory to preserve position
        // of serialized properties in the map.
        JSONObject jsonObject = (JSONObject) process(serialized);
        return jsonObject.toString(JSON_INDENT_FACTOR);
    }

    /**
     * Serializes the open API object map to YAML.
     */
    String toYaml(OpenApiModel serializable) {
        SerializerContext context =  new SerializerContext(serializers);
        Map<String, Object> serialized = context.serialize(serializable);
        Yaml yaml = new Yaml();
        return yaml.dump(serialized);
    }

    @SuppressWarnings("unchecked")
    private Object process(Object value) {
        if (value instanceof Map) {
            JSONObject nested = JsonObjectFactory.newJSONObject();
            Map<String,Object> nestedMap = (Map<String, Object>) value;
            nestedMap.forEach((key, theValue) -> nested.put(key, process(theValue)));
            return nested;
        } else {
            return value;
        }
    }
}
