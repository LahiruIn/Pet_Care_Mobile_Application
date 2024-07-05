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

public class caregiven_login extends AppCompatActivity {

    EditText cgUsername, cgPassword;
    Button cgbutton;
    TextView cgtextRegister;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caregiven_login);

        cgUsername = findViewById(R.id.loginusernamecg);
        cgPassword = findViewById(R.id.loginpasswordcg);
        cgbutton = findViewById(R.id.loginbtncg);
        cgtextRegister = findViewById(R.id.logintextcg);

        cgbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = cgUsername.getText().toString();
                String password = cgPassword.getText().toString();
                Database db = new Database(getApplicationContext(),"PetCare",null,1);
                if(username.length()==0 || password.length()==0){
                    Toast.makeText(getApplicationContext(),"Please Fill All Details",Toast.LENGTH_SHORT).show();

                }else {
                    if(db.cglogin(username,password)==1){
                        Toast.makeText(getApplicationContext(),"Login success",Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", username);
                        editor.apply();

                        startActivity(new Intent(caregiven_login.this,caregiven_home.class));
                    }else {
                        Toast.makeText(getApplicationContext(),"Invalid Username or Password",Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        cgtextRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(caregiven_login.this, caregiven_register.class));
            }
        });

    }
}