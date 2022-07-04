package com.ddm.airsoftorganize.models;

public class Field {
    private String id;
    private String name;
    private City city;

    @Override
    public String toString() {
        return "Field{" +
                "id='" + id + '\'' +
                ", nome='" + name + '\'' +
                ", city=" + city +
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

    public void setNome(String nome) {
        this.name = nome;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Field(String id, String nome, City city) {
        this.id = id;
        this.name = nome;
        this.city = city;
    }
}
