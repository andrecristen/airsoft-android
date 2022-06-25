package com.ddm.airsoftorganize.models;

public class City {
    private String id;
    private String name;
    private State state;

    @Override
    public String toString() {
        return "City{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", state=" + state +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public City(String id, String name, State state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }
}
