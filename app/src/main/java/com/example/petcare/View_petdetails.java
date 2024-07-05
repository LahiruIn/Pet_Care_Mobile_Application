package com.example.petcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class View_petdetails extends AppCompatActivity {

    DatabaseHelperClass databaseHelperClass;
    TextView datalist;
    TextView datalist_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_petdetails);

        databaseHelperClass = new DatabaseHelperClass(View_petdetails.this);
        Button read = findViewById(R.id.refresh_data);
        datalist = findViewById(R.id.all_data_list);
        datalist_count = findViewById(R.id.data_list_count);

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshData();
            }
        });


    }


        private void refreshData() {
            datalist_count.setText("All Data Count : "+databaseHelperClass.getTotal());
            List<RegisterpetClass> registerpetClassList = databaseHelperClass.getAllpet();
            datalist.setText("");
            for (RegisterpetClass registerpetClass : registerpetClassList){
                datalist.append(" Pet ID : "+registerpetClass.getId()+ "\n" +
                        " Pet age : "+registerpetClass.getPetid()+ "\n" +
                        " Pet name : "+registerpetClass.getPetname()+ "\n" +
                        " Pet category : "+registerpetClass.getPetcate()+ "\n" +
                        " Pet colour : "+registerpetClass.getPetcolor()+ "\n" +
                        " Pet owner : "+registerpetClass.getPetowner()+ " \n\n");
            }
        }

    }
