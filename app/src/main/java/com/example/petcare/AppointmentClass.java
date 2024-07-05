package com.example.petcare;

public class AppointmentClass {

    String aid = "";
    String appcare = "";
    String apppetown = "";
    String apppet = "";
    String apppetnote = "";
    String appduration = "";
    String appconf = "";
    String created_ata = "";

    public AppointmentClass(String aid, String appcare, String apppetown, String apppet, String apppetnote, String appduration, String appconf, String created_ata) {
        this.aid = aid;
        this.appcare = appcare;
        this.apppetown = apppetown;
        this.apppet = apppet;
        this.apppetnote = apppetnote;
        this.appduration = appduration;
        this.appconf = appconf;
        this.created_ata = created_ata;
    }

    public AppointmentClass(){

    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getAppcare() {
        return appcare;
    }

    public void setAppcare(String appcare) {
        this.appcare = appcare;
    }

    public String getApppetown() {
        return apppetown;
    }

    public void setApppetown(String apppetown) {
        this.apppetown = apppetown;
    }

    public String getApppet() {
        return apppet;
    }

    public void setApppet(String apppet) {
        this.apppet = apppet;
    }

    public String getApppetnote() {
        return apppetnote;
    }

    public void setApppetnote(String apppetnote) {
        this.apppetnote = apppetnote;
    }

    public String getAppduration() {
        return appduration;
    }

    public void setAppduration(String appduration) {
        this.appduration = appduration;
    }

    public String getAppconf() {
        return appconf;
    }

    public void setAppconf(String appconf) {
        this.appconf = appconf;
    }

    public String getCreated_ata() {
        return created_ata;
    }

    public void setCreated_ata(String created_ata) {
        this.created_ata = created_ata;
    }
}
