package com.ddm.airsoftorganize.models;

public class Field {
    private String id;
    private String nome;
    private City city;

    @Override
    public String toString() {
        return "Field{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", city=" + city +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Field(String id, String nome, City city) {
        this.id = id;
        this.nome = nome;
        this.city = city;
    }
}
