package de.codecentric.reedelk.openapi.v3.deserializer;

import de.codecentric.reedelk.openapi.commons.AbstractDeserializer;
import de.codecentric.reedelk.openapi.v3.DeserializerContext;
import de.codecentric.reedelk.openapi.v3.model.*;

import java.util.Map;

public class ComponentsObjectDeserializer extends AbstractDeserializer<ComponentsObject> {

    @Override
    public ComponentsObject deserialize(DeserializerContext context, Map<String, Object> serialized) {
        ComponentsObject componentsObject = new ComponentsObject();

        // Request Bodies
        mapKeyApiModel(ComponentsObject.Properties.REQUEST_BODIES.value(), serialized,
                (key, source) -> context.deserialize(RequestBodyObject.class, source))
                .ifPresent(componentsObject::setRequestBodies);

        // Schemas
        mapKeyApiModel(ComponentsObject.Properties.SCHEMAS.value(), serialized, (key, source) -> {
            Schema schemaObject = context.deserialize(Schema.class, source);
            SchemaObject schemaObjectObject = new SchemaObject();
            schemaObjectObject.setSchemaId(key);
            schemaObjectObject.setSchema(schemaObject);
            return schemaObjectObject;
        }).ifPresent(componentsObject::setSchemas);

        // Examples
        mapKeyApiModel(ComponentsObject.Properties.EXAMPLES.value(), serialized,
                (key, source) -> context.deserialize(ExampleObject.class, source))
                .ifPresent(componentsObject::setExamples);

        // Security Schemes
        mapKeyApiModel(ComponentsObject.Properties.SECURITY_SCHEMES.value(), serialized,
                (key, source) -> context.deserialize(SecuritySchemeObject.class, source))
                .ifPresent(componentsObject::setSecuritySchemes);

        return componentsObject;
    }
}
