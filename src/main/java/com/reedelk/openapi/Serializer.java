package com.reedelk.openapi;

import com.reedelk.openapi.v3.SerializerContext;

import java.util.Map;

public interface Serializer<T> {

    /**
     * Open API serialize.
     */
    Map<String, Object> serialize(SerializerContext context, T input);

}
