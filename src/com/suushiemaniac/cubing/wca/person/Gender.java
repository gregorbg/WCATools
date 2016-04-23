package com.suushiemaniac.cubing.wca.person;

public enum Gender {
    MALE("m"), FEMALE("f"), UNKNOWN("?");

    private String id;

    Gender(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public static Gender fromId(String identifier) {
        for (Gender g : values()) {
            if (g.getId().equals(identifier.toLowerCase())) {
                return g;
            }
        }

        return UNKNOWN;
    }
}
