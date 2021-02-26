package de.codecentric.reedelk.openapi.v3.model;

import de.codecentric.reedelk.openapi.OpenApiModel;

import java.util.Map;
import java.util.Objects;

public class OAuthFlowObject implements OpenApiModel {

    private String authorizationUrl;
    private String tokenUrl;
    private String refreshUrl;
    private Map<String,String> scopes;

    public String getAuthorizationUrl() {
        return authorizationUrl;
    }

    public void setAuthorizationUrl(String authorizationUrl) {
        this.authorizationUrl = authorizationUrl;
    }

    public String getTokenUrl() {
        return tokenUrl;
    }

    public void setTokenUrl(String tokenUrl) {
        this.tokenUrl = tokenUrl;
    }

    public String getRefreshUrl() {
        return refreshUrl;
    }

    public void setRefreshUrl(String refreshUrl) {
        this.refreshUrl = refreshUrl;
    }

    public Map<String, String> getScopes() {
        return scopes;
    }

    public void setScopes(Map<String, String> scopes) {
        this.scopes = scopes;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        OAuthFlowObject that = (OAuthFlowObject) object;
        return Objects.equals(authorizationUrl, that.authorizationUrl) &&
                Objects.equals(tokenUrl, that.tokenUrl) &&
                Objects.equals(refreshUrl, that.refreshUrl) &&
                Objects.equals(scopes, that.scopes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorizationUrl, tokenUrl, refreshUrl, scopes);
    }

    @Override
    public String toString() {
        return "OAuthFlowObject{" +
                "authorizationUrl='" + authorizationUrl + '\'' +
                ", tokenUrl='" + tokenUrl + '\'' +
                ", refreshUrl='" + refreshUrl + '\'' +
                ", scopes=" + scopes +
                '}';
    }

    public enum Properties {

        AUTHORIZATION_URL("authorizationUrl"),
        TOKEN_URL("tokenUrl"),
        REFRESH_URL("refreshUrl"),
        SCOPES("scopes");

        private final String value;

        Properties(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }
}
