package logico;

import java.io.Serializable;

public class Consulta implements Serializable{
	private String codConsulta;
	private Paciente paciente;
	private Enfermedad enfermedad;
	private String diagnostico;
	private String sintomas;
	private boolean bajoVig;

	public Consulta(String codConsulta, Paciente paciente, Enfermedad enfermedad, String diagnostico, String sintomas,
			boolean bajoVig) {
		super();
		this.codConsulta = codConsulta;
		this.paciente = paciente;
		this.enfermedad = enfermedad;
		this.diagnostico = diagnostico;
		this.sintomas = sintomas;
		this.bajoVig = bajoVig;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public Enfermedad getEnfermedad() {
		return enfermedad;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public String getSintomas() {
		return sintomas;
	}

	public boolean isBajoVig() {
		return bajoVig;
	}

	public String getCodConsulta() {
		return codConsulta;
	}

}
