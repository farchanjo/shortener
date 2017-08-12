package br.eti.archanjo.shortener.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Roles {
    USER, ADMIN;

    @JsonValue
    public int toValue() {
        return ordinal();
    }
}
