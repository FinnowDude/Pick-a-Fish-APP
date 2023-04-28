package com.example.pick_a_fish;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class forgot_password extends AppCompatActivity {

    ImageView return_image;

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






    }
}