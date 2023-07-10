package logico;

public class Persona {
	private String codigo;
	private String nombre;
	private int edad;
	private boolean seguroMedico;

	public Persona(String codigo, String nombre, int edad, boolean seguroMedico) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.edad = edad;
		this.seguroMedico = seguroMedico;
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

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public boolean isSeguroMedico() {
		return seguroMedico;
	}

	public void setSeguroMedico(boolean seguroMedico) {
		this.seguroMedico = seguroMedico;
	}

}
