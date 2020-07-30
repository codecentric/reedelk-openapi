package com.reedelk.openapi.commons;

import com.reedelk.openapi.v3.DeserializerContext;

import java.util.Map;

public interface Deserializer<T> {

    /**
     * Open API deserialize.
     */
    T deserialize(DeserializerContext context, Map<String, Object> serialized);
}
