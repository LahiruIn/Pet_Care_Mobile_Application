package com.example.petcare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class View_feedback extends AppCompatActivity {

    DatabaseHelperClass databaseHelperClass;
    TextView fdatalist;
    TextView fdatalist_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_feedback);

        databaseHelperClass = new DatabaseHelperClass(View_feedback.this);
        Button fread = findViewById(R.id.frefresh_data);
        fdatalist = findViewById(R.id.fall_data_list);
        fdatalist_count = findViewById(R.id.fdata_list_count);

        fread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedrefreshData();
            }
        });

    }

    private void feedrefreshData() {
        fdatalist_count.setText("All Data Count : "+databaseHelperClass.getTotalfeedback());
        List<FeedbackClass> feedbackClassList = databaseHelperClass.getAllfeedback();
        fdatalist.setText("");
        for (FeedbackClass feedbackClass : feedbackClassList){
            fdatalist.append(" No : "+feedbackClass.getFid()+ "\n" +
                    " User Name : "+feedbackClass.getFeedusername()+ "\n" +
                    " User Feedback : "+feedbackClass.getFeednotes()+ "\n" +
                    " User Feedback Status : "+feedbackClass.getFeedstatus()+ "\n\n");
        }
    }

}