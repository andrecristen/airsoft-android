package com.ddm.airsoftorganize.models;

public class User {
    private String email;
    private String password;
    private String birth_date;
    private String full_name;

    public User(String email, String password, String birth_date, String full_name) {
        this.email = email;
        this.password = password;
        this.birth_date = birth_date;
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    @Override
    public String toString() {
        return full_name;
    }
}
