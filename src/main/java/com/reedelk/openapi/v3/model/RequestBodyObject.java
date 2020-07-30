package com.reedelk.openapi.v3.model;

import com.reedelk.openapi.OpenApiModel;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RequestBodyObject implements OpenApiModel {

    private String $ref;
    private Boolean required;
    private String description;
    private Map<String, MediaTypeObject> content = new HashMap<>();

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, MediaTypeObject> getContent() {
        return content;
    }

    public void setContent(Map<String, MediaTypeObject> content) {
        this.content = content;
    }

    public String get$ref() {
        return $ref;
    }

    public void set$ref(String $ref) {
        this.$ref = $ref;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestBodyObject that = (RequestBodyObject) o;
        return Objects.equals($ref, that.$ref) &&
                Objects.equals(required, that.required) &&
                Objects.equals(description, that.description) &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash($ref, required, description, content);
    }
}
