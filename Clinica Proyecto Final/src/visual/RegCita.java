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
import logico.Secretaria;
import sun.misc.Cleaner;

import javax.swing.JRadioButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RegCita extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnRegistrar;
	private JButton btnCancelar;
	private JPanel panel;
	private JSpinner spnFecha;
	private JTextField txtCodigoCita;
	private JTextField txtCodigoDoctor;
	private JTextField txtNombre;
	private JLabel lblEdad;
	private JTextField txtEdad;
	private JLabel lblSeguroMedico;
	private JPanel panelPersona;
	private JTextField txtCodigoPersona;
	private JRadioButton rdbtnTieneSeguro;
	private JRadioButton rdbtnNoTieneSeguro;
	private JLabel lblGenero;
	private JRadioButton rdbtnMasculino;
	private JRadioButton rdbtnFemenino;

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
	public RegCita() {
		setResizable(false);
		setTitle("Registrar Cita");
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
			panelCita.setBorder(new TitledBorder(null, "Informaci\u00F3n de cita:", TitledBorder.LEADING,
					TitledBorder.TOP, null, null));
			panelCita.setBounds(15, 16, 489, 142);
			panel.add(panelCita);
			panelCita.setLayout(null);

			JLabel lblCodigoCita = new JLabel("C\u00F3digo:");
			lblCodigoCita.setBounds(15, 40, 69, 20);
			panelCita.add(lblCodigoCita);

			txtCodigoCita = new JTextField();
			txtCodigoCita.setText("Cita - " + Clinica.codigoCita);
			txtCodigoCita.setBounds(73, 37, 146, 26);
			panelCita.add(txtCodigoCita);
			txtCodigoCita.setEditable(false);
			txtCodigoCita.setColumns(10);

			JLabel lblCodigoDoctor = new JLabel("C\u00F3digo del doctor:");
			lblCodigoDoctor.setBounds(15, 79, 139, 20);
			panelCita.add(lblCodigoDoctor);

			txtCodigoDoctor = new JTextField();
			txtCodigoDoctor.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char key = e.getKeyChar();
					if (!Character.isDigit(key))
						e.consume();
				}
			});
			txtCodigoDoctor.setBounds(151, 79, 146, 26);
			panelCita.add(txtCodigoDoctor);
			txtCodigoDoctor.setColumns(10);

			JLabel lblFecha = new JLabel("Fecha:");
			lblFecha.setBounds(244, 40, 53, 20);
			panelCita.add(lblFecha);

			spnFecha = new JSpinner();
			spnFecha.setBounds(294, 37, 155, 26);
			panelCita.add(spnFecha);
			spnFecha.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_YEAR));

			JButton btnBuscar = new JButton("Buscar");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Medico medico = Clinica.getInstance().buscarMedicoByCode("Médico - "+txtCodigoDoctor.getText());
					if (medico != null) {
						txtCodigoPersona.setEnabled(true);
						txtEdad.setEnabled(true);
						txtNombre.setEnabled(true);
						rdbtnMasculino.setEnabled(true);
						rdbtnFemenino.setEnabled(true);
						rdbtnNoTieneSeguro.setEnabled(true);
						rdbtnTieneSeguro.setEnabled(true);
					}
				}
			});
			btnBuscar.setBounds(304, 78, 115, 29);
			panelCita.add(btnBuscar);

			panelPersona = new JPanel();
			panelPersona.setBorder(new TitledBorder(null, "Informaci\u00F3n de persona:", TitledBorder.LEADING,
					TitledBorder.TOP, null, null));
			panelPersona.setBounds(15, 194, 489, 158);
			panel.add(panelPersona);
			panelPersona.setLayout(null);

			JLabel lblCodigoPersona = new JLabel("C\u00F3digo:");
			lblCodigoPersona.setBounds(15, 36, 57, 20);
			panelPersona.add(lblCodigoPersona);

			txtCodigoPersona = new JTextField();
			txtCodigoPersona.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char key = e.getKeyChar();
					if (!Character.isDigit(key))
						e.consume();
				}
			});
			txtCodigoPersona.setEnabled(false);
			txtCodigoPersona.setColumns(10);
			txtCodigoPersona.setBounds(72, 33, 146, 26);
			panelPersona.add(txtCodigoPersona);

			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(15, 75, 76, 20);
			panelPersona.add(lblNombre);

			txtNombre = new JTextField();
			txtNombre.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char key = e.getKeyChar();
					if (!Character.isAlphabetic(key))
						e.consume();
				}
			});
			txtNombre.setEnabled(false);
			txtNombre.setColumns(10);
			txtNombre.setBounds(83, 72, 146, 26);
			panelPersona.add(txtNombre);

			lblEdad = new JLabel("Edad:");
			lblEdad.setBounds(255, 36, 57, 20);
			panelPersona.add(lblEdad);

			txtEdad = new JTextField();
			txtEdad.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char key = e.getKeyChar();
					if (!Character.isDigit(key))
						e.consume();
				}
			});
			txtEdad.setEnabled(false);
			txtEdad.setColumns(10);
			txtEdad.setBounds(304, 33, 146, 26);
			panelPersona.add(txtEdad);

			lblSeguroMedico = new JLabel("Seguro M\u00E9dico:");
			lblSeguroMedico.setBounds(255, 75, 117, 20);
			panelPersona.add(lblSeguroMedico);

			rdbtnTieneSeguro = new JRadioButton("si");
			rdbtnTieneSeguro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnTieneSeguro.setSelected(true);
					rdbtnNoTieneSeguro.setSelected(false);
				}
			});
			rdbtnTieneSeguro.setEnabled(false);
			rdbtnTieneSeguro.setBounds(365, 71, 57, 29);
			panelPersona.add(rdbtnTieneSeguro);

			rdbtnNoTieneSeguro = new JRadioButton("No");
			rdbtnNoTieneSeguro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnTieneSeguro.setSelected(false);
					rdbtnNoTieneSeguro.setSelected(true);
				}
			});
			rdbtnNoTieneSeguro.setEnabled(false);
			rdbtnNoTieneSeguro.setBounds(424, 71, 65, 29);
			panelPersona.add(rdbtnNoTieneSeguro);

			lblGenero = new JLabel("G\u00E9nero:");
			lblGenero.setBounds(15, 121, 86, 20);
			panelPersona.add(lblGenero);

			rdbtnMasculino = new JRadioButton("M");
			rdbtnMasculino.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnFemenino.setSelected(false);
					rdbtnMasculino.setSelected(true);
					btnRegistrar.setEnabled(true);
				}
			});
			rdbtnMasculino.setEnabled(false);
			rdbtnMasculino.setBounds(94, 117, 57, 29);
			panelPersona.add(rdbtnMasculino);

			rdbtnFemenino = new JRadioButton("F");
			rdbtnFemenino.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnFemenino.setSelected(true);
					rdbtnMasculino.setSelected(false);
					btnRegistrar.setEnabled(true);
				}
			});
			rdbtnFemenino.setEnabled(false);
			rdbtnFemenino.setBounds(153, 117, 65, 29);
			panelPersona.add(rdbtnFemenino);
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
						if(Clinica.getInstance().getMiSecretaria() != null) {
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
							
							Persona persona = new Persona(txtCodigoPersona.getText(), txtNombre.getText(),
									Integer.parseInt(txtEdad.getText()), tieneSeguro, genero);
							
							Cita cita = new Cita(txtCodigoCita.getText(), txtCodigoDoctor.getText(), persona, (Date) spnFecha.getValue());
							Clinica.getInstance().getMiSecretaria().getMisCitas().add(cita);
							Cleaner();
						}
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
		Clinica.codigoCita++;
		txtCodigoDoctor.setText("");
		txtCodigoCita.setText("Cita - " + Clinica.codigoCita);
		txtCodigoPersona.setText("");
		txtNombre.setText("");
		txtEdad.setText("");
		spnFecha.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_YEAR));
		rdbtnNoTieneSeguro.setSelected(false);
		rdbtnTieneSeguro.setSelected(false);
		rdbtnFemenino.setSelected(false);
		rdbtnMasculino.setSelected(false);
	}
}
