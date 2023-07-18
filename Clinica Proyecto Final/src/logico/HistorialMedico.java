package logico;

import java.util.ArrayList;

public class HistorialMedico {
	private ArrayList<Consulta> misConsultasRelevantes;

	public HistorialMedico() {
		super();
		misConsultasRelevantes = new ArrayList<Consulta>();
	}

	public ArrayList<Consulta> getMisConsultasRelevantes() {
		return misConsultasRelevantes;
	}

}
