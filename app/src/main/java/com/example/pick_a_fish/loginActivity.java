package com.example.pick_a_fish;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginActivity extends AppCompatActivity {


    TextView regTxt,forgottxt,closetxt;
    private EditText userMail, userPassword;
    private Button btnLogin;
    private ProgressBar loginProgress;
    private FirebaseAuth mAuth;
    private Intent navigationActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        //Exit App
        closetxt = findViewById(R.id.closeTXT);
        closetxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Forgot Password Activity

        forgottxt = findViewById(R.id.txtforgot);
        forgottxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgotActivit =new Intent(getApplicationContext(),forgot_password.class);
                startActivity(forgotActivit);
                finish();


            }
        });




        //Go to Sign up Activity--------------------------------------------------------------------
        regTxt = findViewById(R.id.txtRegis);
        regTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regisActivty = new Intent(getApplicationContext(),register.class);
                startActivity(regisActivty);
                finish();
            }
        });
        //------------------------------------------------------------------------------------------

        userMail = findViewById(R.id.editTextTextEmailAddress2);
        userPassword = findViewById(R.id.editTextTextPassword3);
        btnLogin = findViewById(R.id.button2);
        loginProgress = findViewById(R.id.loginProgressBar);
        navigationActivity = new Intent(this,navigation.class);

        mAuth = FirebaseAuth.getInstance();


        loginProgress.setVisibility(View.INVISIBLE);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginProgress.setVisibility(View.VISIBLE);
                btnLogin.setVisibility(View.INVISIBLE);

                final String mail = userMail.getText().toString();
                final String password = userPassword.getText().toString();

                if(mail.isEmpty() || password.isEmpty()){

                    showMessage("Please Verify All Fields");
                    btnLogin.setVisibility(View.VISIBLE);
                    loginProgress.setVisibility(View.INVISIBLE);

                }
                else {

                    signIn(mail,password);

                }

            }
        });



    }

    private void signIn(String mail, String password) {

        mAuth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    loginProgress.setVisibility(View.INVISIBLE);
                    btnLogin.setVisibility(View.VISIBLE);
                    updateUI();

                }
                else{


                    showMessage(task.getException().getMessage());
                    btnLogin.setVisibility(View.VISIBLE);
                    loginProgress.setVisibility(View.INVISIBLE);


                }

            }
        });




    }

    private void updateUI() {

        startActivity(navigationActivity);
        finish();


    }

    private void showMessage(String text) {

        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null){

            //User is already connected so we need to re-direct them to the navigation/home activity
            updateUI();




        }
    }

    @Override
    public void onBackPressed() {

    }
}