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

public class UpdateActivity extends AppCompatActivity {

    EditText last_name_input, first_name_input, snc_input;
    Button update_button, delete_button;

    String id, last_name, first_name, snc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        last_name_input = findViewById(R.id.last_name_input2);
        first_name_input = findViewById(R.id.first_name_input2);
        snc_input = findViewById(R.id.snc_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        //First we call this
        getAndSetIntentData();


        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(last_name);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                last_name = last_name_input.getText().toString().trim();
                first_name = first_name_input.getText().toString().trim();
                snc = snc_input.getText().toString().trim();
                myDB.updateData(id, last_name, first_name, snc);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });



    }



    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("last_name") &&
                getIntent().hasExtra("first_name") && getIntent().hasExtra("snc")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            last_name = getIntent().getStringExtra("last_name");
            first_name = getIntent().getStringExtra("first_name");
            snc = getIntent().getStringExtra("snc");

            //Setting Intent Data
            last_name_input.setText(last_name);
            first_name_input.setText(first_name);
            snc_input.setText(snc);
            Log.d("stev", last_name+" "+first_name+" "+snc);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + last_name + " ?");
        builder.setMessage("Are you sure you want to delete " + last_name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
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
