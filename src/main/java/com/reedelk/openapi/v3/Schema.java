package com.reedelk.openapi.v3;

import com.reedelk.openapi.OpenApiSerializableAbstract;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Schema extends OpenApiSerializableAbstract {

    private static final List<String> PROPERTIES_TO_EXCLUDE_FROM_SCHEMA = Arrays.asList("$id", "$schema", "name");

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
}
