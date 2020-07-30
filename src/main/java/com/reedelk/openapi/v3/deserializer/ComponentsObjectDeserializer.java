package com.reedelk.openapi.v3.deserializer;

import com.reedelk.openapi.commons.AbstractDeserializer;
import com.reedelk.openapi.v3.DeserializerContext;
import com.reedelk.openapi.v3.model.ComponentsObject;
import com.reedelk.openapi.v3.model.RequestBodyObject;
import com.reedelk.openapi.v3.model.Schema;
import com.reedelk.openapi.v3.model.SchemaObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class ComponentsObjectDeserializer extends AbstractDeserializer<ComponentsObject> {

    @SuppressWarnings("unchecked")
    @Override
    public ComponentsObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        ComponentsObject componentsObject = new ComponentsObject();

        // Request Bodies
        if (serialized.containsKey("requestBodies")) {
            Map<String, RequestBodyObject> requestBodyObjectMap = new LinkedHashMap<>();
            Map<String,Object> requestBodiesMap = (Map<String, Object>) serialized.get("requestBodies");
            requestBodiesMap.forEach((requestBodyId, requestBodyDataMap) -> {
                Map<String, Object> requestBodyMap = (Map<String, Object>) requestBodyDataMap;
                RequestBodyObject requestBodyObject = context.deserialize(RequestBodyObject.class, requestBodyMap);
                requestBodyObjectMap.put(requestBodyId, requestBodyObject);
            });
            componentsObject.setRequestBodies(requestBodyObjectMap);
        }

        // Schemas
        if (serialized.containsKey("schemas")) {
            Map<String, SchemaObject> schemaObjectMap = new LinkedHashMap<>();
            Map<String,Object> schemasMap = (Map<String, Object>) serialized.get("schemas");
            schemasMap.forEach((schemaId, schemaData) -> {
                Map<String, Object> schemaMap = (Map<String, Object>) schemaData;
                Schema schemaObject = context.deserialize(Schema.class, schemaMap);
                SchemaObject schemaObjectObject = new SchemaObject();
                schemaObjectObject.setSchemaId(schemaId);
                schemaObjectObject.setSchema(schemaObject);
                schemaObjectMap.put(schemaId, schemaObjectObject);
            });
            componentsObject.setSchemas(schemaObjectMap);
        }

        return componentsObject;
    }
}
