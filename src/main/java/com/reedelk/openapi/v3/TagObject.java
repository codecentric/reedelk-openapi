package com.reedelk.openapi.v3;

import com.reedelk.openapi.OpenApiSerializableAbstract;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class TagObject extends OpenApiSerializableAbstract {

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
    public Map<String, Object> serialize() {
        Map<String, Object> map = new LinkedHashMap<>();
        set(map, "name", name);
        set(map, "description", description);
        set(map, "externalDocs", externalDocs);
        return map;
    }

    @Override
    public void deserialize(Map<String, Object> serialized) {
        name = getString(serialized, "name");
        description = getString(serialized, "description");
        if (serialized.containsKey("externalDocs")) {
            externalDocs = new ExternalDocumentationObject();
            externalDocs.deserialize(getMap(serialized, "externalDocs"));
        }
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
}
