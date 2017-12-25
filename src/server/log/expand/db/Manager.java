package server.log.expand.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class Manager {
	private Connection conn;
    private static Manager manager;
    public static final String DB_LOG = "logger";

    private Manager(String db){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connect to mysql.");
            String url="jdbc:mysql://127.0.0.1:3306/" + db + "?useUnicode=true&characterEncoding=UTF-8";
            conn= DriverManager.getConnection(url, "root", "");
            System.out.println("Connect to database " + db);
        }catch (Exception e){
        	System.out.println("Something Wrong");
        }
    }

    public synchronized static Manager getInstance(){
        if (manager == null)
            manager = new Manager(DB_LOG);
        return manager;
    }

	public Connection getConn() {
		return conn;
	}
    
}
