package com.kseniyamargaretphotography.api.enums;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum UserRole {
    DEVELOPER,
    ADMIN,
    USER;

    public static Set<String> valuesAsStrings() {
        return Arrays.stream(UserRole.values()).map(Enum::toString).collect(Collectors.toSet());
    }
}
