package com.reedelk.openapi;

public class Precondition {

    public static void checkNotNull(String propertyName, String value) {
        if (value == null) {
            throw new IllegalArgumentException(String.format("Property '%s' is mandatory", propertyName));
        }
    }
}
