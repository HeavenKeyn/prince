package com.example.model.user;

import com.google.gson.Gson;

/**
 * Created by Heavenkenyn on 2017/3/16.
 */

public class RegisterInfo extends PersonInfo {
    protected int gender;
    private String PIN;

    public RegisterInfo(String email, String password, String PIN) {
        super(email,password);
        this.PIN = PIN;
    }

    protected RegisterInfo(String id) {
        super(id);
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public static RegisterInfo decode(String jsonStr){
        RegisterInfo registerInfo = new Gson().fromJson(jsonStr,RegisterInfo.class);
        return registerInfo;
    }
}
