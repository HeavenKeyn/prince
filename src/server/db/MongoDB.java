package server.db;

import com.mongodb.MongoClient;

public class MongoDB {
	public MongoDB(){
		MongoClient mongoClient = new MongoClient("101.200.155.225",3718);
	}

}
