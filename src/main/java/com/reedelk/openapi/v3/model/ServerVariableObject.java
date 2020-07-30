package com.reedelk.openapi.v3.model;

import com.reedelk.openapi.OpenApiModel;

import java.util.List;
import java.util.Objects;

public class ServerVariableObject implements OpenApiModel {

    private String description;
    private String defaultValue;
    private List<String> enumValues;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public List<String> getEnumValues() {
        return enumValues;
    }

    public void setEnumValues(List<String> enumValues) {
        this.enumValues = enumValues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServerVariableObject that = (ServerVariableObject) o;
        return Objects.equals(description, that.description) &&
                Objects.equals(defaultValue, that.defaultValue) &&
                Objects.equals(enumValues, that.enumValues);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, defaultValue, enumValues);
    }

    @Override
    public String toString() {
        return "ServerVariableObject{" +
                "description='" + description + '\'' +
                ", defaultValue='" + defaultValue + '\'' +
                ", enumValues=" + enumValues +
                '}';
    }
}
