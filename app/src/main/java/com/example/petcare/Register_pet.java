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

public class Register_pet extends AppCompatActivity {

    DatabaseHelperClass databaseHelperClass;
    TextView datalist;
    TextView datalist_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_pet);

        databaseHelperClass = new DatabaseHelperClass(Register_pet.this);
        Button delete = findViewById(R.id.delete_data);
        Button insert = findViewById(R.id.insert_data);
        Button update = findViewById(R.id.update_data);
        Button read = findViewById(R.id.refresh_data);
        datalist = findViewById(R.id.all_data_list);
        datalist_count = findViewById(R.id.data_list_count);

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshData();
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUpdateIdDialog();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteDialog();
            }
        });
    }

    private void refreshData() {
        datalist_count.setText("All Data Count : "+databaseHelperClass.getTotal());
        List<RegisterpetClass> registerpetClassList = databaseHelperClass.getAllpet();
        datalist.setText("");
        for (RegisterpetClass registerpetClass : registerpetClassList){
            datalist.append(" No : "+registerpetClass.getId()+ "\n" +
                    " Pet id : "+registerpetClass.getPetid()+ "\n" +
                    " Pet name : "+registerpetClass.getPetname()+ "\n" +
                    " Pet category : "+registerpetClass.getPetcate()+ "\n" +
                    " Pet colour : "+registerpetClass.getPetcolor()+ "\n" +
                    " Pet owner : "+registerpetClass.getPetowner()+ " \n\n");
        }
    }

    private void showDeleteDialog() {
        AlertDialog.Builder al=new AlertDialog.Builder(Register_pet.this);
        View view = getLayoutInflater().inflate(R.layout.petreg_delete,null);
        al.setView(view);
        final EditText id_input = view.findViewById(R.id.id_input);
        Button delete_btn = view.findViewById(R.id.delete_btn);

        final AlertDialog alertDialog=al.show();
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               databaseHelperClass.deletepet(id_input.getText().toString());
               alertDialog.dismiss();
               refreshData();
            }
        });

    }


    public void showUpdateIdDialog() {
        AlertDialog.Builder al = new AlertDialog.Builder(Register_pet.this);
        View view = getLayoutInflater().inflate(R.layout.petreg_update_id,null);
        al.setView(view);
        final EditText id_input = view.findViewById(R.id.id_input);
        Button fetch_btn = view.findViewById(R.id.update_id_btn);
        final AlertDialog alertDialog=al.show();
        fetch_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDataDialog(id_input.getText().toString());
                alertDialog.dismiss();
                refreshData();
            }
        });

    }


    private void showDataDialog(final String id) {
        RegisterpetClass registerpetClass = databaseHelperClass.getpet(Integer.parseInt(id));
        AlertDialog.Builder al = new AlertDialog.Builder(Register_pet.this);
        View view = getLayoutInflater().inflate(R.layout.petreg_update,null);
        final EditText petid = view.findViewById(R.id.petid);
        final EditText petname = view.findViewById(R.id.petname);
        final EditText petcate = view.findViewById(R.id.petcate);
        final EditText petcolor = view.findViewById(R.id.petcolor);
        final EditText petowner = view.findViewById(R.id.petowner);
        Button update_Btn =view.findViewById(R.id.update_btn);
        al.setView(view);

        petid.setText(registerpetClass.getPetid());
        petname.setText(registerpetClass.getPetname());
        petcate.setText(registerpetClass.getPetcate());
        petcolor.setText(registerpetClass.getPetcolor());
        petowner.setText(registerpetClass.getPetowner());

        final AlertDialog alertDialog=al.show();

        update_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterpetClass registerpetClass = new RegisterpetClass();
                registerpetClass.setPetid(petid.getText().toString());
                registerpetClass.setId(id);
                registerpetClass.setPetname(petname.getText().toString());
                registerpetClass.setPetcate(petcate.getText().toString());
                registerpetClass.setPetcolor(petcolor.getText().toString());
                registerpetClass.setPetowner(petowner.getText().toString());
                databaseHelperClass.updatepet(registerpetClass);
                alertDialog.dismiss();
                refreshData();
            }
        });
    }



    private void showInputDialog() {
        AlertDialog.Builder al = new AlertDialog.Builder(Register_pet.this);
        View view = getLayoutInflater().inflate(R.layout.petreg_insert,null);
        final EditText petid = view.findViewById(R.id.petid);
        final EditText petname = view.findViewById(R.id.petname);
        final EditText petcate = view.findViewById(R.id.petcate);
        final EditText petcolor = view.findViewById(R.id.petcolor);
        final EditText petowner = view.findViewById(R.id.petowner);
        Button insertBtn =view.findViewById(R.id.insert_btn);
        al.setView(view);

        final AlertDialog alertDialog=al.show();
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterpetClass registerpetClass = new RegisterpetClass();
                registerpetClass.setPetid(petid .getText().toString());
                registerpetClass.setPetname(petname.getText().toString());
                registerpetClass.setPetcate(petcate.getText().toString());
                registerpetClass.setPetcolor(petcolor.getText().toString());
                registerpetClass.setPetowner(petowner.getText().toString());
                Date date = new Date();
                registerpetClass.setCreated_at(""+date.getTime());
                databaseHelperClass.addpet(registerpetClass);
                alertDialog.dismiss();
                refreshData();
            }
        });
    }
}