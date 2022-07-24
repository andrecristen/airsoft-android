package com.ddm.airsoftorganize.adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ddm.airsoftorganize.HomeActivity;
import com.ddm.airsoftorganize.R;
import com.ddm.airsoftorganize.models.UserSession;
import com.ddm.airsoftorganize.response.AuthResponse;
import com.ddm.airsoftorganize.response.DefaultResponse;
import com.ddm.airsoftorganize.response.UserTeamResponse;
import com.ddm.airsoftorganize.retrofit.RetrofitInitializer;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                                enterUserTeam(userTeam.getId().toString());
                            }
                        })
                        .setNegativeButton("Não", null)
                        .show();
            }
        });
    }

    private void enterUserTeam(String userTeamId) {
        ProgressDialog progress = new ProgressDialog(context);
        progress.setTitle("Carregando");
        progress.setMessage("Realizando inscrição na equipe.");
        progress.setCancelable(false);
        progress.show();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("equipe", userTeamId)
                .build();
        String token = UserSession.getInstance(null).token;
        Call<DefaultResponse> call = new RetrofitInitializer().userTeam().enterUserTeam(token, requestBody);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                progress.dismiss();
                if (response.body() != null) {
                    if (response.body().getSuccess().equals("true")) {
                        Toast.makeText(context, "Sucesso ao entrar na equipe.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(context, "Erro ao entrar na equipe, tente novamente.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(context, "Erro ao entrar na equipe.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(context, "Falha na requisição", Toast.LENGTH_LONG).show();
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
