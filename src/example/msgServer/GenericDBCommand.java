package com.example.msgServer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Heavenkenyn on 2017/7/25.
 */

public class GenericDBCommand implements Command{
    private String dbURL;
    private String driverName;
    private String username;
    private String password;
    private Connection conn = null;

    public GenericDBCommand(String type){
        if (type.equals("msg")){
            dbURL = "";
            driverName = "";
            username = "";
            password = "";
        }else if (type.equals("user")){
            dbURL = "";
            driverName = "";
            username = "";
            password = "";
        }else {
            throw new IllegalArgumentException(type + "is wrong");
        }
    }

    protected void makeConnection(){
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.out.println("Wrong in Loading Driver");
            System.exit(1);
        }

        try {
            conn = DriverManager.getConnection(dbURL,username,password);
            System.out.println("Connect to Database");
            execute();
        } catch (Exception e) {
            System.out.println("Problem in Connection Database");
        } finally {
            if (conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Exit problem");
                }
            }
        }
    }

    public Connection getConnection(){
        return conn;
    }

    public void execute() throws IOException {

    }
}
