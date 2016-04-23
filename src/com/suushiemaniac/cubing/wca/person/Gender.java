package com.suushiemaniac.cubing.wca.person;

public enum Gender {
    MALE, FEMALE, UNKNOWN;

    public static Gender fromIdentifier(String identifier) {
        identifier = identifier.toLowerCase();

        switch (identifier) {
            case "m":
                return MALE;
            case "f":
                return FEMALE;
            default:
                return UNKNOWN;
        }
    }
}
