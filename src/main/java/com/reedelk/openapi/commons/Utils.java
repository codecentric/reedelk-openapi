package com.reedelk.openapi.commons;

public class Utils {

    private Utils() {
    }

    public static boolean isBlank(final CharSequence sequence) {
        if (sequence == null) return true;

        int sequenceLength = sequence.length();
        if (sequenceLength == 0) return true;

        for (int i = 0; i < sequenceLength; i++) {
            if (!Character.isWhitespace(sequence.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
