package logico;

import java.io.Serializable;
import java.util.Date;

public class Cita implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codCita;
	private String idMedico;
	private Persona persona;
	private Date fecha;
	
	public Cita(String codCita, String idMedico, Persona persona, Date fecha) {
		super();
		this.codCita = codCita;
		this.idMedico = idMedico;
		this.persona = persona;
		this.fecha = fecha;
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
