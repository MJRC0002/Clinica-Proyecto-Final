package logico;

import java.util.Date;

public class Cita {
	
	private String codCita;
	private String idMedico;
	private Persona persona;
	private Date fecha;
	
	public Cita(String codCita, String idMedico, Persona persona) {
		super();
		this.codCita = codCita;
		this.idMedico = idMedico;
		this.persona = persona;
		this.fecha = new Date();
	}

	public String getCodCita() {
		return codCita;
	}

	

	public String getIdMedico() {
		return idMedico;
	}

	public Date getFecha() {
		return fecha;
	}

	public Persona getPersona() {
		return persona;
	}
	 

}
