package com.example.petcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

   // Button button, button2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button petown = findViewById(R.id.petowner);
        petown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Login.class));
            }
        });

        Button caregiv = findViewById(R.id.caregiven);
        caregiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, caregiven_login.class));
            }
        });

        /*button = findViewById(R.id.petowner);
        button2 = findViewById(R.id.caregiven);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Login();
            }
        });
    }

    public void Login() {
        Intent i = new Intent(this, Login.class); // Assuming your activity is named AccountType
        startActivity(i); // Change startActivities to startActivity


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                caregiven_login();

            }
        });
    }

    public void caregiven_login() {
        Intent i = new Intent(this, caregiven_login.class); // Assuming your activity is named AccountType
        startActivity(i); // Change startActivities to startActivity
    }

         */
    }
}

