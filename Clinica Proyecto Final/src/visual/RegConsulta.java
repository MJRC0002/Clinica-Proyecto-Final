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
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.sun.org.apache.bcel.internal.generic.LoadClass;
import com.sun.xml.internal.bind.v2.model.core.ID;

import logico.Cita;
import logico.Clinica;
import logico.Consulta;
import logico.Enfermedad;
import logico.HistorialMedico;
import logico.Medico;
import logico.Paciente;
import logico.Persona;
import logico.Vacuna;
import sun.misc.Cleaner;

import javax.swing.JRadioButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JCheckBox;

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
	private JTable table;
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
		setResizable(false);
		setTitle("Registrar Consulta");
		setBounds(100, 100, 535, 670);
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
			panelConsulta.setBounds(15, 13, 489, 316);
			panel.add(panelConsulta);
			panelConsulta.setLayout(null);

			JLabel lblCodigoConsulta = new JLabel("C\u00F3digo:");
			lblCodigoConsulta.setBounds(15, 40, 69, 20);
			panelConsulta.add(lblCodigoConsulta);

			txtCodigoConsulta = new JTextField();
			txtCodigoConsulta.setText("Consulta - " + Clinica.getInstance().codigoConsulta);
			txtCodigoConsulta.setBounds(73, 37, 146, 26);
			panelConsulta.add(txtCodigoConsulta);
			txtCodigoConsulta.setEditable(false);
			txtCodigoConsulta.setColumns(10);

			JLabel lblFecha = new JLabel("Fecha:");
			lblFecha.setBounds(244, 40, 53, 20);
			panelConsulta.add(lblFecha);

			spnFecha = new JSpinner();
			spnFecha.setBounds(294, 37, 183, 26);
			panelConsulta.add(spnFecha);
			spnFecha.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_YEAR));

			JPanel panelEnfermedad = new JPanel();
			panelEnfermedad.setBorder(new TitledBorder(null, "Enfermedades registradas", TitledBorder.LEADING,
					TitledBorder.TOP, null, null));
			panelEnfermedad.setBounds(14, 106, 205, 140);
			panelConsulta.add(panelEnfermedad);
			panelEnfermedad.setLayout(new BorderLayout(0, 0));

			JScrollPane scrollPane = new JScrollPane();
			panelEnfermedad.add(scrollPane, BorderLayout.CENTER);

			table = new JTable();
			enfermedadesRegistradasModel = new DefaultTableModel();
			String[] columnNames = { "Código", "Nombre" };
			enfermedadesRegistradasModel.setColumnIdentifiers(columnNames);
			table.setModel(enfermedadesRegistradasModel);
			scrollPane.setViewportView(table);

			JLabel lblEnfermedad = new JLabel("Enfermedad:");
			lblEnfermedad.setBounds(15, 73, 83, 20);
			panelConsulta.add(lblEnfermedad);

			txtEnfermedad = new JTextField();
			txtEnfermedad.setEditable(false);
			txtEnfermedad.setEnabled(false);
			txtEnfermedad.setBounds(103, 71, 116, 26);
			panelConsulta.add(txtEnfermedad);
			txtEnfermedad.setColumns(10);

			lblBajoVigilancia = new JLabel("Bajo vigilancia del doctor:");
			lblBajoVigilancia.setBounds(14, 259, 158, 20);
			panelConsulta.add(lblBajoVigilancia);

			rdbtnSiBajoVigilancia = new JRadioButton("Si");
			rdbtnSiBajoVigilancia.setBounds(24, 282, 45, 25);
			panelConsulta.add(rdbtnSiBajoVigilancia);

			rdbtnNoBajoVigilancia = new JRadioButton("No");
			rdbtnNoBajoVigilancia.setBounds(89, 282, 45, 25);
			panelConsulta.add(rdbtnNoBajoVigilancia);

			lblDiagnostico = new JLabel("Diagnostico:");
			lblDiagnostico.setBounds(244, 73, 83, 20);
			panelConsulta.add(lblDiagnostico);

			txtDiagnostico = new JTextField();
			txtDiagnostico.setBounds(244, 103, 233, 76);
			panelConsulta.add(txtDiagnostico);
			txtDiagnostico.setColumns(10);

			lblSintomas = new JLabel("Sintomas:");
			lblSintomas.setBounds(244, 192, 83, 20);
			panelConsulta.add(lblSintomas);

			txtSintomas = new JTextField();
			txtSintomas.setColumns(10);
			txtSintomas.setBounds(244, 225, 233, 76);
			panelConsulta.add(txtSintomas);

			panelPersona = new JPanel();
			panelPersona.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
					"Informaci\u00F3n de Paciente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelPersona.setBounds(15, 342, 489, 210);
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
			lblEnfermo.setBounds(15, 160, 86, 20);
			panelPersona.add(lblEnfermo);

			rdbtnEstaEnfermo = new JRadioButton("Si");
			rdbtnEstaEnfermo.setBounds(94, 158, 57, 29);
			panelPersona.add(rdbtnEstaEnfermo);

			rdbtnNoEstarEnfermo = new JRadioButton("No");
			rdbtnNoEstarEnfermo.setBounds(153, 158, 65, 29);
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
			table.getSelectionModel().addListSelectionListener(e -> {
				if (!e.getValueIsAdjusting()) {
					int selectedRow = table.getSelectedRow();
					if (selectedRow >= 0) {
						String nombreEnfermedad = (String) table.getValueAt(selectedRow, 1);
						txtEnfermedad.setText(nombreEnfermedad);
					}
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

							HistorialMedico historial = null;
							Paciente paciente = new Paciente(txtCodigoPaciente.getText(), txtNombre.getText(),
									Integer.parseInt(txtEdad.getText()), tieneSeguro, genero, enfermo, historial);
							Enfermedad enfermedad = Clinica.getInstance()
									.buscarEnfermedadByCode(txtEnfermedad.getText());
							Consulta consulta = new Consulta(txtCodigoConsulta.getText(), paciente, enfermedad,
									txtDiagnostico.getText(), txtSintomas.getText(), bv);
							if (chckbxHistorialMedico.isSelected()) {
								historial = new HistorialMedico();
								historial.getMisConsultasRelevantes().add(consulta);
							}
							Clinica.getInstance().getMisConsultas().add(consulta);
							dispose();
						
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
		row = new Object[table.getColumnCount()];

		for (Enfermedad enfermedad : Clinica.getInstance().getMisEnfermedades()) {
			row[0] = enfermedad.getCodigo();
			row[1] = enfermedad.getNombre();
			enfermedadesRegistradasModel.addRow(row);
		}
	}
}
