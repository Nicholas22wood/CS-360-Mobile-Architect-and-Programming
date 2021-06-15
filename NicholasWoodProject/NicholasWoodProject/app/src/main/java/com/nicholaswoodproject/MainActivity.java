package com.nicholaswoodproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText firstName, lastName, email, passwordConfirm, passwordCreate, usernameCreate;
    Button create;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user);

        usernameCreate = (EditText) findViewById(R.id.usernameCreate);
        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        email = (EditText) findViewById(R.id.email);
        passwordConfirm = (EditText) findViewById(R.id.passwordConfirm);
        passwordCreate = (EditText) findViewById(R.id.passwordCreate);
        DB = new DBHelper(this);

        create = (Button) findViewById(R.id.create);



        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = usernameCreate.getText().toString();
                String pass = passwordCreate.getText().toString();
                String passConfirm = passwordConfirm.getText().toString();

                if (user.equals("") || pass.equals("")||passConfirm.equals(""))
                    Toast.makeText(MainActivity.this, "Please enter all information", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(passConfirm)){
                        Boolean checkUser = DB.checkUsername(user);
                        if(checkUser==false){
                            Boolean insert = DB.insertData(user, pass);
                            if(insert==true){
                                Toast.makeText(MainActivity.this, "Register Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Login.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT);
                            }


                            }
                        else{
                            Toast.makeText(MainActivity.this, "User already exists! Chose new username", Toast.LENGTH_SHORT).show();
                        }
                        }
                    else{
                        Toast.makeText(MainActivity.this, "Passwords not matching!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}

