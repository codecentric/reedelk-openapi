package com.reedelk.openapi.v3;

import com.reedelk.openapi.OpenApiSerializableAbstract;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Schema extends OpenApiSerializableAbstract {

    private static final String COMPONENTS_SCHEMA_REF_TEMPLATE = "#/components/schemas/%s";
    private static final String JSON_PROPERTY_REF = "$ref";

    private String schemaId;
    private Map<String,Object> schemaData;

    public Schema() {
    }

    public Schema(String schemaId, Map<String,Object> schemaData) {
        this.schemaId = schemaId;
        this.schemaData = schemaData;
    }

    public Schema(Map<String,Object> schemaData) {
        this.schemaData = schemaData;
    }

    public Map<String, Object> getSchemaData() {
        return schemaData;
    }

    public void setSchemaData(Map<String, Object> schemaData) {
        this.schemaData = schemaData;
    }

    public String getSchemaId() {
        return schemaId;
    }

    public boolean isReference() {
        return schemaId != null && schemaId.length() > 0;
    }

    // Creates the following structure if it is a reference:
    // {
    //      "$ref": "#/components/schemas/mySchema"
    // }
    @Override
    public Map<String, Object> serialize() {
        if (isReference()) {
            Map<String, Object> schemaReferenceObject = new LinkedHashMap<>();
            schemaReferenceObject.put(JSON_PROPERTY_REF, String.format(COMPONENTS_SCHEMA_REF_TEMPLATE, schemaId));
            return schemaReferenceObject;
        } else {
            return schemaData;
        }
    }

    @Override
    public void deserialize(Map<String, Object> serialized) {
        if (serialized.containsKey(JSON_PROPERTY_REF)) {
            this.schemaId = getString(serialized, JSON_PROPERTY_REF);
        } else {
            this.schemaData = serialized;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schema schema = (Schema) o;
        return Objects.equals(schemaId, schema.schemaId) &&
                Objects.equals(schemaData, schema.schemaData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schemaId, schemaData);
    }

    @Override
    public String toString() {
        return "Schema{" +
                "schemaId='" + schemaId + '\'' +
                ", schemaData=" + schemaData +
                '}';
    }
}
