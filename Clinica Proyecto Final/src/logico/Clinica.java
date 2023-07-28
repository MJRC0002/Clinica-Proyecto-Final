package logico;

import java.io.Serializable;
import java.util.ArrayList;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

import javafx.scene.control.IndexRange;

public class Clinica implements Serializable {

	private ArrayList<Medico> misMedicos;
	private ArrayList<Paciente> misPacientes;
	private ArrayList<Vacuna> misVacunas;
	private ArrayList<Enfermedad> misEnfermedades;
	private ArrayList<Consulta> misConsultas;
	private static Clinica miClinica = null;
	private Secretaria miSecretaria = null;
	private Administrador miAdministrador = null;
	public static int codigoVacuna = 1, codigoPersonas = 1, codigoCita = 1, codigoMedico = 1, codigoConsulta = 1,
			codigoHistorial = 1;

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
			setMiClinica(new Clinica());
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

	// Cuantos hombres pacientes.
	public int cantHombresPacientes() {
		int aux = 0;
		for (Paciente paciente : misPacientes) {
			if (paciente.getGenero() == 'm') {
				aux++;
			}
		}
		return aux;
	}

	// Cuantos mujeres pacientes.
	public int cantMujeresPacientes() {
		int aux = 0;
		for (Paciente paciente : misPacientes) {
			if (paciente.getGenero() == 'f') {
				aux++;
			}
		}
		return aux;
	}

	// Total pacientes atendidos.
	public int totalPacientesAtendidos() {
		return misConsultas.size();
	}

	// Porcentaje de las enfermedades.
	public int porcentajeEnfermedad(String idEnfermedad) {
		int count = 0;
		for (Consulta consulta : misConsultas) {
			if (consulta.getEnfermedad().getCodigo().equalsIgnoreCase(idEnfermedad)) {
				count++;
			}

		}
		return count;

	}

	// Porcentaje de las enfermedades.
	public float porcentajeVacunado(String idVacuna) {
		int count = 0;
		for (Paciente paciente : misPacientes) {
			for (Vacuna vacuna : paciente.getMisVacunas()) {
				if (vacuna.getCodigo().equalsIgnoreCase(idVacuna))
					count++;
			}
		}
		if (count == 0)
			return count;
		else {
			return misPacientes.size() / (count * misPacientes.size());
		}
	}

	// Crear secretaria
	public void crearSecretaria(Secretaria secretaria) {
		if (miSecretaria == null)
			this.miSecretaria = secretaria;

	}

	public void crearAdministrador(Administrador admin) {
		if (miAdministrador == null)
			miAdministrador = admin;
	}

	public Object loginTipo(String usuario, String contrasenna) {

		Object aux = null;
		for (Medico doc : misMedicos) {
			if (doc.getUsuario().equalsIgnoreCase(usuario) && doc.getContrasenna().equalsIgnoreCase(contrasenna))
				return aux = doc;
		}
		if (miSecretaria != null)
			if (miSecretaria.getUser().equalsIgnoreCase(usuario)
					&& miSecretaria.getPassword().equalsIgnoreCase(contrasenna))
				aux = miSecretaria;

		if (miAdministrador != null)
			if (getMiAdministrador().getUser().equalsIgnoreCase(usuario)
					&& getMiAdministrador().getPassword().equalsIgnoreCase(contrasenna))
				aux = getMiAdministrador();

		return aux;
	}

	public Administrador getMiAdministrador() {
		return miAdministrador;
	}

	public static void setMiClinica(Clinica miClinica) {
		Clinica.miClinica = miClinica;
	}

	public Secretaria getMiSecretaria() {
		return miSecretaria;
	}

}
