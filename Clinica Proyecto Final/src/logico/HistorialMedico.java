package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class HistorialMedico implements Serializable{
	private ArrayList<Consulta> misConsultasRelevantes;

	public HistorialMedico() {
		super();
		misConsultasRelevantes = new ArrayList<Consulta>();
	}

	public ArrayList<Consulta> getMisConsultasRelevantes() {
		return misConsultasRelevantes;
	}

}
