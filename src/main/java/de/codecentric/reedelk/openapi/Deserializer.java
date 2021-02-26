package de.codecentric.reedelk.openapi;

import de.codecentric.reedelk.openapi.v3.DeserializerContext;

import java.util.Map;

public interface Deserializer<T> {

    /**
     * Open API deserialize.
     */
    T deserialize(DeserializerContext context, Map<String, Object> serialized);
}
