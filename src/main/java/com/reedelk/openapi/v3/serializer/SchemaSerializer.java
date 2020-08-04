package com.reedelk.openapi.v3.serializer;

import com.reedelk.openapi.commons.AbstractSerializer;
import com.reedelk.openapi.commons.NavigationPath;
import com.reedelk.openapi.commons.Properties;
import com.reedelk.openapi.v3.SerializerContext;
import com.reedelk.openapi.v3.model.Schema;

import java.util.LinkedHashMap;
import java.util.Map;

public class SchemaSerializer extends AbstractSerializer<Schema> {

    // Creates the following structure if it is a reference:
    // {
    //      "$ref": "#/components/schemas/mySchema"
    // }
    @Override
    public Map<String, Object> serialize(SerializerContext context, NavigationPath navigationPath, Schema input) {
        if (isReference(input)) {
            Map<String, Object> schemaReferenceObject = new LinkedHashMap<>();
            schemaReferenceObject.put(Properties.$REF, input.getSchemaId());
            return schemaReferenceObject;
        } else {
            return input.getSchemaData();
        }
    }

    private boolean isReference(Schema input) {
        return input.getSchemaId() != null && input.getSchemaId().length() > 0;
    }
}
