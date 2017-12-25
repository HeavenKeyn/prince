package server.log.expand.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import server.log.Item;
import server.log.Logger;

public class LogDB implements Logger{
	private static Connection conn;
	private static LogDB logDB;
	private String table;
	private enum Column {create_time,log_level,thread,tag,msg}
	
	private LogDB(Connection connection) {
		LogDB.conn = connection;
		setTable(new SimpleDateFormat("yyyyww").format(Calendar.getInstance().getTime()));
	}
	
	public static LogDB getInstance(Connection connection) {
		if (logDB==null||!connection.equals(conn)) {
			logDB = new LogDB(connection);
		}
		return logDB;
	}
	
	public static LogDB getInstance() {
		if (conn==null) {
			conn = Manager.getInstance().getConn();
		}
		return getInstance(conn);
	}
	
	private String sql(String time) {
		String create = "CREATE TABLE IF NOT EXISTS `"+"log_"+time+"` (\r\n" + 
				"  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `"+Column.create_time.toString()+"` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',\r\n" + 
				"  `"+Column.log_level.toString()+"` varchar(10) NOT NULL DEFAULT 'WARN',\r\n" + 
				"  `"+Column.thread.toString()+"` varchar(255) DEFAULT NULL,\r\n" + 
				"  `"+Column.tag.toString()+"` varchar(255) DEFAULT NULL,\r\n" + 
				"  `"+Column.msg.toString()+"` text,\r\n" + 
				"  PRIMARY KEY (`id`)\r\n" + 
				") ENGINE=InnoDB DEFAULT CHARSET=utf8\r\n" + 
				";";
		return create;
	}

	public String setTable(String time) {
		table = "log_"+time;
		create(time);
		return table;
	}
	
	private boolean create(String time) {
		String sql = sql(time);
		Statement stmt = null;
		try {
            stmt = conn.createStatement();
            return stmt.execute(sql);
        } catch (SQLException e) {
            return false;
        }finally{
			try {
				if(stmt!=null){
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void print(Item item) {
		if (table==null) {
			return;
		}
		PreparedStatement pstm = null;
		String sql = "INSERT INTO "+table+" ( "+
				Column.create_time+", "+Column.log_level+", "+Column.thread+", "+
				Column.tag+", "+Column.msg+
				") "+
				"VALUES "+
				"  ( "+
				"    ?,?,?,?,?"+
				"  ); ";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setTimestamp(1, new Timestamp(item.calendar.getTimeInMillis()));
			pstm.setString(2, item.level.toString());
			pstm.setString(3, item.thread.getName());
			pstm.setString(4, item.tag);
			pstm.setString(5, item.msg.toString());
			pstm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(pstm!=null){
					pstm.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	

}
