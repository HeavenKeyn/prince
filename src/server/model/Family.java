package server.model;

public class Family extends Model{
	private String familyname;
	private String coat;
	private float prestige;
	
	public Family() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Family(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	public String getFamilyname() {
		return familyname;
	}
	public Family setFamilyname(String familyname) {
		this.familyname = familyname;
		return this;
	}
	public String getCoat() {
		return coat;
	}
	public Family setCoat(String coat) {
		this.coat = coat;
		return this;
	}
	public float getPrestige() {
		return prestige;
	}
	public Family setPrestige(float prestige) {
		this.prestige = prestige;
		return this;
	}

}
