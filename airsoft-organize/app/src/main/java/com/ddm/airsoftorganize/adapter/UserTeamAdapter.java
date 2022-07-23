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
import com.ddm.airsoftorganize.models.UserTeam;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class UserTeamAdapter extends RecyclerView.Adapter<UserTeamAdapter.ViewHolder> {

    List<UserTeam> userTeamList;
    Context context;

    public UserTeamAdapter(Context context, List<Event> userTeamList) {
        this.context = context;
        this.userTeamList = userTeamList;
    }

    @NonNull
    @Override
    public UserTeamAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.event_item, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull UserTeamAdapter.ViewHolder holder, int position) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        holder.eventName.setText(userTeamList.get(position).getName());
        final int pos = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserTeam userTeam = userTeamList.get(pos);
                Intent intent = new Intent(view.getContext(), EventDetailActivity.class);
                intent.putExtra("userTeam", (Parcelable) userTeam);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userTeamList.size();
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
