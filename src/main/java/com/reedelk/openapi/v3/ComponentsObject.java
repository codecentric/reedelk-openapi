package com.reedelk.openapi.v3;

import com.reedelk.openapi.OpenApiSerializableAbstract;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ComponentsObject extends OpenApiSerializableAbstract {

    private Map<String, SchemaObject> schemas = new HashMap<>();
    private Map<String, RequestBodyObject> requestBodies = new HashMap<>();

    public Map<String, SchemaObject> getSchemas() {
        return schemas;
    }

    public void setSchemas(Map<String, SchemaObject> schemas) {
        this.schemas = schemas;
    }

    public Map<String, RequestBodyObject> getRequestBodies() {
        return requestBodies;
    }

    public void setRequestBodies(Map<String, RequestBodyObject> requestBodies) {
        this.requestBodies = requestBodies;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new LinkedHashMap<>();
        setMapSerializable(map, "requestBodies", requestBodies);
        setMapSerializable(map,"schemas", schemas);
        return map;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void deserialize(Map<String, Object> serialized) {
        if (serialized.containsKey("requestBodies")) {
            Map<String,Object> requestBodiesMap = (Map<String, Object>) serialized.get("requestBodies");
            requestBodiesMap.forEach((requestBodyId, requestBodyDataMap) -> {
                Map<String, Object> requestBodyMap = (Map<String, Object>) requestBodyDataMap;
                RequestBodyObject requestBody = new RequestBodyObject();
                requestBody.deserialize(requestBodyMap);
                requestBodies.put(requestBodyId, requestBody);
            });
        }
        if (serialized.containsKey("schemas")) {
            Map<String,Object> schemasMap = (Map<String, Object>) serialized.get("schemas");
            schemasMap.forEach((schemaId, schemaData) -> {
                Map<String, Object> schemaMap = (Map<String, Object>) schemaData;
                SchemaObject schemaObject = new SchemaObject();
                schemaObject.deserialize(schemaMap);
                schemas.put(schemaId, schemaObject);
            });
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComponentsObject that = (ComponentsObject) o;
        return Objects.equals(schemas, that.schemas) &&
                Objects.equals(requestBodies, that.requestBodies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schemas, requestBodies);
    }

    @Override
    public String toString() {
        return "ComponentsObject{" +
                "schemas=" + schemas +
                ", requestBodies=" + requestBodies +
                '}';
    }
}
