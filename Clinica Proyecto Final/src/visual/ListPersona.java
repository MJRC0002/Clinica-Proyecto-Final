package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Cita;
import logico.Clinica;
import logico.Enfermedad;
import logico.Persona;
import logico.Vacuna;

public class ListPersona extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable tablePersonas;
	private static DefaultTableModel modelo;
	private static Object[] row;
	private JButton btnCancel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListPersona dialog = new ListPersona();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListPersona() {
		setResizable(false);
		setTitle("Listado Personas");
		setBounds(100, 100, 621, 384);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					tablePersonas = new JTable();
					modelo = new DefaultTableModel();
					String[] headers = { "Código", " Nombre", "Edad", "Genero" };
					modelo.setColumnIdentifiers(headers);
					tablePersonas.setModel(modelo);
					scrollPane.setViewportView(tablePersonas);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnCancel = new JButton("Cancelar");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}

		loadPersonas();
	}

	public static void loadPersonas() {
		modelo.setRowCount(0);
		row = new Object[tablePersonas.getColumnCount()];
		if (Clinica.getMiSecretaria() != null) {
			for (Cita cita : Clinica.getMiSecretaria().getMisCitas()) {
				Persona persona = cita.getPersona();
				row[0] = persona.getCodigo();
				row[1] = persona.getNombre();
				row[2] = persona.getEdad();
				row[3] = persona.getGenero();

				modelo.addRow(row);
			}
		}

	}

	public static String dameMisEnfermedades(ArrayList<Enfermedad> lasEnfermedades) {
		StringBuilder aux = new StringBuilder();
		for (Enfermedad enfermedad : lasEnfermedades) {
			aux.append(enfermedad.getNombre()).append(", ");
		}
		if (aux.length() > 0) {
			aux.delete(aux.length() - 2, aux.length());
		}
		return aux.toString();
	}

}
