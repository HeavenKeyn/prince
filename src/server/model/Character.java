package server.model;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Character extends Model{
	private String firstname;
	private float[] abilities;
	private Map<Integer,Float> attributes;
	private float age;
	private String appearance;
	private int turn;
	//2017.11.28
	private List<Relation> relations;
	private float gold;
	private int uid;
	private int kid;
	private int fid;
	private Location location;
	private State state;

	public Character() {
		super();
	}

	public Character(int id) {
		super(id);
	}

	public String getFirstname() {
		return firstname;
	}

	public Character setFirstname(String firstname) {
		this.firstname = firstname;
		return this;
	}
	
	/*
	 * 
	public float getPolitics() {
		return politics;
	}

	public Character setPolitics(float politics) {
		this.politics = politics;
		return this;
	}

	public float getIntellect() {
		return intellect;
	}

	public Character setIntellect(float intellect) {
		this.intellect = intellect;
		return this;
	}

	public float getCommand() {
		return command;
	}

	public Character setCommand(float command) {
		this.command = command;
		return this;
	}

	public float getForce() {
		return force;
	}

	public Character setForce(float force) {
		this.force = force;
		return this;
	}

	public float getCharm() {
		return charm;
	}

	public Character setCharm(float charm) {
		this.charm = charm;
		return this;
	}
	*/

	public float[] getAbilities() {
		return abilities;
	}
	
	public String getAbilitiesJson() {
		return new Gson().toJson(abilities,float[].class);
	}

	public Character setAbilities(float[] abilities) {
		this.abilities = abilities;
		return this;
	}
	
	public Character setAbilities(String abilities) {
		try {
			this.abilities = new Gson().fromJson(abilities,float[].class);
		} catch (Exception e) {
			
		}
		return this;
	}

	public Map<Integer, Float> getAttributes() {
		return attributes;
	}
	
	public String getAttributesJson() {
		return new Gson().toJson(attributes,new TypeToken<Map<Integer, Float>>(){}.getType());
	}

	public Character setAttributes(Map<Integer, Float> attributes) {
		this.attributes = attributes;
		return this;
	}
	
	public Character setAttributes(String attributes) {
		try {
			this.attributes = new Gson().fromJson(attributes,new TypeToken<Map<Integer, Float>>(){}.getType());
		} catch (Exception e) {
			
		}
		return this;
	}

	public float getAge() {
		return age;
	}

	public Character setAge(float age) {
		this.age = age;
		return this;
	}

	public String getAppearance() {
		return appearance;
	}

	public Character setAppearance(String appearance) {
		this.appearance = appearance;
		return this;
	}

	public int getTurn() {
		return turn;
	}

	public Character setTurn(int turn) {
		this.turn = turn;
		return this;
	}

	public List<Relation> getRelations() {
		return relations;
	}

	public Character setRelations(List<Relation> relations) {
		this.relations = relations;
		return this;
	}

	public float getGold() {
		return gold;
	}

	public Character setGold(float gold) {
		this.gold = gold;
		return this;
	}

	public int getUid() {
		return uid;
	}

	public Character setUid(int uid) {
		this.uid = uid;
		return this;
	}

	public int getKid() {
		return kid;
	}

	public Character setKid(int kid) {
		this.kid = kid;
		return this;
	}

	public int getFid() {
		return fid;
	}

	public Character setFid(int fid) {
		this.fid = fid;
		return this;
	}

	public Location getLocation() {
		if (location==null) {
			location = new Location();
		}
		return location;
	}

	public Character setLocation(Location location) {
		this.location = location;
		return this;
	}

	public State getState() {
		if (state==null) {
			state = new State();
		}
		return state;
	}

	public Character setState(State state) {
		this.state = state;
		return this;
	}
	
	public Character setState(String state) {
		this.state = State.fromJson(state);
		return this;
	}


}
