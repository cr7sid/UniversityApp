package com.example.universityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.universityapp.Models.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.ActionCodeResult;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class OtpVerifyActivity extends AppCompatActivity {

    private String number, otpSent, verificationId, id;
    private TextView tvOtpSent;
    private EditText etOtp1, etOtp2, etOtp3, etOtp4, etOtp5, etOtp6;
    private AppCompatButton btnVerify;
    FirebaseDatabase database;

    public void verifyOtp(View view) {

        String code =
                etOtp1.getText().toString() +
                etOtp2.getText().toString() +
                etOtp3.getText().toString() +
                etOtp4.getText().toString() +
                etOtp5.getText().toString() +
                etOtp6.getText().toString();

        if(verificationId != null) {

            PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                    verificationId,
                    code
            );

            FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()) {

                                //TODO - Add phone number to user profile and link email with phone

                                startActivity(new Intent(OtpVerifyActivity.this, MainActivity.class));

                            } else {

                                if (task.getException() instanceof FirebaseAuthInvalidCredentialsException)
                                    Toast.makeText(OtpVerifyActivity.this, "Invalid Code", Toast.LENGTH_SHORT).show();

                            }

                        }
                    });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verify);

        Intent intent = getIntent();
        verificationId = intent.getStringExtra("vId");
        number = intent.getStringExtra("number");

        tvOtpSent = findViewById(R.id.tvOtpSent);
        etOtp1 = findViewById(R.id.etOtp1);
        etOtp2 = findViewById(R.id.etOtp2);
        etOtp3 = findViewById(R.id.etOtp3);
        etOtp4 = findViewById(R.id.etOtp4);
        etOtp5 = findViewById(R.id.etOtp5);
        etOtp6 = findViewById(R.id.etOtp6);
        btnVerify = findViewById(R.id.btnVerify);

        if(btnVerify.isClickable()) {

            btnVerify.setClickable(false);

        }

        otpSent = "Enter the OTP sent to " + number;
        tvOtpSent.setText(otpSent);
        setUpOtpInputs();

        findViewById(R.id.resendOtp).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + number,
                        60,
                        TimeUnit.SECONDS,
                        OtpVerifyActivity.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {



                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                Toast.makeText(OtpVerifyActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onCodeSent(@NonNull String newVerificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                verificationId = newVerificationId;
                                Toast.makeText(OtpVerifyActivity.this, "OTP Sent!", Toast.LENGTH_SHORT).show();

                            }
                        }
                );

            }
        });

    }

    private void setUpOtpInputs() {

        etOtp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!s.toString().trim().isEmpty()) {

                    etOtp2.requestFocus();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etOtp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!s.toString().trim().isEmpty()) {

                    etOtp3.requestFocus();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etOtp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!s.toString().trim().isEmpty()) {

                    etOtp4.requestFocus();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etOtp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!s.toString().trim().isEmpty()) {

                    etOtp5.requestFocus();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etOtp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!s.toString().trim().isEmpty()) {

                    etOtp6.requestFocus();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etOtp6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!s.toString().trim().isEmpty()) {

                    if(!btnVerify.isClickable()) {

                        btnVerify.setClickable(true);
                        btnVerify.setBackground(getResources().getDrawable(R.drawable.ic_clickverify));

                    }

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

}