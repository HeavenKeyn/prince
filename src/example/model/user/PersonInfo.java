package com.example.model.user;

import com.google.gson.Gson;

/**
 * Created by Heavenkenyn on 2017/3/25.
 */

public class PersonInfo extends User {
    protected String email;
    protected String password;

    public PersonInfo(String email, String password) {
        this.email = email;
        this.password = password;
    }

    protected PersonInfo(String id){
        super(id);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null)
            this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password != null && !password.isEmpty())//
            this.password = password;
    }

    public String code(){
        String jsonStr = new Gson().toJson(this);
        return jsonStr;
    }

    public static PersonInfo decode(String jsonStr){
        PersonInfo personInfo = new Gson().fromJson(jsonStr,PersonInfo.class);
        return personInfo;
    }
}
