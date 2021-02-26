package de.codecentric.reedelk.openapi.v3.model;

import de.codecentric.reedelk.openapi.OpenApiModel;

import java.util.Objects;

public class ExternalDocumentationObject implements OpenApiModel {

    private String description;
    private String url;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExternalDocumentationObject that = (ExternalDocumentationObject) o;
        return Objects.equals(description, that.description) &&
                Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, url);
    }

    @Override
    public String toString() {
        return "ExternalDocumentationObject{" +
                "description='" + description + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public enum Properties {

        DESCRIPTION("description"),
        URL("url");

        private final String value;

        Properties(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }
}
