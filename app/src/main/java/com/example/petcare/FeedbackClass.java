package com.example.petcare;

public class FeedbackClass {

    String fid = "";
    String feedusername = "";
    String feednotes = "";
    String feedstatus = "";
    String created_atf = "";

    public FeedbackClass(String fid, String feedusername, String feednotes, String feedstatus, String created_atf) {
        this.fid = fid;
        this.feedusername = feedusername;
        this.feednotes = feednotes;
        this.feedstatus = feedstatus;
        this.created_atf = created_atf;
    }

    public FeedbackClass(){

    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFeedusername() {
        return feedusername;
    }

    public void setFeedusername(String feedusername) {
        this.feedusername = feedusername;
    }

    public String getFeednotes() {
        return feednotes;
    }

    public void setFeednotes(String feednotes) {
        this.feednotes = feednotes;
    }

    public String getFeedstatus() {
        return feedstatus;
    }

    public void setFeedstatus(String feedstatus) {
        this.feedstatus = feedstatus;
    }

    public String getCreated_atf() {
        return created_atf;
    }

    public void setCreated_atf(String created_atf) {
        this.created_atf = created_atf;
    }
}
