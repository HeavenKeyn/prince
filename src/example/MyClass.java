package com.example;

import com.example.model.msg.Mail;
import com.example.model.msg.Msg;
import com.example.model.msg.RecordMail;

import java.util.Calendar;
import java.util.List;

public class MyClass {
    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                new BasicServer();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                new ChatServer();
            }
        }).start();

        /*List<Mail> list = MySQL.getInstance(MySQL.DB_USER).load("ccc");
        if (list!=null && list.size()>0){
            Mail mail = list.get(0);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(Mail.decode(mail.code()).getCreateTime());
            System.out.println(calendar.get(Calendar.DATE));
            MySQL.getInstance(MySQL.DB_USER).update(mail.getId(),"ccc");
        }*/

    }


}
