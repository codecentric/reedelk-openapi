package com.reedelk.openapi.v3;

import com.reedelk.openapi.OpenApiModel;
import com.reedelk.openapi.Serializer;
import com.reedelk.openapi.v3.serializer.Serializers;

import java.util.Map;

public class SerializerContext {

    private final Serializers serializers;

    public SerializerContext(Serializers serializers) {
        this.serializers = serializers;
    }

    @SuppressWarnings("unchecked")
    public <T extends OpenApiModel> Map<String, Object> serialize(T input) {
        Serializer<T> serializer = (Serializer<T>) serializers.forType(input.getClass());
        return serializer.serialize(this, input);
    }
}
