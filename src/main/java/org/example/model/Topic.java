package org.example;

public enum Topic {
    COMEDY,
    ROMANCE,
    OTHER;

    public static Topic fromString(String value) {
        return switch (value) {
            case "COMEDY" -> COMEDY;
            case "ROMANCE" -> ROMANCE;
            default -> OTHER;
        };
    }
}
