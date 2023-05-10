package com.example.pick_a_fish;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class intelligence extends AppCompatActivity {


    TextView textView42;
    ImageView link_img1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intelligence);


        textView42 = findViewById(R.id.textView42);
        textView42.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        link_img1 = findViewById(R.id.link_img);

        textView42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.vox.com/2014/8/4/5958871/fish-intelligence-smart-research-behavior-pain");
            }
        });

        link_img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ClipboardManager clipboardManage = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("TextView42", textView42.getText().toString());

                clip.getDescription();
                clipboardManage.setPrimaryClip(clip);
                Toast.makeText(intelligence.this,"Copied Link.",Toast.LENGTH_SHORT).show();

            }
        });



    }

    private void gotoUrl(String s) {

        Uri uri =  Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));

    }
}