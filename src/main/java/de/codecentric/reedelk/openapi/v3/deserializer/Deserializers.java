package de.codecentric.reedelk.openapi.v3.deserializer;

import de.codecentric.reedelk.openapi.Deserializer;
import de.codecentric.reedelk.openapi.v3.model.*;

import java.util.HashMap;
import java.util.Map;

public class Deserializers {

    private final Map<Class<?>, Deserializer<?>> all = new HashMap<>();

    public Deserializers() {
        all.put(ComponentsObject.class, new ComponentsObjectDeserializer());
        all.put(ContactObject.class, new ContactObjectDeserializer());
        all.put(ExampleObject.class, new ExampleObjectDeserializer());
        all.put(ExternalDocumentationObject.class, new ExternalDocumentationObjectDeserializer());
        all.put(HeaderObject.class, new HeaderObjectDeserializer());
        all.put(InfoObject.class, new InfoObjectDeserializer());
        all.put(LicenseObject.class, new LicenseObjectDeserializer());
        all.put(MediaTypeObject.class, new MediaTypeObjectDeserializer());
        all.put(OAuthFlowObject.class, new OAuthFlowObjectDeserializer());
        all.put(OAuthFlowsObject.class, new OAuthFlowsObjectDeserializer());
        all.put(OpenApiObject.class, new OpenApiObjectDeserializer());
        all.put(OperationObject.class, new OperationObjectDeserializer());
        all.put(ParameterObject.class, new ParameterObjectDeserializer());
        all.put(PathsObject.class, new PathsObjectDeserializer());
        all.put(RequestBodyObject.class, new RequestBodyObjectDeserializer());
        all.put(ResponseObject.class, new ResponseObjectDeserializer());
        all.put(SecuritySchemeObject.class, new SecuritySchemeObjectDeserializer());
        all.put(Schema.class, new SchemaDeserializer());
        all.put(ServerObject.class, new ServerObjectDeserializer());
        all.put(ServerVariableObject.class, new ServerVariableObjectDeserializer());
        all.put(TagObject.class, new TagObjectDeserializer());
    }

    public Deserializers(Map<Class<?>, Deserializer<?>> overridden) {
        this();
        // Apply user-overridden deserializers.
        overridden.forEach(all::put);
    }

    @SuppressWarnings("unchecked")
    public <T> Deserializer<T> forType(Class<T> modelObjectClazz) {
        return (Deserializer<T>) all.get(modelObjectClazz);
    }
}
