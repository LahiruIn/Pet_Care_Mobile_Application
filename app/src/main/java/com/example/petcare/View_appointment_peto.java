package com.example.petcare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class View_appointment_peto extends AppCompatActivity {

    DatabaseHelperClass databaseHelperClass;
    TextView padatalist;
    TextView padatalist_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointment_peto);

        databaseHelperClass = new DatabaseHelperClass(View_appointment_peto.this);
        Button pread = findViewById(R.id.pviewrefresh_data);
        padatalist = findViewById(R.id.pviewcall_data_list);
        padatalist_count = findViewById(R.id.pviewdata_list_count);

        pread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parefreshData();
            }
        });



        Button pviewback = findViewById(R.id.pviewback_btn);
        pviewback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(View_appointment_peto.this, petowner_home.class));
            }
        });

    }

    private void parefreshData() {
        padatalist_count.setText("Appointment Count : "+databaseHelperClass.getTotala());
        List<AppointmentClass> appointmentList = databaseHelperClass.getAllapp();
        padatalist.setText("");
        for (AppointmentClass appointmentClass : appointmentList){
            padatalist.append(" Appointment ID : "+appointmentClass.getAid()+ "\n" +
                    " Care given : "+appointmentClass.getAppcare()+ "\n" +
                    " Pet Owner : "+appointmentClass.getApppetown()+ "\n" +
                    " Pet : "+appointmentClass.getApppet()+ "\n" +
                    " Pet Note : "+appointmentClass.getApppetnote()+ "\n" +
                    " Duration : "+appointmentClass.getAppduration()+ "\n" +
                    " Appointment confirmation : "+appointmentClass.getAppconf()+ " \n\n");
        }
    }
}