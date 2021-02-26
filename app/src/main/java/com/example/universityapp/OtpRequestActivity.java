package com.example.universityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.universityapp.Models.Users;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import javax.xml.datatype.XMLGregorianCalendar;

public class OtpRequestActivity extends AppCompatActivity {

    private EditText etNumber;
    private String number;
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    public void otpRequest(View view) {

        number = etNumber.getText().toString();

        Log.i("Number", "+91" + number);
        Toast.makeText(OtpRequestActivity.this, number, Toast.LENGTH_SHORT).show();

        if(!number.trim().isEmpty()) {

            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    "+91" + number,
                    60,
                    TimeUnit.SECONDS,
                    OtpRequestActivity.this,
                    mCallbacks);

        } else {

            Toast.makeText(this, "Number is required", Toast.LENGTH_LONG).show();

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_request);

        etNumber = findViewById(R.id.etMobileNumber);

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                Toast.makeText(OtpRequestActivity.this, "Done", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {

                Toast.makeText(OtpRequestActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                super.onCodeSent(verificationId, forceResendingToken);
                Intent intent = new Intent(OtpRequestActivity.this, OtpVerifyActivity.class);
                intent.putExtra("vId", verificationId);
                intent.putExtra("number", number);
                startActivity(intent);


            }
        };


    }
}