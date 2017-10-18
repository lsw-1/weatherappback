package com.example.javaprep.models.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiTemperature {
    private String temp;

    public ApiTemperature() {
    }

    public ApiTemperature(String temp) {
        this.temp = temp;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
}
