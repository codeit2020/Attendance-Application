package com.example.attendanceproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivityAttendance extends AppCompatActivity {

    EditText date_input, reason_input;
    Button update_button2, delete_button2;

    String id, date, reason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_attendance);

        date_input = findViewById(R.id.last_name_input2);
        reason_input = findViewById(R.id.first_name_input2);

        update_button2 = findViewById(R.id.update_button);
        delete_button2 = findViewById(R.id.delete_button);

        //First we call this
        getAndSetIntentData();


        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(date);
        }

        update_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                AttendanceDatabaseHelper myDB = new AttendanceDatabaseHelper(UpdateActivityAttendance.this);
                date = date_input.getText().toString().trim();
                reason = reason_input.getText().toString().trim();

                myDB.updateData(id, date, reason);
            }
        });
        delete_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("last_name") &&
                getIntent().hasExtra("author") && getIntent().hasExtra("pages")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            date = getIntent().getStringExtra("date");
            reason = getIntent().getStringExtra("reason");


            //Setting Intent Data
            date_input.setText(date);
            reason_input.setText(reason);

            Log.d("stev", date+" "+reason+" ");
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + date + " ?");
        builder.setMessage("Are you sure you want to delete " + date + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                AttendanceDatabaseHelper myDB = new AttendanceDatabaseHelper(UpdateActivityAttendance.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
