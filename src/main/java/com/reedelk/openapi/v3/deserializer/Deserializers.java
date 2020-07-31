package com.reedelk.openapi.v3.deserializer;

import com.reedelk.openapi.Deserializer;
import com.reedelk.openapi.v3.model.*;

import java.util.HashMap;
import java.util.Map;

public class Deserializers {

    public Map<Class<?>, Deserializer<?>> deserializers = new HashMap<>();

    public Deserializers() {
        deserializers.put(ComponentsObject.class, new ComponentsObjectDeserializer());
        deserializers.put(ContactObject.class, new ContactObjectDeserializer());
        deserializers.put(ExternalDocumentationObject.class, new ExternalDocumentationObjectDeserializer());
        deserializers.put(HeaderObject.class, new HeaderObjectDeserializer());
        deserializers.put(InfoObject.class, new InfoObjectDeserializer());
        deserializers.put(LicenseObject.class, new LicenseObjectDeserializer());
        deserializers.put(MediaTypeObject.class, new MediaTypeObjectDeserializer());
        deserializers.put(OpenApiObject.class, new OpenApiObjectDeserializer());
        deserializers.put(OperationObject.class, new OperationObjectDeserializer());
        deserializers.put(ParameterObject.class, new ParameterObjectDeserializer());
        deserializers.put(PathsObject.class, new PathsObjectDeserializer());
        deserializers.put(RequestBodyObject.class, new RequestBodyObjectDeserializer());
        deserializers.put(ResponseObject.class, new ResponseObjectDeserializer());
        deserializers.put(Schema.class, new SchemaDeserializer());
        deserializers.put(ServerObject.class, new ServerObjectDeserializer());
        deserializers.put(ServerVariableObject.class, new ServerVariableObjectDeserializer());
        deserializers.put(TagObject.class, new TagObjectDeserializer());
    }

    public Deserializers(Map<Class<?>, Deserializer<?>> overridden) {
        this();
        // Apply user-overridden deserializers.
        overridden.forEach((aClass, deserializer) -> deserializers.put(aClass, deserializer));
    }

    @SuppressWarnings("unchecked")
    public <T> Deserializer<T> forType(Class<T> modelObjectClazz) {
        return (Deserializer<T>) deserializers.get(modelObjectClazz);
    }
}
