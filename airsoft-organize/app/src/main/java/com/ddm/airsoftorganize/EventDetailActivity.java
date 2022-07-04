package com.ddm.airsoftorganize;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ddm.airsoftorganize.models.Event;

import java.text.SimpleDateFormat;

public class EventDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        setContentView(R.layout.activity_event_detail);
        Bundle bundle = getIntent().getExtras();
        Event event = bundle.getParcelable("event");
        TextView eventName = findViewById(R.id.eventDetailName);
        TextView getInitialDate = findViewById(R.id.eventDetailInitialDate);
        TextView eventRules = findViewById(R.id.eventDetailRules);
        TextView eventCost = findViewById(R.id.eventDetailCost);
        eventName.setText(event.getName());
        getInitialDate.setText(formatter.format(event.getInitalDate()));
        eventRules.setText(event.getRules());
        eventCost.setText(event.getCost());
    }
}