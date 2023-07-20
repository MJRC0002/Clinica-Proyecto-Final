package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	private JPanel contentPane;
	private Dimension dim;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setTitle("Clinica");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 647, 442);
		setLocationRelativeTo(null);
		dim = super.getToolkit().getScreenSize();
		// super.setSize(dim.width, dim.height - 100);
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
		mntmNewMenuItem.setEnabled(false);
		mnMedico.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Listar M\u00E9dico");
		mnMedico.add(mntmNewMenuItem_1);

		JMenu mnConsulta = new JMenu("Consulta");
		menuBar.add(mnConsulta);

		JMenuItem mntmRegistrarConsulta = new JMenuItem("Registrar consulta");
		mnConsulta.add(mntmRegistrarConsulta);

		JMenuItem mntmListarConsulta = new JMenuItem("Listar consulta");
		mnConsulta.add(mntmListarConsulta);

		JMenu mnCita = new JMenu("Cita");
		menuBar.add(mnCita);

		JMenuItem mntmRegistrarCita = new JMenuItem("Registrar cita");
		mntmRegistrarCita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegCita cita = new RegCita();
				cita.setModal(true);
				cita.setVisible(true);
			}
		});
		mnCita.add(mntmRegistrarCita);

		JMenuItem mntmListarCita = new JMenuItem("Listar cita");
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
		mnSecretaria.add(mntmRegistrarSecretaria);

		JMenuItem mntmListarSecretaria = new JMenuItem("Listar secretaria");
		mnSecretaria.add(mntmListarSecretaria);

		JMenu mnAdministrador = new JMenu("Administrador");
		menuBar.add(mnAdministrador);

		JMenu mnRespaldo = new JMenu("Respaldo");
		menuBar.add(mnRespaldo);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
