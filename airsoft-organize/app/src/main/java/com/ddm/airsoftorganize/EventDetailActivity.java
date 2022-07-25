package com.ddm.airsoftorganize;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ddm.airsoftorganize.models.UserSession;
import com.ddm.airsoftorganize.response.ClassOperatorResponse;
import com.ddm.airsoftorganize.response.DefaultResponse;
import com.ddm.airsoftorganize.response.EventDetailResponse;
import com.ddm.airsoftorganize.response.EventDetailedTeamResponse;
import com.ddm.airsoftorganize.response.FetchClassOperatorResponse;
import com.ddm.airsoftorganize.retrofit.RetrofitInitializer;
import com.ddm.airsoftorganize.util.ArrayUtil;
import com.ddm.airsoftorganize.util.DateTimeUtil;
import com.ddm.airsoftorganize.util.ImageUrlSetUtil;

import java.util.Date;
import java.util.LinkedHashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventDetailActivity extends AppCompatActivity {

    AppCompatActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.context = this;
        ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Carregando");
        progress.setMessage("Realizando busca do evento selecionado.");
        progress.setCancelable(false);
        progress.show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        Bundle bundle = getIntent().getExtras();
        String event = bundle.getString("event");
        String finished = bundle.getString("finished");
        String token = UserSession.getInstance(null).token;
        Call<EventDetailResponse> call = new RetrofitInitializer().event().eventDetails(event, token);
        call.enqueue(new Callback<EventDetailResponse>() {
            @Override
            public void onResponse(Call<EventDetailResponse> call, Response<EventDetailResponse> response) {
                progress.dismiss();
                if (response.isSuccessful()) {
                    TextView eventName = findViewById(R.id.eventDetailName);
                    TextView getInitialDate = findViewById(R.id.eventDetailInitialDate);
                    TextView eventRules = findViewById(R.id.eventDetailRules);
                    TextView eventCost = findViewById(R.id.eventDetailCost);
                    eventName.setText(response.body().getEvento().getNome());
                    Date initialDate = DateTimeUtil.stringToDateTime(response.body().getEvento().getDataInicio(), DateTimeUtil.FORMAT_DATE_DEFAULT);
                    getInitialDate.setText(DateTimeUtil.dateTimeToString(initialDate, DateTimeUtil.FORMAT_DATE_BRAZILIAN));
                    eventRules.setText(response.body().getEvento().getRegras());
                    eventCost.setText(response.body().getEvento().getCusto());
                    if (response.body().getEvento().getImagem() != null) {
                        new ImageUrlSetUtil((ImageView) findViewById(R.id.eventDetailImageUrl)).execute(response.body().getEvento().getImagem().toString());
                    }
                    LinearLayout main_layer = (LinearLayout) findViewById(R.id.content_event_detail_scroll);
                    if (response.body().getEvento().getTimeUsuarioInscrito() != null && finished == null) {
                        //Usuário já está inscrito
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        params.gravity = Gravity.CENTER_HORIZONTAL;
                        LinearLayout layout = new LinearLayout(getApplicationContext());
                        LinearLayout.LayoutParams paramsButton = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        layout.setOrientation(LinearLayout.VERTICAL);
                        layout.setLayoutParams(params);
                        layout.setPadding(10, 10, 10, 10);
                        Button buttonUnsubscribeTeam = new Button(getApplicationContext());
                        buttonUnsubscribeTeam.setLayoutParams(paramsButton);
                        buttonUnsubscribeTeam.setText("Cancelar minha inscrição");
                        buttonUnsubscribeTeam.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                LinkedHashMap timeInscrito = (LinkedHashMap) response.body().getEvento().getTimeUsuarioInscrito();
                                unsubscribeEvent(timeInscrito.get("id").toString());
                            }
                        });
                        layout.addView(buttonUnsubscribeTeam);
                        main_layer.addView(layout);
                    } else if(finished == null) {
                        //Cria um botão pra cada time para o usuário se inscrever
                        for (EventDetailedTeamResponse team : response.body().getEvento().getTimes()) {
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                            params.gravity = Gravity.CENTER_HORIZONTAL;
                            LinearLayout layout = new LinearLayout(getApplicationContext());
                            LinearLayout.LayoutParams paramsButton = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                            layout.setOrientation(LinearLayout.VERTICAL);
                            layout.setLayoutParams(params);
                            layout.setPadding(10, 10, 10, 10);
                            Button buttonSubscribeTeam = new Button(getApplicationContext());
                            buttonSubscribeTeam.setLayoutParams(paramsButton);
                            String nameEvent = "";
                            if (team.getMaximoOperadores() != null) {
                                nameEvent = "Entrar em " + team.getNome() + " (" + team.getInscritos() + "/" + team.getMaximoOperadores() + ")";
                            } else {
                                nameEvent = "Entrar em " + team.getNome() + " (" + team.getInscritos() + ")";
                            }
                            buttonSubscribeTeam.setText(nameEvent);
                            buttonSubscribeTeam.setPadding(20, 20, 20, 20);
                            if (team.getCor() != null) {
                                buttonSubscribeTeam.setBackgroundColor(Color.parseColor(team.getCor().toString()));
                            }
                            buttonSubscribeTeam.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    loadClassOperatorModalSelect(team.getId().toString());
                                }
                            });
                            layout.addView(buttonSubscribeTeam);
                            main_layer.addView(layout);
                        }
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Não foi possível realizar a busca do evento.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EventDetailResponse> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadClassOperatorModalSelect(String teamId) {
        AppCompatActivity contextLocal = this.context;
        ProgressDialog progress = new ProgressDialog(this.context);
        progress.setTitle("Carregando");
        progress.setMessage("Realizando busca das classes para jogo.");
        progress.setCancelable(false);
        progress.show();
        Call<FetchClassOperatorResponse> call = new RetrofitInitializer().classOperator().fetchAllClassOperator();
        call.enqueue(new Callback<FetchClassOperatorResponse>() {
            @Override
            public void onResponse(Call<FetchClassOperatorResponse> call, Response<FetchClassOperatorResponse> response) {
                progress.dismiss();
                if (response.isSuccessful()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(contextLocal);
                    builder.setTitle("Selecione sua classe no jogo");
                    String[] classOperatorNames = {};
                    String[] classOperatorIds = {};
                    for (ClassOperatorResponse classOperator : response.body().getClasses()) {
                        classOperatorNames = ArrayUtil.append(classOperatorNames, classOperator.getNome() + " (FPS Máx.: " + classOperator.getFpsMaximo() + ")");
                        classOperatorIds = ArrayUtil.append(classOperatorIds, classOperator.getId().toString());
                    }
                    String[] finalClassOperatorIds = classOperatorIds;
                    builder.setItems(classOperatorNames, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String idClassOperatorSelected = finalClassOperatorIds[which];
                            if (!idClassOperatorSelected.isEmpty()) {
                                subscribeEvent(idClassOperatorSelected, teamId);
                            }
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    Toast.makeText(getApplicationContext(), "Não foi possível realizar a busca do evento.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FetchClassOperatorResponse> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void subscribeEvent(String idClassOperator, String idTeam) {
        ProgressDialog progress = new ProgressDialog(this.context);
        progress.setTitle("Carregando");
        progress.setMessage("Realizando inscrição no evento.");
        progress.setCancelable(false);
        progress.show();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("time", idTeam)
                .addFormDataPart("classe", idClassOperator)
                .build();
        String token = UserSession.getInstance(null).token;
        Call<DefaultResponse> call = new RetrofitInitializer().event().subscribeEvento(requestBody, token);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                progress.dismiss();
                if (response.body() != null) {
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Erro ao inscrever-se do evento.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<DefaultResponse> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void unsubscribeEvent(String idTeam) {
        ProgressDialog progress = new ProgressDialog(this.context);
        progress.setTitle("Carregando");
        progress.setMessage("Realizando desinscrição no evento.");
        progress.setCancelable(false);
        progress.show();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("time", idTeam)
                .build();
        String token = UserSession.getInstance(null).token;
        Call<DefaultResponse> call = new RetrofitInitializer().event().unsubscribeEvento(requestBody, token);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                progress.dismiss();
                if (response.body() != null) {
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Erro ao desinscrever-se do evento.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}