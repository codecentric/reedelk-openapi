package com.reedelk.openapi.v3.serializer;

import com.reedelk.openapi.AbstractSerializer;
import com.reedelk.openapi.v3.SerializerContext;
import com.reedelk.openapi.v3.model.ComponentsObject;
import com.reedelk.openapi.v3.model.RequestBodyObject;
import com.reedelk.openapi.v3.model.SchemaObject;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ComponentsObjectSerializer extends AbstractSerializer<ComponentsObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, ComponentsObject input) {
        Map<String, Object> map = new LinkedHashMap<>();

        // Request bodies
        Map<String, RequestBodyObject> requestBodiesMap = input.getRequestBodies();
        Map<String, Object> requestBodies = new HashMap<>();
        requestBodiesMap.forEach((requestBody, requestBodyObject) -> {
            Map<String, Object> serializedRequestBody = context.serialize(requestBodyObject);
            requestBodies.put(requestBody, serializedRequestBody);
        });
        map.put("requestBodies", requestBodies);

        // Schemas
        Map<String, SchemaObject> schemasMap = input.getSchemas();
        Map<String, Object> schemas = new HashMap<>();
        schemasMap.forEach((schemaId, schemaObject) -> {
            Map<String, Object> serializedSchema = context.serialize(schemaObject);
            schemas.put(schemaId, serializedSchema);
        });
        map.put("schemas", schemas);

        return map;
    }
}
