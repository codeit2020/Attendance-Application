package com.example.attendanceproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText last_name_input, first_name_input, snc_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        last_name_input = findViewById(R.id.last_name_input);
        first_name_input = findViewById(R.id.first_name_input);
        snc_input = findViewById(R.id.snc_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addStudent(last_name_input.getText().toString().trim(),
                        first_name_input.getText().toString().trim(),
                        Integer.valueOf(snc_input.getText().toString().trim()));
            }
        });
    }
}
