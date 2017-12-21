package server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import server.model.Character;
import server.model.Link;
import server.model.Location;

public class CDB {
	private Connection conn;
	private static CDB cdb;
	public static final String TABLE = "mtl_c_test";
	public static final String ID = "id";
	public static final String FIRSTNAME = "firstname";
	public static final String ABILITIES = "abilities";
	public static final String ATTRIBUTES = "attributes";
	public static final String AGE = "age";
	public static final String APPEARANCE = "appearance";
	public static final String TURN = "turn";
	public static final String UID = "uid";
	public static final String FID = "fid";
	public static final String KID = "kid";
	public static final String GOLD = "gold";
	public static final String LOCATE_K = "locate_k";
	public static final String LOCATE_X = "locate_x";
	public static final String LOCATE_Y = "locate_y";
	public static final String LOCATE_Z = "locate_z";
	public static final String STATE = "state";
	
	private CDB(Connection connection) {
		this.conn = connection;
	}
	
	public static CDB getInstance() {
		if (cdb==null) {
			cdb = new CDB(MySQL.getInstance().getConn());
		}
		return cdb;
	}
	
	public List<Character> getLocateInKingdom(Link link){
		if (link==null) {
			return null;
		}
		return getLocateInKingdom(link.getKid());
	}
	
	private List<Character> getLocateInKingdom(int kid){
		if (kid==0) {
			return null;
		}
		String sql = "SELECT "+ID+", "+
				FIRSTNAME+", "+ABILITIES+", "+ATTRIBUTES+", "+
				AGE+", "+APPEARANCE+", "+TURN+", "+
				UID+", "+FID+", "+KID+", "+GOLD+", "+
				LOCATE_K+", "+LOCATE_X+", "+LOCATE_Y+", "+STATE+
				" FROM "+TABLE+" WHERE "+LOCATE_K+" = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Character> list = new ArrayList<>();
        try {
        	stmt = conn.prepareStatement(sql);
        	stmt.setInt(1, kid);
            rs = stmt.executeQuery();
            while (rs.next()) {
            	System.out.println("Get Link!");
            	list.add(new Character(rs.getInt(1))
            			.setFirstname(rs.getString(2)).setAbilities(rs.getString(3)).setAttributes(rs.getString(4))
            			.setAge(rs.getFloat(5)).setAppearance(rs.getString(6)).setTurn(rs.getInt(7))
            			.setUid(rs.getInt(8)).setFid(rs.getInt(9)).setKid(rs.getInt(10)).setGold(rs.getFloat(11))
            			.setLocation(new Location(rs.getInt(12), rs.getFloat(13), rs.getFloat(14)))
            			.setState(rs.getString(15)));
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
	
	public boolean insert(Character character) {
		if (character==null) {
			return false;
		}
		PreparedStatement pstm = null;
		String sql = "INSERT INTO "+TABLE+" ( "+
				FIRSTNAME+", "+ABILITIES+", "+ATTRIBUTES+", "+
				AGE+", "+APPEARANCE+", "+TURN+", "+
				UID+", "+FID+", "+KID+", "+GOLD+", "+
				LOCATE_K+", "+LOCATE_X+", "+LOCATE_Y+", "+STATE+
				") "+
				"VALUES "+
				"  ( "+
				"    ?,?,?,?,?,?,?,?,?,?,?,?,?,?"+
				"  ); ";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, character.getFirstname());
			pstm.setString(2, character.getAbilitiesJson());
			pstm.setString(3, character.getAttributesJson());
			pstm.setFloat(4, character.getAge());
			pstm.setString(5, character.getAppearance());
			pstm.setInt(6, character.getTurn());
			pstm.setInt(7, character.getUid());
			pstm.setInt(8, character.getFid());
			pstm.setInt(9, character.getKid());
			pstm.setFloat(10, character.getGold());
			pstm.setInt(11, character.getLocation().getK());
			pstm.setFloat(12, character.getLocation().getX());
			pstm.setFloat(13, character.getLocation().getY());
			pstm.setString(14, character.getState().toJson());
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
