package com.ddm.airsoftorganize.response;

import com.ddm.airsoftorganize.models.Event;

import java.util.List;

public class FetchEventResponse {

    List<Event> eventList;
    String success;

    String error;

    public FetchEventResponse(List<Event> eventList, String success, String error) {
        this.eventList = eventList;
        this.success = success;
        this.error = error;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
