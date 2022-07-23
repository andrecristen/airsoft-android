package com.ddm.airsoftorganize.models;

import java.io.Serializable;
import java.util.Date;

public class UserTeam implements Serializable {

    private String id;
    private String creator;
    private String name;
    private City city;

    public UserTeam(String id, String creator, String name, City city) {
        this.id = id;
        this.creator = creator;
        this.name = name;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
