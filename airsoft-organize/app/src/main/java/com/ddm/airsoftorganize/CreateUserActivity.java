package com.ddm.airsoftorganize;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ddm.airsoftorganize.response.DefaultResponse;
import com.ddm.airsoftorganize.retrofit.RetrofitInitializer;
import com.ddm.airsoftorganize.util.MaskEditUtil;

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

        EditText editTextDateBith = findViewById(R.id.createBirthDate);
        editTextDateBith.addTextChangedListener(MaskEditUtil.mask(editTextDateBith, MaskEditUtil.FORMAT_DATE));

        Button btnCreate = findViewById(R.id.confirmBtn);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog progress = new ProgressDialog(context);
                progress.setTitle("Carregando..");
                progress.setMessage("Aguarde a validação de cadastro...");
                progress.setCancelable(false);
                progress.show();
                EditText fullName = findViewById(R.id.createFullname);
                EditText birthDate = findViewById(R.id.createBirthDate);
                String birtDateInput = birthDate.getText().toString();
                //API Format Y-m-d
                String birthDateSend = birtDateInput.substring(6, birtDateInput.length()) + "-" + birtDateInput.substring(3, 5) + "-" + birtDateInput.substring(0, 2);
                EditText username = findViewById(R.id.createUsername);
                EditText password = findViewById(R.id.createPassword);
                EditText confirmPassowrd = findViewById(R.id.createConfirmPassword);
                if (password.getText().toString().equals(confirmPassowrd.getText().toString())){
                    RequestBody requestBody = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM)
                            .addFormDataPart("nomeCompleto", fullName.getText().toString())
                            .addFormDataPart("dataNascimento", birthDateSend)
                            .addFormDataPart("email", username.getText().toString())
                            .addFormDataPart("senha", password.getText().toString())
                            .build();
                    Call<DefaultResponse> call = new RetrofitInitializer().user().executeCreateUser(requestBody);
                    call.enqueue(new Callback<DefaultResponse>() {
                        @Override
                        public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                            progress.dismiss();
                            if (response.body() != null) {
                                if (response.body().getSuccess().equals("true")) {
                                    Toast.makeText(context, "Usuário cadastrado com sucesso", Toast.LENGTH_LONG).show();
                                    Intent login = new Intent(view.getContext(), LoginActivity.class);
                                    startActivity(login);
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
                }else{
                    progress.dismiss();
                    Toast.makeText(context, "Senha e confirmação de senha diferentes", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}