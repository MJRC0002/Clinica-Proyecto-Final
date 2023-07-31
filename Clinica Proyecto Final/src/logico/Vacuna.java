package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Vacuna implements Serializable{
	private static final long serialVersionUID = 1L;
	private String codigo;
	private String nombre;
	private String descripcion;
	private ArrayList<Enfermedad> lasEnfermedades;
	private boolean used;

	public Vacuna(String codigo, String nombre, String descripcion) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.lasEnfermedades = new ArrayList<>();
		this.used = false;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public ArrayList<Enfermedad> getLasEnfermedades() {
		return lasEnfermedades;
	}

	public boolean isUsed() {
		return used;
	}
	
	public void usarVacuna() {
		if(!used)
			used = true;
	}

}
