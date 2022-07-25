package com.ddm.airsoftorganize.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.ddm.airsoftorganize.EventDetailActivity;
import com.ddm.airsoftorganize.response.EventResponse;
import com.ddm.airsoftorganize.util.DateTimeUtil;

import java.util.Date;
import java.util.List;

public class MyEventAdapter extends EventAdapter {

    public MyEventAdapter(Context context, List<EventResponse> eventList) {
        super(context, eventList);
        this.isMyRegistrations = true;
    }

    public void onClickItemList(Integer pos, View view) {
        EventResponse event = eventList.get(pos);
        Intent intent = new Intent(view.getContext(), EventDetailActivity.class);
        intent.putExtra("event", event.getId().toString());
        Date currentDate = new Date();
        currentDate.setHours(0);
        currentDate.setMinutes(0);
        currentDate.setSeconds(0);
        Date dataInicio = DateTimeUtil.stringToDateTime(event.getDataInicio(), DateTimeUtil.FORMAT_DATE_DEFAULT);
        long timeInitial = dataInicio.getTime();
        long timeCurrent = currentDate.getTime();
        if (timeInitial <= timeCurrent) {
            intent.putExtra("finished", "true");
        }
        view.getContext().startActivity(intent);
    }
}
