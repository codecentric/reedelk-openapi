package com.reedelk.openapi.v3;

import com.reedelk.openapi.OpenApiSerializableAbstract;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class SchemaDefault extends OpenApiSerializableAbstract implements Schema {

    private static final String JSON_PROPERTY_REF = "$ref";

    private String schemaId;
    private Map<String,Object> schemaData;

    public SchemaDefault() {
    }

    public SchemaDefault(String schemaId) {
        this.schemaId = schemaId;
    }

    public SchemaDefault(Map<String,Object> schemaData) {
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

    // Creates the following structure if it is a reference:
    // {
    //      "$ref": "#/components/schemas/mySchema"
    // }
    @Override
    public Map<String, Object> serialize() {
        if (isReference()) {
            Map<String, Object> schemaReferenceObject = new LinkedHashMap<>();
            schemaReferenceObject.put(JSON_PROPERTY_REF, schemaId);
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
        return Objects.equals(schemaId, schema.getSchemaId()) &&
                Objects.equals(schemaData, schema.getSchemaData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(schemaId, schemaData);
    }

    @Override
    public String toString() {
        return "SchemaDefault{" +
                "schemaId='" + schemaId + '\'' +
                ", schemaData=" + schemaData +
                '}';
    }

    private boolean isReference() {
        return schemaId != null && schemaId.length() > 0;
    }
}
