package logico;

import java.util.ArrayList;

public class HistorialMedico {
	private String codigo;
	private ArrayList<Consulta> misConsultasRelevantes;
	public HistorialMedico(String codigo) {
		super();
		this.codigo = codigo;
		misConsultasRelevantes = new ArrayList<Consulta>();
	}
	public String getCodigo() {
		return codigo;
	}
	public ArrayList<Consulta> getMisConsultasRelevantes() {
		return misConsultasRelevantes;
	}
	
	

}
