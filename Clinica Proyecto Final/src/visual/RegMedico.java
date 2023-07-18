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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.sun.org.apache.bcel.internal.generic.LoadClass;

import logico.Cita;
import logico.Clinica;
import logico.Medico;
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
import javax.swing.ScrollPaneConstants;

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
	private JTextField txtCodigoPersona;
	private JPanel panel_1;
	private JScrollPane scrollPane_1;
	private JTable tableEnfCust;
	private JTable tableEnfVig;
	private JButton btnIzq;
	private JButton btnDer;
	private DefaultTableModel enfermedadesCustodiarModel;
	private DefaultTableModel enfermedadesBajoVigilanciaModel;
	private static Object[] row;

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
		setBounds(100, 100, 623, 462);
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

			JPanel panelCita = new JPanel();
			panelCita.setBorder(
					new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informaci\u00F3n de m\u00E9dico",
							TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelCita.setBounds(15, 16, 580, 142);
			panel.add(panelCita);
			panelCita.setLayout(null);

			JLabel lblCodigoCita = new JLabel("C\u00F3digo:");
			lblCodigoCita.setBounds(15, 40, 69, 20);
			panelCita.add(lblCodigoCita);

			txtCodigoMedico = new JTextField();
			txtCodigoMedico.setText("Médico - " + Clinica.getInstance().codigoMedico);
			txtCodigoMedico.setBounds(73, 37, 146, 26);
			panelCita.add(txtCodigoMedico);
			txtCodigoMedico.setEditable(false);
			txtCodigoMedico.setColumns(10);

			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(15, 79, 69, 20);
			panelCita.add(lblNombre);

			txtNombre = new JTextField();
			txtNombre.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char key = e.getKeyChar();
					if (!Character.isDigit(key))
						e.consume();
				}
			});
			txtNombre.setBounds(83, 76, 146, 26);
			panelCita.add(txtNombre);
			txtNombre.setColumns(10);

			JLabel lblEspecializacion = new JLabel("Especializaci\u00F3n:");
			lblEspecializacion.setBounds(241, 79, 121, 20);
			panelCita.add(lblEspecializacion);

			txtCodigoPersona = new JTextField();
			txtCodigoPersona.setBounds(358, 76, 210, 26);
			panelCita.add(txtCodigoPersona);
			txtCodigoPersona.setColumns(10);

			lblTelefono = new JLabel("Telefono:");
			lblTelefono.setBounds(277, 40, 69, 20);
			panelCita.add(lblTelefono);

			txtTelefono = new JTextField();
			txtTelefono.setBounds(358, 37, 146, 26);
			panelCita.add(txtTelefono);
			txtTelefono.setColumns(10);

			panelPersona = new JPanel();
			panelPersona.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
					"Enfermedades a custodiar", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelPersona.setBounds(15, 194, 231, 158);
			panel.add(panelPersona);
			panelPersona.setLayout(null);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBounds(15, 28, 201, 114);
			panelPersona.add(scrollPane);

			tableEnfCust = new JTable();
			tableEnfCust.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent event) {
					if (tableEnfCust.getSelectedRow() >= 0) {
						btnDer.setEnabled(true);
					} else {
						btnDer.setEnabled(false);
					}
				}
			});
			enfermedadesCustodiarModel = new DefaultTableModel();
			String[] columnNames = { "Código", "Nombre", "Sintomas" };
			enfermedadesCustodiarModel.setColumnIdentifiers(columnNames);
			tableEnfCust.setModel(enfermedadesCustodiarModel);
			scrollPane.setViewportView(tableEnfCust);

			panel_1 = new JPanel();
			panel_1.setLayout(null);
			panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
					"Enfermedades bajo vigilancia:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_1.setBounds(364, 194, 231, 158);
			panel.add(panel_1);

			scrollPane_1 = new JScrollPane();
			scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane_1.setBounds(15, 28, 201, 114);
			panel_1.add(scrollPane_1);

			tableEnfVig = new JTable();
			tableEnfVig.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent event) {
					if (tableEnfVig.getSelectedRow() >= 0) {
						btnIzq.setEnabled(true);
					} else {
						btnIzq.setEnabled(false);
					}
				}
			});
			enfermedadesBajoVigilanciaModel = new DefaultTableModel();
			String[] columnNames2 = { "Código", "Nombre", "Sintomas" };
			enfermedadesBajoVigilanciaModel.setColumnIdentifiers(columnNames2);
			tableEnfVig.setModel(enfermedadesBajoVigilanciaModel);
			scrollPane_1.setViewportView(tableEnfVig);

			btnIzq = new JButton("<");
			btnIzq.setEnabled(false);
			btnIzq.setBounds(255, 227, 97, 25);
			panel.add(btnIzq);

			btnDer = new JButton(">");
			btnDer.setEnabled(false);
			btnDer.setBounds(255, 288, 97, 25);
			panel.add(btnDer);
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

					}
				});
				btnRegistrar.setEnabled(false);
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
		enfermedadesBajoVigilanciaModel.setRowCount(0);
		row = new Object[tableEnfVig.getColumnCount()];

		for (Vacuna v : Clinica.getInstance().getMisVacunas()) {
			row[0] = v.getCodigo();
			row[1] = v.getNombre();
			row[2] = v.getDescripcion();
			enfermedadesBajoVigilanciaModel.addRow(row);
		}
	}

	protected void Cleaner() {
		Clinica.getInstance().codigoMedico++;
		txtNombre.setText("");
		txtCodigoPersona.setText("");
		txtNombre.setText("");
		txtTelefono.setText("");

	}
}
