package com.example.petcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class caregiven_register extends AppCompatActivity {

    EditText cgeditUsername,cgeditEmail,cgeditPassword,cgeditConfirm;
    Button cgbutton;
    TextView cgeditLog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caregiven_register);


            cgeditUsername = findViewById(R.id.registerusernamecg);
            cgeditEmail = findViewById(R.id.registeremailcg);
            cgeditPassword = findViewById(R.id.registerpasswordcg);
            cgeditConfirm = findViewById(R.id.registercpasswordcg);
            cgbutton = findViewById(R.id.registerbtncg);
            cgeditLog = findViewById(R.id.regtextcg);

            cgeditLog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(caregiven_register.this, caregiven_login.class));
                }
            });

            cgbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String username = cgeditUsername.getText().toString();
                    String email = cgeditEmail.getText().toString();
                    String password = cgeditPassword.getText().toString();
                    String confirm = cgeditConfirm.getText().toString();
                    Database db = new Database(getApplicationContext(),"PetCare",null,1);

                    if (username.length() == 0 || email.length() == 0 || password.length() == 0 || confirm.length() == 0) {
                        Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_SHORT).show();
                    } else {
                        if (password.compareTo(confirm) == 0) {
                            if(isValid (password)){
                                db.cgregister(username,email,password);
                                Toast.makeText(getApplicationContext(),"Record Inserted",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(caregiven_register.this,caregiven_login.class));

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

        public static boolean isValid (String passwordhere) {
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