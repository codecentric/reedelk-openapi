package com.reedelk.openapi.v3;

import com.reedelk.openapi.OpenApiSerializableAbstract;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class HeaderObject extends OpenApiSerializableAbstract {

    private String description;
    private ParameterStyle style = ParameterStyle.simple;
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
    public Map<String,Object> serialize() {
        Map<String, Object> map = new LinkedHashMap<>();
        set(map, "description", description);
        set(map, "style", style.name());
        set(map, schema);
        set(map, "example", example);
        set(map, "explode", explode);
        set(map, "deprecated", deprecated);
        set(map, "allowReserved", allowReserved);
        return map;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void deserialize(Map<String, Object> serialized) {
        description = getString(serialized, "description");
        String styleValue = getString(serialized, "style");
        if (styleValue != null) style = ParameterStyle.valueOf(styleValue);
        if (serialized.containsKey("schema")) {
            Map<String, Object> schemaMap = (Map<String, Object>) serialized.get("schema");
            schema = new Schema();
            schema.deserialize(schemaMap);
        }
        example = getString(serialized, "example");
        explode = getBoolean(serialized, "explode");
        deprecated = getBoolean(serialized, "deprecated");
        allowReserved = getBoolean(serialized, "allowReserved");
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
}
