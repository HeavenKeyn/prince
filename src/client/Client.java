package client;

public class Client {
	
	public int IsLegal(String user) {
		if (user.contains("+")) {
			return 1;
		}else if (user.contains("@")) {
			return 2;
		}else {
			return 0;
		}
	}

}
