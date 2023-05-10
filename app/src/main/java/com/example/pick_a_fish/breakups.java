package com.example.pick_a_fish;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class breakups extends AppCompatActivity {


    ImageView imageView46;
    TextView textView36;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakups);

        textView36 = findViewById(R.id.textView36);
        textView36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gotoUrl("https://www.vox.com/future-perfect/2019/7/20/20700775/fish-pain-love-emotion-animal-cognition-study");
            }
        });

        imageView46 = findViewById(R.id.imageView46);
        imageView46.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManage = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("textView36", textView36.getText().toString());

                clip.getDescription();
                clipboardManage.setPrimaryClip(clip);
                Toast.makeText(breakups.this,"Copied Link.",Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void gotoUrl(String s) {

        Uri uri =  Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));

    }
}