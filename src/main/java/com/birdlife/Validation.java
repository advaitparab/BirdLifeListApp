package com.birdlife;

import java.util.Objects;

public final class Validation {
    private Validation() {}

    public static String requireNonBlank(String value, String fieldName) {
        Objects.requireNonNull(fieldName, "fieldName");
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " must not be blank");
        }
        return value;
    }

    public static <T> T requireNonNull(T value, String fieldName) {
        if (value == null) {
            throw new NullPointerException(fieldName + " must not be null");
        }
        return value;
    }
}
