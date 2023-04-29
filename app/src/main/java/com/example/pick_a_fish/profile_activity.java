package com.example.pick_a_fish;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class profile_activity extends AppCompatActivity {

    ImageView image_profile;
    TextView profileNametxt, profileEmailtxt;
    ImageView logout_arrow;

    FirebaseUser currentUser;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        //ini Firebase

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();


        //Viewing
        image_profile = findViewById(R.id.profile_image);
        profileNametxt = findViewById(R.id.profile_name);
        profileEmailtxt = findViewById(R.id.profile_email);
        logout_arrow = findViewById(R.id.logout_img);

        // Displaying User Profile

        profileNametxt.setText(currentUser.getDisplayName());
        profileEmailtxt.setText(currentUser.getEmail());

        Glide.with(this).load(currentUser.getPhotoUrl()).into(image_profile);

        //Logout Account
        logout_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                Intent login_activity = new Intent(getApplicationContext(),loginActivity.class);
                startActivity(login_activity);
                finish();

            }
        });





    }
}