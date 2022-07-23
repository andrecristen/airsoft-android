package com.ddm.airsoftorganize;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ddm.airsoftorganize.adapter.EventAdapter;
import com.ddm.airsoftorganize.models.Event;
import com.ddm.airsoftorganize.models.UserSession;
import com.ddm.airsoftorganize.response.EventDetailResponse;
import com.ddm.airsoftorganize.response.EventResponse;
import com.ddm.airsoftorganize.response.FetchEventResponse;
import com.ddm.airsoftorganize.retrofit.RetrofitInitializer;
import com.ddm.airsoftorganize.util.DateTimeUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Carregando..");
        progress.setMessage("Realizando busca do evento selecionado...");
        progress.setCancelable(false);
        progress.show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        Bundle bundle = getIntent().getExtras();
        String event = bundle.getString("event");
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
}