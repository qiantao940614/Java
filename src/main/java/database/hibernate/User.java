package database.hibernate;

public class User {
	private String name;
	private int id;
	private String pwd;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPassword(String pwd) {
		this.pwd = pwd;
	}
	
}
