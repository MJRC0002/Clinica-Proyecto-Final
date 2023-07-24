package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Clinica;
import logico.Enfermedad;
import logico.Medico;
import logico.Vacuna;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;

public class RegMedico extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnRegistrar;
	private JButton btnCancelar;
	private JPanel panel;
	private JTextField txtCodigoMedico;
	private JTextField txtNombre;
	private JLabel lblTelefono;
	private JTextField txtTelefono;
	private JPanel panelPersona;
	private JTextField txtEspecializacion;
	private JPanel panel_1;
	private JScrollPane scrollPane_1;
	private JTable tableEnfCust;
	private JTable tableRegistradas;
	private JButton btnIzq;
	private JButton btnDer;
	private DefaultTableModel enfermedadesCustodiadasModel;
	private DefaultTableModel enfermedadesRegistradasModel;
	private static Object[] row;
	private JPanel panelEnfermedades;
	private JPanel panelUsuario;
	private JTextField txtUser;
	private JLabel lblUser;
	private JLabel lblContrasenna;
	private JTextField txtContrasenna;
	private ArrayList<Enfermedad> enfermedaCustodiadas = new ArrayList<Enfermedad>();
	private ArrayList<Enfermedad> enfermedaRegistradas = new ArrayList<Enfermedad>();
	private int indexCustodiada;
	private int indexRegistrada;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegMedico dialog = new RegMedico();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegMedico() {
		setResizable(false);
		setTitle("Registrar M\u00E9dico");
		setBounds(100, 100, 652, 575);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);

			JPanel panelDatosMedicos = new JPanel();
			panelDatosMedicos.setBorder(
					new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informaci\u00F3n de m\u00E9dico",
							TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelDatosMedicos.setBounds(15, 16, 592, 142);
			panel.add(panelDatosMedicos);
			panelDatosMedicos.setLayout(null);

			JLabel lblCodigoCita = new JLabel("C\u00F3digo:");
			lblCodigoCita.setBounds(15, 40, 69, 20);
			panelDatosMedicos.add(lblCodigoCita);

			txtCodigoMedico = new JTextField();
			txtCodigoMedico.setText("Médico - " + Clinica.getInstance().codigoMedico);
			txtCodigoMedico.setBounds(73, 37, 146, 26);
			panelDatosMedicos.add(txtCodigoMedico);
			txtCodigoMedico.setEditable(false);
			txtCodigoMedico.setColumns(10);

			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(15, 79, 69, 20);
			panelDatosMedicos.add(lblNombre);

			txtNombre = new JTextField();
			txtNombre.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char key = e.getKeyChar();
					if (Character.isDigit(key))
						e.consume();
				}
			});
			txtNombre.setBounds(83, 76, 146, 26);
			panelDatosMedicos.add(txtNombre);
			txtNombre.setColumns(10);

			JLabel lblEspecializacion = new JLabel("Especializaci\u00F3n:");
			lblEspecializacion.setBounds(241, 79, 121, 20);
			panelDatosMedicos.add(lblEspecializacion);

			txtEspecializacion = new JTextField();
			txtEspecializacion.setBounds(358, 76, 210, 26);
			panelDatosMedicos.add(txtEspecializacion);
			txtEspecializacion.setColumns(10);

			lblTelefono = new JLabel("Telefono:");
			lblTelefono.setBounds(277, 40, 69, 20);
			panelDatosMedicos.add(lblTelefono);

			txtTelefono = new JTextField();
			txtTelefono.setBounds(358, 37, 146, 26);
			panelDatosMedicos.add(txtTelefono);
			txtTelefono.setColumns(10);

			enfermedadesCustodiadasModel = new DefaultTableModel();
			String[] columnNames = { "Código", "Nombre", "Sintomas" };
			enfermedadesCustodiadasModel.setColumnIdentifiers(columnNames);

			panel_1 = new JPanel();
			panel_1.setLayout(null);
			panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Enfermedades registradas:",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_1.setBounds(364, 295, 231, 158);
			panel.add(panel_1);

			scrollPane_1 = new JScrollPane();
			scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane_1.setBounds(15, 28, 201, 114);
			panel_1.add(scrollPane_1);

			tableRegistradas = new JTable();
			tableRegistradas.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					indexRegistrada = tableRegistradas.getSelectedRow();
					if (indexRegistrada >= 0)
						btnIzq.setEnabled(true);
					else {
						btnIzq.setEnabled(false);
					}
				}
			});

			enfermedadesRegistradasModel = new DefaultTableModel();
			enfermedadesRegistradasModel.setColumnIdentifiers(columnNames);
			tableRegistradas.setModel(enfermedadesRegistradasModel);
			scrollPane_1.setViewportView(tableRegistradas);

			panelEnfermedades = new JPanel();
			panelEnfermedades.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Enfermedades",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelEnfermedades.setBounds(15, 269, 592, 196);
			panel.add(panelEnfermedades);
			panelEnfermedades.setLayout(null);
			
						btnIzq = new JButton("<");
						btnIzq.setBounds(252, 61, 81, 25);
						panelEnfermedades.add(btnIzq);
						btnIzq.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								Enfermedad enfermedad = Clinica.getInstance()
										.buscarEnfermedadByCode((String) tableRegistradas.getValueAt(indexRegistrada, 0));
								if (!isEnfermedadCustodiada(enfermedad)) {
									enfermedaCustodiadas.add(enfermedad);
									LoadEnfermedades();
									btnIzq.setEnabled(false);
								}
							}
						});
						btnIzq.setEnabled(false);
						
									btnDer = new JButton(">");
									btnDer.setBounds(252, 120, 81, 25);
									panelEnfermedades.add(btnDer);
									btnDer.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent e) {
											enfermedaRegistradas.add(Clinica.getInstance()
													.buscarEnfermedadByCode((String) tableEnfCust.getValueAt(indexCustodiada, 0)));
											enfermedaCustodiadas.remove(indexCustodiada);
											LoadEnfermedades();
											btnDer.setEnabled(false);
										}
									});
									btnDer.setEnabled(false);
									
												panelPersona = new JPanel();
												panelPersona.setBounds(12, 25, 231, 158);
												panelEnfermedades.add(panelPersona);
												panelPersona.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
														"Enfermedades a custodiar", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
												panelPersona.setLayout(null);
												
															JScrollPane scrollPane = new JScrollPane();
															scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
															scrollPane.setBounds(15, 28, 201, 114);
															panelPersona.add(scrollPane);
															
																		tableEnfCust = new JTable();
																		tableEnfCust.addMouseListener(new MouseAdapter() {
																			@Override
																			public void mouseClicked(MouseEvent e) {
																				indexCustodiada = tableEnfCust.getSelectedRow();
																				if (indexCustodiada >= 0)
																					btnDer.setEnabled(true);
																				else {
																					btnDer.setEnabled(false);
																				}
																			}
																		});
																		tableEnfCust.setModel(enfermedadesCustodiadasModel);
																		scrollPane.setViewportView(tableEnfCust);

			panelUsuario = new JPanel();
			panelUsuario.setLayout(null);
			panelUsuario
					.setBorder(new TitledBorder(null, "Usuario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelUsuario.setBounds(15, 162, 592, 91);
			panel.add(panelUsuario);

			txtUser = new JTextField();
			txtUser.setText("Admin");
			txtUser.setColumns(10);
			txtUser.setBounds(81, 42, 190, 26);
			panelUsuario.add(txtUser);

			lblUser = new JLabel("Usuario:");
			lblUser.setBounds(15, 41, 83, 29);
			panelUsuario.add(lblUser);

			lblContrasenna = new JLabel("Contras\u00F1a:");
			lblContrasenna.setBounds(273, 41, 83, 29);
			panelUsuario.add(lblContrasenna);

			txtContrasenna = new JTextField();
			txtContrasenna.setText("Admin");
			txtContrasenna.setColumns(10);
			txtContrasenna.setBounds(354, 42, 211, 26);
			panelUsuario.add(txtContrasenna);
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
						//validar campos!
						if (!(txtCodigoMedico.getText().isEmpty() || txtContrasenna.getText().isEmpty()
								|| txtEspecializacion.getText().isEmpty() || txtNombre.getText().isEmpty()
								|| txtTelefono.getText().isEmpty() || txtUser.getText().isEmpty())) {
							//Hacer el registro
							Medico medico = new Medico(txtCodigoMedico.getText(), txtNombre.getText(),
									txtTelefono.getText(), txtEspecializacion.getText(), txtUser.getText(),
									txtContrasenna.getText());

							for (Enfermedad enfermedad : enfermedaCustodiadas)
								medico.getMisEnfermedades().add(enfermedad);

							Clinica.getInstance().getMisMedicos().add(medico);
						}else {
							JOptionPane.showMessageDialog(null,
									"Por favor, complete todos los campos antes de registrar el Medico!",
									"Campos incompletos", JOptionPane.ERROR_MESSAGE);
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
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		LoadEnfermedades();
	}

	public void LoadEnfermedades() {
		enfermedadesRegistradasModel.setRowCount(0);
		enfermedadesCustodiadasModel.setRowCount(0);
		row = new Object[tableRegistradas.getColumnCount()];

		// Cargar las enfermedades registradas
		for (Enfermedad enfermedad : Clinica.getInstance().getMisEnfermedades()) {
			row[0] = enfermedad.getCodigo();
			row[1] = enfermedad.getNombre();
			row[2] = enfermedad.getSintomas();
			enfermedadesRegistradasModel.addRow(row);
		}

		// Cargar las enfermedades custodiadas
		row = new Object[tableEnfCust.getColumnCount()];
		for (Enfermedad enfermedad : enfermedaCustodiadas) {
			row[0] = enfermedad.getCodigo();
			row[1] = enfermedad.getNombre();
			row[2] = enfermedad.getSintomas();
			enfermedadesCustodiadasModel.addRow(row);
		}
	}

	// Método para verificar si una enfermedad ya está en la lista de enfermedades
	// custodiadas
	private boolean isEnfermedadCustodiada(Enfermedad enfermedad) {
		for (Enfermedad enf : enfermedaCustodiadas) {
			if (enf.getCodigo().equals(enfermedad.getCodigo())) {
				return true;
			}
		}
		return false;
	}

	protected void Cleaner() {
		Clinica.getInstance().codigoMedico++;
		txtNombre.setText("");
		txtEspecializacion.setText("");
		txtNombre.setText("");
		txtTelefono.setText("");

	}
}
