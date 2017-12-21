package server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import server.model.User;

public class UDB {
	private Connection conn;
	private static UDB udb;
	public static final String TABLE = "user";
	public static final String ID = "id";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String PHONE = "phone";
	public static final String EMAIL = "email";
	
	private UDB(Connection connection) {
		this.conn = connection;
	}
	
	public static UDB getInstance() {
		if (udb==null) {
			udb = new UDB(MySQL.getInstance().getConn());
		}
		return udb;
	}
	
	public User getIdPassword(String upe) {
		if (upe==null) {
			return null;
		}
		User result = null;
		String column;
		if (upe.contains("@")) {
			column = EMAIL;
		}else if (upe.contains("+")) {
			column = PHONE;
		}else {
			column = USERNAME;
		}
		String sql = "SELECT "+ID+", "+PASSWORD+" FROM "+TABLE+" WHERE "+column+" = ?";
        try {
        	PreparedStatement stmt = conn.prepareStatement(sql);
        	stmt.setString(1, upe);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	result = new User();
            	result.setId(rs.getInt(1));
            	result.setPassword(rs.getString(2));
            }
            rs.close();
            stmt.close();
            System.out.println("Get User!");
        } catch (SQLException e) {
            return null;
        }
		return result;
	}
	
	public boolean exsists(String upe) {
		if (upe==null) {
			return false;
		}
		String column;
		if (upe.contains("@")) {
			column = EMAIL;
		}else if (upe.contains("+")) {
			column = PHONE;
		}else {
			column = USERNAME;
		}
		String sql = "SELECT COUNT(1) FROM "+TABLE+" WHERE "+column+" = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
        try {
        	stmt = conn.prepareStatement(sql);
        	stmt.setString(1, upe);
            rs = stmt.executeQuery();
            if (rs.next()&&rs.getInt(1)>0) {
            	System.out.println("Exsists!");
            	return true;
            }
        } catch (SQLException e) {
            return false;
        }finally{
			try {
				if(stmt!=null){
					stmt.close();
				}
				if(rs!=null){
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean insert(User user) {
		if (user==null) {
			return false;
		}
		PreparedStatement pstm = null;
		String sql = "INSERT INTO "+TABLE+" ( "+
				USERNAME+", "+
				PASSWORD+", "+
				PHONE+", "+
				EMAIL+" "+
				") "+
				"VALUES "+
				"  ( "+
				"    ?,?,?,?"+
				"  ); ";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, user.getUsername());
			pstm.setString(2, user.getPassword());
			pstm.setString(3, user.getPhone());
			pstm.setString(4, user.getEmail());
			pstm.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
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
