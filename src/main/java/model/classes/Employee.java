package model.classes;

public class Employee extends Person{

	private String registration;
	private String token;
	private String username;
	private String password;
	
	public Employee(int id, String name, String registration, String username, String password) {
		super(id, name);
		// TODO Auto-generated constructor stub
	}
	
	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int create() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int update() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int delete() {
		// TODO Auto-generated method stub
		return 1;
	}

}
