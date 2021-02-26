package com.example.universityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.universityapp.Models.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    private TextInputEditText etEmail,etPassword;
    private TextView tvSignIn;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private String emailId, password;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;

    public void signUp(View view) {

        emailId = etEmail.getText().toString();
        password = etPassword.getText().toString();

        if(emailId.trim().matches(emailPattern) && !password.equals("")) {

            mAuth.createUserWithEmailAndPassword(emailId,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()) {

                        Users users = new Users(emailId, password);
                        String id = task.getResult().getUser().getUid();
                        database.getReference().child("Users").child(id).setValue(users);
                        Toast.makeText(SignUpActivity.this, "User Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignUpActivity.this, OtpRequestActivity.class);
                        startActivity(intent);

                    } else {

                        Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                    }

                }});

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        tvSignIn = findViewById(R.id.toSignIn);

        database = FirebaseDatabase.getInstance();
        mAuth=FirebaseAuth.getInstance();

        tvSignIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(SignUpActivity.this, SignInActivity.class));

            }
        });

        etEmail.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.toString().trim().matches(emailPattern)) {

                    Drawable tick = getResources().getDrawable(R.drawable.ic_tick);
                    tick.setBounds(0, 0, tick.getIntrinsicWidth(), tick.getIntrinsicHeight());
                    etEmail.setCompoundDrawables(null, null, tick, null);

                } else {

                    etEmail.setCompoundDrawables(null, null, null, null);

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}