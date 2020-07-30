package com.reedelk.openapi.v3.model;

import com.reedelk.openapi.OpenApiModel;

import java.util.Objects;

public class ParameterObject implements OpenApiModel {

    private String name;
    private String description;
    private ParameterLocation in;
    private ParameterStyle style;
    private Schema schema;
    private String example;
    private Boolean explode;
    private Boolean deprecated;
    private Boolean required;
    private Boolean allowEmptyValue;
    private Boolean allowReserved;

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

    public ParameterLocation getIn() {
        return in;
    }

    public void setIn(ParameterLocation in) {
        this.in = in;
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

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Boolean getAllowEmptyValue() {
        return allowEmptyValue;
    }

    public void setAllowEmptyValue(Boolean allowEmptyValue) {
        this.allowEmptyValue = allowEmptyValue;
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
        ParameterObject that = (ParameterObject) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                in == that.in &&
                style == that.style &&
                Objects.equals(schema, that.schema) &&
                Objects.equals(example, that.example) &&
                Objects.equals(explode, that.explode) &&
                Objects.equals(deprecated, that.deprecated) &&
                Objects.equals(required, that.required) &&
                Objects.equals(allowEmptyValue, that.allowEmptyValue) &&
                Objects.equals(allowReserved, that.allowReserved);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, in, style, schema, example, explode, deprecated, required, allowEmptyValue, allowReserved);
    }
}
