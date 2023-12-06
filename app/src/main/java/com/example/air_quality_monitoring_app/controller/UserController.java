package com.example.air_quality_monitoring_app.controller;

import com.example.air_quality_monitoring_app.model.User;

public class UserController {
    public User user;
    private static UserController instance;
    public static synchronized UserController getInstance() {
        if (instance == null) {
            instance = new UserController();
        }
        return instance;
    }

    public String token;

}
