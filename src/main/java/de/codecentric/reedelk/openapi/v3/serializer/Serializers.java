package de.codecentric.reedelk.openapi.v3.serializer;

import de.codecentric.reedelk.openapi.Serializer;
import de.codecentric.reedelk.openapi.v3.model.*;

import java.util.HashMap;
import java.util.Map;

public class Serializers {

    private Map<Class<?>, Serializer<?>> all = new HashMap<>();

    public Serializers() {
        all.put(ComponentsObject.class, new ComponentsObjectSerializer());
        all.put(ContactObject.class, new ContactObjectSerializer());
        all.put(ExampleObject.class, new ExampleObjectSerializer());
        all.put(ExternalDocumentationObject.class, new ExternalDocumentationObjectSerializer());
        all.put(HeaderObject.class, new HeaderObjectSerializer());
        all.put(InfoObject.class, new InfoObjectSerializer());
        all.put(LicenseObject.class, new LicenseObjectSerializer());
        all.put(MediaTypeObject.class, new MediaTypeObjectSerializer());
        all.put(OAuthFlowObject.class, new OAuthFlowObjectSerializer());
        all.put(OAuthFlowsObject.class, new OAuthFlowsObjectSerializer());
        all.put(OpenApiObject.class, new OpenApiObjectSerializer());
        all.put(OperationObject.class, new OperationObjectSerializer());
        all.put(ParameterObject.class, new ParameterObjectSerializer());
        all.put(PathsObject.class, new PathsObjectSerializer());
        all.put(RequestBodyObject.class, new RequestBodyObjectSerializer());
        all.put(ResponseObject.class, new ResponseObjectSerializer());
        all.put(Schema.class, new SchemaSerializer());
        all.put(SecuritySchemeObject.class, new SecuritySchemeObjectSerializer());
        all.put(ServerObject.class, new ServerObjectSerializer());
        all.put(ServerVariableObject.class, new ServerVariableObjectSerializer());
        all.put(TagObject.class, new TagObjectSerializer());
    }

    public Serializers(Map<Class<?>, Serializer<?>> overridden) {
        this();
        // Apply user-overridden serializers.
        overridden.forEach((aClass, deserializer) -> all.put(aClass, deserializer));
    }

    @SuppressWarnings("unchecked")
    public <T> Serializer<T> forType(Class<T> targetClazz) {
        return (Serializer<T>) all.get(targetClazz);
    }
}
