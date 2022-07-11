package com.ddm.airsoftorganize.controller;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.ddm.airsoftorganize.HomeActivity;
import com.ddm.airsoftorganize.LoginActivity;
import com.ddm.airsoftorganize.models.UserSession;
import com.ddm.airsoftorganize.response.AuthResponse;
import com.ddm.airsoftorganize.response.DefaultResponse;
import com.ddm.airsoftorganize.retrofit.RetrofitInitializer;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserController {

    public void createUser(Context context, String fullName,
                           String birthDateSend,
                           String username,
                           String password,
                           String confirmPassowrd) {
        ProgressDialog progress = new ProgressDialog(context);
        progress.setTitle("Carregando..");
        progress.setMessage("Aguarde a validação de cadastro...");
        progress.setCancelable(false);
        progress.show();
        if (password.equals(confirmPassowrd)){
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("nomeCompleto", fullName)
                    .addFormDataPart("dataNascimento", birthDateSend)
                    .addFormDataPart("email", username)
                    .addFormDataPart("senha", password)
                    .build();
            Call<DefaultResponse> call = new RetrofitInitializer().user().executeCreateUser(requestBody);
            call.enqueue(new Callback<DefaultResponse>() {
                @Override
                public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                    progress.dismiss();
                    if (response.body() != null) {
                        if (response.body().getSuccess().equals("true")) {
                            Toast.makeText(context, "Usuário cadastrado com sucesso", Toast.LENGTH_LONG).show();
                            Intent login = new Intent(context, LoginActivity.class);
                            context.startActivity(login);
                        } else {
                            Toast.makeText(context, "Erro ao cadastrar: " + response.body().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(context, "Erro ao cadastrar usuário, verifique os dados e tente novamente", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<DefaultResponse> call, Throwable t) {
                    progress.dismiss();
                    Toast.makeText(context, "Falha na requisição", Toast.LENGTH_LONG).show();
                }
            });
        } else{
            progress.dismiss();
            Toast.makeText(context, "Senha e confirmação de senha diferentes", Toast.LENGTH_LONG).show();
        }
    }

    public void login(String login, String password, Context context) {
        ProgressDialog progress = new ProgressDialog(context);
        progress.setTitle("Carregando..");
        progress.setMessage("Aguarde a validação de login...");
        progress.setCancelable(false);
        progress.show();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("login", login)
                .addFormDataPart("password", password)
                .build();
        Call<AuthResponse> call = new RetrofitInitializer().auth().executeAuth(requestBody);
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                progress.dismiss();
                if (response.body() != null) {
                    if (response.body().getSuccess().equals("true") && !response.body().getToken().isEmpty()) {
                        UserSession.getInstance(response.body().getToken());
                        Toast.makeText(context, "Login efetuado com sucesso", Toast.LENGTH_LONG).show();
                        Intent home = new Intent(context, HomeActivity.class);
                        context.startActivity(home);
                    } else {
                        Toast.makeText(context, "Erro ao efetuar login: " + response.body().getMessage(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(context, "Erro ao efetuar login, verifique os dados e tente novamente.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(context, "Falha na requisição", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void logout(Context context) {
        UserSession.getInstance(UserSession.tokenEmptySession);
        Intent login = new Intent(context, LoginActivity.class);
        context.startActivity(login);
    }

}
