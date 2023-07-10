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

	public String getNombre() {
		return nombre;
	}

	public int getEdad() {
		return edad;
	}

	public boolean isSeguroMedico() {
		return seguroMedico;
	}

}
