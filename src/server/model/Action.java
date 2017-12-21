package server.model;

public class Action {
	private int cid1;
	private int cid2;
	private int aid;
	public Action(int cid1, int cid2, int aid) {
		this.cid1 = cid1;
		this.cid2 = cid2;
		this.aid = aid;
	}
	public int getCid1() {
		return cid1;
	}
	public int getCid2() {
		return cid2;
	}
	public int getAid() {
		return aid;
	}
	
	

}
