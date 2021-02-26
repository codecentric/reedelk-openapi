package de.codecentric.reedelk.openapi.v3.model;

import de.codecentric.reedelk.openapi.OpenApiModel;

import java.util.Map;
import java.util.Objects;

public class ServerObject implements OpenApiModel {

    private String url;
    private String description;
    private Map<String, ServerVariableObject> variables;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, ServerVariableObject> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, ServerVariableObject> variables) {
        this.variables = variables;
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
        ServerObject that = (ServerObject) o;
        return Objects.equals(url, that.url) &&
                Objects.equals(description, that.description) &&
                Objects.equals(variables, that.variables);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, description, variables);
    }

    @Override
    public String toString() {
        return "ServerObject{" +
                "url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", variables=" + variables +
                '}';
    }

    public enum Properties {

        URL("url"),
        DESCRIPTION("description"),
        VARIABLES("variables");

        private final String value;

        Properties(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }
}

