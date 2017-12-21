package com.example.model.msg;

import com.example.model.msg.Mail;
import com.google.gson.Gson;

/**
 * Created by Heavenkenyn on 2017/3/24.
 */

public class RecordMail extends Mail {
    String receiver;

    public RecordMail(){
        super();
    }

    public RecordMail(String id, String sender) {
        super(id, sender);
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public static RecordMail decode(String jsonStr){
        return new Gson().fromJson(jsonStr,RecordMail.class);
    }
}
