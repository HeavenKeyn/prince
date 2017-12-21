package com.example.server;

import com.example.User;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Server {
    public static void main(String[] args){
        /*try {
            System.out.println(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }*/
        new MessageServer();
    }
}
