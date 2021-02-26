package com.example.universityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class ForgotPasswordActivity extends AppCompatActivity {

    private String emailId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        emailId = getIntent().getStringExtra("email");

        Button useEmail = findViewById(R.id.forgotEmail);
        Button usePhone = findViewById(R.id.forgotPhone);

        useEmail.setText("Via Email:-\n" + emailId);

        //TODO - Add forgot password.

    }
}