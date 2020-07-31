package com.reedelk.openapi.v3.deserializer;

import com.reedelk.openapi.commons.AbstractDeserializer;
import com.reedelk.openapi.v3.DeserializerContext;
import com.reedelk.openapi.v3.model.ComponentsObject;
import com.reedelk.openapi.v3.model.RequestBodyObject;
import com.reedelk.openapi.v3.model.Schema;
import com.reedelk.openapi.v3.model.SchemaObject;

import java.util.Map;

public class ComponentsObjectDeserializer extends AbstractDeserializer<ComponentsObject> {

    @Override
    public ComponentsObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        ComponentsObject componentsObject = new ComponentsObject();

        // Request Bodies
        mapKeyApiModel("requestBodies", serialized,
                (key, source) -> context.deserialize(RequestBodyObject.class, source))
                .ifPresent(componentsObject::setRequestBodies);

        // Schemas
        mapKeyApiModel("schemas", serialized, (key, source) -> {
            Schema schemaObject = context.deserialize(Schema.class, source);
            SchemaObject schemaObjectObject = new SchemaObject();
            schemaObjectObject.setSchemaId(key);
            schemaObjectObject.setSchema(schemaObject);
            return schemaObjectObject;
        }).ifPresent(componentsObject::setSchemas);

        return componentsObject;
    }
}
