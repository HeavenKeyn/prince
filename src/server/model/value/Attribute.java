package server.model.value;

public class Attribute{
	private int id;
	private float experience;
	
	public Attribute(int id, float experience) {
		this.id = id;
		this.experience = experience;
	}

	public float getExperience() {
		return experience;
	}

	public Attribute setExperience(float experience) {
		this.experience = experience;
		return this;
	}

	public int getId() {
		return id;
	}
	
}
