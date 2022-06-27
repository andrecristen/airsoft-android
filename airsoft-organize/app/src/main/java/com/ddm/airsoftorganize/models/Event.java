package com.ddm.airsoftorganize.models;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {
    private String id;
    private String name;
    private Date initalDate;
    private Date finalDate;
    private String rules;
    private String cost;
    private String image_url;
    private Field field;

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", initalDate=" + initalDate +
                ", finalDate=" + finalDate +
                ", rules='" + rules + '\'' +
                ", cost='" + cost + '\'' +
                ", image_url='" + image_url + '\'' +
                ", field=" + field +
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

    public Date getInitalDate() {
        return initalDate;
    }

    public void setInitalDate(Date initalDate) {
        this.initalDate = initalDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Event(String id, String name, Date initalDate, Date finalDate, String rules, String cost, String image_url, Field field) {
        this.id = id;
        this.name = name;
        this.initalDate = initalDate;
        this.finalDate = finalDate;
        this.rules = rules;
        this.cost = cost;
        this.image_url = image_url;
        this.field = field;
    }
}
