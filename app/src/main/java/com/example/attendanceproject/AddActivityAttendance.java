package com.example.attendanceproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivityAttendance extends AppCompatActivity {

    EditText date_input, reason_input;
    Button add_button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        date_input = findViewById(R.id.date_input);
        reason_input = findViewById(R.id.reason_input);

        add_button2 = findViewById(R.id.add_button);
        add_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AttendanceDatabaseHelper myDB = new AttendanceDatabaseHelper(AddActivityAttendance.this);
                myDB.addAbsence(date_input.getText().toString().trim(),
                        reason_input.getText().toString().trim());
            }
        });
    }
}
