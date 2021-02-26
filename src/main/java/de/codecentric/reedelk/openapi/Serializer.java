package de.codecentric.reedelk.openapi;

import de.codecentric.reedelk.openapi.commons.NavigationPath;
import de.codecentric.reedelk.openapi.v3.SerializerContext;

import java.util.Map;

public interface Serializer<T> {

    /**
     * Open API serialize.
     */
    Map<String, Object> serialize(SerializerContext context, NavigationPath navigationPath, T input);

}
