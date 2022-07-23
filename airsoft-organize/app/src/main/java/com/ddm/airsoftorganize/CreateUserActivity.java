package com.ddm.airsoftorganize;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ddm.airsoftorganize.controller.UserController;
import com.ddm.airsoftorganize.util.MaskEditUtil;

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

                EditText fullName = findViewById(R.id.createFullname);
                EditText birthDate = findViewById(R.id.createBirthDate);
                String birtDateInput = birthDate.getText().toString();
                //API Format Y-m-d
                String birthDateSend = birtDateInput.substring(6, birtDateInput.length()) + "-" + birtDateInput.substring(3, 5) + "-" + birtDateInput.substring(0, 2);
                EditText username = findViewById(R.id.createUsername);
                EditText password = findViewById(R.id.createPassword);
                EditText confirmPassowrd = findViewById(R.id.createConfirmPassword);
                UserController userController = new UserController();
                userController.createUser(context, fullName.getText().toString(),
                        birthDateSend,
                        username.getText().toString(),
                        password.getText().toString(),
                        confirmPassowrd.getText().toString());
            }
        });
    }
}