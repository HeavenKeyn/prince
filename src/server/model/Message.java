package server.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class Message {
	private int code;
	private String info;
	private List<User> users;
	private List<Link> links;
	private List<Kingdom> kingdoms;
	private List<Family> families;
	private List<Character> characters;
	private List<Action> actions;
	/*
	 * 2017.11.28
	private List<Relation> relations;
	*/

	public Message(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}

	public Message setCode(int code) {
		this.code = code;
		return this;
	}

	public String getInfo() {
		return info;
	}

	public Message setInfo(String info) {
		this.info = info;
		return this;
	}

//	public List<User> getUsers() {
//		return users;
//	}
//
//	public Message setUsers(List<User> uList) {
//		this.users = uList;
//		return this;
//	}

	public List<Link> getLinks() {
		return links;
	}

	public Message setLinks(List<Link> links) {
		this.links = links;
		return this;
	}

	public List<Kingdom> getKingdoms() {
		return kingdoms;
	}

	public Message setKingdoms(List<Kingdom> kingdoms) {
		this.kingdoms = kingdoms;
		return this;
	}

	public List<Family> getFamilies() {
		return families;
	}

	public Message setFamilies(List<Family> families) {
		this.families = families;
		return this;
	}

	public List<Character> getCharacters() {
		return characters;
	}

	public Message setCharacters(List<Character> cList) {
		this.characters = cList;
		return this;
	}

	public List<Action> getActions() {
		return actions;
	}

	public Message setActions(List<Action> actions) {
		this.actions = actions;
		return this;
	}

	public Message add(User user) {
		if (user!=null) {
			if (users==null) {
				users = new ArrayList<>();
			}
			users.add(user);
		}
		return this;
	}
	
	public User getUser() {
		User user = null;
		if (users!=null&&users.size()>0) {
			user = users.get(0);
		}
		return user;
	}
	
	public Message add(Link link) {
		if (link!=null) {
			if (links==null) {
				links = new ArrayList<>();
			}
			links.add(link);
		}
		return this;
	}
	
	public Link getLink() {
		Link link = null;
		if (links!=null&&links.size()>0) {
			link = links.get(0);
		}
		return link;
	}
	
	public Message add(Kingdom kingdom) {
		if (kingdom!=null) {
			if (kingdoms==null) {
				kingdoms = new ArrayList<>();
			}
			kingdoms.add(kingdom);
		}
		return this;
	}
	
	public Kingdom getKingdom() {
		Kingdom kingdom = null;
		if (kingdoms!=null&&kingdoms.size()>0) {
			kingdom = kingdoms.get(0);
		}
		return kingdom;
	}
	
	public Message add(Family family) {
		if (family!=null) {
			if (families==null) {
				families = new ArrayList<>();
			}
			families.add(family);
		}
		return this;
	}

	public Family getFamily() {
		Family family = null;
		if (families!=null&&families.size()>0) {
			family = families.get(0);
		}
		return family;
	}

	public static Message fromJson(String json) {
		return new Gson().fromJson(json, Message.class);
	}
	
	public String toJson() {
		return new Gson().toJson(this,Message.class);
	}
	
	/*
	 * 2017.11.28
	public List<Relation> getRelations() {
		return relations;
	}

	public Message setRelations(List<Relation> relations) {
		this.relations = relations;
		return this;
	}
	*/

}
