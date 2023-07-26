package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Secretaria implements Serializable{
	private ArrayList<Cita> misCitas;
	private String codigo;
	private String nombre;
	private String user;
	private String password;

	public Secretaria(String codigo, String nombre, String user, String password) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.user = user;
		this.password = password;
	}

	public ArrayList<Cita> getMisCitas() {
		return misCitas;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

}
