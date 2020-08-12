package com.reedelk.openapi.v3.model;

import com.reedelk.openapi.OpenApiModel;

import java.util.Objects;

public class ContactObject implements OpenApiModel {

    private String name;
    private String url;
    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactObject that = (ContactObject) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(url, that.url) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url, email);
    }

    @Override
    public String toString() {
        return "ContactObject{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public enum Properties {

        NAME("name"),
        URL("url"),
        EMAIL("email");

        private final String value;

        Properties(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }
}
