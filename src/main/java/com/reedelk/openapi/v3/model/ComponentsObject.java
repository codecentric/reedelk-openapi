package com.reedelk.openapi.v3.model;

import com.reedelk.openapi.OpenApiModel;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ComponentsObject implements OpenApiModel {

    private Map<String, ExampleObject> examples = new LinkedHashMap<>();
    private Map<String, SchemaObject> schemas = new LinkedHashMap<>();
    private Map<String, RequestBodyObject> requestBodies = new LinkedHashMap<>();
    private Map<String, SecuritySchemeObject> securitySchemes = new LinkedHashMap<>();

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

    public Map<String, ExampleObject> getExamples() {
        return examples;
    }

    public void setExamples(Map<String, ExampleObject> examples) {
        this.examples = examples;
    }

    public Map<String, SecuritySchemeObject> getSecuritySchemes() {
        return securitySchemes;
    }

    public void setSecuritySchemes(Map<String, SecuritySchemeObject> securitySchemes) {
        this.securitySchemes = securitySchemes;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ComponentsObject that = (ComponentsObject) object;
        return Objects.equals(examples, that.examples) &&
                Objects.equals(schemas, that.schemas) &&
                Objects.equals(requestBodies, that.requestBodies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(examples, schemas, requestBodies);
    }

    @Override
    public String toString() {
        return "ComponentsObject{" +
                "examples=" + examples +
                ", schemas=" + schemas +
                ", requestBodies=" + requestBodies +
                '}';
    }

    public enum Properties {

        SCHEMAS("schemas"),
        EXAMPLES("examples"),
        REQUEST_BODIES("requestBodies"),
        SECURITY_SCHEMES("securitySchemes");

        private final String value;

        Properties(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }
}
