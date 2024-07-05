package com.example.petcare;

public class RegisterpetClass {
    String id = "";
    String petid = "";
    String petname = "";
    String petcate = "";
    String petcolor = "";
    String petowner = "";
    String created_at = "";

    public RegisterpetClass(String id, String petid, String petname, String petcate, String petcolor, String petowner, String created_at) {
        this.id = id;
        this.petid = petid;
        this.petname = petname;
        this.petcate = petcate;
        this.petcolor = petcolor;
        this.petowner = petowner;
        this.created_at = created_at;
    }

    public RegisterpetClass (){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPetid() {
        return petid;
    }

    public void setPetid(String petid) {
        this.petid = petid;
    }

    public String getPetname() {
        return petname;
    }

    public void setPetname(String petname) {
        this.petname = petname;
    }

    public String getPetcate() {
        return petcate;
    }

    public void setPetcate(String petcate) {
        this.petcate = petcate;
    }

    public String getPetcolor() {
        return petcolor;
    }

    public void setPetcolor(String petcolor) {
        this.petcolor = petcolor;
    }

    public String getPetowner() {
        return petowner;
    }

    public void setPetowner(String petowner) {
        this.petowner = petowner;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
