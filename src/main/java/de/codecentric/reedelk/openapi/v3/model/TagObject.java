package de.codecentric.reedelk.openapi.v3.model;

import de.codecentric.reedelk.openapi.OpenApiModel;

import java.util.Objects;

public class TagObject implements OpenApiModel {

    private String name;
    private String description;
    private ExternalDocumentationObject externalDocs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ExternalDocumentationObject getExternalDocs() {
        return externalDocs;
    }

    public void setExternalDocs(ExternalDocumentationObject externalDocs) {
        this.externalDocs = externalDocs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagObject tagObject = (TagObject) o;
        return Objects.equals(name, tagObject.name) &&
                Objects.equals(description, tagObject.description) &&
                Objects.equals(externalDocs, tagObject.externalDocs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, externalDocs);
    }

    @Override
    public String toString() {
        return "TagObject{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", externalDocs=" + externalDocs +
                '}';
    }

    public enum Properties {

        NAME("name"),
        DESCRIPTION("description"),
        EXTERNAL_DOCS("externalDocs");

        private final String value;

        Properties(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }
}
