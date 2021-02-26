package com.example.universityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    private TextInputEditText etEmail,etPassword;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private FirebaseAuth mAuth;
    private String emailId, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Drawable tick = getResources().getDrawable(R.drawable.ic_tick);
        tick.setBounds(0, 0, tick.getIntrinsicWidth(), tick.getIntrinsicHeight());

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.forgotPass).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(!emailId.isEmpty()) {

                    Intent intent = new Intent(SignInActivity.this, ForgotPasswordActivity.class);
                    intent.putExtra("email", emailId);
                    startActivity(intent);

                    //TODO - Keep user Signed in.

                } else {

                    etEmail.setError("Required to reset password");

                }

            }
        });

        findViewById(R.id.btnSignIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                emailId = etEmail.getText().toString();
                password = etPassword.getText().toString();

                if(emailId.trim().matches(emailPattern) && !password.equals("")) {

                    mAuth.signInWithEmailAndPassword(emailId, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {

                                        startActivity(new Intent(SignInActivity.this, MainActivity.class));

                                    } else {

                                        Toast.makeText(SignInActivity.this, task.getException().getMessage().toString(), Toast.LENGTH_SHORT).show();

                                    }

                                }
                            });
                } else {

                    if(password.equals("")) {

                        etPassword.setError("Password Required");

                    }

                    if(emailId.equals("")) {

                        etEmail.setError("Email Address Required");

                    } else if(!emailId.matches(emailPattern)) {

                        etEmail.setError("Invalid Email Address");

                    }
                }

            }
        });

        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.toString().trim().matches(emailPattern)) {

                    etEmail.setCompoundDrawables(null, null, tick, null);

                }

            }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

    }
}