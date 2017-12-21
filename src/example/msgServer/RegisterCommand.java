package com.example.msgServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Created by Heavenkenyn on 2017/7/19.
 */

public class RegisterCommand implements Command {
    private BufferedWriter out;
    private BufferedReader in;
    private MsgSvrConnection conn;

    public RegisterCommand(BufferedReader in, BufferedWriter out,
                        MsgSvrConnection serverConn)
    {
        this.out = out;
        this.in = in;
        this.conn = serverConn;
    }

    public void execute() throws IOException {
        String username = in.readLine();
        String password = in.readLine();
        String birthday = in.readLine();
        String telephone = in.readLine();
        String address = in.readLine();

        if (conn.getServer().isValidUser(username)){
            (new ErrorCommand(in, out,conn, "The username already exist.")).execute();
        }else {
            conn.getServer().setUser(username,password);
            conn.getServer().setUser(username+"birthday",birthday);
            conn.getServer().setUser(username+"telephone",telephone);
            conn.getServer().setUser(username+"address",address);
            out.write("200\r\n");
            out.flush();
        }
    }
}
