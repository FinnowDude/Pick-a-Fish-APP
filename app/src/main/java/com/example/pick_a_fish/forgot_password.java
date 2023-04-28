package com.example.pick_a_fish;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgot_password extends AppCompatActivity {

    ImageView return_image;

    private EditText emailEditText;
    private Button resetPasswordButton;
    private ProgressBar progressBar;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        // Return to Sign In
        return_image = findViewById(R.id.img_return);
        return_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent return_activity =new Intent(getApplicationContext(),loginActivity.class);
                startActivity(return_activity);
                finish();

            }
        });


        ///////////////////////////////////////////
        ////////Firebase Forgot Password///////////
        ///////////////////////////////////////////

        emailEditText = findViewById(R.id.email_forgotEDT);
        resetPasswordButton = findViewById(R.id.forgotbtn);
        progressBar = findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();

        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });


    }

    private void resetPassword() {

        String email = emailEditText.getText().toString().trim();

        if (email.isEmpty()) {
            emailEditText.setError("Email is required!");
            emailEditText.requestFocus();
            return;


        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Please provide valid email!");
            emailEditText.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(forgot_password.this,"Check your email to reset your password!", Toast.LENGTH_LONG).show();
                }else{
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(forgot_password.this, "Try again, Something went wrong!", Toast.LENGTH_LONG).show();


                }
            }
        });

    }
    @Override
    public void onBackPressed() { }
}