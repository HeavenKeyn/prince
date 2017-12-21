package server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import server.model.Character;
import server.model.Family;
import server.model.Kingdom;
import server.model.Link;
import server.model.User;

public class LDB {
	private Connection conn;
	private static LDB ldb;
	public static final String TABLE = "link";
	public static final String ID = "id";
	public static final String UID = "uid";
	public static final String FID = "fid";
	//public static final String CID = "cid";
	public static final String KID = "kid";
	
	private LDB(Connection connection) {
		this.conn = connection;
	}
	
	public static LDB getInstance() {
		if (ldb==null) {
			ldb = new LDB(MySQL.getInstance().getConn());
		}
		return ldb;
	}
	
	public Link get(User user) {
		if (user==null||user.getId()==0) {
			return null;
		}
		String sql = "SELECT "+
		ID+", "+UID+", "+FID+", "+KID+
		" FROM "+TABLE+" WHERE "+UID+" = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
        try {
        	stmt = conn.prepareStatement(sql);
        	stmt.setInt(1, user.getId());
            rs = stmt.executeQuery();
            if (rs.next()) {
            	System.out.println("Get Link!");
            	return new Link().setId(rs.getInt(1))
            			.setUid(rs.getInt(2)).setFid(rs.getInt(3)).setKid(rs.getInt(5));
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
	
	public Link get(Character character) {
		if (character==null||character.getId()==0) {
			return null;
		}
		String sql = "SELECT "+
		ID+", "+UID+", "+FID+", "+KID+
		" FROM "+TABLE+" WHERE "+UID+" = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
        try {
        	stmt = conn.prepareStatement(sql);
        	stmt.setInt(1, character.getUid());
            rs = stmt.executeQuery();
            if (rs.next()) {
            	System.out.println("Get Link!");
            	return new Link().setId(rs.getInt(1))
            			.setUid(rs.getInt(2)).setFid(rs.getInt(3)).setKid(rs.getInt(5));
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
	
	public List<Link> get(Family family){
		if (family==null||family.getId()==0) {
			return null;
		}
		String sql = "SELECT "+
		ID+", "+UID+", "+FID+", "+KID+
		" FROM "+TABLE+" WHERE "+FID+" = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Link> list = new ArrayList<>();
        try {
        	stmt = conn.prepareStatement(sql);
        	stmt.setInt(1, family.getId());
            rs = stmt.executeQuery();
            while (rs.next()) {
            	System.out.println("Get Link!");
            	list.add(new Link().setId(rs.getInt(1))
            			.setUid(rs.getInt(2)).setFid(rs.getInt(3)).setKid(rs.getInt(5)));
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
		return list;
	}
	
	public List<Link> get(Kingdom kingdom){
		if (kingdom==null||kingdom.getId()==0) {
			return null;
		}
		String sql = "SELECT "+
		ID+", "+UID+", "+FID+", "+KID+
		" FROM "+TABLE+" WHERE "+KID+" = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Link> list = new ArrayList<>();
        try {
        	stmt = conn.prepareStatement(sql);
        	stmt.setInt(1, kingdom.getId());
            rs = stmt.executeQuery();
            while (rs.next()) {
            	System.out.println("Get Link!");
            	list.add(new Link().setId(rs.getInt(1))
            			.setUid(rs.getInt(2)).setFid(rs.getInt(3)).setKid(rs.getInt(5)));
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
		return list;
	}
	
	public boolean insert(Link link) {
		if (link==null) {
			return false;
		}
		PreparedStatement pstm = null;
		String sql = "INSERT INTO "+TABLE+" ( "+
				UID+", "+FID+", "+KID+
				") "+
				"VALUES "+
				"  ( "+
				"    ?,?,?"+
				"  ); ";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, link.getUid());
			pstm.setInt(2, link.getFid());
			pstm.setInt(3, link.getKid());
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
