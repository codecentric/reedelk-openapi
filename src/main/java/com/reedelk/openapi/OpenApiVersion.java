package com.reedelk.openapi;

import com.reedelk.openapi.v3.DeserializerContext;
import com.reedelk.openapi.v3.deserializer.Deserializers;
import com.reedelk.openapi.v3.model.OpenApiObject;

import java.util.Map;

public enum OpenApiVersion {

    v30() {

        @Override
        boolean isSupported(String openapi) {
            return openapi.startsWith("3.0");
        }

        @Override
        public OpenApiObject deserialize(Map<String, Object> openApiMap, Map<Class<?>, Deserializer<?>> overridden) {
            Deserializers deserializers = new Deserializers(overridden);
            DeserializerContext context =  new DeserializerContext(deserializers);
            return context.deserialize(OpenApiObject.class, openApiMap);
        }
    };

    abstract boolean isSupported(String openapi);

    public abstract OpenApiObject deserialize(Map<String, Object> openApiMap, Map<Class<?>, Deserializer<?>> overridden);

}
