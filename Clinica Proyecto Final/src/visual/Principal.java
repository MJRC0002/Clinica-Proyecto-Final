package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Clinica;
import logico.Secretaria;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Dimension dim;

	/**
	 * Create the frame.
	 */
	public Principal(Object usuario) {
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				FileOutputStream clinica;
				ObjectOutputStream clincaWrite;
				try {
					clinica = new FileOutputStream("clinica.dat");
					clincaWrite = new ObjectOutputStream(clinica);
					clincaWrite.writeObject(Clinica.getInstance());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		setTitle("Clinica");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 647, 442);
		setLocationRelativeTo(null);
		dim = super.getToolkit().getScreenSize();
		super.setSize(dim.width, dim.height - 100);
		setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnMedico = new JMenu("M\u00E9dico");
		menuBar.add(mnMedico);

		JMenuItem mntmNewMenuItem = new JMenuItem("Registrar m\u00E9dico");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegMedico medico = new RegMedico();
				medico.setModal(true);
				medico.setVisible(true);
			}
		});
		mnMedico.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Listar M\u00E9dico");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarMedicos medicos = new ListarMedicos();
				medicos.setModal(true);
				medicos.setVisible(true);
			}
		});
		mnMedico.add(mntmNewMenuItem_1);

		JMenu mnConsulta = new JMenu("Consulta");
		menuBar.add(mnConsulta);

		JMenuItem mntmRegistrarConsulta = new JMenuItem("Registrar consulta");
		mntmRegistrarConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegConsulta consulta = new RegConsulta(null);
				consulta.setModal(true);
				consulta.setVisible(true);
			}
		});
		mnConsulta.add(mntmRegistrarConsulta);

		JMenuItem mntmListarConsulta = new JMenuItem("Listar consulta");
		mntmListarConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListConsulta consulta = new ListConsulta();
				consulta.setModal(true);
				consulta.setVisible(true);
			}
		});
		mnConsulta.add(mntmListarConsulta);

		JMenu mnCita = new JMenu("Cita");
		menuBar.add(mnCita);

		JMenuItem mntmRegistrarCita = new JMenuItem("Registrar cita");
		mntmRegistrarCita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Clinica.getInstance().getMiSecretaria() != null) {
					RegCita cita = new RegCita();
					cita.setModal(true);
					cita.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Agregrar una secretaria antes de registrar una cita",
							"¡No hay secretaria!", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		mnCita.add(mntmRegistrarCita);

		JMenuItem mntmListarCita = new JMenuItem("Listar cita");
		mntmListarCita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListCita cita = new ListCita();
				cita.setModal(true);
				cita.setVisible(true);
			}
		});
		mnCita.add(mntmListarCita);

		JMenu mnEnfermedad = new JMenu("Enfermedad");
		menuBar.add(mnEnfermedad);

		JMenuItem mntmRegistrarEnfermedad = new JMenuItem("Registrar enfermedad");
		mntmRegistrarEnfermedad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegEnfermedad enfermedad = new RegEnfermedad(null, 0);
				enfermedad.setModal(true);
				enfermedad.setVisible(true);
			}
		});
		mnEnfermedad.add(mntmRegistrarEnfermedad);

		JMenuItem mntmListarEnfermedad = new JMenuItem("Listar enfermedad");
		mntmListarEnfermedad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListEnfermedad enfermedad = new ListEnfermedad();
				enfermedad.setModal(true);
				enfermedad.setVisible(true);
			}
		});
		mnEnfermedad.add(mntmListarEnfermedad);

		JMenu mnVacuna = new JMenu("Vacuna");
		menuBar.add(mnVacuna);

		JMenuItem mntmRegistrarVacuna = new JMenuItem("Registrar vacuna");
		mntmRegistrarVacuna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegVacuna vacuna = new RegVacuna();
				vacuna.setModal(true);
				vacuna.setVisible(true);
			}
		});
		mnVacuna.add(mntmRegistrarVacuna);

		JMenuItem mntmListarVacuna = new JMenuItem("Listar vacuna");
		mntmListarVacuna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListVacuna vacuna = new ListVacuna();
				vacuna.setModal(true);
				vacuna.setVisible(true);
			}
		});
		mnVacuna.add(mntmListarVacuna);

		JMenu mnSecretaria = new JMenu("Secretaria");
		menuBar.add(mnSecretaria);

		JMenuItem mntmRegistrarSecretaria = new JMenuItem("Registrar secretaria");
		mntmRegistrarSecretaria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Clinica.getInstance().getMiSecretaria() == null) {
					RegSecretaria secretaria = new RegSecretaria();
					secretaria.setModal(true);
					secretaria.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null,
							"Puesto de secretaria ocupado, no es posible ingresar una nueva", "¡Puesto ocupado!",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnSecretaria.add(mntmRegistrarSecretaria);

		JMenuItem mntmListarSecretaria = new JMenuItem("Listar secretaria");
		mntmListarSecretaria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListSecretaria secretaria = new ListSecretaria();
				secretaria.setModal(true);
				secretaria.setVisible(true);
			}
		});
		mnSecretaria.add(mntmListarSecretaria);

		JMenu mnAdministrador = new JMenu("Administrador");
		menuBar.add(mnAdministrador);

		JMenu mnRespaldo = new JMenu("Respaldo");
		menuBar.add(mnRespaldo);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Respaldar");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Socket sfd = null;
				try {
					sfd = new Socket("localhost", 8000);
					DataInputStream EntradaSocket = new DataInputStream(new BufferedInputStream(sfd.getInputStream()));
					DataOutputStream SalidaSocket = new DataOutputStream(
							new BufferedOutputStream(sfd.getOutputStream()));

					try (FileInputStream fis = new FileInputStream("Clinica.dat")) {
						byte[] buffer = new byte[4096];
						int bytesRead;
						while ((bytesRead = fis.read(buffer)) != -1) {
							SalidaSocket.write(buffer, 0, bytesRead);
						}
						SalidaSocket.flush();
						System.out.println("Respaldo enviado correctamente.");
					} catch (IOException eo) {
						System.out.println("Error al enviar el respaldo: " + eo.getMessage());
					}
				} catch (UnknownHostException uhe) {
					System.out.println("No se puede acceder al servidor.");
				} catch (IOException ioe) {
					System.out.println("Comunicación rechazada.");
				} finally {
					try {
						if (sfd != null)
							sfd.close();
					} catch (IOException eo) {
						eo.printStackTrace();
					}
				}
			}
		});
		mnRespaldo.add(mntmNewMenuItem_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
