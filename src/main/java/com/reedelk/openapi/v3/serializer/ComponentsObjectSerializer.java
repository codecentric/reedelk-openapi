package com.reedelk.openapi.v3.serializer;

import com.reedelk.openapi.commons.AbstractSerializer;
import com.reedelk.openapi.commons.NavigationPath;
import com.reedelk.openapi.v3.SerializerContext;
import com.reedelk.openapi.v3.model.ComponentsObject;
import com.reedelk.openapi.v3.model.ExampleObject;
import com.reedelk.openapi.v3.model.RequestBodyObject;
import com.reedelk.openapi.v3.model.SchemaObject;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.reedelk.openapi.v3.model.ComponentsObject.Properties;

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
                        .with(NavigationPath.SegmentKey.SCHEMAS)
                        .with(NavigationPath.SegmentKey.SCHEMA_ID, schemaId);
                Map<String, Object> serializedSchema = context.serialize(currentNavigationPath, schemaObject.getSchema());
                schemas.put(schemaId, serializedSchema);
            });
            map.put(Properties.SCHEMAS.value(), schemas);
        }

        // Request bodies
        Map<String, RequestBodyObject> requestBodiesMap = input.getRequestBodies();
        if (requestBodiesMap != null && !requestBodiesMap.isEmpty()) {
            Map<String, Object> requestBodies = new LinkedHashMap<>();
            requestBodiesMap.forEach((requestBodyId, requestBodyObject) -> {
                NavigationPath currentNavigationPath = navigationPath
                        .with(NavigationPath.SegmentKey.REQUEST_BODIES)
                        .with(NavigationPath.SegmentKey.REQUEST_BODY_ID, requestBodyId);
                Map<String, Object> serializedRequestBody = context.serialize(currentNavigationPath, requestBodyObject);
                requestBodies.put(requestBodyId, serializedRequestBody);
            });
            map.put(Properties.REQUEST_BODIES.value(), requestBodies);
        }

        // Examples
        Map<String, ExampleObject> examplesMap = input.getExamples();
        if (examplesMap != null && !examplesMap.isEmpty()) {
            Map<String, Object> examples = new LinkedHashMap<>();
            examplesMap.forEach((exampleId, exampleObject) -> {
                NavigationPath currentNavigationPath = navigationPath
                        .with(NavigationPath.SegmentKey.EXAMPLES)
                        .with(NavigationPath.SegmentKey.EXAMPLE_ID, exampleId);
                Map<String, Object> serializedExample = context.serialize(currentNavigationPath, exampleObject);
                examples.put(exampleId, serializedExample);
            });
            map.put(Properties.EXAMPLES.value(), examples);
        }

        return map;
    }
}
