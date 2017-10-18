package com.example.javaprep.models;

import com.example.javaprep.models.enums.UnitFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @NotNull
    private String location;
    @NotNull
    private UnitFormat unit;

    public City() {

    }

    public City(String location, UnitFormat unit) {
        this.location = location;
        this.unit = unit;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public UnitFormat getUnit() {
        return unit;
    }

    public String getFormattedUnit() {
        if (unit.equals(UnitFormat.CELSIUS)){
            return "metric";
        } else {
            return "imperial";
        }
    }

    public void setUnit(UnitFormat unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "City{" +
                "id='" + id + '\'' +
                ", location='" + location + '\'' +
                ", unit=" + unit +
                '}';
    }
}
