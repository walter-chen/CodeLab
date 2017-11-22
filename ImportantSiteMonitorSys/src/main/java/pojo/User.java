package pojo;

public class User {
	public String account;
	public String city;
	public String orgCode;
	
	public User(String username, String city, String orgCode){
		this.account = username;
		this.city = city;
		this.orgCode = orgCode; 
	}
}
