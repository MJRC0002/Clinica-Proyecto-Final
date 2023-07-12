package logico;

public class Consulta {
	private String codConsulta;
	private String codigoPaciente;
	private Enfermedad enfermedad;
	private String diagnostico;
	private String sintomas;
	private boolean bajoVig;

	public Consulta(String codConsulta, String codigoPaciente, Enfermedad enfermedad, String diagnostico,
			String sintomas, boolean bajoVig) {
		super();
		this.codConsulta = codConsulta;
		this.codigoPaciente = codigoPaciente;
		this.enfermedad = enfermedad;
		this.diagnostico = diagnostico;
		this.sintomas = sintomas;
		this.bajoVig = bajoVig;
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

	public boolean isBajoVig() {
		return bajoVig;
	}

	public String getCodConsulta() {
		return codConsulta;
	}

}
