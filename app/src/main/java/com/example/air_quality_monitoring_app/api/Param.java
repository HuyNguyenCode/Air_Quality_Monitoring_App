package com.example.air_quality_monitoring_app.api;

public class Param {
    String type;
    String value;

    boolean temporary;

    public Param(String type, String value, boolean temporary) {
        this.type = type;
        this.value = value;
        this.temporary = temporary;
    }
}
