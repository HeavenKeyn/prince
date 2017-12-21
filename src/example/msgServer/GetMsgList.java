package com.example.msgServer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Heavenkenyn on 2017/7/25.
 */

public class GetMsgList extends GenericDBCommand{
    public GetMsgList(String type) {
        super(type);
    }

    public List<Message> excute(){
        try {
            Connection conn = getConnection();
            ResultSet rs = excuteStatement(conn);
            return processResults(rs);
        }catch (SQLException e){
            return null;
        }
    }

    private ResultSet excuteStatement(Connection conn) throws SQLException{
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM "+"msg";
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }

    private List<Message> processResults(ResultSet rs) throws SQLException{
        List<Message> list = new ArrayList<>();
        ResultSetMetaData md = rs.getMetaData();
        int cols = md.getColumnCount();
        while (rs.next()){
            String sender = null;
            String recipient = null;
            String content = null;
            for (int i=0;i<cols;i++){
                if (md.getColumnType(i) == Types.VARCHAR){
                    String str = rs.getString(i);
                    if (md.getCatalogName(i).equals("sender")){
                        sender = str;
                    }else if (md.getCatalogName(i).equals("recipient")){
                        recipient = str;
                    }else if (md.getCatalogName(i).equals("content")){
                        content = str;
                    }
                }
                Message msg = new Message(sender,recipient,content);
                list.add(msg);
            }
        }
        return list;
    }
}
