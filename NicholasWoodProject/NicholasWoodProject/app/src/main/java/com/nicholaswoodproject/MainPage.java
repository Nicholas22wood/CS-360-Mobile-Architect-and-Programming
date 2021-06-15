package com.nicholaswoodproject;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainPage extends AppCompatActivity {

    EditText weight, date;
    Button insert, update, delete, view;
    DBTracker DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        weight = findViewById(R.id.weight);
        date = findViewById(R.id.weight);

        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
        DB = new DBTracker(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weightTXT = weight.getText().toString();
                String dateTXT = date.getText().toString();
                
                Boolean checkInsertData = DB.insertUserData(weightTXT, dateTXT);
                if(checkInsertData == true)
                    Toast.makeText(MainPage.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainPage.this, "No Entry Inserted", Toast.LENGTH_SHORT).show();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weightTXT = weight.getText().toString();
                String dateTXT = date.getText().toString();

                Boolean checkUpdateData = DB.updateUserData(weightTXT, dateTXT);
                if(checkUpdateData == true)
                    Toast.makeText(MainPage.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainPage.this, "No Entry Updated", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dateTXT = date.getText().toString();

                Boolean checkDeleteData = DB.deleteUserData(dateTXT);
                if(checkDeleteData == true)
                    Toast.makeText(MainPage.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainPage.this, "No Entry Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor result = DB.getData();
                if(result.getCount()==0){
                    Toast.makeText(MainPage.this, "No Entries", Toast.LENGTH_SHORT).show();
                }
                StringBuffer buffer = new StringBuffer();
                while(result.moveToNext()){
                    buffer.append("Weight :" + result.getString(0)+ "\n");
                    buffer.append("Date :" + result.getString(1) + "\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainPage.this);
                builder.setCancelable(true);
                builder.setTitle("Weights Logged");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

    }
}
