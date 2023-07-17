package logico;

import java.util.ArrayList;

public class Secretaria {
	 private ArrayList<Cita>misCitas;
	 private String codigo;
	 private String nombre;

	public Secretaria(String codigo, String nombre, String descripcion) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
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
	
}
