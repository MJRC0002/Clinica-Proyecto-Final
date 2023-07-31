package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Cita;
import logico.Clinica;
import logico.Consulta;
import logico.Enfermedad;
import logico.HistorialMedico;
import logico.Medico;
import logico.Paciente;
import logico.Persona;
import logico.Vacuna;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RegConsulta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnRegistrar;
	private JButton btnCancelar;
	private JPanel panel;
	private JSpinner spnFecha;
	private JTextField txtCodigoConsulta;
	private JTextField txtNombre;
	private JLabel lblEdad;
	private JTextField txtEdad;
	private JLabel lblSeguroMedico;
	private JPanel panelPersona;
	private JTextField txtCodigoPaciente;
	private JRadioButton rdbtnTieneSeguro;
	private JRadioButton rdbtnNoTieneSeguro;
	private JLabel lblGenero;
	private JRadioButton rdbtnMasculino;
	private JRadioButton rdbtnFemenino;
	private JRadioButton rdbtnNoEstarEnfermo;
	private JRadioButton rdbtnEstaEnfermo;
	private JTextField txtEnfermedad;
	private JTable tableEnfermedades;
	private DefaultTableModel enfermedadesRegistradasModel;
	private JLabel lblBajoVigilancia;
	private JLabel lblDiagnostico;
	private JTextField txtDiagnostico;
	private JLabel lblSintomas;
	private JTextField txtSintomas;
	private JRadioButton rdbtnSiBajoVigilancia;
	private JRadioButton rdbtnNoBajoVigilancia;
	private JCheckBox chckbxHistorialMedico;
	private static Object[] row;
	private String codigoEnfermedad = null;
	private ArrayList<Vacuna> lasVacunas = null;
	private JTextField txtVacuna;
	private static JTable tableVacunas;
	private static DefaultTableModel vacunasModel;
	private ArrayList<String> vacunasSeleccionadas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegConsulta dialog = new RegConsulta(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegConsulta(Cita cita) {
		lasVacunas = new ArrayList<>();
		setResizable(false);
		setTitle("Registrar Consulta");
		setBounds(100, 100, 755, 630);
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

			JPanel panelConsulta = new JPanel();
			panelConsulta.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
					"Informaci\u00F3n de consulta:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelConsulta.setBounds(15, 13, 702, 330);
			panel.add(panelConsulta);
			panelConsulta.setLayout(null);

			JLabel lblCodigoConsulta = new JLabel("C\u00F3digo:");
			lblCodigoConsulta.setBounds(15, 40, 69, 20);
			panelConsulta.add(lblCodigoConsulta);

			txtCodigoConsulta = new JTextField();
			txtCodigoConsulta.setText("Consulta - " + (Clinica.getInstance().getMisConsultas().size() + 1));
			txtCodigoConsulta.setBounds(73, 37, 146, 26);
			panelConsulta.add(txtCodigoConsulta);
			txtCodigoConsulta.setEditable(false);
			txtCodigoConsulta.setColumns(10);

			JLabel lblFecha = new JLabel("Fecha:");
			lblFecha.setBounds(260, 40, 53, 20);
			panelConsulta.add(lblFecha);

			spnFecha = new JSpinner();
			spnFecha.setBounds(316, 37, 183, 26);
			panelConsulta.add(spnFecha);
			spnFecha.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_YEAR));

			JPanel panelEnfermedad = new JPanel();
			panelEnfermedad.setBorder(new TitledBorder(null, "Enfermedades registradas", TitledBorder.LEADING,
					TitledBorder.TOP, null, null));
			panelEnfermedad.setBounds(485, 113, 205, 140);
			panelConsulta.add(panelEnfermedad);
			panelEnfermedad.setLayout(new BorderLayout(0, 0));

			JScrollPane scrollPane = new JScrollPane();
			panelEnfermedad.add(scrollPane, BorderLayout.CENTER);

			tableEnfermedades = new JTable();
			enfermedadesRegistradasModel = new DefaultTableModel();
			String[] columnNames = { "Código", "Nombre" };
			enfermedadesRegistradasModel.setColumnIdentifiers(columnNames);
			tableEnfermedades.setModel(enfermedadesRegistradasModel);
			scrollPane.setViewportView(tableEnfermedades);

			JLabel lblEnfermedad = new JLabel("Enfermedad:");
			lblEnfermedad.setBounds(485, 73, 83, 20);
			panelConsulta.add(lblEnfermedad);

			txtEnfermedad = new JTextField();
			txtEnfermedad.setEditable(false);
			txtEnfermedad.setEnabled(false);
			txtEnfermedad.setBounds(574, 70, 116, 26);
			panelConsulta.add(txtEnfermedad);
			txtEnfermedad.setColumns(10);

			lblBajoVigilancia = new JLabel("Bajo vigilancia del doctor:");
			lblBajoVigilancia.setBounds(260, 266, 158, 20);
			panelConsulta.add(lblBajoVigilancia);

			rdbtnSiBajoVigilancia = new JRadioButton("Si");
			rdbtnSiBajoVigilancia.setBounds(426, 264, 45, 25);
			panelConsulta.add(rdbtnSiBajoVigilancia);

			rdbtnNoBajoVigilancia = new JRadioButton("No");
			rdbtnNoBajoVigilancia.setBounds(478, 264, 45, 25);
			panelConsulta.add(rdbtnNoBajoVigilancia);

			lblDiagnostico = new JLabel("Diagnostico:");
			lblDiagnostico.setBounds(15, 73, 83, 20);
			panelConsulta.add(lblDiagnostico);

			txtDiagnostico = new JTextField();
			txtDiagnostico.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char key = e.getKeyChar();
					if (!Character.isDigit(key) && (!Character.isWhitespace(key) && !Character.isAlphabetic(key)))
						e.consume();
				}
			});
			txtDiagnostico.setBounds(15, 103, 233, 76);
			panelConsulta.add(txtDiagnostico);
			txtDiagnostico.setColumns(10);

			lblSintomas = new JLabel("Sintomas:");
			lblSintomas.setBounds(15, 192, 83, 20);
			panelConsulta.add(lblSintomas);

			txtSintomas = new JTextField();
			txtSintomas.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char key = e.getKeyChar();
					if (!Character.isDigit(key) && (!Character.isWhitespace(key) && !Character.isAlphabetic(key)))
						e.consume();
				}
			});
			txtSintomas.setColumns(10);
			txtSintomas.setBounds(15, 225, 233, 76);
			panelConsulta.add(txtSintomas);

			JPanel panelVacunas = new JPanel();
			panelVacunas.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Vacunas Registradas",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelVacunas.setBounds(260, 113, 189, 140);
			panelConsulta.add(panelVacunas);
			panelVacunas.setLayout(new BorderLayout(0, 0));

			JScrollPane spVacunas = new JScrollPane();
			panelVacunas.add(spVacunas, BorderLayout.CENTER);

			tableVacunas = new JTable();
			vacunasModel = new DefaultTableModel();
			String[] vacunaColumnNames = { "Código", "Nombre", "Descripcion" };
			vacunasModel.setColumnIdentifiers(vacunaColumnNames);
			tableVacunas.setModel(vacunasModel);
			spVacunas.setViewportView(tableVacunas);

			JLabel lblVacuna = new JLabel("Vacuna:");
			lblVacuna.setBounds(260, 73, 53, 20);
			panelConsulta.add(lblVacuna);

			txtVacuna = new JTextField();
			txtVacuna.setEnabled(false);
			txtVacuna.setEditable(false);
			txtVacuna.setColumns(10);
			txtVacuna.setBounds(333, 70, 116, 26);
			panelConsulta.add(txtVacuna);

			panelPersona = new JPanel();
			panelPersona.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
					"Informaci\u00F3n de Paciente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelPersona.setBounds(15, 356, 702, 162);
			panel.add(panelPersona);
			panelPersona.setLayout(null);

			JLabel lblCodigoPaciente = new JLabel("C\u00F3digo:");
			lblCodigoPaciente.setBounds(15, 36, 57, 20);
			panelPersona.add(lblCodigoPaciente);

			txtCodigoPaciente = new JTextField();
			txtCodigoPaciente.setEnabled(false);
			txtCodigoPaciente.setColumns(10);
			txtCodigoPaciente.setBounds(72, 33, 146, 26);
			panelPersona.add(txtCodigoPaciente);

			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(15, 75, 76, 20);
			panelPersona.add(lblNombre);

			txtNombre = new JTextField();
			txtNombre.setEnabled(false);
			txtNombre.setColumns(10);
			txtNombre.setBounds(83, 72, 146, 26);
			panelPersona.add(txtNombre);

			lblEdad = new JLabel("Edad:");
			lblEdad.setBounds(255, 36, 57, 20);
			panelPersona.add(lblEdad);

			txtEdad = new JTextField();
			txtEdad.setEnabled(false);
			txtEdad.setColumns(10);
			txtEdad.setBounds(304, 33, 146, 26);
			panelPersona.add(txtEdad);

			lblSeguroMedico = new JLabel("Seguro M\u00E9dico:");
			lblSeguroMedico.setBounds(255, 75, 117, 20);
			panelPersona.add(lblSeguroMedico);

			rdbtnTieneSeguro = new JRadioButton("si");
			rdbtnTieneSeguro.setEnabled(false);
			rdbtnTieneSeguro.setBounds(365, 71, 57, 29);
			panelPersona.add(rdbtnTieneSeguro);

			rdbtnNoTieneSeguro = new JRadioButton("No");
			rdbtnNoTieneSeguro.setEnabled(false);
			rdbtnNoTieneSeguro.setBounds(424, 71, 57, 29);
			panelPersona.add(rdbtnNoTieneSeguro);

			lblGenero = new JLabel("G\u00E9nero:");
			lblGenero.setBounds(15, 121, 86, 20);
			panelPersona.add(lblGenero);

			rdbtnMasculino = new JRadioButton("M");
			rdbtnMasculino.setEnabled(false);
			rdbtnMasculino.setBounds(94, 117, 57, 29);
			panelPersona.add(rdbtnMasculino);

			rdbtnFemenino = new JRadioButton("F");
			rdbtnFemenino.setEnabled(false);
			rdbtnFemenino.setBounds(153, 117, 65, 29);
			panelPersona.add(rdbtnFemenino);

			JLabel lblEnfermo = new JLabel("Enfermo:");
			lblEnfermo.setBounds(501, 36, 86, 20);
			panelPersona.add(lblEnfermo);

			rdbtnEstaEnfermo = new JRadioButton("Si");
			rdbtnEstaEnfermo.setBounds(563, 32, 57, 29);
			panelPersona.add(rdbtnEstaEnfermo);

			rdbtnNoEstarEnfermo = new JRadioButton("No");
			rdbtnNoEstarEnfermo.setBounds(624, 32, 65, 29);
			panelPersona.add(rdbtnNoEstarEnfermo);

			chckbxHistorialMedico = new JCheckBox("Agregar a historial m\u00E9dico.");
			chckbxHistorialMedico.setBounds(255, 112, 195, 38);
			panelPersona.add(chckbxHistorialMedico);

			// Cargar persona para crear el paciente.
			if (cita != null)
				loadPaciente(cita);
			else {
				txtCodigoPaciente.setEnabled(true);
				txtNombre.setEnabled(true);
				txtEdad.setEnabled(true);
				rdbtnTieneSeguro.setEnabled(true);
				rdbtnNoTieneSeguro.setEnabled(true);
				rdbtnMasculino.setEnabled(true);
				rdbtnFemenino.setEnabled(true);
				rdbtnEstaEnfermo.setEnabled(true);
				rdbtnNoEstarEnfermo.setEnabled(true);
			}

			// Agregar listener para la selección de enfermedad en la tabla
			tableEnfermedades.getSelectionModel().addListSelectionListener(e -> {
				if (!e.getValueIsAdjusting()) {
					int selectedRow = tableEnfermedades.getSelectedRow();
					if (selectedRow >= 0) {
						String nombreEnfermedad = (String) tableEnfermedades.getValueAt(selectedRow, 1);
						codigoEnfermedad = (String) tableEnfermedades.getValueAt(selectedRow, 0);
						txtEnfermedad.setText(nombreEnfermedad);
					}
				}
			});
			// Agregar listener para la selección de vacunas en la tabla
			vacunasSeleccionadas = new ArrayList<>();

			tableVacunas.getSelectionModel().addListSelectionListener(e -> {
				if (!e.getValueIsAdjusting()) {
					int[] selectedRows = tableVacunas.getSelectedRows();
					vacunasSeleccionadas.clear();
					for (int row : selectedRows) {
						String codigoVacuna = (String) tableVacunas.getValueAt(row, 0);
						String nombreVacuna = (String) tableVacunas.getValueAt(row, 1);
						lasVacunas.add(Clinica.getInstance().buscarVacunaByCode(codigoVacuna));
						vacunasSeleccionadas.add(nombreVacuna);
					}
					txtVacuna.setText(String.join(", ", vacunasSeleccionadas));
				}
			});
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
						boolean tieneSeguro;
						if (rdbtnTieneSeguro.isSelected())
							tieneSeguro = true;
						else
							tieneSeguro = false;

						char genero;
						if (rdbtnMasculino.isSelected())
							genero = 'm';
						else
							genero = 'f';

						boolean enfermo;
						if (rdbtnEstaEnfermo.isSelected())
							enfermo = true;
						else
							enfermo = false;

						boolean bv;
						if (rdbtnSiBajoVigilancia.isSelected())
							bv = true;
						else
							bv = false;
						boolean vacunado;
						if (txtVacuna.getText().isEmpty())
							vacunado = true;
						else
							vacunado = false;

						if (txtCodigoConsulta.getText().isEmpty() || txtNombre.getText().isEmpty()
								|| txtCodigoPaciente.getText().isEmpty() || txtDiagnostico.getText().isEmpty()
								|| txtSintomas.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null,
									"Debes completar todos los campos para registrar una consulta",
									"No puedes registrar", JOptionPane.ERROR_MESSAGE);
						} else {
							Paciente paciente = new Paciente(txtCodigoPaciente.getText(), txtNombre.getText(),
									Integer.parseInt(txtEdad.getText()), tieneSeguro, genero, enfermo, vacunado);
							Enfermedad enfermedad = Clinica.getInstance().buscarEnfermedadByCode(codigoEnfermedad);
							Consulta consulta = new Consulta(txtCodigoConsulta.getText(), paciente, enfermedad,
									txtDiagnostico.getText(), txtSintomas.getText(), bv, (Date) spnFecha.getValue());
							if (chckbxHistorialMedico.isSelected())
								paciente.getMiHIstorial().getMisConsultasRelevantes().add(consulta);

							if (vacunado == true)
								for (Vacuna vacuna : lasVacunas)
								{	vacuna.usarVacuna();
									paciente.vacunar(vacuna);
								}

							Clinica.getInstance().getMisConsultas().add(consulta);
							Clinica.getInstance().getMisPacientes().add(paciente);
							// borrar cita para medico y secretaria.
							Clinica.getInstance().buscarMedicoByCode(cita.getIdMedico()).getMisCitas().remove(cita);
							Clinica.getInstance().buscarMedicoByCode(cita.getIdMedico()).getMisConsultas().add(consulta);
							Clinica.getInstance().getMiSecretaria().getMisCitas().remove(cita);
							dispose();
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
		loadEnfermedades();
		loadVacunas();
	}

	private void loadPaciente(Cita cita) {
		Persona persona = cita.getPersona();
		txtCodigoPaciente.setText(persona.getCodigo());
		txtNombre.setText(persona.getNombre());
		txtEdad.setText(Integer.toString(persona.getEdad()));
		if (persona.getGenero() == 'm')
			rdbtnMasculino.setSelected(true);
		else
			rdbtnFemenino.setSelected(true);

		if (persona.isSeguroMedico())
			rdbtnTieneSeguro.setSelected(true);
		else
			rdbtnNoTieneSeguro.setSelected(false);

	}

	private void loadEnfermedades() {
		enfermedadesRegistradasModel.setRowCount(0);
		row = new Object[tableEnfermedades.getColumnCount()];

		for (Enfermedad enfermedad : Clinica.getInstance().getMisEnfermedades()) {
			row[0] = enfermedad.getCodigo();
			row[1] = enfermedad.getNombre();
			enfermedadesRegistradasModel.addRow(row);
		}
	}

	public static void loadVacunas() {
		vacunasModel.setRowCount(0);
		row = new Object[tableVacunas.getColumnCount()];

		for (Vacuna vacuna : Clinica.getInstance().getMisVacunas()) {
			row[0] = vacuna.getCodigo();
			row[1] = vacuna.getNombre();
			row[2] = vacuna.getDescripcion();
			vacunasModel.addRow(row);
		}

	}
}
