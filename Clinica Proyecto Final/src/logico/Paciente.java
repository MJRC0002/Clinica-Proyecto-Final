package logico;

import java.util.ArrayList;

public class Paciente extends Persona {
	private ArrayList <Consulta> misConsultas;
	private ArrayList<Vacuna>misVacunas;
	

	public Paciente(String codigo, String nombre, int edad, boolean seguroMedico) {
		super(codigo, nombre, edad, seguroMedico);
		// TODO Auto-generated constructor stub
	}

}
