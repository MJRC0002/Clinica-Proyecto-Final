package logico;

import java.util.ArrayList;

public class Clinica {
	private ArrayList<Medico> misMedicos;
	private ArrayList<Paciente> misPacientes;
	private ArrayList<Vacuna> misVacunas;
	private ArrayList<Enfermedad> misEnfermedades;
	private ArrayList<Consulta> misConsultas;
	private static Clinica miClinica = null;
	private static Secretaria miSecretaria = null;
	public static int codigoVacuna = 1, codigoPersonas = 1, codigoCita = 1, codigoMedico = 1;

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

	// Buscar Paciente por el código.
	public Paciente BuscarPacienteByCode(String idPaciente) {
		Paciente aux = null;
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < misPacientes.size()) {
			if (misPacientes.get(i).getCodigo().equalsIgnoreCase(idPaciente)) {
				aux = misPacientes.get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;

	}

	// Buscar medico por el código.
	public Medico buscarMedicoByCode(String idMedico) {
		Medico aux = null;
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < misMedicos.size()) {
			if (misMedicos.get(i).getCodigo().equalsIgnoreCase(idMedico)) {
				aux = misMedicos.get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
	}

	// Buscar Vacuna por el código.
	public Vacuna buscarVacunaByCode(String idVacuna) {
		Vacuna aux = null;
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < misVacunas.size()) {
			if (misVacunas.get(i).getCodigo().equalsIgnoreCase(idVacuna)) {
				aux = misVacunas.get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
	}

	// buscar Enfermedad por el código.
	public Enfermedad buscarEnfermedadByCode(String idEnfermedad) {
		Enfermedad aux = null;
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < misEnfermedades.size()) {
			if (misEnfermedades.get(i).getCodigo().equalsIgnoreCase(idEnfermedad)) {
				aux = misEnfermedades.get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
	}

	// buscar Consulta por el código.
	public Consulta buscarConsultaByCode(String idConsulta) {
		Consulta aux = null;
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < misConsultas.size()) {
			if (misConsultas.get(i).getCodConsulta().equalsIgnoreCase(idConsulta)) {
				aux = misConsultas.get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
	}
	/*
	 * public static Secretaria getInstanceSecretaria() { if (miSecretaria == null)
	 * { miSecretaria = new Secretaria(); } return miSecretaria;
	 * 
	 * }
	 */
}
