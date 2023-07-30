package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.sun.org.apache.xml.internal.serializer.ElemDesc;

import java.util.Calendar;
import java.util.Date;
import logico.Administrador;
import logico.Clinica;
import logico.Consulta;
import logico.Enfermedad;
import logico.Medico;
import logico.Paciente;
import logico.Secretaria;
import logico.Vacuna;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Dimension dim;
	private JPanel panel;

	private class ActualizarGraficosWorker extends Thread {
		@Override
		public void run() {

			while (true) {
				SwingUtilities.invokeLater(() -> actualizarGraficos());
				try {
					Thread.sleep(5000); // Pausa de 5 segundos antes de actualizar nuevamente
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		private void actualizarGraficos() {
			// Primera GRafica.
			DefaultPieDataset data = new DefaultPieDataset();
			for (Enfermedad enfermedad : Clinica.getInstance().getMisEnfermedades()) {
				data.setValue(enfermedad.getNombre(),
						Clinica.getInstance().porcentajeEnfermedad(enfermedad.getCodigo()));
			}
			JFreeChart chart = ChartFactory.createPieChart("Porcentaje de enfermedades en la Clinica", data, true, true,
					false);
			ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setPreferredSize(new Dimension(panel.getWidth(), 500));

			// Segunda grafica.
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();

			dataset.setValue(0, "Mujeres", "Lunes");
			dataset.setValue(0, "Hombres", "Lunes");
			dataset.setValue(0, "Mujeres", "Martes");
			dataset.setValue(0, "Hombres", "Martes");
			dataset.setValue(0, "Mujeres", "Miércoles");
			dataset.setValue(0, "Hombres", "Miércoles");
			dataset.setValue(0, "Mujeres", "Jueves");
			dataset.setValue(0, "Hombres", "Jueves");
			dataset.setValue(0, "Mujeres", "Viernes");
			dataset.setValue(0, "Hombres", "Viernes");
			dataset.setValue(0, "Mujeres", "Sábado");
			dataset.setValue(0, "Hombres", "Sábado");
			dataset.setValue(0, "Mujeres", "Domingo");
			dataset.setValue(0, "Hombres", "Domingo");
			Calendar calendar = Calendar.getInstance();
			for (Consulta consulta : Clinica.getInstance().ConsultasDeLaSemana()) {
				calendar.setTime(consulta.getFecha());
				if (consulta.getPaciente().getGenero() == 'm')
					dataset.incrementValue(1, "Hombres", obtenerNombreDia(calendar.get(Calendar.DAY_OF_WEEK)));
				else
					dataset.incrementValue(1, "Mujeres", obtenerNombreDia(calendar.get(Calendar.DAY_OF_WEEK)));

			}

			JFreeChart chart2 = ChartFactory.createBarChart3D("Cantidad de pacientes por Género Atendidos por Día",
					"Días", "Cantidad", dataset, PlotOrientation.VERTICAL, true, true, false);
			chart2.setBackgroundPaint(Color.cyan);
			chart2.getTitle().setPaint(Color.black);
			CategoryPlot p = chart2.getCategoryPlot();
			p.setRangeGridlinePaint(Color.red);

			// tercera grafica
			DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
			for (Vacuna vacuna : Clinica.getInstance().getMisVacunas()) {
				line_chart_dataset.addValue(Clinica.getInstance().porcentajeVacunado(vacuna.getCodigo()), "Vacunados",
						vacuna.getNombre());
			}
			JFreeChart vacunadoChart = ChartFactory.createLineChart("Porcentaje Vacunado", "Mes", "Porciento",
					line_chart_dataset, PlotOrientation.VERTICAL, true, true, false);

			JPanel chartPanel2 = new JPanel();
			chartPanel2.setPreferredSize(new Dimension(panel.getWidth(), 500));
			chartPanel2.setLayout(new BorderLayout());
			chartPanel2.add(new ChartPanel(chart2), BorderLayout.CENTER);

			JPanel vacunChartPanel = new JPanel();
			vacunChartPanel.setLayout(new BorderLayout());
			vacunChartPanel.add(new ChartPanel(vacunadoChart), BorderLayout.CENTER);

			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			panel.add(chartPanel);
			panel.add(chartPanel2);
			panel.add(vacunChartPanel);
			// Asegúrate de que las actualizaciones se realicen en el hilo de despacho de
			// eventos (Event Dispatch Thread)
			SwingUtilities.invokeLater(() -> {
				panel.removeAll(); // Limpia el panel antes de añadir los nuevos gráficos
				panel.add(chartPanel);
				panel.add(chartPanel2);
				panel.add(vacunChartPanel);
				panel.revalidate();
				panel.repaint();
			});
		}
	}

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
					e1.printStackTrace();
				} catch (IOException e1) {
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
				if (usuario instanceof Medico && ((Medico) usuario).getMisCitas().size() == 0)
					JOptionPane.showMessageDialog(null,
							"Usted no puede registrar una consulta porque no tiene citas disponibles.",
							"No tiene citas", JOptionPane.ERROR_MESSAGE);
				else {
					ListCita cita = new ListCita(true, usuario);
					cita.setModal(true);
					cita.setVisible(true);
				}

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
				ListCita cita = new ListCita(false, usuario);
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
				ListVacuna vacuna = new ListVacuna(null, null);
				vacuna.setModal(true);
				vacuna.setVisible(true);
			}
		});
		mnVacuna.add(mntmListarVacuna);
		
		JMenuItem mntmAplicarVacuna = new JMenuItem("Aplicar vacuna");
		mntmAplicarVacuna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Medico medico = (Medico) usuario;
				AplicarVacuna aplicarVacuna = new AplicarVacuna(Clinica.getInstance().pacientesEnfermosConVacunasDisponibles(medico.getMisConsultas()));
				aplicarVacuna.setModal(true);
				aplicarVacuna.setVisible(true);
			}
		});
		mnVacuna.add(mntmAplicarVacuna);

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

		JMenu mnRespaldo = new JMenu("Respaldo");
		menuBar.add(mnRespaldo);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Respaldar");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Socket sfd = null;
				try {
					sfd = new Socket("localhost", 8000);
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

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane, BorderLayout.CENTER);

		// usuario instanceof
		if (usuario instanceof Administrador) {
			mnConsulta.setEnabled(false);
			mnCita.setEnabled(false);
			// Inicia el hilo solo para el usuario Administrador
			ActualizarGraficosWorker worker = new ActualizarGraficosWorker();
			worker.start();
		} else if (usuario instanceof Medico) {
			mnCita.setEnabled(false);
			mnMedico.setEnabled(false);
			mnSecretaria.setEnabled(false);
			mnRespaldo.setEnabled(false);
			mntmRegistrarEnfermedad.setEnabled(false);
			mntmRegistrarVacuna.setEnabled(false);
			
		} else if (usuario instanceof Secretaria) {
			mnConsulta.setEnabled(false);
			mnRespaldo.setEnabled(false);
			mnMedico.setEnabled(false);
			mnSecretaria.setEnabled(false);
			mnEnfermedad.setEnabled(false);
			mnVacuna.setEnabled(false);
		}

		JPanel buttonPane = new JPanel();
		buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			JButton btnMes = new JButton("Mes");
			buttonPane.add(btnMes);
		}

	}

	private static String obtenerNombreDia(int diaDeLaSemana) {
		String[] nombresDias = { "Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado" };
		return nombresDias[diaDeLaSemana - 1];
	}

}
