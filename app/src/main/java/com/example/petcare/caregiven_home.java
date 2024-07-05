package com.example.petcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class caregiven_home extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caregiven_home);

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();
        Toast.makeText(getApplicationContext(),"Welcome"+username,Toast.LENGTH_SHORT).show();

        Button exit =findViewById(R.id.logout);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(caregiven_home.this,MainActivity.class));
            }
        });


        Button regcare = findViewById(R.id.regcare_btn);
        regcare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(caregiven_home.this, Register_caregiven.class));
            }
        });


        Button viewpet = findViewById(R.id.viewpet_btn);
        viewpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(caregiven_home.this, View_petdetails.class));
            }
        });


        Button viewapp = findViewById(R.id.viewapp_btn);
        viewapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(caregiven_home.this, View_appointment.class));
            }
        });

        Button viewfeed = findViewById(R.id.viewfeed);
        viewfeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(caregiven_home.this, View_feedback.class));
            }
        });
    }
}