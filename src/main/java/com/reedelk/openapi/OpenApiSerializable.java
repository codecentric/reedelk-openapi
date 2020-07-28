package com.reedelk.openapi;

import java.util.Map;

public interface OpenApiSerializable {

    /**
     * Open API serialize.
     */
    Map<String, Object> serialize();

    /**
     * Open API deserialize.
     */
    void deserialize(Map<String, Object> serialized);

}
