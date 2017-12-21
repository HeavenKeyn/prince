package server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import server.model.Kingdom;
import server.model.Link;

public class KDB {
	private Connection conn;
	private static KDB kdb;
	public static final String TABLE = "kingdom";
	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String GOLD = "gold";
	public static final String FOOD = "food";
	public static final String PARENT = "parent";
	
	private KDB(Connection connection) {
		this.conn = connection;
	}
	
	public static KDB getInstance() {
		if (kdb==null) {
			kdb = new KDB(MySQL.getInstance().getConn());
		}
		return kdb;
	}
	
	public boolean insert(Kingdom kingdom) {
		if (kingdom==null) {
			return false;
		}
		PreparedStatement pstm = null;
		String sql = "INSERT INTO "+TABLE+" ( "+
				NAME+", "+GOLD+", "+FOOD+", "+PARENT+
				") "+
				"VALUES "+
				"  ( "+
				"    ?,?,?,?"+
				"  ); ";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, kingdom.getName());
			pstm.setFloat(2, kingdom.getGold());
			pstm.setFloat(3, kingdom.getFood());
			pstm.setInt(4, kingdom.getParent());
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
	
	public Kingdom get(Link link) {
		if (link==null||link.getKid()==0) {
			return null;
		}
		String sql = "SELECT "+
		ID+", "+NAME+", "+GOLD+", "+FOOD+", "+PARENT+
		" FROM "+TABLE+" WHERE "+ID+" = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
        try {
        	stmt = conn.prepareStatement(sql);
        	stmt.setInt(1, link.getKid());
            rs = stmt.executeQuery();
            if (rs.next()) {
            	System.out.println("Get Kingdom!");
            	return new Kingdom(rs.getInt(1))
            			.setName(rs.getString(2)).setGold(rs.getFloat(3)).setFood(rs.getFloat(4))
            			.setParent(rs.getInt(5));
            }
        } catch (SQLException e) {
            return null;
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
		return null;
	}

}
