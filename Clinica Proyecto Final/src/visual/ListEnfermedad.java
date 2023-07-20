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

import logico.Clinica;
import logico.Enfermedad;
import logico.Vacuna;

public class ListEnfermedad extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable tableEnfermedades;
	private static DefaultTableModel modelo;
	private static Object[] row;
	private Enfermedad selected = null;
	private JButton btnCancel;
	private JButton btnUpdate;
	int index;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListEnfermedad dialog = new ListEnfermedad();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListEnfermedad() {
		setResizable(false);
		setTitle("Listado Enfermedades");
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
					tableEnfermedades = new JTable();
					tableEnfermedades.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							index = tableEnfermedades.getSelectedRow();
							if (index >= 0) {

								selected = Clinica.getInstance().getMisEnfermedades().get(index);
								btnUpdate.setEnabled(true);
							}
						}
					});
					modelo = new DefaultTableModel();
					String[] headers = { "Código", "Nombre", "Sintomas", "Medicamentos", "Vacuna" };
					modelo.setColumnIdentifiers(headers);
					tableEnfermedades.setModel(modelo);
					scrollPane.setViewportView(tableEnfermedades);
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
				{
					btnUpdate = new JButton("Modificar");
					btnUpdate.setEnabled(false);
					btnUpdate.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							RegEnfermedad modificar = new RegEnfermedad(selected, index);
							modificar.setModal(true);
							modificar.setVisible(true);
							loadEnfermedades();
						}
					});
					buttonPane.add(btnUpdate);
				}
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}

		loadEnfermedades();
	}

	public static void loadEnfermedades() {
		modelo.setRowCount(0);
		row = new Object[tableEnfermedades.getColumnCount()];

		for (Enfermedad enfermedad : Clinica.getInstance().getMisEnfermedades()) {
			row[0] = enfermedad.getCodigo();
			row[1] = enfermedad.getNombre();
			row[2] = enfermedad.getSintomas();
			row[3] = enfermedad.getMedicamentos();
			row[4] = dameMisVacunasEfectivas(enfermedad.getVacunasEfectivas());
			modelo.addRow(row);
		}

	}

	public static String dameMisVacunasEfectivas(ArrayList<Vacuna> vacunasEfectivas) {
		StringBuilder aux = new StringBuilder();
		for (Vacuna vacuna : vacunasEfectivas) {
			aux.append(vacuna.getNombre()).append(", ");
		}
		if (aux.length() > 0) {
			aux.delete(aux.length() - 2, aux.length());
		}
		return aux.toString();
	}

}