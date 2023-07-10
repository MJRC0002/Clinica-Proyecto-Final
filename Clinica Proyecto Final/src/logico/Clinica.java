package logico;

import java.util.ArrayList;

public class Clinica {
	private ArrayList<Medico> misMedicos;
	private ArrayList<Paciente> misPacientes;
	private ArrayList<Vacuna> misVacunas;
	private ArrayList<Enfermedad> misEnfermedades;
	private ArrayList<Consulta> misConsultas;
	private static Clinica miClinica = null;

	public Clinica() {
		super();
		this.misMedicos = new ArrayList<>();
		this.misPacientes = new ArrayList<>();
		this.misVacunas = new ArrayList<>();
		this.misEnfermedades = new ArrayList<>();
		this.misConsultas = new ArrayList<>();
	}

	public static Clinica getInstance() {
		if (miClinica == null) {
			miClinica = new Clinica();
		}
		return miClinica;
	}

	public ArrayList<Medico> getMisMedicos() {
		return misMedicos;
	}

	public ArrayList<Paciente> getMisPacientes() {
		return misPacientes;
	}

	public ArrayList<Vacuna> getMisVacunas() {
		return misVacunas;
	}

	public ArrayList<Enfermedad> getMisEnfermedades() {
		return misEnfermedades;
	}

	public ArrayList<Consulta> getMisConsultas() {
		return misConsultas;
	}

}
