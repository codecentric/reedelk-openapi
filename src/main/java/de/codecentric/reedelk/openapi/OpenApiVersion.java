package de.codecentric.reedelk.openapi;

import de.codecentric.reedelk.openapi.commons.DataFormat;
import de.codecentric.reedelk.openapi.v3.DeserializerContext;
import de.codecentric.reedelk.openapi.v3.deserializer.Deserializers;
import de.codecentric.reedelk.openapi.v3.model.OpenApiObject;

import java.util.Map;

public enum OpenApiVersion {

    v30() {

        @Override
        boolean isSupported(String openapi) {
            return openapi.startsWith("3.0");
        }

        @Override
        public String displayName() {
            return "3.0.x";
        }

        @Override
        public OpenApiObject deserialize(Map<String, Object> openApiMap,
                                         Map<Class<?>, Deserializer<?>> overridden,
                                         DataFormat dataFormat) {
            Deserializers deserializers = new Deserializers(overridden);
            DeserializerContext context =  new DeserializerContext(deserializers, dataFormat);
            return context.deserialize(OpenApiObject.class, openApiMap);
        }
    };

    abstract boolean isSupported(String openapi);

    public abstract String displayName();

    public abstract OpenApiObject deserialize(
            Map<String, Object> openApiMap,
            Map<Class<?>, Deserializer<?>> overridden,
            DataFormat openApiFormat);

}
