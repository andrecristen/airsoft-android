package com.ddm.airsoftorganize.models;

public final class UserSession {

    private static UserSession instance;
    public String token;

    private UserSession(String value) {
        this.token = value;
    }

    public String getToken() {
        return token;
    }

    public static UserSession getInstance(String value) {
        if (instance == null) {
            instance = new UserSession(value);
        }
        return instance;
    }
}