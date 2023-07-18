package logico;

import java.util.ArrayList;

public class Paciente extends Persona {
	private ArrayList<Consulta> misConsultas;
	private ArrayList<Vacuna> misVacunas;
	private HistorialMedico miHIstorial;
	private boolean enfermo;

	public Paciente(String codigo, String nombre, int edad, boolean seguroMedico, char genero, boolean enfermo,
			HistorialMedico miHistorial) {
		super(codigo, nombre, edad, seguroMedico, genero);

		this.enfermo = enfermo;
		misConsultas = new ArrayList<Consulta>();
		misVacunas = new ArrayList<Vacuna>();
		this.miHIstorial = miHistorial;
	}

	public boolean isEnfermo() {
		return enfermo;
	}

	public void setEnfermo(boolean enfermo) {
		this.enfermo = enfermo;
	}

	public ArrayList<Consulta> getMisConsultas() {
		return misConsultas;
	}

	public ArrayList<Vacuna> getMisVacunas() {
		return misVacunas;
	}

	public HistorialMedico getMiHIstorial() {
		return miHIstorial;
	}

}
