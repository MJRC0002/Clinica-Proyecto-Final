package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Medico implements Serializable {

	private String codigo;
	private String nombre;
	private String telefono;
	private String usuario;
	private String contrasenna;
	private String especializacion;
	private ArrayList<Consulta> misConsultas;
	private ArrayList<Enfermedad> misEnfermedades;
	private ArrayList<Cita> misCitas;

	public Medico(String codigo, String nombre, String telefono, String especializacion, String usuario, String contrasenna) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.telefono = telefono;
		this.especializacion = especializacion;
		this.usuario = usuario;
		this.contrasenna = contrasenna;
		misConsultas = new ArrayList<Consulta>();
		misEnfermedades = new ArrayList<Enfermedad>();
		misCitas = new ArrayList<Cita>();
	}

	public String getCodigo() {
		return codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getEspecializacion() {
		return especializacion;
	}

	public ArrayList<Consulta> getMisConsultas() {
		return misConsultas;
	}

	public ArrayList<Enfermedad> getMisEnfermedades() {
		return misEnfermedades;
	}

	public ArrayList<Cita> getMisCitas() {
		return misCitas;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getContrasenna() {
		return contrasenna;
	}

}
