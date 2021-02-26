package de.codecentric.reedelk.openapi.v3;

import de.codecentric.reedelk.openapi.OpenApiModel;
import de.codecentric.reedelk.openapi.Serializer;
import de.codecentric.reedelk.openapi.commons.NavigationPath;
import de.codecentric.reedelk.openapi.v3.serializer.Serializers;

import java.util.Map;

public class SerializerContext {

    private final Serializers serializers;

    public SerializerContext(Serializers serializers) {
        this.serializers = serializers;
    }

    @SuppressWarnings("unchecked")
    public <T extends OpenApiModel> Map<String, Object> serialize(NavigationPath navigationPath, T input) {
        Serializer<T> serializer = (Serializer<T>) serializers.forType(input.getClass());
        return serializer.serialize(this, navigationPath, input);
    }
}
