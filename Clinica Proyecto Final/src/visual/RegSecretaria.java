package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Clinica;
import logico.Secretaria;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegSecretaria extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtUsuario;
	private JTextField txtContrasena;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegSecretaria dialog = new RegSecretaria();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegSecretaria() {
		setTitle("Registrar Secretaria");
		setResizable(false);
		setBounds(100, 100, 462, 310);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);

			JPanel panelSecretaria = new JPanel();
			panelSecretaria.setBorder(new TitledBorder(null, "Informaci\u00F3n Secretaria", TitledBorder.LEADING,
					TitledBorder.TOP, null, null));
			panelSecretaria.setBounds(12, 13, 410, 88);
			panel.add(panelSecretaria);
			panelSecretaria.setLayout(null);

			JLabel lblCodigo = new JLabel("C\u00F3digo:");
			lblCodigo.setBounds(12, 33, 56, 16);
			panelSecretaria.add(lblCodigo);

			txtCodigo = new JTextField();
			txtCodigo.setText("Sec - " + 1);
			txtCodigo.setEditable(false);
			txtCodigo.setBounds(68, 30, 116, 22);
			panelSecretaria.add(txtCodigo);
			txtCodigo.setColumns(10);

			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(196, 33, 56, 16);
			panelSecretaria.add(lblNombre);

			txtNombre = new JTextField();
			txtNombre.setBounds(264, 30, 116, 22);
			panelSecretaria.add(txtNombre);
			txtNombre.setColumns(10);

			JPanel panelUsuarioSec = new JPanel();
			panelUsuarioSec.setBorder(
					new TitledBorder(null, "Usuario Secretaria", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelUsuarioSec.setBounds(12, 114, 410, 88);
			panel.add(panelUsuarioSec);
			panelUsuarioSec.setLayout(null);

			JLabel lblUsuario = new JLabel("Usuario:");
			lblUsuario.setBounds(12, 36, 56, 16);
			panelUsuarioSec.add(lblUsuario);

			txtUsuario = new JTextField();
			txtUsuario.setBounds(80, 33, 116, 22);
			panelUsuarioSec.add(txtUsuario);
			txtUsuario.setColumns(10);

			JLabel lblContrasegna = new JLabel("Contrase\u00F1a:");
			lblContrasegna.setBounds(208, 36, 80, 16);
			panelUsuarioSec.add(lblContrasegna);

			txtContrasena = new JTextField();
			txtContrasena.setColumns(10);
			txtContrasena.setBounds(282, 33, 116, 22);
			panelUsuarioSec.add(txtContrasena);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegistrar = new JButton("Registrar");
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Secretaria secretaria = null;
						if (!(txtNombre.getText().isEmpty() || txtContrasena.getText().isEmpty()
								|| txtUsuario.getText().isEmpty())) {
							secretaria = new Secretaria(txtCodigo.getText(), txtNombre.getText(), txtUsuario.getText(),
									txtContrasena.getText());
							Clinica.getInstance().crearSecretaria(secretaria);
							JOptionPane.showMessageDialog(null, "Registro hecho con exito", "Registrado correctamente",
									JOptionPane.INFORMATION_MESSAGE);
							dispose();
						} else {
							JOptionPane.showMessageDialog(null,
									"Por favor, complete todos los campos antes de registrar la Secretaria.",
									"Campos incompletos", JOptionPane.ERROR_MESSAGE);
						}

					}
				});
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
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
	}

	protected void clean() {

	}
}
