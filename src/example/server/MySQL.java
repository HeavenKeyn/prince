package com.example.server;

import com.example.MsgData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Heavenkenyn on 2017/4/17.
 */

public class MySQL {
    private Connection conn;
    private static MySQL mySQL;
    private final String DB_NAME = "user";
    private final String TABLE_NAME = "msg_data";

    private MySQL(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connect to mysql.");
            String url="jdbc:mysql://127.0.0.1:3306/" + DB_NAME;
            conn= DriverManager.getConnection(url, "root", "360240");
            System.out.println("Connect to database " + DB_NAME);
        }catch (Exception e){

        }
    }

    public synchronized static MySQL getInstance(){
        if (mySQL == null)
            mySQL = new MySQL();
        return mySQL;
    }

    public int insert(String sender, MsgData msgData){
        String sql = "INSERT INTO "+TABLE_NAME+"(_id,sender,receiver,date_send,content) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,msgData.getId());
            ps.setString(2,sender);
            ps.setString(3,msgData.getUser_id());
            ps.setString(4,msgData.getDate_send());
            ps.setString(5,msgData.getContent());
            int result = ps.executeUpdate();
            if(result > 0){
                System.out.println("Insert RecordMail success!");
            }
            return result;
        } catch (SQLException e) {
            return -1;
        }
    }

    public MsgData get(String receiver){
        String sql = "SELECT * FROM "+TABLE_NAME+" WHERE receiver='"+receiver+"' AND has_read=0";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            MsgData msgData = null;
            if (rs.next()) {
                msgData = new MsgData(rs.getString(1));
                msgData.setUser_id(rs.getString(2));
                msgData.setDate_send(rs.getString(4));
                msgData.setContent(rs.getString(5));
                System.out.println("Get Mail!");
            }
            rs.close();
            stmt.close();
            return msgData;
        } catch (SQLException e) {
            return null;
        }
    }

    public List<MsgData> load(String receiver){
        String sql = "SELECT * FROM "+TABLE_NAME+" WHERE receiver='"+receiver+"' AND has_read=0";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            List<MsgData> list = new ArrayList<>();
            while (rs.next()) {
                MsgData msgData = new MsgData(rs.getString(1));
                msgData.setUser_id(rs.getString(2));
                msgData.setDate_send(rs.getString(4));
                msgData.setContent(rs.getString(5));
                list.add(msgData);
                System.out.println("Get Mail!");
            }
            rs.close();
            stmt.close();
            return list;
        } catch (SQLException e) {
            return null;
        }
    }

    public int update(String id){
        String sql = "UPDATE "+TABLE_NAME+" SET has_read=1 WHERE _id='"+id+"'";
        try {
            Statement stmt = conn.createStatement();
            int result = stmt.executeUpdate(sql);
            stmt.close();
            if (result>0)
                System.out.println("Update Mail!");
            return result;
        } catch (SQLException e) {
            return -1;
        }
    }
}
