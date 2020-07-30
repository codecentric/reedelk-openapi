package com.reedelk.openapi.v3.model;

import com.reedelk.openapi.OpenApiModel;

import java.util.Map;
import java.util.Objects;

public class Schema implements OpenApiModel {

    private String schemaId;
    private Map<String,Object> schemaData;

    public Schema() {
    }

    public Schema(String schemaId) {
        this.schemaId = schemaId;
    }

    public Schema(Map<String,Object> schemaData) {
        this.schemaData = schemaData;
    }

    public Map<String, Object> getSchemaData() {
        return schemaData;
    }

    public void setSchemaData(Map<String, Object> schemaData) {
        this.schemaData = schemaData;
    }

    public String getSchemaId() {
        return schemaId;
    }

    public void setSchemaId(String schemaId) {
        this.schemaId = schemaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schema schema = (Schema) o;
        return Objects.equals(schemaId, schema.getSchemaId()) &&
                Objects.equals(schemaData, schema.getSchemaData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(schemaId, schemaData);
    }

}
