package com.reedelk.openapi.v3.model;

import com.reedelk.openapi.OpenApiModel;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ComponentsObject implements OpenApiModel {

    private Map<String, SchemaObject> schemas = new LinkedHashMap<>();
    private Map<String, RequestBodyObject> requestBodies = new LinkedHashMap<>();

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
