package com.example;

import com.example.model.msg.Mail;
import com.example.model.msg.Msg;
import com.example.model.msg.RecordMail;
import com.example.model.user.PersonUser;

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
 * Created by Heavenkenyn on 2017/3/1.
 */

public class MySQL {

    private Connection conn;
    private static MySQL mySQL;
    public static final String DB_USER = "user";
    final String TABLE_USER = "person";
    final String TABLE_MSG = "msg";
    final String TABLE_MAIL = "mail";
    final String[] COL_MAIL = {"mail_id","sender","messenger","msg_id","receiver","create_time","read"};

    private MySQL(String db){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connect to mysql.");
            String url="jdbc:mysql://127.0.0.1:3306/" + db;
            conn= DriverManager.getConnection(url, "root", "360240");
            System.out.println("Connect to database " + db);
        }catch (Exception e){

        }
    }

    public synchronized static MySQL getInstance(String db){
        if (mySQL == null)
            mySQL = new MySQL(db);
        return mySQL;
    }

    public int insert(PersonUser user){
        String sql = "INSERT INTO "+TABLE_USER+"(user_id,username,email,password) VALUES(?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,user.getId());
            ps.setString(2,user.getName());
            ps.setString(3,user.getEmail());
            ps.setString(4,user.getPassword());
            //ps.setTimestamp(5,new Timestamp(System.currentTimeMillis()));
            int result = ps.executeUpdate();
            if(result > 0){
                System.out.println("Insert success!");
            }
            return result;
        } catch (SQLException e) {
            return -1;
        }

    }

    public PersonUser load(String id, String email){
        String sql;
        if (id != null)
            sql = "SELECT * FROM "+TABLE_USER+" WHERE user_id='"+id+"'";
        else if (email != null)
            sql = "SELECT * FROM "+TABLE_USER+" WHERE email='"+email+"'";
        else
            return null;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            PersonUser user = null;
            if (rs.next()) {
                user = new PersonUser(rs.getString(1));
                user.setName(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setPassword(rs.getString(4));
            }
            rs.close();
            stmt.close();
            System.out.println("Get User!");
            return user;
        } catch (SQLException e) {
            return null;
        }
    }

    private int insert(Msg msg){
        String sql = "INSERT INTO "+TABLE_MSG+"(msg_id,content,resource_id) VALUES(?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,msg.getId());
            ps.setString(2,msg.getContent());
            ps.setString(3,msg.getResourceId());
            int result = ps.executeUpdate();
            if(result > 0){
                System.out.println("Insert Msg success!");
            }
            return result;
        } catch (SQLException e) {
            return -1;
        }
    }

    public Msg getMsg(String id){
        String sql = "SELECT * FROM "+TABLE_MSG+" WHERE msg_id='"+id+"'";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            Msg msg = null;
            if (rs.next()) {
                msg = new Msg(rs.getString(1));
                msg.setContent(rs.getString(2));
                msg.setResourceId(rs.getString(3));
            }
            rs.close();
            stmt.close();
            System.out.println("Get Msg!");
            return msg;
        } catch (SQLException e) {
            return null;
        }
    }

    public int insert(RecordMail mail){
        Msg msg = mail.getMsg();
        if (insert(msg)!=-1){
            String sql = "INSERT INTO "+TABLE_MAIL+"(mail_id,sender,messenger,msg_id,receiver,create_time) VALUES(?,?,?,?,?,?)";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1,mail.getId());
                ps.setString(2,mail.getSender());
                ps.setString(3,mail.getMessenger());
                ps.setString(4,msg.getId());
                ps.setString(5,mail.getReceiver());
                ps.setTimestamp(6,new Timestamp(mail.getCreateTime()));
                int result = ps.executeUpdate();
                if(result > 0){
                    System.out.println("Insert RecordMail success!");
                }
                return result;
            } catch (SQLException e) {
                return -1;
            }
        }else {
            return -1;
        }
    }

    public List<Mail> load(String receiver){
        String sql;
        if (receiver != null)
            sql = "SELECT * FROM "+TABLE_MAIL+" WHERE receiver='"+receiver+"' AND has_read=0";
        else
            sql = "SELECT * FROM "+TABLE_MAIL+" WHERE "+COL_MAIL[4]+"='null'";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            List<Mail> list = new ArrayList<>();
            while (rs.next()) {
                Mail mail = new Mail(rs.getString(1), rs.getString(2));
                mail.setMessenger(rs.getString(3));
                mail.setMsg(getMsg(rs.getString(4)));
                mail.setCreateTime(rs.getTimestamp(6).getTime());
                list.add(mail);
                System.out.println("Get Mail!");
            }
            rs.close();
            stmt.close();
            return list;
        } catch (SQLException e) {
            return null;
        }
    }

    public int update(String id, String receiver){
        String sql = "UPDATE "+TABLE_MAIL+" SET has_read=1 WHERE mail_id='"+id+"' AND receiver='"+receiver+"'";
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
