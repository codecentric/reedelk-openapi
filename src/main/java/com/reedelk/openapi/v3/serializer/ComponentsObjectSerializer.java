package com.reedelk.openapi.v3.serializer;

import com.reedelk.openapi.commons.AbstractSerializer;
import com.reedelk.openapi.commons.NavigationPath;
import com.reedelk.openapi.v3.SerializerContext;
import com.reedelk.openapi.v3.model.ComponentsObject;
import com.reedelk.openapi.v3.model.RequestBodyObject;
import com.reedelk.openapi.v3.model.SchemaObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class ComponentsObjectSerializer extends AbstractSerializer<ComponentsObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, NavigationPath navigationPath, ComponentsObject input) {
        Map<String, Object> map = new LinkedHashMap<>();

        // Schemas
        Map<String, SchemaObject> schemasMap = input.getSchemas();
        if (schemasMap != null && !schemasMap.isEmpty()) {
            Map<String, Object> schemas = new LinkedHashMap<>();
            schemasMap.forEach((schemaId, schemaObject) -> {
                NavigationPath currentNavigationPath = navigationPath
                        .with("schemas")
                        .with("schemaId", schemaId);
                Map<String, Object> serializedSchema = context.serialize(currentNavigationPath, schemaObject.getSchema());
                schemas.put(schemaId, serializedSchema);
            });
            map.put("schemas", schemas);
        }

        // Request bodies
        Map<String, RequestBodyObject> requestBodiesMap = input.getRequestBodies();
        if (requestBodiesMap != null && !requestBodiesMap.isEmpty()) {
            Map<String, Object> requestBodies = new LinkedHashMap<>();
            requestBodiesMap.forEach((requestBodyId, requestBodyObject) -> {
                NavigationPath currentNavigationPath = navigationPath
                        .with("requestBodies")
                        .with("requestBodyId", requestBodyId);
                Map<String, Object> serializedRequestBody = context.serialize(currentNavigationPath, requestBodyObject);
                requestBodies.put(requestBodyId, serializedRequestBody);
            });
            map.put("requestBodies", requestBodies);
        }

        return map;
    }
}
