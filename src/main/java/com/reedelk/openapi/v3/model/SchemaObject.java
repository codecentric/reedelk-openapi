package com.reedelk.openapi.v3.model;

import com.reedelk.openapi.OpenApiModel;

import java.util.Objects;

public class SchemaObject implements OpenApiModel {

    private Schema schema;
    private String schemaId;

    public Schema getSchema() {
        return schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
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
        SchemaObject that = (SchemaObject) o;
        return Objects.equals(schema, that.schema);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schema);
    }

}
