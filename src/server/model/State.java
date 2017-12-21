package server.model;

import com.google.gson.Gson;

public class State {
	private int building;
	private int job;

	public int getBuilding() {
		return building;
	}
	public State setBuilding(int building) {
		this.building = building;
		return this;
	}
	public int getJob() {
		return job;
	}
	public State setJob(int job) {
		this.job = job;
		return this;
	}
	
	public String toJson() {
		return new Gson().toJson(this,State.class);
	}
	
	public static State fromJson(String json) {
		if (json==null) {
			return null;
		}
		return new Gson().fromJson(json, State.class);
	}
}
