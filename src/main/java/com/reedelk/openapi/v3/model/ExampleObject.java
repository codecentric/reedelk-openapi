package com.reedelk.openapi.v3.model;

import com.reedelk.openapi.OpenApiModel;

import java.util.Objects;

public class ExampleObject implements OpenApiModel {

    private Example example;
    private String exampleId;

    public Example getExample() {
        return example;
    }

    public void setExample(Example example) {
        this.example = example;
    }

    public String getExampleId() {
        return exampleId;
    }

    public void setExampleId(String exampleId) {
        this.exampleId = exampleId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ExampleObject that = (ExampleObject) object;
        return Objects.equals(example, that.example) &&
                Objects.equals(exampleId, that.exampleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(example, exampleId);
    }

    @Override
    public String toString() {
        return "ExampleObject{" +
                "example=" + example +
                ", exampleId='" + exampleId + '\'' +
                '}';
    }

    public enum Properties {

        SCHEMA("example");

        private final String value;

        Properties(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }
}
