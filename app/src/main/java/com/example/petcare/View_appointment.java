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

public class View_appointment extends AppCompatActivity {

    DatabaseHelperClass databaseHelperClass;
    TextView adatalist;
    TextView adatalist_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointment);

        databaseHelperClass = new DatabaseHelperClass(View_appointment.this);
        Button aupdate = findViewById(R.id.viewupdate_data);
        Button aread = findViewById(R.id.viewrefresh_data);
        adatalist = findViewById(R.id.viewcall_data_list);
        adatalist_count = findViewById(R.id.viewdata_list_count);

        aread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arefreshData();
            }
        });

        aupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ashowUpdateIdDialog();
            }
        });


        Button viewpet = findViewById(R.id.viewpet_btn);
        viewpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(View_appointment.this, View_petdetails.class));
            }
        });

        Button viewback = findViewById(R.id.viewback_btn);
        viewback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(View_appointment.this, caregiven_home.class));
            }
        });


    }

    private void arefreshData() {
        adatalist_count.setText("Appointment Count : "+databaseHelperClass.getTotala());
        List<AppointmentClass> appointmentList = databaseHelperClass.getAllapp();
        adatalist.setText("");
        for (AppointmentClass appointmentClass : appointmentList){
            adatalist.append(" Appointment ID : "+appointmentClass.getAid()+ "\n" +
                    " Care given : "+appointmentClass.getAppcare()+ "\n" +
                    " Pet Owner : "+appointmentClass.getApppetown()+ "\n" +
                    " Pet : "+appointmentClass.getApppet()+ "\n" +
                    " Pet Note : "+appointmentClass.getApppetnote()+ "\n" +
                    " Duration : "+appointmentClass.getAppduration()+ "\n" +
                    " Appointment confirmation : "+appointmentClass.getAppconf()+ " \n\n");
        }
    }

    public void ashowUpdateIdDialog() {
        AlertDialog.Builder al = new AlertDialog.Builder(View_appointment.this);
        View view = getLayoutInflater().inflate(R.layout.appointment_update_id,null);
        al.setView(view);
        final EditText appid_input = view.findViewById(R.id.appid_input);
        Button Afetch_btn = view.findViewById(R.id.appupdate_id_btn);
        final AlertDialog alertDialog=al.show();
        Afetch_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ashowDataDialog(appid_input.getText().toString());
                alertDialog.dismiss();
                arefreshData();
            }
        });

    }


    private void ashowDataDialog(final String aid) {
        AppointmentClass appointmentClass = databaseHelperClass.getapp(Integer.parseInt(aid));
        AlertDialog.Builder al = new AlertDialog.Builder(View_appointment.this);
        View view = getLayoutInflater().inflate(R.layout.appointment_update,null);
        final EditText appcare = view.findViewById(R.id.appcare);
        final EditText apppetown = view.findViewById(R.id.apppetown);
        final EditText apppet = view.findViewById(R.id.apppet);
        final EditText apppetnote = view.findViewById(R.id.apppetnote);
        final EditText appduration = view.findViewById(R.id.appduration);
        final EditText appconf = view.findViewById(R.id.appconf);
        Button Aupdate_Btn =view.findViewById(R.id.appupdate_btn);
        al.setView(view);

        appcare.setText(appointmentClass.getAppcare());
        apppetown.setText(appointmentClass.getApppetown());
        apppet.setText(appointmentClass.getApppet());
        apppetnote.setText(appointmentClass.getApppetnote());
        appduration.setText(appointmentClass.getAppduration());
        appconf.setText(appointmentClass.getAppconf());

        final AlertDialog alertDialog=al.show();

        Aupdate_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppointmentClass appointmentClass = new AppointmentClass();
                appointmentClass.setAppcare(appcare.getText().toString());
                appointmentClass.setAid(aid);
                appointmentClass.setApppetown(apppetown.getText().toString());
                appointmentClass.setApppet(apppet.getText().toString());
                appointmentClass.setApppetnote(apppetnote.getText().toString());
                appointmentClass.setAppduration(appduration.getText().toString());
                appointmentClass.setAppconf(appconf.getText().toString());
                databaseHelperClass.updateapp(appointmentClass);
                alertDialog.dismiss();
                arefreshData();
            }
        });
    }
}