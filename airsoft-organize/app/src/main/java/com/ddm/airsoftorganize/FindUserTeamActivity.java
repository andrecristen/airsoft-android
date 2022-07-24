package com.ddm.airsoftorganize;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.ddm.airsoftorganize.adapter.EventAdapter;
import com.ddm.airsoftorganize.adapter.UserTeamAdapter;
import com.ddm.airsoftorganize.models.UserSession;
import com.ddm.airsoftorganize.response.EventResponse;
import com.ddm.airsoftorganize.response.FetchEventResponse;
import com.ddm.airsoftorganize.response.FetchUserTeamResponse;
import com.ddm.airsoftorganize.response.UserTeamResponse;
import com.ddm.airsoftorganize.retrofit.RetrofitInitializer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindUserTeamActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_user_team);
        AppCompatActivity context = this;
        recyclerView = (RecyclerView) findViewById(R.id.recycleUserTeam);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        ProgressDialog progress = new ProgressDialog(context);
        progress.setTitle("Carregando");
        progress.setMessage("Realizando busca de equipes.");
        progress.setCancelable(false);
        progress.show();
        List<UserTeamResponse> userTeamList = new ArrayList<>();
        String token = UserSession.getInstance(null).token;
        Call<FetchUserTeamResponse> call = new RetrofitInitializer().userTeam().fetchAllUserTeams(token);
        call.enqueue(new Callback<FetchUserTeamResponse>() {
            @Override
            public void onResponse(Call<FetchUserTeamResponse> call, Response<FetchUserTeamResponse> response) {
                progress.dismiss();
                if (response.isSuccessful()) {
                    for (UserTeamResponse userTeam : response.body().getEquipes()) {
                        userTeamList.add(userTeam);
                    }
                    recyclerView.setAdapter(new UserTeamAdapter(context, userTeamList));
                } else {
                    Toast.makeText(context, "Não foi possível realizar a busca de eventos.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FetchUserTeamResponse> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}