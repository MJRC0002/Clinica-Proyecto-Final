package logico;

import java.io.Serializable;
import java.util.Date;

public class Consulta implements Serializable{
	private static final long serialVersionUID = 1L;

	private String codConsulta;
	private Paciente paciente;
	private Enfermedad enfermedad;
	private String diagnostico;
	private String sintomas;
	private Date fecha;
	private boolean bajoVig;

	public Consulta(String codConsulta, Paciente paciente, Enfermedad enfermedad, String diagnostico, String sintomas,
			boolean bajoVig, Date fecha) {
		super();
		this.codConsulta = codConsulta;
		this.paciente = paciente;
		this.enfermedad = enfermedad;
		this.diagnostico = diagnostico;
		this.sintomas = sintomas;
		this.bajoVig = bajoVig;
		this.fecha = fecha;
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

	public Date getFecha() {
		return fecha;
	}

}
