package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Cita;
import logico.Clinica;
import logico.Medico;
import logico.Secretaria;

public class ListCita extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnCancelar;
	private JPanel panel;
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
	private JButton btnAnterior;
	private JButton btnSiguiente;
	private int index = 0;
	private Cita miCita = null;
	private Object instancia = null;
	private JButton btnRegistrarConsulta;
	private JTextField txtFecha;

	public ListCita(boolean hacerConsulta, Object usuario) {
		instancia = usuario;
		setResizable(false);
		setTitle("Listar Cita");
		setBounds(100, 100, 535, 515);
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
			txtCodigoCita.setBounds(73, 37, 146, 26);
			panelCita.add(txtCodigoCita);
			txtCodigoCita.setEditable(false);
			txtCodigoCita.setColumns(10);

			JLabel lblCodigoDoctor = new JLabel("C\u00F3digo del doctor:");
			lblCodigoDoctor.setBounds(15, 79, 139, 20);
			panelCita.add(lblCodigoDoctor);

			txtCodigoDoctor = new JTextField();
			txtCodigoDoctor.setEnabled(false);
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

			txtFecha = new JTextField();
			txtFecha.setEditable(false);
			txtFecha.setBounds(297, 37, 146, 26);
			panelCita.add(txtFecha);
			txtFecha.setColumns(10);

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
			txtCodigoPersona.setEnabled(false);
			txtCodigoPersona.setColumns(10);
			txtCodigoPersona.setBounds(72, 33, 146, 26);
			panelPersona.add(txtCodigoPersona);

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
			rdbtnTieneSeguro.setBounds(365, 71, 45, 29);
			panelPersona.add(rdbtnTieneSeguro);

			rdbtnNoTieneSeguro = new JRadioButton("No");
			rdbtnNoTieneSeguro.setEnabled(false);
			rdbtnNoTieneSeguro.setBounds(416, 71, 65, 29);
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

			btnAnterior = new JButton("Anterior");
			btnAnterior.setEnabled(false);
			btnSiguiente = new JButton("Siguiente");
			btnSiguiente.setEnabled(false);

			btnAnterior.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (index > 0) {
						index--;
						loadCita();
						if (index == 0)
							btnAnterior.setEnabled(false);

					}
				}
			});
			btnAnterior.setBounds(66, 371, 115, 29);
			panel.add(btnAnterior);
			btnSiguiente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (usuario instanceof Medico) {
						Medico medico = (Medico) usuario;
						if (index < medico.getMisCitas().size()) {
							index++;
							btnAnterior.setEnabled(true);
							loadCita();
						}
					} else {
						Secretaria secretaria = (Secretaria) usuario;
						if (index < secretaria.getMisCitas().size()) {
							index++;
							btnAnterior.setEnabled(true);
							loadCita();
						}
					}

				}
			});
			btnSiguiente.setBounds(342, 371, 115, 29);
			panel.add(btnSiguiente);

			if (hacerConsulta) {
				btnRegistrarConsulta = new JButton("Registrar consulta");
				btnRegistrarConsulta.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RegConsulta regConsulta = new RegConsulta(miCita);
						dispose();
						regConsulta.setModal(true);
						regConsulta.setVisible(true);
					}
				});
				btnRegistrarConsulta.setEnabled(true);
				btnRegistrarConsulta.setBounds(182, 371, 159, 29);
				panel.add(btnRegistrarConsulta);
			}

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
		loadCita();

	}

	public void loadCita() {
		int sizeCitas = 0;
		if (instancia instanceof Medico) {
			Medico medico = (Medico) instancia;
			miCita = medico.getMisCitas().get(index);
			sizeCitas = medico.getMisCitas().size();

		} else if (instancia instanceof Secretaria) {
			Secretaria secretaria = (Secretaria) instancia;
			miCita = secretaria.getMisCitas().get(index);
			sizeCitas = secretaria.getMisCitas().size();
		}

		if (sizeCitas > 0 && index < sizeCitas) {

			txtCodigoCita.setText(miCita.getCodCita());
			txtCodigoDoctor.setText(miCita.getIdMedico());
			txtCodigoPersona.setText(miCita.getPersona().getCodigo());
			txtEdad.setText(Integer.toString(miCita.getPersona().getEdad()));
			txtNombre.setText(miCita.getPersona().getNombre());
			SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
			txtFecha.setText(dateFormat.format(miCita.getFecha()));
			if (miCita.getPersona().getGenero() == 'm')
				rdbtnMasculino.setSelected(true);
			else
				rdbtnFemenino.setSelected(true);

			if (miCita.getPersona().isSeguroMedico())
				rdbtnTieneSeguro.setSelected(true);
			else
				rdbtnNoTieneSeguro.setSelected(true);

			if (index < sizeCitas - 1)
				btnSiguiente.setEnabled(true);
			else
				btnSiguiente.setEnabled(false);

		}
	}
}
