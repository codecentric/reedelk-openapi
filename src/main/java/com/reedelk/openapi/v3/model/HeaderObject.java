package com.reedelk.openapi.v3.model;

import com.reedelk.openapi.OpenApiModel;

import java.util.Objects;

public class HeaderObject implements OpenApiModel {

    private String description;
    private ParameterStyle style;
    private Schema schema;
    private String example;

    private Boolean explode;
    private Boolean deprecated;
    private Boolean allowReserved;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ParameterStyle getStyle() {
        return style;
    }

    public void setStyle(ParameterStyle style) {
        this.style = style;
    }

    public Schema getSchema() {
        return schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public Boolean getExplode() {
        return explode;
    }

    public void setExplode(Boolean explode) {
        this.explode = explode;
    }

    public Boolean getDeprecated() {
        return deprecated;
    }

    public void setDeprecated(Boolean deprecated) {
        this.deprecated = deprecated;
    }

    public Boolean getAllowReserved() {
        return allowReserved;
    }

    public void setAllowReserved(Boolean allowReserved) {
        this.allowReserved = allowReserved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeaderObject that = (HeaderObject) o;
        return Objects.equals(description, that.description) &&
                style == that.style &&
                Objects.equals(schema, that.schema) &&
                Objects.equals(example, that.example) &&
                Objects.equals(explode, that.explode) &&
                Objects.equals(deprecated, that.deprecated) &&
                Objects.equals(allowReserved, that.allowReserved);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, style, schema, example, explode, deprecated, allowReserved);
    }

    @Override
    public String toString() {
        return "HeaderObject{" +
                "description='" + description + '\'' +
                ", style=" + style +
                ", schema=" + schema +
                ", example='" + example + '\'' +
                ", explode=" + explode +
                ", deprecated=" + deprecated +
                ", allowReserved=" + allowReserved +
                '}';
    }

    public enum Properties {

        SCHEMA("schema"),
        EXAMPLE("example"),
        EXPLODE("explode"),
        DEPRECATED("deprecated"),
        ALLOW_RESERVED("allowReserved"),
        DESCRIPTION("description"),
        STYLE("style");

        private final String value;

        Properties(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }
}
