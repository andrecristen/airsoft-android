package com.ddm.airsoftorganize.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ddm.airsoftorganize.R;
import com.ddm.airsoftorganize.models.Event;

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

        holder.eventName.setText(eventList.get(position).getName());
        holder.eventId.setText(eventList.get(position).getField().getNome());
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView eventName, eventId;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            eventName=itemView.findViewById(R.id.event_name);
            eventId=itemView.findViewById(R.id.event_id);
        }
    }
}
