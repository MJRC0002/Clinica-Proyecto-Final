package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
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

import logico.Clinica;
import logico.Consulta;
import logico.Enfermedad;

public class ListConsulta extends JDialog {

	private final JPanel contentPanel = new JPanel();
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
	private static Object[] row;
	private Consulta miConsulta = null;
	private int index = 0;
	private JButton btnAnterior;
	private JButton btnSiguiente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListConsulta dialog = new ListConsulta();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListConsulta() {
		setResizable(false);
		setTitle("Listar Consulta");
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
			txtCodigoConsulta.setBounds(73, 37, 146, 26);
			panelConsulta.add(txtCodigoConsulta);
			txtCodigoConsulta.setEditable(false);
			txtCodigoConsulta.setColumns(10);

			JLabel lblFecha = new JLabel("Fecha:");
			lblFecha.setBounds(244, 40, 53, 20);
			panelConsulta.add(lblFecha);

			spnFecha = new JSpinner();
			spnFecha.setEnabled(false);
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
			String[] columnNames = { "Código", "Nombre", "Sintomas" };
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
			rdbtnSiBajoVigilancia.setEnabled(false);
			rdbtnSiBajoVigilancia.setBounds(24, 282, 45, 25);
			panelConsulta.add(rdbtnSiBajoVigilancia);

			rdbtnNoBajoVigilancia = new JRadioButton("No");
			rdbtnNoBajoVigilancia.setEnabled(false);
			rdbtnNoBajoVigilancia.setBounds(89, 282, 45, 25);
			panelConsulta.add(rdbtnNoBajoVigilancia);

			lblDiagnostico = new JLabel("Diagnostico:");
			lblDiagnostico.setBounds(244, 73, 83, 20);
			panelConsulta.add(lblDiagnostico);
			txtDiagnostico = new JTextField();
			txtDiagnostico.setEnabled(false);
			txtDiagnostico.setBounds(244, 103, 233, 76);
			panelConsulta.add(txtDiagnostico);
			txtDiagnostico.setColumns(10);

			lblSintomas = new JLabel("Sintomas:");
			lblSintomas.setBounds(244, 192, 83, 20);
			panelConsulta.add(lblSintomas);

			txtSintomas = new JTextField();
			txtSintomas.setEnabled(false);
			txtSintomas.setColumns(10);
			txtSintomas.setBounds(244, 225, 233, 76);
			panelConsulta.add(txtSintomas);

			panelPersona = new JPanel();
			panelPersona.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
					"Informaci\u00F3n de Paciente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelPersona.setBounds(15, 342, 489, 160);
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
			lblEnfermo.setBounds(255, 121, 86, 20);
			panelPersona.add(lblEnfermo);

			rdbtnEstaEnfermo = new JRadioButton("Si");
			rdbtnEstaEnfermo.setEnabled(false);
			rdbtnEstaEnfermo.setBounds(315, 117, 57, 29);
			panelPersona.add(rdbtnEstaEnfermo);

			rdbtnNoEstarEnfermo = new JRadioButton("No");
			rdbtnNoEstarEnfermo.setEnabled(false);
			rdbtnNoEstarEnfermo.setBounds(376, 117, 65, 29);
			panelPersona.add(rdbtnNoEstarEnfermo);

			// Agregar listener para la selección de enfermedad en la tabla
			table.getSelectionModel().addListSelectionListener(e -> {
				if (!e.getValueIsAdjusting()) {
					int selectedRow = table.getSelectedRow();
					if (selectedRow >= 0) {
						String codigoEnfermedad = (String) table.getValueAt(selectedRow, 0);
						txtEnfermedad.setText(codigoEnfermedad);
					}
				}
			});
			btnAnterior = new JButton("Anterior");
			btnAnterior.setEnabled(false);
			btnSiguiente = new JButton("Siguiente");
			btnSiguiente.setEnabled(false);

			btnAnterior.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (index > 0) {
						index--;
						loadConsulta();
						if (index == 0)
							btnAnterior.setEnabled(false);

					}
				}
			});
			btnAnterior.setBounds(106, 530, 115, 29);
			panel.add(btnAnterior);
			btnSiguiente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (index < Clinica.getInstance().getMisConsultas().size()) {
						index++;
						btnAnterior.setEnabled(true);
						loadConsulta();
					}
				}
			});
			btnSiguiente.setBounds(306, 530, 115, 29);
			panel.add(btnSiguiente);
		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
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
		loadConsulta();
	}

	public void loadConsulta() {
		int size = Clinica.getInstance().getMisConsultas().size();
		if (size > 0 && index < size ) {
			miConsulta = Clinica.getInstance().getMisConsultas().get(index);
			txtCodigoConsulta.setText(miConsulta.getCodConsulta());
			txtCodigoPaciente.setText(miConsulta.getPaciente().getCodigo());
			txtDiagnostico.setText(miConsulta.getDiagnostico());
			txtEdad.setText(Integer.toString(miConsulta.getPaciente().getEdad()));
			txtNombre.setText(miConsulta.getPaciente().getNombre());
			spnFecha.setToolTipText(new Date().toString());
			txtDiagnostico.setText(miConsulta.getDiagnostico());
			txtSintomas.setText(miConsulta.getSintomas());
			txtEnfermedad.setText(miConsulta.getEnfermedad().getNombre());

			if (miConsulta.isBajoVig()) {
				rdbtnSiBajoVigilancia.setEnabled(true);
			} else {
				rdbtnNoBajoVigilancia.setEnabled(true);
			}

			if (miConsulta.getPaciente().getGenero() == 'm') {
				rdbtnMasculino.setEnabled(true);
			} else {
				rdbtnFemenino.setEnabled(true);
			}

			if (miConsulta.getPaciente().isSeguroMedico()) {
				rdbtnTieneSeguro.setEnabled(true);
			} else {
				rdbtnNoTieneSeguro.setEnabled(true);
			}

			if (miConsulta.getPaciente().isEnfermo()) {
				rdbtnEstaEnfermo.setEnabled(true);
			} else {
				rdbtnNoEstarEnfermo.setEnabled(true);
			}
			loadEnfermedades();
			if (index < size-1) 
				btnSiguiente.setEnabled(true);
			else 
				btnSiguiente.setEnabled(false);
			
		}

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
