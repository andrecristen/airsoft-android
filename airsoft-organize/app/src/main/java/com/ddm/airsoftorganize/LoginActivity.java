package com.ddm.airsoftorganize;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ddm.airsoftorganize.controller.UserController;
import com.ddm.airsoftorganize.models.UserSession;

public class LoginActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onRestart() {
        super.onRestart();
        if (!UserSession.getInstance(UserSession.tokenEmptySession).token.equals(UserSession.tokenEmptySession)) {
            Intent home = new Intent(context, HomeActivity.class);
            startActivity(home);
        }
    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.context = this;

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
                EditText login = findViewById(R.id.loginUsername);
                EditText password = findViewById(R.id.loginPassword);
                if(isEmpty(login) || isEmpty(password)) {
                    Toast.makeText(context, "E-mail e(ou) Senha incorreto(s)", Toast.LENGTH_LONG).show();
                } else {
                    UserController userController = new UserController();
                    userController.login(login.getText().toString(), password.getText().toString(), context);
                }
            }
        });
    }

}