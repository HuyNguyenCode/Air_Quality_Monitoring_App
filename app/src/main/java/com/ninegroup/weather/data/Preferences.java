package com.ninegroup.weather.data;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Preferences implements Serializable {
    private String username;
    private int theme;
    private boolean notificationsEnabled;

    public Preferences(String username, int theme, boolean notificationsEnabled) {
        this.username = username;
        this.theme = theme;
        this.notificationsEnabled = notificationsEnabled;
    }

    // Getters and setters for each field
}
