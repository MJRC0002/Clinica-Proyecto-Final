package logico;

import java.io.Serializable;

public class Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codigo;
	private String nombre;
	private int edad;
	private boolean seguroMedico;
	private char genero;

	public Persona(String codigo, String nombre, int edad, boolean seguroMedico, char genero) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.edad = edad;
		this.seguroMedico = seguroMedico;
		this.genero = genero;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public int getEdad() {
		return edad;
	}

	public boolean isSeguroMedico() {
		return seguroMedico;
	}

	public char getGenero() {
		return genero;
	}

}
