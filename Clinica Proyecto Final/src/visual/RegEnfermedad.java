package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import logico.Clinica;
import logico.Enfermedad;
import logico.Vacuna;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.UIManager;
import java.awt.Color;

public class RegEnfermedad extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtSintomas;
	private JTextField txtMedicamentos;
	private JTable tableVacunasDisp;
	private JTable tableVacunasEfec;
	private DefaultTableModel vacunasDispModel;
	private DefaultTableModel vacunasEfecModel;
	private JButton btnIzq;
	private JButton btnDer;
	private static Object[] row;
	private Enfermedad miEnfermedad = null;
	private JButton btnRegistar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegEnfermedad dialog = new RegEnfermedad(null, 0);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegEnfermedad(Enfermedad enfermedad, int ind) {
		miEnfermedad = enfermedad;
		if (enfermedad != null) {
			setTitle("Modificar Enfermedad");
			btnRegistar.setText("Modificar");
			loadEnfermedadData();
		} else {
			setTitle("Registrar Enfermedad");
		}
		setResizable(false);
		setBounds(100, 100, 586, 507);
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
			{
				JLabel lblCodigo = new JLabel("Codigo:");
				lblCodigo.setBounds(12, 10, 56, 30);
				panel.add(lblCodigo);
			}
			{
				txtCodigo = new JTextField();
				txtCodigo.setBounds(12, 37, 116, 30);
				panel.add(txtCodigo);
				txtCodigo.setColumns(10);
			}
			{
				JLabel lblNombreDeLa = new JLabel("Nombre de la enfermedad:");
				lblNombreDeLa.setBounds(189, 10, 155, 30);
				panel.add(lblNombreDeLa);
			}
			{
				txtNombre = new JTextField();
				txtNombre.setColumns(10);
				txtNombre.setBounds(189, 37, 155, 30);
				panel.add(txtNombre);
			}
			{
				JLabel lblSntomas = new JLabel("S\u00EDntomas:");
				lblSntomas.setBounds(12, 96, 70, 30);
				panel.add(lblSntomas);
			}
			{
				txtSintomas = new JTextField();
				txtSintomas.setColumns(10);
				txtSintomas.setBounds(12, 127, 155, 75);
				panel.add(txtSintomas);
			}
			{
				JLabel lblMedicamentosParaTratar = new JLabel("Medicamentos para tratar la enfermedad:");
				lblMedicamentosParaTratar.setBounds(189, 96, 254, 30);
				panel.add(lblMedicamentosParaTratar);
			}
			{
				txtMedicamentos = new JTextField();
				txtMedicamentos.setColumns(10);
				txtMedicamentos.setBounds(189, 127, 241, 75);
				panel.add(txtMedicamentos);
			}
			{
				JPanel panelVacunas = new JPanel();
				panelVacunas.setBorder(new TitledBorder(null, "Vacunas disponibles", TitledBorder.LEADING,
						TitledBorder.TOP, null, null));
				panelVacunas.setBounds(12, 220, 225, 189);
				panel.add(panelVacunas);
				panelVacunas.setLayout(new BorderLayout(0, 0));
				{
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
					panelVacunas.add(scrollPane, BorderLayout.CENTER);
					{
						tableVacunasDisp = new JTable();
						tableVacunasDisp.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
							public void valueChanged(ListSelectionEvent event) {
								if (tableVacunasDisp.getSelectedRow() >= 0) {
									btnDer.setEnabled(true);
								} else {
									btnDer.setEnabled(false);
								}
							}
						});
						vacunasDispModel = new DefaultTableModel();
						String[] columnNames2 = { "Código", "Nombre", "descripcion" };
						vacunasDispModel.setColumnIdentifiers(columnNames2);
						tableVacunasDisp.setModel(vacunasDispModel);
						scrollPane.setViewportView(tableVacunasDisp);
					}
				}
			}
			{
				JPanel panelVacunasEfec = new JPanel();
				panelVacunasEfec.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
						"Vacunas Efectivas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panelVacunasEfec.setBounds(325, 220, 225, 189);
				panel.add(panelVacunasEfec);
				panelVacunasEfec.setLayout(new BorderLayout(0, 0));
				{
					JScrollPane scrollPane = new JScrollPane();
					panelVacunasEfec.add(scrollPane, BorderLayout.CENTER);
					{
						tableVacunasEfec = new JTable();
						tableVacunasEfec.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
							public void valueChanged(ListSelectionEvent event) {
								if (tableVacunasEfec.getSelectedRow() >= 0) {
									btnIzq.setEnabled(true);
								} else {
									btnIzq.setEnabled(false);
								}
							}
						});
						vacunasEfecModel = new DefaultTableModel();
						String[] columnNames = { "Código", "Nombre", "descripcion" };
						vacunasEfecModel.setColumnIdentifiers(columnNames);
						tableVacunasEfec.setModel(vacunasEfecModel);
						scrollPane.setViewportView(tableVacunasEfec);
					}
				}
			}
			{
				btnDer = new JButton(">");
				btnDer.setEnabled(false);
				btnDer.setBounds(243, 262, 70, 25);
				panel.add(btnDer);
			}
			{
				btnIzq = new JButton("<");
				btnIzq.setEnabled(false);
				btnIzq.setBounds(243, 316, 70, 25);
				panel.add(btnIzq);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRegistar = new JButton("Registrar");
				btnRegistar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (enfermedad == null) {
							int cant = vacunasEfecModel.getRowCount();
							Enfermedad enf = null;
							String codigo = txtCodigo.getText();
							String sintomas = txtSintomas.getText();
							String nombre = txtNombre.getText();
							String medicamentos = txtMedicamentos.getText();
							enf = new Enfermedad(codigo, nombre, sintomas, medicamentos);
							for (int i = 0; i < cant; i++) {
								Vacuna vacuna = Clinica.getInstance()
										.buscarVacunaByCode((String) vacunasEfecModel.getValueAt(i, 0));
								enf.getVacunasEfectivas().add(vacuna);
							}
							Clinica.getInstance().getMisEnfermedades().add(enf);
							clean();
						} else {
							int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea continuar?",
									"Confirmación", JOptionPane.YES_NO_OPTION);
							if (respuesta == JOptionPane.YES_OPTION) {
								Enfermedad modificado = new Enfermedad(miEnfermedad.getCodigo(),
										miEnfermedad.getNombre(), txtSintomas.getText(),
										miEnfermedad.getMedicamentos());
								Clinica.getInstance().getMisEnfermedades().set(ind, modificado);
								JOptionPane.showMessageDialog(null,
										"Modifico la enfermedad con nombre: " + modificado.getNombre()
												+ ", correctamente.",
										"Modificacion con exito", JOptionPane.INFORMATION_MESSAGE);
								ListEnfermedad.loadEnfermedades();
								dispose();
							}

						}

					}
				});
				btnRegistar.setActionCommand("OK");
				buttonPane.add(btnRegistar);
				getRootPane().setDefaultButton(btnRegistar);
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
		loadVacunas();
	}

	private void loadEnfermedadData() {
		if (miEnfermedad != null) {
			txtCodigo.setEnabled(false);
			txtCodigo.setText(miEnfermedad.getCodigo());
			txtNombre.setEnabled(false);
			txtNombre.setText(miEnfermedad.getNombre());
			txtSintomas.setText(miEnfermedad.getNombre());
			txtMedicamentos.setEnabled(false);
			txtMedicamentos.setText(miEnfermedad.getMedicamentos());
			dameMisVacunasEfectivas(miEnfermedad.getVacunasEfectivas());
			btnIzq.setEnabled(false);
			btnDer.setEnabled(false);
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

	public void loadVacunas() {
		vacunasDispModel.setRowCount(0);
		row = new Object[tableVacunasDisp.getColumnCount()];

		for (Vacuna v : Clinica.getInstance().getMisVacunas()) {
			row[0] = v.getCodigo();
			row[1] = v.getNombre();
			row[2] = v.getDescripcion();
			vacunasDispModel.addRow(row);
		}
	}

	// Restablecer los campos a sus valores predeterminados
	public void clean() {
		txtCodigo.setText("");
		txtNombre.setText("");
		txtSintomas.setText("");
		txtMedicamentos.setText("");
		vacunasEfecModel.setRowCount(0);
		btnIzq.setEnabled(false);
		btnDer.setEnabled(false);
	}

}
