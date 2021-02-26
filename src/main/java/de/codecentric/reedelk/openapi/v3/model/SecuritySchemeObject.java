package de.codecentric.reedelk.openapi.v3.model;

import de.codecentric.reedelk.openapi.OpenApiModel;

import java.util.Objects;

public class SecuritySchemeObject implements OpenApiModel {

    private SecurityType type;
    private String description;
    private String name;
    private SecurityKeyLocation in;
    private String scheme;
    private String bearerFormat;
    private OAuthFlowsObject flows;
    private String openIdConnectUrl;

    public SecurityType getType() {
        return type;
    }

    public void setType(SecurityType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SecurityKeyLocation getIn() {
        return in;
    }

    public void setIn(SecurityKeyLocation in) {
        this.in = in;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getBearerFormat() {
        return bearerFormat;
    }

    public void setBearerFormat(String bearerFormat) {
        this.bearerFormat = bearerFormat;
    }

    public OAuthFlowsObject getFlows() {
        return flows;
    }

    public void setFlows(OAuthFlowsObject flows) {
        this.flows = flows;
    }

    public String getOpenIdConnectUrl() {
        return openIdConnectUrl;
    }

    public void setOpenIdConnectUrl(String openIdConnectUrl) {
        this.openIdConnectUrl = openIdConnectUrl;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        SecuritySchemeObject that = (SecuritySchemeObject) object;
        return type == that.type &&
                Objects.equals(description, that.description) &&
                Objects.equals(name, that.name) &&
                in == that.in &&
                Objects.equals(scheme, that.scheme) &&
                Objects.equals(bearerFormat, that.bearerFormat) &&
                Objects.equals(flows, that.flows) &&
                Objects.equals(openIdConnectUrl, that.openIdConnectUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, description, name, in, scheme, bearerFormat, flows, openIdConnectUrl);
    }

    @Override
    public String toString() {
        return "SecuritySchemeObject{" +
                "type=" + type +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", in=" + in +
                ", scheme='" + scheme + '\'' +
                ", bearerFormat='" + bearerFormat + '\'' +
                ", flows=" + flows +
                ", openIdConnectUrl='" + openIdConnectUrl + '\'' +
                '}';
    }

    public enum Properties {

        TYPE("type"),
        DESCRIPTION("description"),
        NAME("name"),
        IN("in"),
        SCHEME("scheme"),
        BEARER_FORMAT("bearerFormat"),
        FLOWS("flows"),
        OPEN_ID_CONNECT_URL("openIdConnectUrl");

        private final String value;

        Properties(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }
}
