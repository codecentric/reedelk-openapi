package com.reedelk.openapi.commons;

public class Precondition {

    private Precondition() {
    }

    public static void checkNotNull(String propertyName, Object value) {
        if (value == null) {
            throw new IllegalArgumentException(String.format("Property '%s' is MANDATORY.", propertyName));
        }
    }

    public static void checkTrue(String propertyName, Boolean value) {
        if (!value) {
            throw new IllegalArgumentException(String.format("Property '%s' must be true.", propertyName));
        }
    }
}
