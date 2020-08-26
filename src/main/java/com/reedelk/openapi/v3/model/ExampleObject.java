package com.reedelk.openapi.v3.model;

import com.reedelk.openapi.OpenApiModel;

import java.util.Objects;

public class ExampleObject implements OpenApiModel {

    private String exampleRef;
    private String summary;
    private String description;
    private String externalValue;
    private String value;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExternalValue() {
        return externalValue;
    }

    public void setExternalValue(String externalValue) {
        this.externalValue = externalValue;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getExampleRef() {
        return exampleRef;
    }

    public void setExampleRef(String exampleRef) {
        this.exampleRef = exampleRef;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ExampleObject that = (ExampleObject) object;
        return Objects.equals(exampleRef, that.exampleRef) &&
                Objects.equals(summary, that.summary) &&
                Objects.equals(description, that.description) &&
                Objects.equals(externalValue, that.externalValue) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exampleRef, summary, description, externalValue, value);
    }

    @Override
    public String toString() {
        return "ExampleObject{" +
                "exampleRef='" + exampleRef + '\'' +
                ", summary='" + summary + '\'' +
                ", description='" + description + '\'' +
                ", externalValue='" + externalValue + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public enum Properties {

        $REF("$ref"),
        SUMMARY("summary"),
        DESCRIPTION("description"),
        EXTERNAL_VALUE("externalValue"),
        VALUE("value");

        private final String value;

        Properties(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }
}
