package com.ddm.airsoftorganize.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ddm.airsoftorganize.R;
import com.ddm.airsoftorganize.response.UserTeamResponse;

import java.util.List;

public class UserTeamAdapter extends RecyclerView.Adapter<UserTeamAdapter.ViewHolder> {

    List<UserTeamResponse> userTeamList;
    Context context;

    public UserTeamAdapter(Context context, List<UserTeamResponse> userTeamList) {
        this.context = context;
        this.userTeamList = userTeamList;
    }

    @NonNull
    @Override
    public UserTeamAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_team_item, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull UserTeamAdapter.ViewHolder holder, int position) {
        UserTeamResponse userTeam = userTeamList.get(position);
        holder.userTeamName.setText(userTeam.getNome());
        holder.userTeamCreator.setText(userTeam.getCriador());
        holder.userTeamId.setText(userTeam.getId().toString());
        holder.userTeamCity.setText(userTeam.getCidade().getNome() + " - " + userTeam.getCidade().getEstado().getSigla());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(view.getContext())
                        .setTitle("Confirmação")
                        .setMessage("Deseja realmente inscrever-se na equipe " + userTeam.getNome() + "?")
                        .setCancelable(false)
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("Não", null)
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return userTeamList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView userTeamName, userTeamId, userTeamCreator, userTeamCity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userTeamName = itemView.findViewById(R.id.userTeam_name);
            userTeamId = itemView.findViewById(R.id.userTeam_id);
            userTeamCreator = itemView.findViewById(R.id.userTeam_creator);
            userTeamCity = itemView.findViewById(R.id.userTeam_city);
        }
    }
}
