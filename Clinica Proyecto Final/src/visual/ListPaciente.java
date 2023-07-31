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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Clinica;
import logico.Consulta;
import logico.Enfermedad;
import logico.HistorialMedico;
import logico.Paciente;
import logico.Vacuna;

public class ListPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable tablePaciente;
	private static DefaultTableModel modelo;
	private static Object[] row;
	private JButton btnCancel;
	private JButton btnHistorialMedico;
	private int index;
	private Paciente miPaciente = null;
	private HistorialMedico historialMedico = null;

	
	public ListPaciente(ArrayList<Consulta>misConsultas) {
		setResizable(false);
		setTitle("Listado de Pacientes");
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
					tablePaciente = new JTable();
					tablePaciente.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							index = tablePaciente.getSelectedRow();
							if (index >= 0) {
								miPaciente = Clinica.getInstance()
										.BuscarPacienteByCode((String) tablePaciente.getValueAt(index, 0));
								if (miPaciente.getMiHIstorial().getMisConsultasRelevantes().size() > 0)
									btnHistorialMedico.setEnabled(true);

							}
						}
					});
					modelo = new DefaultTableModel();
					String[] headers = { "Código", " Nombre", "Edad", "Genero", "Historial medico" };
					modelo.setColumnIdentifiers(headers);
					tablePaciente.setModel(modelo);
					scrollPane.setViewportView(tablePaciente);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnHistorialMedico = new JButton("Ver Historial Medico");
				btnHistorialMedico.setEnabled(false);
				btnHistorialMedico.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ListConsulta listarHistoriales = new ListConsulta(
								miPaciente.getMiHIstorial().getMisConsultasRelevantes());
						listarHistoriales.setModal(true);
						listarHistoriales.setVisible(true);
					}
				});
				buttonPane.add(btnHistorialMedico);

			}
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

		loadPacientes(misConsultas);
	}

	public static void loadPacientes(ArrayList<Consulta>consultas) {
		modelo.setRowCount(0);
		row = new Object[tablePaciente.getColumnCount()];

		for (Consulta consulta : consultas) {
			row[0] = consulta.getPaciente().getCodigo();
			row[1] = consulta.getPaciente().getNombre();
			row[2] = consulta.getPaciente().getEdad();
			row[3] = consulta.getPaciente().getGenero();

			if (consulta.getPaciente().getMiHIstorial().getMisConsultasRelevantes().size() > 0)
				row[4] = "Tiene";
			else
				row[4] = "No tiene";

			modelo.addRow(row);
		}

	}

}
