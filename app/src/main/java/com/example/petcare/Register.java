package com.example.petcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText editUsername,editEmail,editPassword,editConfirm;
    Button button;
    TextView editLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editUsername = findViewById(R.id.registerusername);
        editEmail = findViewById(R.id.registeremail);
        editPassword = findViewById(R.id.registerpassword);
        editConfirm = findViewById(R.id.registercpassword);
        button = findViewById(R.id.registerbtn);
        editLog = findViewById(R.id.regtext);

        editLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editUsername.getText().toString();
                String email = editEmail.getText().toString();
                String password = editPassword.getText().toString();
                String confirm = editConfirm.getText().toString();
                Database db = new Database(getApplicationContext(),"PetCare",null,1);

                if (username.length() == 0 || email.length() == 0 || password.length() == 0 || confirm.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.compareTo(confirm) == 0) {
                        if(isValid(password)){
                            db.register(username,email,password);
                            Toast.makeText(getApplicationContext(),"Record Inserted",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Register.this,Login.class));

                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Password must contain at least characters,having letter,digit and special symbol",Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Passsword and Confirm Password didn't match", Toast.LENGTH_SHORT).show();
                    }

                }
            }

        });

    }
    public static boolean isValid(String passwordhere) {
        int f1 = 0, f2 = 0, f3 = 0;
        if (passwordhere.length() < 8) {
            return false;
        } else {
            for (int p = 0; p < passwordhere.length(); p++) {
                if (Character.isLetter(passwordhere.charAt(p))) {
                    f1 = 1;
                }
            }
            for (int r = 0; r < passwordhere.length(); r++) {
                if (Character.isDigit(passwordhere.charAt(r))) {
                    f2 = 1;
                }
            }
            for (int s = 0; s < passwordhere.length(); s++) {
                char c = passwordhere.charAt(s);
                if (c >= 33 && c <= 46 || c == 64) {
                    f3 = 1;
                }
            }
            if (f1 == 1 && f2 == 1 && f3 == 1)
                return true;
            return false;
        }
    }
}