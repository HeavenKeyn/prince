package com.example.model.user;

import com.google.gson.Gson;

/**
 * Created by Heavenkenyn on 2017/3/3.
 */

public class PersonUser extends RegisterInfo {

    public PersonUser(String id) {
        super(id);
        this.name = id;
        this.password = "000000";
    }

    public static PersonUser decode(String jsonStr){
        PersonUser user = new Gson().fromJson(jsonStr,PersonUser.class);
        return user;
    }

    public void setRegisterInfo(RegisterInfo registerInfo){
        setEmail(registerInfo.getEmail());
        setPassword(registerInfo.getPassword());
        setName(registerInfo.getName());
    }

}
