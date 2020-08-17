package com.reedelk.openapi.v3.serializer;

import com.reedelk.openapi.Serializer;
import com.reedelk.openapi.v3.model.*;

import java.util.HashMap;
import java.util.Map;

public class Serializers {

    private Map<Class<?>, Serializer<?>> serializers = new HashMap<>();

    public Serializers() {
        serializers.put(ComponentsObject.class, new ComponentsObjectSerializer());
        serializers.put(ContactObject.class, new ContactObjectSerializer());
        serializers.put(ExternalDocumentationObject.class, new ExternalDocumentationObjectSerializer());
        serializers.put(HeaderObject.class, new HeaderObjectSerializer());
        serializers.put(InfoObject.class, new InfoObjectSerializer());
        serializers.put(LicenseObject.class, new LicenseObjectSerializer());
        serializers.put(MediaTypeObject.class, new MediaTypeObjectSerializer());
        serializers.put(OpenApiObject.class, new OpenApiObjectSerializer());
        serializers.put(OperationObject.class, new OperationObjectSerializer());
        serializers.put(ParameterObject.class, new ParameterObjectSerializer());
        serializers.put(PathsObject.class, new PathsObjectSerializer());
        serializers.put(RequestBodyObject.class, new RequestBodyObjectSerializer());
        serializers.put(ResponseObject.class, new ResponseObjectSerializer());
        serializers.put(Schema.class, new SchemaSerializer());
        serializers.put(ServerObject.class, new ServerObjectSerializer());
        serializers.put(ServerVariableObject.class, new ServerVariableObjectSerializer());
        serializers.put(TagObject.class, new TagObjectSerializer());
    }

    public Serializers(Map<Class<?>, Serializer<?>> overridden) {
        this();
        // Apply user-overridden serializers.
        overridden.forEach((aClass, deserializer) -> serializers.put(aClass, deserializer));
    }

    @SuppressWarnings("unchecked")
    public <T> Serializer<T> forType(Class<T> targetClazz) {
        return (Serializer<T>) serializers.get(targetClazz);
    }
}
