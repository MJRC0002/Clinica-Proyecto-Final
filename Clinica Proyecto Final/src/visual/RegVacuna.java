package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import logico.Clinica;
import logico.Vacuna;
import sun.misc.Cleaner;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class RegVacuna extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JButton btnRegistrar;
	private JButton btnCancelar;
	private JTextField txtDescripci�n;
	private JTable tableRegistradas;
	private DefaultTableModel enferRegistradasModel;
	private DefaultTableModel enferSeleccionadasModel;
	private JTable tableSelecionadas;
	private JButton btnAgregar;
	private JButton btnQuitar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegVacuna dialog = new RegVacuna();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegVacuna() {
		setTitle("Registrar Vacuna");
		setResizable(false);
		setBounds(100, 100, 783, 416);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);

			JLabel lblCodigo = new JLabel("Codigo:");
			lblCodigo.setBounds(12, 13, 56, 22);
			panel.add(lblCodigo);

			txtCodigo = new JTextField();
			txtCodigo.setEditable(false);
			txtCodigo.setText("Vac - " + Clinica.getInstance().codigoVacuna);
			txtCodigo.setBounds(94, 13, 116, 22);
			panel.add(txtCodigo);
			txtCodigo.setColumns(10);

			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(12, 48, 56, 22);
			panel.add(lblNombre);

			txtNombre = new JTextField();
			txtNombre.setBounds(94, 48, 116, 22);
			panel.add(txtNombre);
			txtNombre.setColumns(10);

			JLabel lblDescripci�n = new JLabel("Descripci\u00F3n:");
			lblDescripci�n.setBounds(12, 83, 81, 22);
			panel.add(lblDescripci�n);

			txtDescripci�n = new JTextField();
			txtDescripci�n.setColumns(10);
			txtDescripci�n.setBounds(12, 118, 325, 165);
			panel.add(txtDescripci�n);

			JPanel panelEnfermedades = new JPanel();
			panelEnfermedades.setBorder(new TitledBorder(null, "Enfermedades con las que funciona la vacuna:",
					TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelEnfermedades.setBounds(407, 16, 328, 116);
			panel.add(panelEnfermedades);
			panelEnfermedades.setLayout(new BorderLayout(0, 0));

			JScrollPane scrollPane = new JScrollPane();
			panelEnfermedades.add(scrollPane, BorderLayout.CENTER);

			tableSelecionadas = new JTable();
			tableSelecionadas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent event) {
					if (tableSelecionadas.getSelectedRow() >= 0) {
						btnQuitar.setEnabled(true);
					} else {
						btnQuitar.setEnabled(false);
					}
				}
			});
			enferSeleccionadasModel = new DefaultTableModel();
			String[] columnNames = { "C�digo", "Nombre", "Descripcion" };
			enferSeleccionadasModel.setColumnIdentifiers(columnNames);
			tableSelecionadas.setModel(enferSeleccionadasModel);
			scrollPane.setViewportView(tableSelecionadas);

			JPanel panelEnferRegistradas = new JPanel();
			panelEnferRegistradas.setBorder(new TitledBorder(null, "Enfermedades registradas:", TitledBorder.LEADING,
					TitledBorder.TOP, null, null));
			panelEnferRegistradas.setBounds(407, 167, 328, 116);
			panel.add(panelEnferRegistradas);
			panelEnferRegistradas.setLayout(new BorderLayout(0, 0));

			JScrollPane scrollPane_1 = new JScrollPane();
			panelEnferRegistradas.add(scrollPane_1, BorderLayout.CENTER);

			tableRegistradas = new JTable();
			tableRegistradas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent event) {
					if (tableRegistradas.getSelectedRow() >= 0) {
						btnAgregar.setEnabled(true);
					} else {
						btnAgregar.setEnabled(false);
					}
				}
			});
			enferRegistradasModel = new DefaultTableModel();
			String[] columnNames2 = { "C�digo", "Nombre", "Descripcion" };
			enferRegistradasModel.setColumnIdentifiers(columnNames2);
			tableRegistradas.setModel(enferRegistradasModel);
			scrollPane_1.setViewportView(tableRegistradas);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRegistrar = new JButton("Registrar");
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String codigo = txtCodigo.getText();
						String nombre = txtNombre.getText();
						String descripcion = txtDescripci�n.getText();
						if (nombre.isEmpty() || descripcion.isEmpty()) {
							JOptionPane.showMessageDialog(null,
									"Por favor, complete todos los campos antes de registrar la vacuna.",
									"Campos incompletos", JOptionPane.ERROR_MESSAGE);
						} else {
							Vacuna vacuna = new Vacuna(codigo, nombre, descripcion);
							Clinica.getInstance().getMisVacunas().add(vacuna);
							clean();
						}
					}
				});

				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});

				btnAgregar = new JButton("Agregar");
				btnAgregar.setEnabled(false);
				buttonPane.add(btnAgregar);

				btnQuitar = new JButton("Quitar");
				btnQuitar.setEnabled(false);
				buttonPane.add(btnQuitar);
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}

	protected void clean() {
		Clinica.getInstance().codigoVacuna++;
		txtCodigo.setText("Vac - " + Clinica.getInstance().codigoVacuna);
		txtNombre.setText("");
		txtDescripci�n.setText("");
		enferSeleccionadasModel.setRowCount(0);
		enferRegistradasModel.setRowCount(0);

		// Deshabilita los botones Agregar y Quitar
		btnAgregar.setEnabled(false);
		btnQuitar.setEnabled(false);
	}
}
