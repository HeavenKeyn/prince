package server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import server.model.Family;
import server.model.Link;

public class FDB {
	private Connection conn;
	private static FDB fdb;
	public static final String TABLE = "family";
	public static final String ID = "id";
	public static final String FAMILYNAME = "familyname";
	public static final String COAT = "coat";
	public static final String PRESTIGE = "prestige";
	
	private FDB(Connection connection) {
		this.conn = connection;
	}
	
	public static FDB getInstance() {
		if (fdb==null) {
			fdb = new FDB(MySQL.getInstance().getConn());
		}
		return fdb;
	}
	
	public boolean insert(Family family) {
		if (family==null) {
			return false;
		}
		PreparedStatement pstm = null;
		String sql = "INSERT INTO "+TABLE+" ( "+
				FAMILYNAME+", "+COAT+", "+PRESTIGE+
				") "+
				"VALUES "+
				"  ( "+
				"    ?,?,?,?"+
				"  ); ";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, family.getFamilyname());
			pstm.setString(2, family.getCoat());
			pstm.setFloat(3, family.getPrestige());
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
	
	public Family get(Link link) {
		if (link==null||link.getKid()==0) {
			return null;
		}
		String sql = "SELECT "+
		ID+", "+FAMILYNAME+", "+COAT+", "+PRESTIGE+
		" FROM "+TABLE+" WHERE "+ID+" = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
        try {
        	stmt = conn.prepareStatement(sql);
        	stmt.setInt(1, link.getFid());
            rs = stmt.executeQuery();
            if (rs.next()) {
            	System.out.println("Get Kingdom!");
            	return new Family(rs.getInt(1));
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
