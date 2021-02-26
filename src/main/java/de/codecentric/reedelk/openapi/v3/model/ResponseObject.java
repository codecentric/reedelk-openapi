package de.codecentric.reedelk.openapi.v3.model;

import de.codecentric.reedelk.openapi.OpenApiModel;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ResponseObject implements OpenApiModel {

    private String description;
    private Map<String, MediaTypeObject> content = new LinkedHashMap<>();
    private Map<String, HeaderObject> headers = new LinkedHashMap<>();

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

    public Map<String, HeaderObject> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, HeaderObject> headers) {
        this.headers = headers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseObject that = (ResponseObject) o;
        return Objects.equals(description, that.description) &&
                Objects.equals(content, that.content) &&
                Objects.equals(headers, that.headers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, content, headers);
    }

    @Override
    public String toString() {
        return "ResponseObject{" +
                "description='" + description + '\'' +
                ", content=" + content +
                ", headers=" + headers +
                '}';
    }

    public enum Properties {

        DESCRIPTION("description"),
        CONTENT("content"),
        HEADERS("headers");

        private final String value;

        Properties(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }
}
