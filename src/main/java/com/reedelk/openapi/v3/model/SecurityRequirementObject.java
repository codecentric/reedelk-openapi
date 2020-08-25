package com.reedelk.openapi.v3.model;

import com.reedelk.openapi.OpenApiModel;

import java.util.List;
import java.util.Objects;

public class SecurityRequirementObject implements OpenApiModel {

    private List<String> scopes;

    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        SecurityRequirementObject that = (SecurityRequirementObject) object;
        return Objects.equals(scopes, that.scopes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scopes);
    }

    @Override
    public String toString() {
        return "SecurityRequirementObject{" +
                "scopes=" + scopes +
                '}';
    }
}
