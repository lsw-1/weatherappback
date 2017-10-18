package com.example.javaprep.models.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum UnitFormat {
    @JsonProperty("celsius")
    CELSIUS,
    @JsonProperty("fahrenheit")
    FAHRENHEIT
}
