package com.reedelk.openapi.commons;

public class Precondition {

    public static void checkNotNull(String propertyName, Object value) {
        if (value == null) {
            throw new IllegalArgumentException(String.format("Property '%s' is MANDATORY.", propertyName));
        }
    }
}
