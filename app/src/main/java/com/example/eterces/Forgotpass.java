package com.example.eterces;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.api.Logging;
import com.google.firebase.auth.FirebaseAuth;

public class Forgotpass extends AppCompatActivity {

    private EditText emailEdittext;
    private Button resetpasswordbutn;
    private LottieAnimationView backbtn;
    private ProgressBar progressBar;
    //**Firebase
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpass);
        emailEdittext= (EditText) findViewById(R.id.forgot_email);
        resetpasswordbutn =(Button) findViewById(R.id.button);
        progressBar =(ProgressBar) findViewById(R.id.progressBar);
        backbtn=(LottieAnimationView) findViewById(R.id.back);
        //**Firebase
        auth = FirebaseAuth.getInstance();

        resetpasswordbutn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Forgotpass.this ,Login.class));
                finish();
            }
        });

    }
    private void reset() {
        String email = emailEdittext.getText().toString().trim();

        if (email.isEmpty()){
            emailEdittext.setError("Please Enter your Email");
            emailEdittext.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEdittext.setError("Please enter Valid Email");
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmailp(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Forgotpass.this, "Chek Your email to reset ", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}