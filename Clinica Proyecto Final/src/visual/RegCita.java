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

public class RegCita extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnRegistrar;
	private JButton btnCancelar;
	private JPanel panel;
	private JSpinner spnFecha;
	private JTextField txtCodigoCita;
	private JTextField txtCodigoPersona;
	private JTextField txtCodigoDoctor;

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
		setBounds(100, 100, 535, 437);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblCodigoCita = new JLabel("Codigo:");
			lblCodigoCita.setBounds(15, 28, 69, 20);
			panel.add(lblCodigoCita);
			
			JLabel lblCodigoPersona = new JLabel("Codigo de la persona:");
			lblCodigoPersona.setBounds(15, 142, 166, 20);
			panel.add(lblCodigoPersona);
			
			JLabel lblCodigoDoctor = new JLabel("Codigo del doctor:");
			lblCodigoDoctor.setBounds(15, 108, 139, 20);
			panel.add(lblCodigoDoctor);
			
			JLabel lblFecha = new JLabel("Fecha:");
			lblFecha.setBounds(15, 66, 53, 20);
			panel.add(lblFecha);
			
			spnFecha = new JSpinner();
			spnFecha.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_YEAR));
			spnFecha.setBounds(67, 63, 139, 26);
			panel.add(spnFecha);
			
			txtCodigoCita = new JTextField();
			txtCodigoCita.setEditable(false);
			txtCodigoCita.setBounds(73, 25, 146, 26);
			panel.add(txtCodigoCita);
			txtCodigoCita.setColumns(10);
			
			txtCodigoPersona = new JTextField();
			txtCodigoPersona.setBounds(182, 139, 146, 26);
			panel.add(txtCodigoPersona);
			txtCodigoPersona.setColumns(10);
			
			txtCodigoDoctor = new JTextField();
			txtCodigoDoctor.setColumns(10);
			txtCodigoDoctor.setBounds(169, 105, 146, 26);
			panel.add(txtCodigoDoctor);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRegistrar = new JButton("Registrar");
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
}
