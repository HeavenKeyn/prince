package prince.model;

public abstract class MUserLogin extends MUser{
	private String password;
	private String email;
	private String phone;
	public String getPassword() {
		return password;
	}
	public MUserLogin setPassword(String password) {
		this.password = password;
		return this;
	}
	public String getEmail() {
		return email;
	}
	public MUserLogin setEmail(String email) {
		this.email = email;
		return this;
	}
	public String getPhone() {
		return phone;
	}
	public MUserLogin setPhone(String phone) {
		this.phone = phone;
		return this;
	}
	
	
}
