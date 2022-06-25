package com.ddm.airsoftorganize.models;

public class State {
    private String id;
    private String name;
    private String sigla;

    @Override
    public String toString() {
        return "State{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sigla='" + sigla + '\'' +
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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public State(String id, String name, String sigla) {
        this.id = id;
        this.name = name;
        this.sigla = sigla;
    }
}
