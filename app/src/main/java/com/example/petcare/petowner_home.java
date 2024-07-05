package com.example.petcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class petowner_home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petowner_home);

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
               startActivity(new Intent(petowner_home.this,MainActivity.class));
            }
        });


        Button findcare = findViewById(R.id.findcareg);
        findcare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(petowner_home.this, Find_Caregiver.class));
            }
        });

        Button regpet = findViewById(R.id.petregister);
        regpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(petowner_home.this, Register_pet.class));
            }
        });

        Button viewpet = findViewById(R.id.viewapp_btn);
        viewpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(petowner_home.this, View_appointment_peto.class));
            }
        });

        Button feed = findViewById(R.id.feed_btn);
        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(petowner_home.this, Feedback.class));
            }
        });


    }
}