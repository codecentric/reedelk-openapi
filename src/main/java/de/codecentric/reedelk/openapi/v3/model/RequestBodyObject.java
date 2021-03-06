package de.codecentric.reedelk.openapi.v3.model;

import de.codecentric.reedelk.openapi.OpenApiModel;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class RequestBodyObject implements OpenApiModel {

    private String $ref;
    private Boolean required;
    private String description;
    private Map<String, MediaTypeObject> content = new LinkedHashMap<>();

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

    @Override
    public String toString() {
        return "RequestBodyObject{" +
                "$ref='" + $ref + '\'' +
                ", required=" + required +
                ", description='" + description + '\'' +
                ", content=" + content +
                '}';
    }

    public enum Properties {

        $REF("$ref"),
        REQUIRED("required"),
        DESCRIPTION("description"),
        CONTENT("content");

        private final String value;

        Properties(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }
}
