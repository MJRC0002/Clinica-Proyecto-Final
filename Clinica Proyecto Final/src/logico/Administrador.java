package logico;

import java.io.Serializable;

public class Administrador implements Serializable{
	
	private String user;
	private String password;
	
	public Administrador(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}
	
	
	

}
