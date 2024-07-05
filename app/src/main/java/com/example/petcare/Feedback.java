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

public class Feedback extends AppCompatActivity {

    DatabaseHelperClass databaseHelperClass;
    TextView fdatalist;
    TextView fdatalist_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        databaseHelperClass = new DatabaseHelperClass(Feedback.this);
        Button fdelete = findViewById(R.id.fdelete_data);
        Button finsert = findViewById(R.id.finsert_data);
        Button fupdate = findViewById(R.id.fupdate_data);
        Button fread = findViewById(R.id.frefresh_data);
        fdatalist = findViewById(R.id.fall_data_list);
        fdatalist_count = findViewById(R.id.fdata_list_count);

        fread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedrefreshData();
            }
        });

        finsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedshowInputDialog();
            }
        });

        fupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedshowUpdateIdDialog();
            }
        });

        fdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedshowDeleteDialog();
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

    private void feedshowDeleteDialog() {
        AlertDialog.Builder al=new AlertDialog.Builder(Feedback.this);
        View view = getLayoutInflater().inflate(R.layout.feedback_delete,null);
        al.setView(view);
        final EditText feedbackid_input = view.findViewById(R.id.feedbackid_input);
        Button delete_btn = view.findViewById(R.id.feeddelete_btn);

        final AlertDialog alertDialog=al.show();
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelperClass.deletefeedback(feedbackid_input.getText().toString());
                alertDialog.dismiss();
                feedrefreshData();
            }
        });

    }


    public void feedshowUpdateIdDialog() {
        AlertDialog.Builder al = new AlertDialog.Builder(Feedback.this);
        View view = getLayoutInflater().inflate(R.layout.feedback_update_id,null);
        al.setView(view);
        final EditText feedid_input = view.findViewById(R.id.feedbackid_input);
        Button fetch_btn = view.findViewById(R.id.feedupdate_id_btn);
        final AlertDialog alertDialog=al.show();
        fetch_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedshowDataDialog(feedid_input.getText().toString());
                alertDialog.dismiss();
                feedrefreshData();
            }
        });

    }


    private void feedshowDataDialog(final String fid) {
        FeedbackClass feedbackClass = databaseHelperClass.getfeedback(Integer.parseInt(fid));
        AlertDialog.Builder al = new AlertDialog.Builder(Feedback.this);
        View view = getLayoutInflater().inflate(R.layout.feedback_update,null);
        final EditText feedusername = view.findViewById(R.id.feedusername);
        final EditText feednotes = view.findViewById(R.id.feednotes);
        final EditText feedstatus = view.findViewById(R.id.feedstatus);
        Button fupdate_Btn =view.findViewById(R.id.feedupdate_btn);
        al.setView(view);

        feedusername.setText(feedbackClass.getFeedusername());
        feednotes.setText(feedbackClass.getFeednotes());
        feedstatus.setText(feedbackClass.getFeedstatus());

        final AlertDialog alertDialog=al.show();

        fupdate_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeedbackClass feedbackClass = new FeedbackClass();
                feedbackClass.setFeedusername(feedusername.getText().toString());
                feedbackClass.setFid(fid);
                feedbackClass.setFeednotes(feednotes.getText().toString());
                feedbackClass.setFeedstatus(feedstatus.getText().toString());
                databaseHelperClass.updatefeedback(feedbackClass);
                alertDialog.dismiss();
                feedrefreshData();
            }
        });
    }



    private void feedshowInputDialog() {
        AlertDialog.Builder al = new AlertDialog.Builder(Feedback.this);
        View view = getLayoutInflater().inflate(R.layout.feedback_insert,null);
        final EditText feedusername = view.findViewById(R.id.feedusername);
        final EditText feednotes = view.findViewById(R.id.feednotes);
        final EditText feedstatus = view.findViewById(R.id.feedstatus);
        Button feedinsertBtn =view.findViewById(R.id.feedinsert_btn);
        al.setView(view);

        final AlertDialog alertDialog=al.show();
        feedinsertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeedbackClass feedbackClass = new FeedbackClass();
                feedbackClass.setFeedusername(feedusername .getText().toString());
                feedbackClass.setFeednotes(feednotes.getText().toString());
                feedbackClass.setFeedstatus(feedstatus.getText().toString());
                Date date = new Date();
                feedbackClass.setCreated_atf(""+date.getTime());
                databaseHelperClass.addfeedback(feedbackClass);
                alertDialog.dismiss();
                feedrefreshData();
            }
        });
    }
}