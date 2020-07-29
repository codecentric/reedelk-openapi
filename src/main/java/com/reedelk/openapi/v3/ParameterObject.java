package com.reedelk.openapi.v3;

import com.reedelk.openapi.OpenApiSerializableAbstract;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static com.reedelk.openapi.v3.ParameterLocation.query;
import static java.util.Optional.ofNullable;

public class ParameterObject extends OpenApiSerializableAbstract {

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
    public Map<String, Object> serialize() {
        Map<String, Object> map = new LinkedHashMap<>();
        set(map, "name", Optional.ofNullable(name).orElse(""));
        set(map, "description", description);
        set(map, "in", ofNullable(in).orElse(query).name().toLowerCase());
        set(map, "style", style.name());
        set(map, "example", example);
        set(map, "explode", explode);
        set(map, "deprecated", deprecated);
        set(map, schema);
        // If the parameter location is "path", this property is REQUIRED and its value MUST be true.
        // Otherwise, the property MAY be included and its default value is false.
        if (ParameterLocation.path.equals(in)) {
            set(map, "required", Boolean.TRUE);
        } else {
            set(map, "required", required);
        }
        set(map, "allowEmptyValue", allowEmptyValue);
        set(map, "allowReserved", allowReserved);
        return map;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void deserialize(Map<String, Object> serialized) {
        name = getString(serialized, "name");
        description = getString(serialized, "description");

        String inValue = getString(serialized, "in");
        if (inValue != null) this.in = ParameterLocation.valueOf(inValue);

        String styleValue = getString(serialized, "style");
        if (styleValue != null) this.style = ParameterStyle.valueOf(styleValue);

        if (serialized.containsKey("schema")) {
            Map<String, Object> schemaMap = (Map<String, Object>) serialized.get("schema");
            schema = new Schema();
            schema.deserialize(schemaMap);
        }

        example = getString(serialized, "example");
        explode = getBoolean(serialized, "explode");
        deprecated = getBoolean(serialized, "deprecated");
        required = getBoolean(serialized, "required");
        allowEmptyValue = getBoolean(serialized, "allowEmptyValue");
        allowReserved = getBoolean(serialized, "allowReserved");
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

    @Override
    public String toString() {
        return "ParameterObject{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", in=" + in +
                ", style=" + style +
                ", schema=" + schema +
                ", example='" + example + '\'' +
                ", explode=" + explode +
                ", deprecated=" + deprecated +
                ", required=" + required +
                ", allowEmptyValue=" + allowEmptyValue +
                ", allowReserved=" + allowReserved +
                '}';
    }
}
