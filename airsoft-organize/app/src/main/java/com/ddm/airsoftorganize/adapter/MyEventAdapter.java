package com.ddm.airsoftorganize.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.ddm.airsoftorganize.response.EventResponse;

import java.util.List;

public class MyEventAdapter extends EventAdapter {

    public MyEventAdapter(Context context, List<EventResponse> eventList) {
        super(context, eventList);
    }

    public void onClickItemList(Integer pos, View view) {
//        EventResponse event = eventList.get(pos);
//        Intent intent = new Intent(view.getContext(), MyEventDetailActivity.class);
//        intent.putExtra("event", event.getId().toString());
//        view.getContext().startActivity(intent);
    }
}