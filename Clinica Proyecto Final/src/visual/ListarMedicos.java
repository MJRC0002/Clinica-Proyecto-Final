package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Cita;
import logico.Clinica;
import logico.Enfermedad;
import logico.Medico;
import logico.Paciente;
import logico.Vacuna;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ScrollPaneConstants;

public class ListarMedicos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtEspecializacion;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JButton btnAnterior;
	private JTable tablePacientes;
	private DefaultTableModel pacienteModel;
	private DefaultTableModel enfermedadModel;
	private DefaultTableModel citaModel;
	private Object[] rowPaciente;
	private Object[] rowCita;
	private Object[] rowEnfermedad;
	private Medico miMedico = null;
	private int index = 0;
	private JButton btnSiguiente;
	private JTable tableEnfermedades;
	private JTable tableCitas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarMedicos dialog = new ListarMedicos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarMedicos() {
		setTitle("Listado de m\u00E9dicos");
		setBounds(100, 100, 905, 572);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);

			JLabel lblCodigo = new JLabel("C\u00F3digo:");
			lblCodigo.setBounds(15, 50, 83, 29);
			panel.add(lblCodigo);

			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(15, 95, 83, 29);
			panel.add(lblNombre);

			JLabel lblTelefono = new JLabel("Telefono:");
			lblTelefono.setBounds(15, 183, 83, 29);
			panel.add(lblTelefono);

			JLabel lblEspecializacion = new JLabel("Especializaci\u00F3n:");
			lblEspecializacion.setBounds(15, 140, 115, 29);
			panel.add(lblEspecializacion);

			txtCodigo = new JTextField();
			txtCodigo.setEditable(false);
			txtCodigo.setBounds(73, 51, 146, 26);
			panel.add(txtCodigo);
			txtCodigo.setColumns(10);

			txtEspecializacion = new JTextField();
			txtEspecializacion.setEditable(false);
			txtEspecializacion.setColumns(10);
			txtEspecializacion.setBounds(125, 141, 146, 26);
			panel.add(txtEspecializacion);

			txtNombre = new JTextField();
			txtNombre.setEditable(false);
			txtNombre.setColumns(10);
			txtNombre.setBounds(83, 96, 146, 26);
			panel.add(txtNombre);

			txtTelefono = new JTextField();
			txtTelefono.setEditable(false);
			txtTelefono.setColumns(10);
			txtTelefono.setBounds(93, 183, 193, 26);
			panel.add(txtTelefono);

			JLabel lblDatosDelMedico = new JLabel("Datos del m\u00E9dico:");
			lblDatosDelMedico.setBounds(36, 16, 137, 20);
			panel.add(lblDatosDelMedico);

			btnAnterior = new JButton("Anterior");
			btnAnterior.setEnabled(false);
			btnSiguiente = new JButton("Siguiente");
			btnSiguiente.setEnabled(false);

			btnAnterior.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (index > 0) {
						index--;
						loadMedico();
						if (index == 0)
							btnAnterior.setEnabled(false);
						btnSiguiente.setEnabled(true);

					}
				}
			});
			btnAnterior.setBounds(267, 422, 115, 29);
			panel.add(btnAnterior);
			btnSiguiente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (index < Clinica.getInstance().getMisMedicos().size()) {
						index++;
						btnAnterior.setEnabled(true);
						if (index >= Clinica.getInstance().getMisMedicos().size())
							btnSiguiente.setEnabled(false);
						loadMedico();
					}
				}
			});
			btnSiguiente.setBounds(469, 422, 115, 29);
			panel.add(btnSiguiente);

			JPanel panelPaciente = new JPanel();
			panelPaciente.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Pacientes", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelPaciente.setBounds(15, 247, 430, 159);
			panel.add(panelPaciente);
			panelPaciente.setLayout(new BorderLayout(0, 0));

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			panelPaciente.add(scrollPane, BorderLayout.CENTER);

			tablePacientes = new JTable();
			pacienteModel = new DefaultTableModel();
			String letrero[] = { "Codigo", "Nombre", "Edad", "Seguro Medico", "Genero"};
			pacienteModel.setColumnIdentifiers(letrero);
			tablePacientes.setModel(pacienteModel);
			scrollPane.setViewportView(tablePacientes);
			
			JPanel panelEnfermedad = new JPanel();
			panelEnfermedad.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Enfermedades bajo vigilancia", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelEnfermedad.setBounds(321, 16, 471, 159);
			panel.add(panelEnfermedad);
			panelEnfermedad.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			panelEnfermedad.add(scrollPane_1, BorderLayout.CENTER);
			
			tableEnfermedades = new JTable();
			enfermedadModel = new DefaultTableModel();
			String[] headers = { "Código", "Nombre", "Sintomas", "Medicamentos", "Vacuna" };
			enfermedadModel.setColumnIdentifiers(headers);
			tableEnfermedades.setModel(enfermedadModel);
			scrollPane_1.setViewportView(tableEnfermedades);
			
			JPanel panelCitas = new JPanel();
			panelCitas.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Citas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelCitas.setBounds(507, 247, 285, 159);
			panel.add(panelCitas);
			panelCitas.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane_2 = new JScrollPane();
			scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			panelCitas.add(scrollPane_2, BorderLayout.CENTER);
			
			tableCitas = new JTable();
			citaModel = new DefaultTableModel();
			String[] cabeza = { "Código", "Persona", "Fecha"};
			citaModel.setColumnIdentifiers(cabeza);
			tableCitas.setModel(citaModel);
			scrollPane_2.setViewportView(tableCitas);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnCerrar = new JButton("Cerrar");
				btnCerrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCerrar.setActionCommand("Cancel");
				buttonPane.add(btnCerrar);
			}
		}
		loadMedico();
	}

	public void loadMedico() {
		if (Clinica.getInstance().getMisMedicos().size() > 0) {
			miMedico = Clinica.getInstance().getMisMedicos().get(index);
			txtCodigo.setText(miMedico.getCodigo());
			txtNombre.setText(miMedico.getNombre());
			txtEspecializacion.setText(miMedico.getEspecializacion());
			txtTelefono.setText(miMedico.getTelefono());
			
			tablesUpdate();
			if (Clinica.getInstance().getMisMedicos().size() > 1) 
				btnSiguiente.setEnabled(true);
			

		}
	}

	public void tablesUpdate() {
		pacienteModel.setRowCount(0);
		enfermedadModel.setRowCount(0);
		citaModel.setRowCount(0);
		
		rowPaciente = new Object[tablePacientes.getColumnCount()];
		rowEnfermedad = new Object[tableEnfermedades.getColumnCount()];
		rowCita = new Object[tableCitas.getColumnCount()];

		for (Paciente paciente : miMedico.getMisPacientes()) {
			rowPaciente[0] = paciente.getCodigo();
			rowPaciente[1] = paciente.getNombre();
			rowPaciente[2] = Integer.toString(paciente.getEdad());
			if(paciente.isSeguroMedico())
				rowPaciente[3] = "Si";
			else
				rowPaciente[3] = "No";
			rowPaciente[4] = paciente.getGenero();
			pacienteModel.addRow(rowPaciente);
		}
		
		for (Enfermedad enfermedad : miMedico.getMisEnfermedades()) {
			rowEnfermedad[0] = enfermedad.getCodigo();
			rowEnfermedad[1] = enfermedad.getNombre();
			rowEnfermedad[2] = enfermedad.getSintomas();
			rowEnfermedad[3] = enfermedad.getMedicamentos();
			rowEnfermedad[4] = dameMisVacunasEfectivas(enfermedad.getVacunasEfectivas());
			enfermedadModel.addRow(rowEnfermedad);
		}
		for (Cita cita : miMedico.getMisCitas()) {
			
			rowCita[0] = cita.getCodCita();
			rowCita[1] = cita.getPersona().getCodigo();
			SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
			rowCita[2] = dateFormat.format(cita.getFecha());

			
			citaModel.addRow(rowCita);
		}


	}
	
	public static String dameMisVacunasEfectivas(ArrayList<Vacuna> vacunasEfectivas) {
		StringBuilder aux = new StringBuilder();
		for (Vacuna vacuna : vacunasEfectivas) {
			aux.append(vacuna.getNombre()).append(", ");
		}
		if (aux.length() > 0) {
			aux.delete(aux.length() - 2, aux.length());
		}
		return aux.toString();
	}

}
