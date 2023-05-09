package com.example.pick_a_fish;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pick_a_fish.Utiliy.userReport;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

public class bug_report extends AppCompatActivity {

    StorageReference mStorageRef;
    DatabaseReference mDatabaseRef;
    EditText report_edt;
    Button submit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bug_report);


        submit = findViewById(R.id.btn_report);
        report_edt = findViewById(R.id.reportEDT);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("reports");


        report_edt.getText().toString();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reportmessage();

            }
        });

    }

    private void reportmessage() {

        if (report_edt.getText().toString().isEmpty()) {


            Toast.makeText(bug_report.this, "Please type your report", Toast.LENGTH_LONG).show();

        }else{

            String message = report_edt.getText().toString();

            userReport userReport = new userReport(message);

            mDatabaseRef.push().setValue(userReport);
            Toast.makeText(bug_report.this, "Report Sent", Toast.LENGTH_LONG).show();
            report_edt.setText("");
        }

    }


}