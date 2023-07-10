package logico;

import java.util.ArrayList;

public class Medico {
	private String codigo;
	private String nombre;
	private String telefono;
	private String especializacion;
	private ArrayList<Paciente> misPacientes;
	private ArrayList<Enfermedad> misEnfermedades;
	private ArrayList<Cita> misCitas;
	public Medico(String codigo, String nombre, String telefono, String especializacion) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.telefono = telefono;
		this.especializacion = especializacion;
		misPacientes = new ArrayList<Paciente>();
		misEnfermedades= new ArrayList<Enfermedad>();
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
	public ArrayList<Paciente> getMisPacientes() {
		return misPacientes;
	}
	public ArrayList<Enfermedad> getMisEnfermedades() {
		return misEnfermedades;
	}
	public ArrayList<Cita> getMisCitas() {
		return misCitas;
	}
	
	
	
	
	

}
