package server.model;

public class Link {
	private int id;
	private int uid;
	private int fid;
	//private int cid;
	private int kid;

	public Link setId(int id) {
		this.id = id;
		return this;
	}

	public int getUid() {
		return uid;
	}

	public Link setUid(int uid) {
		this.uid = uid;
		return this;
	}

	public int getFid() {
		return fid;
	}

	public Link setFid(int fid) {
		this.fid = fid;
		return this;
	}

//	public int getCid() {
//		return cid;
//	}
//
//	public Link setCid(int cid) {
//		this.cid = cid;
//		return this;
//	}

	public int getKid() {
		return kid;
	}

	public Link setKid(int kid) {
		this.kid = kid;
		return this;
	}

	public int getId() {
		return id;
	}
	
	

}
