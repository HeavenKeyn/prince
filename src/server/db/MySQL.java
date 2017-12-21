package server.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQL {
	
	private Connection conn;
    private static MySQL mySQL;
    public static final String DB_USER = "test";

    private MySQL(String db){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connect to mysql.");
            String url="jdbc:mysql://127.0.0.1:3306/" + db + "?useUnicode=true&characterEncoding=UTF-8";
            conn= DriverManager.getConnection(url, "root", "");
//            String url="jdbc:mysql://jindiprismtest.mysql.rds.aliyuncs.com:3306/" + db;
//            conn= DriverManager.getConnection(url, "jindi", "jindichangsheng");
            System.out.println("Connect to database " + db);
        }catch (Exception e){
        	System.out.println("Something Wrong");
        }
    }

    public synchronized static MySQL getInstance(){
        if (mySQL == null)
            mySQL = new MySQL(DB_USER);
        return mySQL;
    }

	public Connection getConn() {
		return conn;
	}
    
    


}
