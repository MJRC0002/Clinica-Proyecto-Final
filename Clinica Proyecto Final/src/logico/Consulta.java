package logico;

public class Consulta {
	private String codigoPaciente;
	private Enfermedad enfermedad;
	private String diagnostico;
	private String sintomas;

	public Consulta(String codigoPaciente, Enfermedad enfermedad, String diagnostico, String sintomas) {
		super();
		this.codigoPaciente = codigoPaciente;
		this.enfermedad = enfermedad;
		this.diagnostico = diagnostico;
		this.sintomas = sintomas;
	}

	public String getCodigoPaciente() {
		return codigoPaciente;
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

}
