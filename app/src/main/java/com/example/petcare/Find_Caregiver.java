package com.example.petcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class Find_Caregiver extends AppCompatActivity {

    DatabaseHelperClass databaseHelperClass;
    TextView datalistc;
    TextView datalist_countc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_caregiver);

        databaseHelperClass = new DatabaseHelperClass(Find_Caregiver.this);
        Button read = findViewById(R.id.findrefresh_data);
        datalistc = findViewById(R.id.findcall_data_list);
        datalist_countc = findViewById(R.id.finddata_list_count);

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crefreshData();
            }
        });


        Button bookapp = findViewById(R.id.book_appointment_btn);
        bookapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Find_Caregiver.this, Book_Appointment.class));
            }
        });

        Button findback = findViewById(R.id.findback_btn);
        findback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Find_Caregiver.this, petowner_home.class));
            }
        });


    }

        private void crefreshData() {
            datalist_countc.setText("All Account Count : "+databaseHelperClass.getTotalc());
            List<RegistercareClass> registercareList = databaseHelperClass.getAllcare();
            datalistc.setText("");
            for (RegistercareClass registercareClass : registercareList){
                datalistc.append(" No : "+registercareClass.getCid()+ "\n" +
                        " Caregiver id : "+registercareClass.getCareid()+ "\n" +
                        " Caregiver name : "+registercareClass.getCarename()+ "\n" +
                        " Caregiver exp : "+registercareClass.getCareexp()+ "\n" +
                        " Caregiver contact : "+registercareClass.getCarecontact()+ "\n" +
                        " Caregiver fee : "+registercareClass.getCarefee()+" \n\n");
            }
        }
    }
