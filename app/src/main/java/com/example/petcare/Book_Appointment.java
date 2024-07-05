package com.example.petcare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

public class Book_Appointment extends AppCompatActivity {

    DatabaseHelperClass databaseHelperClass;
    TextView adatalist;
    TextView adatalist_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        databaseHelperClass = new DatabaseHelperClass(Book_Appointment.this);
        Button adelete = findViewById(R.id.appdelete_data);
        Button ainsert = findViewById(R.id.appadd_data);
        Button aupdate = findViewById(R.id.appupdate_data);
        Button aread = findViewById(R.id.apprefresh_data);
        adatalist = findViewById(R.id.appall_data_list);
        adatalist_count = findViewById(R.id.appdata_list_count);

        aread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arefreshData();
            }
        });

        ainsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ashowInputDialog();
            }
        });

        aupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ashowUpdateIdDialog();
            }
        });

        adelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ashowDeleteDialog();
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

    private void ashowDeleteDialog() {
        AlertDialog.Builder al=new AlertDialog.Builder(Book_Appointment.this);
        View view = getLayoutInflater().inflate(R.layout.appointment_delete,null);
        al.setView(view);
        final EditText appid_input = view.findViewById(R.id.appid_input);
        Button Adelete_btn = view.findViewById(R.id.appdelete_btn);

        final AlertDialog alertDialog=al.show();
        Adelete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelperClass.deleteapp(appid_input.getText().toString());
                alertDialog.dismiss();
                arefreshData();
            }
        });

    }


    public void ashowUpdateIdDialog() {
        AlertDialog.Builder al = new AlertDialog.Builder(Book_Appointment.this);
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
        AlertDialog.Builder al = new AlertDialog.Builder(Book_Appointment.this);
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



    private void ashowInputDialog() {
        AlertDialog.Builder al = new AlertDialog.Builder(Book_Appointment.this);
        View view = getLayoutInflater().inflate(R.layout.appointment_add,null);
        final EditText appcare = view.findViewById(R.id.appcare);
        final EditText apppetown = view.findViewById(R.id.apppetown);
        final EditText apppet = view.findViewById(R.id.apppet);
        final EditText apppetnote = view.findViewById(R.id.apppetnote);
        final EditText appduration = view.findViewById(R.id.appduration);
        final EditText appconf = view.findViewById(R.id.appconf);
        Button addBtn =view.findViewById(R.id.appbook_btn);
        al.setView(view);

        final AlertDialog alertDialog=al.show();
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppointmentClass appointmentClass = new AppointmentClass();
                appointmentClass.setAppcare(appcare.getText().toString());
                appointmentClass.setApppetown(apppetown.getText().toString());
                appointmentClass.setApppet(apppet.getText().toString());
                appointmentClass.setApppetnote(apppetnote.getText().toString());
                appointmentClass.setAppduration(appduration.getText().toString());
                appointmentClass.setAppconf(appconf.getText().toString());
                Date date = new Date();
                appointmentClass.setCreated_ata(""+date.getTime());
                databaseHelperClass.addapp(appointmentClass);
                alertDialog.dismiss();
                arefreshData();
            }
        });
    }
}
