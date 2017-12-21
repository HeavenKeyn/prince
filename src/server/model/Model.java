package server.model;

public abstract class Model {
	protected int id;
	
	protected Model(int id) {
		this.id = id;
	}

	protected Model() {
	}

	public Model setId(int id) {
		this.id = id;
		return this;
	}

	public int getId() {
		return id;
	}

}
