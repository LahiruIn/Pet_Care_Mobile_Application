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

public class Register_caregiven extends AppCompatActivity {

    DatabaseHelperClass databaseHelperClass;
    TextView datalistc;
    TextView datalist_countc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_caregiven);


        databaseHelperClass = new DatabaseHelperClass(Register_caregiven.this);
        Button cdelete = findViewById(R.id.cdelete_data);
        Button cinsert = findViewById(R.id.cinsert_data);
        Button cupdate = findViewById(R.id.cupdate_data);
        Button cread = findViewById(R.id.crefresh_data);
        datalistc = findViewById(R.id.call_data_list);
        datalist_countc = findViewById(R.id.cdata_list_count);

        cread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crefreshData();
            }
        });

        cinsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cshowInputDialog();
            }
        });

        cupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cshowUpdateIdDialog();
            }
        });

        cdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cshowDeleteDialog();
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

    private void cshowDeleteDialog() {
        AlertDialog.Builder al=new AlertDialog.Builder(Register_caregiven.this);
        View view = getLayoutInflater().inflate(R.layout.carereg_delete,null);
        al.setView(view);
        final EditText cid_input = view.findViewById(R.id.cid_input);
        Button cdelete_btn = view.findViewById(R.id.cdelete_btn);

        final AlertDialog alertDialog=al.show();
        cdelete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelperClass.deletecare(cid_input.getText().toString());
                alertDialog.dismiss();
                crefreshData();
            }
        });

    }


    public void cshowUpdateIdDialog() {
        AlertDialog.Builder al = new AlertDialog.Builder(Register_caregiven.this);
        View view = getLayoutInflater().inflate(R.layout.carereg_update_id,null);
        al.setView(view);
        final EditText cid_input = view.findViewById(R.id.cid_input);
        Button cfetch_btn = view.findViewById(R.id.cupdate_id_btn);
        final AlertDialog alertDialog=al.show();
        cfetch_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cshowDataDialog(cid_input.getText().toString());
                alertDialog.dismiss();
                crefreshData();
            }
        });

    }


    private void cshowDataDialog(final String cid) {
        RegistercareClass registercareClass = databaseHelperClass.getcare(Integer.parseInt(cid));
        AlertDialog.Builder al = new AlertDialog.Builder(Register_caregiven.this);
        View view = getLayoutInflater().inflate(R.layout.carereg_update,null);
        final EditText careid = view.findViewById(R.id.careid);
        final EditText carename = view.findViewById(R.id.carename);
        final EditText careexp = view.findViewById(R.id.careexp);
        final EditText carecontact = view.findViewById(R.id.carecontact);
        final EditText carefee = view.findViewById(R.id.carefee);
        Button cupdate_Btn =view.findViewById(R.id.cupdate_btn);
        al.setView(view);

        careid.setText(registercareClass.getCareid());
        carename.setText(registercareClass.getCarename());
        careexp.setText(registercareClass.getCareexp());
        carecontact.setText(registercareClass.getCarecontact());
        carefee.setText(registercareClass.getCarefee());

        final AlertDialog alertDialog=al.show();

        cupdate_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistercareClass registercareClass = new RegistercareClass();
                registercareClass.setCareid(careid.getText().toString());
                registercareClass.setCid(cid);
                registercareClass.setCarename(carename.getText().toString());
                registercareClass.setCareexp(careexp.getText().toString());
                registercareClass.setCarecontact(carecontact.getText().toString());
                registercareClass.setCarefee(carefee.getText().toString());
                databaseHelperClass.updatecare(registercareClass);
                alertDialog.dismiss();
                crefreshData();
            }
        });
    }



    private void cshowInputDialog() {
        AlertDialog.Builder al = new AlertDialog.Builder(Register_caregiven.this);
        View view = getLayoutInflater().inflate(R.layout.carereg_insert,null);
        final EditText careid = view.findViewById(R.id.careid);
        final EditText carename = view.findViewById(R.id.carename);
        final EditText careexp = view.findViewById(R.id.careexp);
        final EditText carecontact = view.findViewById(R.id.carecontact);
        final EditText carefee = view.findViewById(R.id.carefee);
        Button cinsertBtn =view.findViewById(R.id.cinsert_btn);
        al.setView(view);

        final AlertDialog alertDialog=al.show();
        cinsertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistercareClass registercareClass = new RegistercareClass();
                registercareClass.setCareid(careid .getText().toString());
                registercareClass.setCarename(carename.getText().toString());
                registercareClass.setCareexp(careexp.getText().toString());
                registercareClass.setCarecontact(carecontact.getText().toString());
                registercareClass.setCarefee(carefee.getText().toString());
                Date date = new Date();
                registercareClass.setCreated_atc(""+date.getTime());
                databaseHelperClass.addcare(registercareClass);
                alertDialog.dismiss();
                crefreshData();
            }
        });
    }
}