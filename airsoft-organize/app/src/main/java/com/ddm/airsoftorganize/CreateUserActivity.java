package com.ddm.airsoftorganize;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ddm.airsoftorganize.models.AuthResponse;
import com.ddm.airsoftorganize.models.UserSession;
import com.ddm.airsoftorganize.retrofit.RetrofitInitializer;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        Context context = this;

        Button btnCreate = findViewById(R.id.confirmBtn);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog progress = new ProgressDialog(context);
                progress.setTitle("Carregando..");
                progress.setMessage("Aguarde a validação de cadastro...");
                progress.setCancelable(false);
                progress.show();
                EditText full_name = findViewById(R.id.createFullname);
                EditText birth_date = findViewById(R.id.createBirthDate);
                EditText username = findViewById(R.id.createUsername);
                EditText password = findViewById(R.id.createPassword);
                EditText confirm_passowrd = findViewById(R.id.createConfirmPassword);
//                todo fazer validacao senha e confirmacao de senha
//                if (confirm_passowrd != password){
//                    progress.dismiss();
//                    Toast.makeText(context, "Senha e confirmação de senha inválidas", Toast.LENGTH_LONG).show();
//                }
                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("nomeCompleto", full_name.getText().toString())
                        .addFormDataPart("dataNascimento", birth_date.getText().toString())
                        .addFormDataPart("email", username.getText().toString())
                        .addFormDataPart("senha", password.getText().toString())
                        .build();
                Call<AuthResponse> call = new RetrofitInitializer().user().executeCreateUser(requestBody);
                call.enqueue(new Callback<AuthResponse>() {
                    @Override
                    public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                        progress.dismiss();
                        if (response.body() != null) {
                            if (response.body().getSuccess().equals("true") && !response.body().getToken().isEmpty()) {
                                UserSession userSession = UserSession.getInstance(response.body().getToken());
                                Toast.makeText(context, "Usuário cadastrado com sucesso", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(context, "Erro ao cadastrar: " + response.body().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(context, "Erro ao cadastrar usuário, verifique os dados e tente novamente.", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<AuthResponse> call, Throwable t) {
                        progress.dismiss();
                        Toast.makeText(context, "Falha na requisição", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}