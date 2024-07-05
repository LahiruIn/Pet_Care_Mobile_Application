package com.example.petcare;

public class RegistercareClass {

    String cid = "";
    String careid = "";
    String carename = "";
    String careexp = "";
    String carecontact = "";
    String carefee = "";
    String created_atc = "";

    public RegistercareClass(String cid, String careid, String carename, String careexp, String carecontact, String carefee, String created_atc) {
        this.cid = cid;
        this.careid = careid;
        this.carename = carename;
        this.careexp = careexp;
        this.carecontact = carecontact;
        this.carefee = carefee;
        this.created_atc = created_atc;
    }

    public RegistercareClass(){

    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCareid() {
        return careid;
    }

    public void setCareid(String careid) {
        this.careid = careid;
    }

    public String getCarename() {
        return carename;
    }

    public void setCarename(String carename) {
        this.carename = carename;
    }

    public String getCareexp() {
        return careexp;
    }

    public void setCareexp(String careexp) {
        this.careexp = careexp;
    }

    public String getCarecontact() {
        return carecontact;
    }

    public void setCarecontact(String carecontact) {
        this.carecontact = carecontact;
    }

    public String getCarefee() {
        return carefee;
    }

    public void setCarefee(String carefee) {
        this.carefee = carefee;
    }

    public String getCreated_atc() {
        return created_atc;
    }

    public void setCreated_atc(String created_atc) {
        this.created_atc = created_atc;
    }
}
