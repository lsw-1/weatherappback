package com.example.javaprep.models.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiCurrentWeatherResponse {
    private ApiTemperature main;
    private String name;

    public ApiCurrentWeatherResponse() {
    }

    public ApiCurrentWeatherResponse(ApiTemperature main, String name) {
        this.main = main;
        this.name = name;
    }

    public ApiTemperature getMain() {
        return main;
    }

    public void setMain(ApiTemperature main) {
        this.main = main;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Temperature in " + name + " is " + main.getTemp();
    }
}
