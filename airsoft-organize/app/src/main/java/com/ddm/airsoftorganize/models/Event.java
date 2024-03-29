package com.ddm.airsoftorganize.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Event implements Serializable, Parcelable {

    private String id;
    private String name;
    private Date initalDate;
    private Date finalDate;
    private String rules;
    private String cost;
    private String imageUrl;
    private Field field;

    protected Event(Parcel in) {
        id = in.readString();
        name = in.readString();
        initalDate = new Date(in.readString());
        finalDate = new Date(in.readString());
        rules = in.readString();
        cost = in.readString();
        imageUrl = in.readString();
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", initalDate=" + initalDate +
                ", finalDate=" + finalDate +
                ", rules='" + rules + '\'' +
                ", cost='" + cost + '\'' +
                ", image_url='" + imageUrl + '\'' +
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Event(String id, String name, Date initalDate, Date finalDate, String rules, String cost, String imageUrl, Field field) {
        this.id = id;
        this.name = name;
        this.initalDate = initalDate;
        this.finalDate = finalDate;
        this.rules = rules;
        this.cost = cost;
        this.imageUrl = imageUrl;
        this.field = field;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(formatter.format(initalDate));
        parcel.writeString(formatter.format(finalDate));
        parcel.writeString(rules);
        parcel.writeString(cost);
        parcel.writeString(imageUrl);
        parcel.writeString(field.getName());
    }
}
