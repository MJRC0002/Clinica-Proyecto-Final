package logico;

import java.util.ArrayList;

public class Paciente extends Persona {
	private static final long serialVersionUID = 1L;

	private ArrayList<Consulta> misConsultas;
	private ArrayList<Vacuna> misVacunas;
	private HistorialMedico miHistorial;
	private boolean enfermo;
	private boolean vacunado;

	public Paciente(String codigo, String nombre, int edad, boolean seguroMedico, char genero, boolean enfermo, boolean vacunado) {
		super(codigo, nombre, edad, seguroMedico, genero);

		this.enfermo = enfermo;
		this.vacunado = vacunado;
		misConsultas = new ArrayList<Consulta>();
		misVacunas = new ArrayList<Vacuna>();
		miHistorial = new HistorialMedico();
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
		return miHistorial;
	}
	public void vacunar(Vacuna vacuna) {
		if(!vacunado)
			vacunado = true;
		misVacunas.add(vacuna);
	}

	public boolean isVacunado() {
		return vacunado;
	}

}
