package com.reedelk.openapi.v3.model;

import com.reedelk.openapi.OpenApiModel;

import java.util.Objects;

public class MediaTypeObject implements OpenApiModel {

    private Example example;
    private Schema schema;

    public Example getExample() {
        return example;
    }

    public void setExample(Example example) {
        this.example = example;
    }

    public Schema getSchema() {
        return schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MediaTypeObject that = (MediaTypeObject) o;
        return Objects.equals(example, that.example) &&
                Objects.equals(schema, that.schema);
    }

    @Override
    public int hashCode() {
        return Objects.hash(example, schema);
    }

    @Override
    public String toString() {
        return "MediaTypeObject{" +
                "example=" + example +
                ", schema=" + schema +
                '}';
    }

    public enum Properties {

        EXAMPLE("example"),
        SCHEMA("schema");

        private final String value;

        Properties(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }
}
