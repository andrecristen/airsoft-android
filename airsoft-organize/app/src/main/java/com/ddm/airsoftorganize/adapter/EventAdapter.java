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
import com.ddm.airsoftorganize.response.EventResponse;
import com.ddm.airsoftorganize.util.DateTimeUtil;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    List<EventResponse> eventList;
    Context context;

    public EventAdapter(Context context, List<EventResponse> eventList) {
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
        holder.eventName.setText(eventList.get(position).getNome());
        holder.eventId.setText(eventList.get(position).getCampo().getNome());
        holder.eventCost.setText(eventList.get(position).getId().toString());
        Date dataInicio = DateTimeUtil.stringToDateTime(eventList.get(position).getDataInicio(), DateTimeUtil.FORMAT_DATE_DEFAULT);
        holder.eventInitialDate.setText(DateTimeUtil.dateTimeToString(dataInicio, DateTimeUtil.FORMAT_DATE_BRAZILIAN));
        final int pos = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventResponse event = eventList.get(pos);
                Intent intent = new Intent(view.getContext(), EventDetailActivity.class);
                intent.putExtra("event", event.getId());
                view.getContext().startActivity(intent);
            }
        });
        Date currentDate = new Date();
        currentDate.setHours(0);
        currentDate.setMinutes(0);
        currentDate.setSeconds(0);
        long timeInitial = dataInicio.getTime();
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
            eventName = itemView.findViewById(R.id.event_name);
            eventId = itemView.findViewById(R.id.event_id);
            eventInitialDate = itemView.findViewById(R.id.event_cost);
            eventCost = itemView.findViewById(R.id.event_initial_date);
            firstLinear = itemView.findViewById(R.id.firstLinear);
        }
    }
}
