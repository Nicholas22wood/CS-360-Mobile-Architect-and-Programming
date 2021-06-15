package com.nicholaswoodproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserPermission extends AppCompatActivity {

    Button yesPress, noPress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_permission);

        yesPress = (Button) findViewById(R.id.yes);
        noPress = (Button) findViewById(R.id.no);

        yesPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserPermission.this, "Notifications Turned On!", Toast.LENGTH_SHORT).show();
                Intent intent  = new Intent(getApplicationContext(), MainPage.class);
                startActivity(intent);

            }
        });

        noPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserPermission.this, "Notifications Turned Off!", Toast.LENGTH_SHORT).show();
                Intent intent  = new Intent(getApplicationContext(), MainPage.class);
                startActivity(intent);

            }
        });

    }
}
