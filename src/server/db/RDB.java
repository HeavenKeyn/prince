package server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import server.model.Character;
import server.model.Relation;

public class RDB {
	private Connection conn;
	private static RDB rdb;
	public static final String TABLE = "baidu_test";
	public static final String ID = "id";
	public static final String IMPOSE = "impose";
	public static final String BEAR = "bear";	
	public static final String OPINION = "opinion";
	public static final String TURN = "turn";
	
	private RDB(Connection connection) {
		this.conn = connection;
	}
	
	public static RDB getInstance() {
		if (rdb==null) {
			rdb = new RDB(MySQL.getInstance().getConn());
		}
		return rdb;
	}
	
	public List<Relation> get(Character character){
		if (character==null) {
			return null;
		}
		String sql = "SELECT "+
				ID+", "+IMPOSE+", "+BEAR+", "+OPINION+", "+TURN+
				" FROM "+TABLE+" WHERE "+IMPOSE+" = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Relation> list = new ArrayList<>();
		try {
        	stmt = conn.prepareStatement(sql);
        	stmt.setInt(1, character.getId());
            rs = stmt.executeQuery();
            while (rs.next()) {
            	System.out.println("Get Relation!");
            	list.add(new Relation(rs.getInt(1))
            			.setImpose(rs.getInt(2)).setBear(rs.getInt(3))
            			.setOpinion(rs.getInt(4)).setTurn(rs.getInt(5)));
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
	
	public List<Relation> get(List<Character> characters){
		if (characters==null||characters.size()==0) {
			return null;
		}
		String sql = "SELECT "+
		ID+", "+IMPOSE+", "+BEAR+", "+OPINION+", "+TURN+
		" FROM "+TABLE+" WHERE "+BEAR+" = ";
		String union = " UNION ALL ";
		StringBuffer sqls = new StringBuffer(sql);sqls.append(characters.get(0).getId());
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Relation> list = new ArrayList<>();
        try {
        	for (int i = 1; i < characters.size(); i++) {
				sqls.append(union).append(characters.get(i).getId());
			}
        	stmt = conn.prepareStatement(sqls.toString());
            rs = stmt.executeQuery();
            while (rs.next()) {
            	System.out.println("Get Link!");
            	list.add(new Relation(rs.getInt(1))
            			.setImpose(rs.getInt(2)).setBear(rs.getInt(3))
            			.setOpinion(rs.getInt(4)).setTurn(rs.getInt(5)));
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

}
