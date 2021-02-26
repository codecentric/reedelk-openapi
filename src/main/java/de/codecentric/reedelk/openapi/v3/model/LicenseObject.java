package de.codecentric.reedelk.openapi.v3.model;

import de.codecentric.reedelk.openapi.OpenApiModel;

import java.util.Objects;

public class LicenseObject implements OpenApiModel {

    private String name;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        LicenseObject that = (LicenseObject) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url);
    }

    @Override
    public String toString() {
        return "LicenseObject{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public enum Properties {

        NAME("name"),
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
