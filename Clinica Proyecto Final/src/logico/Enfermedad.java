package logico;

public class Enfermedad {
	private String codigo;
	private String nombre;
	private String sintomas;	
	private String medVacuna;
	public Enfermedad(String codigo, String nombre, String sintomas, String medVacuna) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.sintomas = sintomas;
		this.medVacuna = medVacuna;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getSintomas() {
		return sintomas;
	}
	public void setSintomas(String sintomas) {
		this.sintomas = sintomas;
	}
	public String getMedVacuna() {
		return medVacuna;
	}
	public void setMedVacuna(String medVacuna) {
		this.medVacuna = medVacuna;
	}
	
	

}
