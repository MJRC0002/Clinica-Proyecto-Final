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
import logico.Enfermedad;
import logico.Paciente;
import logico.Vacuna;

public class ListVacuna extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable tableVacunas;
	private static DefaultTableModel modelo;
	private static Object[] row;
	private JButton btnCancel;
	private JButton btnDelete;
	private JButton btnAplicarVacuna;
	private int index;

	public ListVacuna(Enfermedad enfermedad, Paciente paciente) {
		setResizable(false);
		setTitle("Listado Vacunas");
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
					tableVacunas = new JTable();
					tableVacunas.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							index = tableVacunas.getSelectedRow();
							if (index >= 0) {
								if (enfermedad == null)
									btnDelete.setEnabled(true);
								else
									btnAplicarVacuna.setEnabled(true);
							}
						}
					});
					modelo = new DefaultTableModel();
					String[] headers = { "Código", " Nombre", "Descripcion", "Efectiva con", "Estado" };
					modelo.setColumnIdentifiers(headers);
					tableVacunas.setModel(modelo);
					scrollPane.setViewportView(tableVacunas);
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
					if (enfermedad == null) {
						btnDelete = new JButton("Eliminar");
						btnDelete.setEnabled(false);
						btnDelete.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								Clinica.getInstance().getMisVacunas()
										.remove(Clinica.getInstance().getMisVacunas().remove(index));
								btnDelete.setEnabled(false);
								loadVacunas();
							}
						});
						buttonPane.add(btnDelete);
					}
				}
				{
					if (enfermedad != null) {
						btnAplicarVacuna = new JButton("Aplicar vacuna");
						btnAplicarVacuna.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								int option = JOptionPane.showConfirmDialog(ListVacuna.this, "¿Desea aplicar la vacuna?",
										"Confirmación", JOptionPane.YES_NO_OPTION);
								if (option == JOptionPane.YES_OPTION) {
									paciente.vacunar(Clinica.getInstance()
											.buscarVacunaByCode((String) tableVacunas.getValueAt(index, 0)));
									dispose();
								}
							}
						});
						btnAplicarVacuna.setEnabled(false);
						buttonPane.add(btnAplicarVacuna);
					}
				}
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}
		if (enfermedad == null)
			loadVacunas();
		else {
			loadVacunasAplicar(enfermedad);
		}
	}

	public static void loadVacunas() {
		modelo.setRowCount(0);
		row = new Object[tableVacunas.getColumnCount()];

		for (Vacuna vacuna : Clinica.getInstance().getMisVacunas()) {
			row[0] = vacuna.getCodigo();
			row[1] = vacuna.getNombre();
			row[2] = vacuna.getDescripcion();
			row[3] = dameMisEnfermedades(vacuna.getLasEnfermedades());

			if (vacuna.isUsed())
				row[4] = "Usada";
			else
				row[4] = "Sin usar";

			modelo.addRow(row);
		}

	}

	public void loadVacunasAplicar(Enfermedad enfermedad) {
		modelo.setRowCount(0);
		row = new Object[tableVacunas.getColumnCount()];

		for (Vacuna vacuna : Clinica.getInstance().getMisVacunas()) {
			if (vacuna.getLasEnfermedades().contains(enfermedad) && !vacuna.isUsed()) {
				row[0] = vacuna.getCodigo();
				row[1] = vacuna.getNombre();
				row[2] = vacuna.getDescripcion();
				row[3] = dameMisEnfermedades(vacuna.getLasEnfermedades());

				if (vacuna.isUsed())
					row[4] = "Usada";
				else
					row[4] = "Sin usar";

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
