package br.eti.archanjo.velociraptor.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
    ENABLED, DISABLED;

    @JsonValue
    public int toValue() {
        return ordinal();
    }
}
