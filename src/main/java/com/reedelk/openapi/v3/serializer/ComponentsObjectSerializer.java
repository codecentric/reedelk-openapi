package com.reedelk.openapi.v3.serializer;

import com.reedelk.openapi.commons.AbstractSerializer;
import com.reedelk.openapi.commons.NavigationPath;
import com.reedelk.openapi.v3.SerializerContext;
import com.reedelk.openapi.v3.model.*;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.reedelk.openapi.v3.model.ComponentsObject.Properties.*;

public class ComponentsObjectSerializer extends AbstractSerializer<ComponentsObject> {

    @Override
    public Map<String, Object> serialize(SerializerContext context, NavigationPath navigationPath, ComponentsObject input) {
        Map<String, Object> map = new LinkedHashMap<>();

        // Schemas
        Map<String, SchemaObject> schemasMap = input.getSchemas();
        serializeSchemas(context, navigationPath, map, schemasMap);

        // Request bodies
        Map<String, RequestBodyObject> requestBodiesMap = input.getRequestBodies();
        serializeRequestBodies(context, navigationPath, map, requestBodiesMap);

        // Examples
        Map<String, ExampleObject> examplesMap = input.getExamples();
        serializeExamples(context, navigationPath, map, examplesMap);

        // Security Schemes
        Map<String, SecuritySchemeObject> securitySchemesMap = input.getSecuritySchemes();
        serializeSecuritySchemas(context, navigationPath, map, securitySchemesMap);

        return map;
    }

    protected void serializeSecuritySchemas(SerializerContext context, NavigationPath navigationPath, Map<String, Object> map, Map<String, SecuritySchemeObject> securitySchemesMap) {
        if (securitySchemesMap != null && !securitySchemesMap.isEmpty()) {
            Map<String, Object> securitySchemes = new LinkedHashMap<>();
            securitySchemesMap.forEach((securitySchemeId, securitySchemeObject) -> {
                NavigationPath currentNavigationPath = navigationPath
                        .with(NavigationPath.SegmentKey.SECURITY_SCHEMES)
                        .with(NavigationPath.SegmentKey.SECURITY_SCHEME_ID, securitySchemeId);
                Map<String, Object> serializedSecurityScheme = context.serialize(currentNavigationPath, securitySchemeObject);
                securitySchemes.put(securitySchemeId, serializedSecurityScheme);
            });
            map.put(SECURITY_SCHEMES.value(), securitySchemes);
        }
    }

    protected void serializeExamples(SerializerContext context, NavigationPath navigationPath, Map<String, Object> map, Map<String, ExampleObject> examplesMap) {
        if (examplesMap != null && !examplesMap.isEmpty()) {
            Map<String, Object> examples = new LinkedHashMap<>();
            examplesMap.forEach((exampleId, exampleObject) -> {
                NavigationPath currentNavigationPath = navigationPath
                        .with(NavigationPath.SegmentKey.EXAMPLES)
                        .with(NavigationPath.SegmentKey.EXAMPLE_ID, exampleId);
                Map<String, Object> serializedExample = context.serialize(currentNavigationPath, exampleObject);
                examples.put(exampleId, serializedExample);
            });
            map.put(EXAMPLES.value(), examples);
        }
    }

    protected void serializeRequestBodies(SerializerContext context, NavigationPath navigationPath, Map<String, Object> map, Map<String, RequestBodyObject> requestBodiesMap) {
        if (requestBodiesMap != null && !requestBodiesMap.isEmpty()) {
            Map<String, Object> requestBodies = new LinkedHashMap<>();
            requestBodiesMap.forEach((requestBodyId, requestBodyObject) -> {
                NavigationPath currentNavigationPath = navigationPath
                        .with(NavigationPath.SegmentKey.REQUEST_BODIES)
                        .with(NavigationPath.SegmentKey.REQUEST_BODY_ID, requestBodyId);
                Map<String, Object> serializedRequestBody = context.serialize(currentNavigationPath, requestBodyObject);
                requestBodies.put(requestBodyId, serializedRequestBody);
            });
            map.put(REQUEST_BODIES.value(), requestBodies);
        }
    }

    protected void serializeSchemas(SerializerContext context, NavigationPath navigationPath, Map<String, Object> map, Map<String, SchemaObject> schemasMap) {
        if (schemasMap != null && !schemasMap.isEmpty()) {
            Map<String, Object> schemas = new LinkedHashMap<>();
            schemasMap.forEach((schemaId, schemaObject) -> {
                NavigationPath currentNavigationPath = navigationPath
                        .with(NavigationPath.SegmentKey.SCHEMAS)
                        .with(NavigationPath.SegmentKey.SCHEMA_ID, schemaId);
                Map<String, Object> serializedSchema = context.serialize(currentNavigationPath, schemaObject.getSchema());
                schemas.put(schemaId, serializedSchema);
            });
            map.put(SCHEMAS.value(), schemas);
        }
    }
}
