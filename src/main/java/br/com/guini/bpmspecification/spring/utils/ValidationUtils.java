package br.com.guini.bpmspecification.spring.utils;

import java.util.Objects;

public final class ValidationUtils {

    private ValidationUtils() {}

    public static void NotEmpty(Object[] value) {
        Objects.requireNonNull(value);

        if (value.length == 0) {
            throw new RuntimeException("Value is empty");
        }
    }

    public static void NotBlank(String value) {
        Objects.requireNonNull(value);

        if (value.isBlank()) {
            throw new RuntimeException("Value is blank");
        }
    }
}
