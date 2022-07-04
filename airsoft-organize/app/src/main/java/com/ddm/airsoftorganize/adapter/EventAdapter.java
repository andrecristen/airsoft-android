package com.ddm.airsoftorganize.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ddm.airsoftorganize.EventDetailActivity;
import com.ddm.airsoftorganize.HomeActivity;
import com.ddm.airsoftorganize.R;
import com.ddm.airsoftorganize.models.Event;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    List<Event> eventList;
    Context context;

    public EventAdapter(Context context, List<Event> eventList) {
        this.context = context;
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.event_item, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull EventAdapter.ViewHolder holder, int position) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        holder.eventName.setText(eventList.get(position).getName());
        holder.eventId.setText(eventList.get(position).getField().getName());
        holder.eventCost.setText(eventList.get(position).getCost());

        holder.eventInitialDate.setText(formatter.format(eventList.get(position).getInitalDate()));
        Date currentDate = new Date();
        currentDate.setHours(0);
        currentDate.setMinutes(0);
        currentDate.setSeconds(0);
        final int pos = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Event event = eventList.get(pos);
                Intent intent = new Intent(view.getContext(), EventDetailActivity.class);
                intent.putExtra("event", (Parcelable) event);
                view.getContext().startActivity(intent);
            }
        });
        long timeInitial = eventList.get(position).getInitalDate().getTime();
        long timeCurrent = currentDate.getTime();
        if (timeInitial >= timeCurrent) {
            holder.firstLinear.setBackgroundColor(Color.parseColor("#57db57"));
        } else {
            holder.firstLinear.setBackgroundColor(Color.parseColor("#db5757"));
        }
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView eventName, eventId, eventInitialDate, eventCost;
        LinearLayout firstLinear;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            eventName=itemView.findViewById(R.id.event_name);
            eventId=itemView.findViewById(R.id.event_id);
            eventInitialDate=itemView.findViewById(R.id.event_cost);
            eventCost=itemView.findViewById(R.id.event_initial_date);
            firstLinear=itemView.findViewById(R.id.firstLinear);
        }
    }
}
