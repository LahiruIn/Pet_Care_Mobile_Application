package com.example.petcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText editUsername, editPassword;
    Button button;
    TextView textRegister;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editUsername = findViewById(R.id.loginusername);
        editPassword = findViewById(R.id.loginpassword);
        button = findViewById(R.id.loginbtn);
        textRegister = findViewById(R.id.logintext);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editUsername.getText().toString();
                String password = editPassword.getText().toString();
                Database db = new Database(getApplicationContext(),"PetCare",null,1);
                if(username.length()==0 || password.length()==0){
                    Toast.makeText(getApplicationContext(),"Please Fill All Details",Toast.LENGTH_SHORT).show();

                }else {
                    if(db.Login(username,password)==1){
                        Toast.makeText(getApplicationContext(),"Login success",Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", username);
                        editor.apply();

                        startActivity(new Intent(Login.this,petowner_home.class));
                    }else {
                        Toast.makeText(getApplicationContext(),"Invalid Username or Password",Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });


    }
}