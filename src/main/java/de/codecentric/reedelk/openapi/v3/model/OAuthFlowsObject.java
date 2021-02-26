package de.codecentric.reedelk.openapi.v3.model;

import de.codecentric.reedelk.openapi.OpenApiModel;

import java.util.Objects;

public class OAuthFlowsObject implements OpenApiModel {

    private OAuthFlowObject implicit;
    private OAuthFlowObject password;
    private OAuthFlowObject clientCredentials;
    private OAuthFlowObject authorizationCode;

    public OAuthFlowObject getImplicit() {
        return implicit;
    }

    public void setImplicit(OAuthFlowObject implicit) {
        this.implicit = implicit;
    }

    public OAuthFlowObject getPassword() {
        return password;
    }

    public void setPassword(OAuthFlowObject password) {
        this.password = password;
    }

    public OAuthFlowObject getClientCredentials() {
        return clientCredentials;
    }

    public void setClientCredentials(OAuthFlowObject clientCredentials) {
        this.clientCredentials = clientCredentials;
    }

    public OAuthFlowObject getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(OAuthFlowObject authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        OAuthFlowsObject that = (OAuthFlowsObject) object;
        return Objects.equals(implicit, that.implicit) &&
                Objects.equals(password, that.password) &&
                Objects.equals(clientCredentials, that.clientCredentials) &&
                Objects.equals(authorizationCode, that.authorizationCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(implicit, password, clientCredentials, authorizationCode);
    }

    @Override
    public String toString() {
        return "OAuthFlowsObject{" +
                "implicit=" + implicit +
                ", password=" + password +
                ", clientCredentials=" + clientCredentials +
                ", authorizationCode=" + authorizationCode +
                '}';
    }

    public enum Properties {

        IMPLICIT("implicit"),
        PASSWORD("password"),
        CLIENT_CREDENTIALS("clientCredentials"),
        AUTHORIZATION_CODE("authorizationCode");

        private final String value;

        Properties(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }
}
