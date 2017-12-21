package server.model;

public class Relation extends Model{
	private int impose;
	private int bear;
	private int opinion;
	private int turn;
	
	public Relation(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	public Relation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getImpose() {
		return impose;
	}
	public Relation setImpose(int cid1) {
		this.impose = cid1;
		return this;
	}
	public int getBear() {
		return bear;
	}
	public Relation setBear(int cid2) {
		this.bear = cid2;
		return this;
	}
	public int getOpinion() {
		return opinion;
	}
	public Relation setOpinion(int opinion) {
		this.opinion = opinion;
		return this;
	}
	public int getTurn() {
		return turn;
	}
	public Relation setTurn(int turn) {
		this.turn = turn;
		return this;
	}
	

}
