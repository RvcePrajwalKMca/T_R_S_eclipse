package therollshop.model;

public class Admin {
	private int id;
	private String admin_id;
	private String password;
	public Admin() {
		super();
	}
	public Admin(int id, String admin_id, String password) {
		super();
		this.id = id;
		this.admin_id = admin_id;
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", admin_id=" + admin_id + ", password=" + password + "]";
	}
	
}
