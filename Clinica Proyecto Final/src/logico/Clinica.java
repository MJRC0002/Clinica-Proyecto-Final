package logico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.sun.org.apache.bcel.internal.generic.AALOAD;

public class Clinica implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Medico> misMedicos;
	private ArrayList<Paciente> misPacientes;
	private ArrayList<Vacuna> misVacunas;
	private ArrayList<Enfermedad> misEnfermedades;
	private ArrayList<Consulta> misConsultas;
	private static Clinica miClinica = null;
	private Secretaria miSecretaria = null;
	private Administrador miAdministrador = null;

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

	// Buscar Paciente por el c�digo.
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

	// Buscar medico por el c�digo.
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

	// Buscar Vacuna por el c�digo.
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

	// buscar Enfermedad por el c�digo.
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

	// buscar Consulta por el c�digo.
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
	public double porcentajeEnfermedad(String codigo) {
		int count = 0;
		for (Consulta consulta : misConsultas) {
			if (consulta.getEnfermedad().getCodigo().equalsIgnoreCase(codigo) && consulta.getPaciente().isEnfermo()) {
				count++;
			}
		}

		if (count == 0) {
			return 0.0;
		} else if (pacientesEnfermos() != 0) {
			return ((double) count / pacientesEnfermos()) * 100.0;
		} else {
			return 0.0;
		}
	}

	public int pacientesEnfermos() {
		int aux = 0;

		for (Paciente paciente : misPacientes)
			if (paciente.isEnfermo())
				aux++;

		return aux;
	}

	// Porcentaje de las vacunas.
	public double porcentajeVacunado(String idVacuna) {
		int count = 0;
		for (Paciente paciente : misPacientes) {
			for (Vacuna vacuna : paciente.getMisVacunas()) {
				if (vacuna.getCodigo().equalsIgnoreCase(idVacuna) && vacuna.isUsed()) {
					count++;
				}
			}
		}

		if (count == 0) {
			return 0.0;
		} else if (pacientesVacunados() != 0) {
			return ((double) count / pacientesVacunados()) * 100.0;
		} else {
			return 0.0;
		}
	}

	public int pacientesVacunados() {
		int aux = 0;

		for (Paciente paciente : misPacientes)
			if (paciente.isVacunado())
				aux++;

		return aux;
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
			if (doc.getUsuario().equalsIgnoreCase(usuario) && doc.getContrasenna().equals(contrasenna))
				return aux = doc;
		}
		if (miSecretaria != null)
			if (miSecretaria.getUser().equalsIgnoreCase(usuario) && miSecretaria.getPassword().equals(contrasenna))
				aux = miSecretaria;

		if (miAdministrador != null)
			if (getMiAdministrador().getUser().equalsIgnoreCase(usuario)
					&& getMiAdministrador().getPassword().equals(contrasenna))
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

	public ArrayList<Consulta> ConsultasDeLaSemana() {
		ArrayList<Consulta> consultaSemana = new ArrayList<Consulta>();
		for (Consulta consulta : misConsultas) {
			if (calcularDiferenciaDias(consulta.getFecha()) <= 7)
				consultaSemana.add(consulta);
		}

		return consultaSemana;
	}

	public long calcularDiferenciaDias(Date fechaObjeto) {
		long diffInMillis = new Date().getTime() - fechaObjeto.getTime();
		return TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
	}

	public ArrayList<Consulta> pacientesEnfermosConVacunasDisponibles(ArrayList<Consulta> consultas) {
		ArrayList<Consulta> aux = new ArrayList<Consulta>();
		for (Consulta consulta2 : consultas) {
			for (Vacuna vacuna : misVacunas) {
				if (vacuna.getLasEnfermedades().contains(consulta2.getEnfermedad()) && !vacuna.isUsed()) {
					aux.add(consulta2);

				}
			}
		}
		if (aux.size() > 0)
			return aux;
		else {
			return null;
		}
	}
}
