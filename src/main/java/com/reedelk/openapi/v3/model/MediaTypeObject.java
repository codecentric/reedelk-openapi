package com.reedelk.openapi.v3.model;

import com.reedelk.openapi.OpenApiModel;

import java.util.Map;
import java.util.Objects;

public class MediaTypeObject implements OpenApiModel {

    private Schema schema;
    private Example example;
    private Map<String, Example> examples;

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

    public Map<String, Example> getExamples() {
        return examples;
    }

    public void setExamples(Map<String, Example> examples) {
        this.examples = examples;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        MediaTypeObject that = (MediaTypeObject) object;
        return Objects.equals(schema, that.schema) &&
                Objects.equals(example, that.example) &&
                Objects.equals(examples, that.examples);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schema, example, examples);
    }

    @Override
    public String toString() {
        return "MediaTypeObject{" +
                "schema=" + schema +
                ", example=" + example +
                ", examples=" + examples +
                '}';
    }

    public enum Properties {

        EXAMPLES("examples"),
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
