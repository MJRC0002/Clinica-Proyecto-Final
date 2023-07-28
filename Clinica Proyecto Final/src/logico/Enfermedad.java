package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Enfermedad implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codigo;
	private String nombre;
	private String sintomas;
	private String medicamentos;
	private ArrayList<Vacuna> VacunasEfectivas;

	public Enfermedad(String codigo, String nombre, String sintomas, String medicamentos) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.sintomas = sintomas;
		this.medicamentos = medicamentos;
		this.VacunasEfectivas = new ArrayList<>();
	}

	public String getCodigo() {
		return codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public String getSintomas() {
		return sintomas;
	}

	public String getMedicamentos() {
		return medicamentos;
	}

	public ArrayList<Vacuna> getVacunasEfectivas() {
		return VacunasEfectivas;
	}

}
