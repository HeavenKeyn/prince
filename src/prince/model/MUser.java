package prince.model;

public abstract class MUser {
	private int uid;
	private String uname;
	
	public MUser setUid(int uid) {
		this.uid = uid;
		return this;
	}

	public String getUname() {
		return uname;
	}

	public MUser setUname(String uname) {
		this.uname = uname;
		return this;
	}

	public int getUid() {
		return uid;
	}

}
