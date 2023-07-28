package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Administrador;
import logico.Clinica;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUser;
	private JTextField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				FileInputStream clinica;
				FileOutputStream clinicaCreada;
				ObjectInputStream clinicaRead;
				ObjectOutputStream clinicaWrite;
				try {
					clinica = new FileInputStream("Clinica.dat");
					clinicaRead = new ObjectInputStream(clinica);
					Clinica temp = (Clinica) clinicaRead.readObject();
					Clinica.setMiClinica(temp);
					clinica.close();
					clinicaRead.close();
				} catch (FileNotFoundException e) {
					try {
						clinicaCreada = new FileOutputStream("Clinica.dat");
						clinicaWrite = new ObjectOutputStream(clinicaCreada);
						clinicaWrite.writeObject(Clinica.getInstance());
						clinicaCreada.close();
						clinicaWrite.close();
					} catch (FileNotFoundException e1) {
					} catch (IOException e1) {
						// TODO Auto-generated catch block
					}
				} catch (IOException e) {

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					if (Clinica.getInstance().getMiAdministrador() == null) {
						Administrador aux = new Administrador("Admin", "Admin");
						Clinica.getInstance().crearAdministrador(aux);
						Principal frame = new Principal(null);
						frame.setVisible(true);
					} else {
						login frame = new login();
						frame.setVisible(true);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(39, 39, 105, 14);
		panel.add(lblUsuario);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(39, 98, 105, 14);
		panel.add(lblContrasea);

		txtUser = new JTextField();
		txtUser.setBounds(39, 64, 191, 20);
		panel.add(txtUser);
		txtUser.setColumns(10);

		txtPassword = new JTextField();
		txtPassword.setBounds(39, 128, 191, 20);
		panel.add(txtPassword);
		txtPassword.setColumns(10);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object aux = null;
					if ((aux = Clinica.getInstance().loginTipo(txtUser.getText(), txtPassword.getText())) != null) {
						Principal frame = new Principal(aux);
						dispose();
						frame.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null,
								"Usuario o contraseña inconrrecta, favor de revisar!",
								"Usuario no encontrado", JOptionPane.ERROR_MESSAGE);
						txtPassword.setText("");
						txtUser.setText("");
					}
				;

			}
		});
		btnLogin.setBounds(37, 175, 89, 23);
		panel.add(btnLogin);
	}

}
