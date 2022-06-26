package com.ddm.airsoftorganize;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ddm.airsoftorganize.models.AuthResponse;
import com.ddm.airsoftorganize.models.UserSession;
import com.ddm.airsoftorganize.retrofit.RetrofitInitializer;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Context context = this;

        Button btnCreate = findViewById(R.id.create_btn);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createUser = new Intent(view.getContext(), CreateUserActivity.class);
                startActivity(createUser);
            }
        });
        Button btnLogin = findViewById(R.id.login_btn);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("login", "teste@airsoftorganize.com")
                        .addFormDataPart("password", "12345")
                        .build();
                Call<AuthResponse> call = new RetrofitInitializer().auth().executeAuth(requestBody);
                call.enqueue(new Callback<AuthResponse>() {
                    @Override
                    public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                        if (response.body().getSuccess().equals("true") && !response.body().getToken().isEmpty()) {
                            UserSession userSession = UserSession.getInstance(response.body().getToken());
                            Toast.makeText(context, "Login efetuado com sucesso", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(context, "Erro ao efetuar login: " + response.body().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<AuthResponse> call, Throwable t) {
                        Toast.makeText(context, "Falha na requisição", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}