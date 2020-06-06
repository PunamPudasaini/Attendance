package com.example.myproject.DTO;

import com.example.myproject.activity.ViewRegisteredStudent;

import java.util.ArrayList;

public class RegisteredStudent extends ArrayList<RegisteredStudent> {
    int registered_id;
    String registered_firstname;
    String registered_lastname;
    String registered_address;
    String registered_lcid;
    String registered_password;


  /*  public RegisteredStudent() {
    }

    public RegisteredStudent(int registered_id, String registered_firstname, String registered_lastname, String registered_address, String registered_lcid, String registered_password) {
        this.registered_id = registered_id;
        this.registered_firstname = registered_firstname;
        this.registered_lastname = registered_lastname;
        this.registered_address = registered_address;
        this.registered_lcid = registered_lcid;
        this.registered_password = registered_password;
    }*/

    public int getRegistered_id() {
        return registered_id;
    }

    public void setRegistered_id(int registered_id) {
        this.registered_id = registered_id;
    }

    public String getRegistered_firstname() {
        return registered_firstname;
    }

    public void setRegistered_firstname(String registered_firstname) {
        this.registered_firstname = registered_firstname;
    }

    public String getRegistered_lastname() {
        return registered_lastname;
    }

    public void setRegistered_lastname(String registered_lastname) {
        this.registered_lastname = registered_lastname;
    }

    public String getRegistered_address() {
        return registered_address;
    }

    public void setRegistered_address(String registered_address) {
        this.registered_address = registered_address;
    }

    public String getRegistered_lcid() {
        return registered_lcid;
    }

    public void setRegistered_lcid(String registered_lcid) {
        this.registered_lcid = registered_lcid;
    }

    public String getRegistered_password() {
        return registered_password;
    }

    public void setRegistered_password(String registered_password) {
        this.registered_password = registered_password;
    }
}
