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

public class fish_study extends AppCompatActivity {

    TextView textView37;
    ImageView imageView50;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_study);

        imageView50 = findViewById(R.id.imageView50);
        imageView50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ClipboardManager clipboardManage = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("textView37", textView37.getText().toString());

                clip.getDescription();
                clipboardManage.setPrimaryClip(clip);
                Toast.makeText(fish_study.this,"Copied Link.",Toast.LENGTH_SHORT).show();

            }
        });

        textView37 = findViewById(R.id.textView37);
        textView37.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        textView37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gotoUrl("https://www.sciencedaily.com/releases/2016/06/160607080356.htm");


            }
        });
    }

    private void gotoUrl(String s) {

        Uri uri =  Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));

    }
}