package com.example.pick_a_fish;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class register extends AppCompatActivity {


    TextView logintxt;
    ImageView ImgUserPhoto;
    static int PReqcode = 1;
    static int REQUESCODE = 1;
    Uri pickedImgUri;

    private EditText userEmail, userPassword,userPassword2,userName;
    private ProgressBar loadingprogress;

    private FirebaseAuth mAuth;


    private Button reg_but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        userName = findViewById(R.id.editTextTextPersonName);
        userPassword = findViewById(R.id.editTextTextPassword);
        userPassword2 = findViewById(R.id.editTextTextPassword2);
        userEmail = findViewById(R.id.editTextTextEmailAddress);
        loadingprogress = findViewById(R.id.regprogressBar);
        loadingprogress.setVisibility(View.INVISIBLE);
        logintxt = findViewById(R.id.logintxt);

        mAuth = FirebaseAuth.getInstance();

        //Login transfer Activity

        logintxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginActivity = new Intent(getApplicationContext(),loginActivity.class);
                startActivity(loginActivity);
                finish();
            }
        });



        //------------------------------------------------------------------------------------------
        reg_but = findViewById(R.id.sign_up);
        reg_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reg_but.setVisibility(View.INVISIBLE);
                loadingprogress.setVisibility(View.VISIBLE);
                final String email = userEmail.getText().toString();
                final String password = userPassword.getText().toString();
                final String password2 = userPassword2.getText().toString();
                final String name = userName.getText().toString();


                if(email.isEmpty() || name.isEmpty() || password.isEmpty() || !password.equals(password2)){

                    //Field must be filled
                    //Display Error message or Toast
                    showmessage("Please Verify all fields");
                    reg_but.setVisibility(View.VISIBLE);
                    loadingprogress.setVisibility(View.INVISIBLE);


                }
                else if(pickedImgUri==null) {
                    showmessage("Please Pick a picture");
                    reg_but.setVisibility(View.VISIBLE);
                    loadingprogress.setVisibility(View.INVISIBLE);
                }
                else {
                    //Successful with complete filled and Create User's Account

                    CreateUserAccount(email,name,password);

                }



            }
        });
        //------------------------------------------------------------------------------------------
        //inu Views
        ImgUserPhoto = findViewById(R.id.user_photo);
        ImgUserPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >=22){
                    checkAndRequestForPermission();
                }else{
                    openGallery();
                }
            }
        });




    }
    //Creating Account
    private void CreateUserAccount(String email, String name, String password) {

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    showmessage("Account Created");
                    //After Creating account, update profile picture and name
                    updateUserInfo(name, pickedImgUri,mAuth.getCurrentUser());




                }else{

                    showmessage("Account creation failed" + task.getException().getMessage());
                    reg_but.setVisibility(View.VISIBLE);
                    loadingprogress.setVisibility(View.INVISIBLE);

                }

            }
        });


    }
    //Update User info
    private void updateUserInfo(String name, Uri pickedImgUri, FirebaseUser currentUser) {

        //Upload profile photo to firebase ang get its url
        StorageReference mStorage = FirebaseStorage.getInstance().getReference().child("user_photo");
        StorageReference imageFilePath = mStorage.child(pickedImgUri.getLastPathSegment());
        imageFilePath.putFile(pickedImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                // image uploaded successfully and get image url
                imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        //Uri Contains users image

                        UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                                .setDisplayName(name)
                                .setPhotoUri(uri)
                                .build();

                        currentUser.updateProfile(profileUpdate)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()) {

                                            //user info updated
                                            showmessage("Register Complete");
                                            updateUI();

                                        }



                                    }
                                });



                    }
                });



            }
        });



    }
    //Go to Navigation Activity
    private void updateUI() {

        Intent nagivationActivity = new Intent(getApplicationContext(),navigation.class);
        startActivity(nagivationActivity);
        finish();

    }

    //Show Error message
    private void showmessage(String message) {

        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();

    }

    private void openGallery() {
        //TODO: Open gallery and Pick image from Gallery
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,REQUESCODE);

    }



    private void checkAndRequestForPermission() {

            if(ContextCompat.checkSelfPermission(register.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED){
                if (ActivityCompat.shouldShowRequestPermissionRationale(register.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    Toast.makeText(register.this, "Please Accept for required permission", Toast.LENGTH_SHORT).show();

                }

                else {
                    ActivityCompat.requestPermissions(register.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PReqcode);
                }
            }
            else{
                openGallery();
            }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUESCODE && data != null) {
            //the user has successfully pick an image and save to firebase
            pickedImgUri = data.getData();
            ImgUserPhoto.setImageURI(pickedImgUri);

        }
    }

    @Override
    public void onBackPressed() {

    }
}