package server.model;

public class Kingdom extends Model{
	private String name;
	private float gold;
	private float food;
	private int parent;

	public Kingdom() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Kingdom(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public Kingdom setName(String name) {
		this.name = name;
		return this;
	}

	public float getGold() {
		return gold;
	}

	public Kingdom setGold(float gold) {
		this.gold = gold;
		return this;
	}

	public float getFood() {
		return food;
	}

	public Kingdom setFood(float food) {
		this.food = food;
		return this;
	}

	public int getParent() {
		return parent;
	}

	public Kingdom setParent(int parent) {
		this.parent = parent;
		return this;
	}

}
