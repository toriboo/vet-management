package com.toribo.vet_menagement.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AnimalGender {
    MALE("male"),
    FEMALE("female"),
    UNDEFINED("undefined");

    private final String value;

    AnimalGender(String value) {
        this.value = value;
    }
    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static AnimalGender fromValue(String value) {
        for (AnimalGender gender : values()) {
            if (gender.value.equalsIgnoreCase(value)) {
                return gender;
            }
        }
        return UNDEFINED;
    }
}
