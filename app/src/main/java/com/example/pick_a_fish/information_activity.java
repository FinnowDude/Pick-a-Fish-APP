package com.example.pick_a_fish;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class information_activity extends AppCompatActivity {


    TextView FNtxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);


        FNtxt = findViewById(R.id.txtFN);

        Intent intent = getIntent();
        String fishname = intent.getExtras().getString("fishname");

        FNtxt.setText(fishname);


    }
}