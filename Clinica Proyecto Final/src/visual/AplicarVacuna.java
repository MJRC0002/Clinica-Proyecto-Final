package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Consulta;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AplicarVacuna extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel model;
	private Object[] rows;
	private ArrayList<Consulta> misConsultas;
	private JButton btnAplicarVacuna;
	private int index;

	public AplicarVacuna(ArrayList<Consulta> consulta) {
		misConsultas = consulta;
		setTitle("Aplicar Vacuna");
		setBounds(100, 100, 931, 526);
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

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			panel.add(scrollPane, BorderLayout.CENTER);

			table = new JTable();
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					index = table.getSelectedRow();
					if (index >= 0)
						btnAplicarVacuna.setEnabled(true);

				}
			});
			model = new DefaultTableModel();
			String letrero[] = { "Codigo", "Nombre", "Edad", "Enfermedad" };
			model.setColumnIdentifiers(letrero);
			table.setModel(model);
			scrollPane.setViewportView(table);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnAplicarVacuna = new JButton("Aplicar vacuna");
				btnAplicarVacuna.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ListVacuna listarVacuna = new ListVacuna(misConsultas.get(index).getEnfermedad(),
								misConsultas.get(index).getPaciente());
						listarVacuna.setModal(true);
						listarVacuna.setVisible(true);
					}
				});
				btnAplicarVacuna.setEnabled(false);
				btnAplicarVacuna.setActionCommand("OK");
				buttonPane.add(btnAplicarVacuna);
				getRootPane().setDefaultButton(btnAplicarVacuna);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		loadTable();
	}

	public void loadTable() {
		model.setRowCount(0);
		rows = new Object[table.getColumnCount()];
		for (Consulta consulta : misConsultas) {
			if (consulta.getEnfermedad() != null) {
				rows[0] = consulta.getPaciente().getCodigo();
				rows[1] = consulta.getPaciente().getNombre();
				rows[2] = Integer.toString(consulta.getPaciente().getEdad());
				rows[3] = consulta.getEnfermedad().getNombre();
			}

			model.addRow(rows);

		}

	}
}
