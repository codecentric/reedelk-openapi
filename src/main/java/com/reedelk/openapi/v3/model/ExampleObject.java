package com.reedelk.openapi.v3.model;

import com.reedelk.openapi.OpenApiModel;

public class ExampleObject implements OpenApiModel {

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

    public enum Properties {

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
