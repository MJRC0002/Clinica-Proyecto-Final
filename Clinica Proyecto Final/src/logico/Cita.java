package logico;

import java.util.Date;

public class Cita {
	
	private String codCita;
	private String idPersona;
	private String idMedico;
	private Date fecha;
	
	public Cita(String codCita, String idPersona, String idMedico) {
		super();
		this.codCita = codCita;
		this.idPersona = idPersona;
		this.idMedico = idMedico;
		this.fecha = new Date();
	}

	public String getCodCita() {
		return codCita;
	}

	public String getIdPersona() {
		return idPersona;
	}

	public String getIdMedico() {
		return idMedico;
	}

	public Date getFecha() {
		return fecha;
	}
	
	

}
