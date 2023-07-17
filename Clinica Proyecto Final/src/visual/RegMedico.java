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

import logico.Cita;
import logico.Clinica;
import logico.Medico;
import logico.Persona;
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
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegCita dialog = new RegCita();
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
		setBounds(100, 100, 535, 462);
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
			panelCita.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informaci\u00F3n de m\u00E9dico", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelCita.setBounds(15, 16, 489, 142);
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
						lblEspecializacion.setBounds(249, 79, 121, 20);
						panelCita.add(lblEspecializacion);
						
									txtCodigoPersona = new JTextField();
									txtCodigoPersona.setBounds(244, 100, 210, 26);
									panelCita.add(txtCodigoPersona);
									txtCodigoPersona.setColumns(10);
									
												lblTelefono = new JLabel("Telefono:");
												lblTelefono.setBounds(239, 37, 69, 20);
												panelCita.add(lblTelefono);
												
															txtTelefono = new JTextField();
															txtTelefono.setBounds(308, 37, 146, 26);
															panelCita.add(txtTelefono);
															txtTelefono.setColumns(10);

			panelPersona = new JPanel();
			panelPersona.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Enfermedades a custodiar", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelPersona.setBounds(15, 194, 231, 158);
			panel.add(panelPersona);
			panelPersona.setLayout(null);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBounds(15, 28, 201, 114);
			panelPersona.add(scrollPane);
			
			table = new JTable();
			scrollPane.setViewportView(table);
			
			panel_1 = new JPanel();
			panel_1.setLayout(null);
			panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Enfermedades bajo vigilancia:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_1.setBounds(273, 194, 231, 158);
			panel.add(panel_1);
			
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane_1.setBounds(15, 28, 201, 114);
			panel_1.add(scrollPane_1);
			
			table_1 = new JTable();
			scrollPane_1.setViewportView(table_1);
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
	}

	protected void Cleaner() {
		Clinica.getInstance().codigoMedico++;
		txtNombre.setText("");
		txtCodigoPersona.setText("");
		txtNombre.setText("");
		txtTelefono.setText("");
		
	}
}
