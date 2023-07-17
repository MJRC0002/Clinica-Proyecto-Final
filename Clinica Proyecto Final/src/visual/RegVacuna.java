package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

import logico.Clinica;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class RegVacuna extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JButton btnRegistrar;
	private JButton btnCancelar;
	private JTextField txtDescripción;
	private JTable tableSelecionadas;
	private JTable tableRegistradas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegVacuna dialog = new RegVacuna();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegVacuna() {
		setTitle("Registrar Vacuna");
		setResizable(false);
		setBounds(100, 100, 765, 440);
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
			
			JLabel lblCodigo = new JLabel("Codigo:");
			lblCodigo.setBounds(12, 13, 56, 22);
			panel.add(lblCodigo);
			
			txtCodigo = new JTextField();
			txtCodigo.setEditable(false);
			txtCodigo.setText("Vac - " + Clinica.getInstance().codigoVacuna);
			txtCodigo.setBounds(94, 13, 116, 22);
			panel.add(txtCodigo);
			txtCodigo.setColumns(10);
			
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(12, 48, 56, 22);
			panel.add(lblNombre);
			
			txtNombre = new JTextField();
			txtNombre.setBounds(94, 48, 116, 22);
			panel.add(txtNombre);
			txtNombre.setColumns(10);
			
			JLabel lblDescripción = new JLabel("Descripci\u00F3n:");
			lblDescripción.setBounds(12, 83, 81, 22);
			panel.add(lblDescripción);
			
			txtDescripción = new JTextField();
			txtDescripción.setColumns(10);
			txtDescripción.setBounds(12, 118, 325, 165);
			panel.add(txtDescripción);
			
			JPanel panelEnfermedades = new JPanel();
			panelEnfermedades.setBounds(407, 48, 328, 116);
			panel.add(panelEnfermedades);
			panelEnfermedades.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			panelEnfermedades.add(scrollPane, BorderLayout.CENTER);
			
			tableSelecionadas = new JTable();
			
			scrollPane.setViewportView(tableSelecionadas);
			
			JLabel lblEnfermEfectivas = new JLabel("Enfermedades con las que funciona la vacuna:");
			lblEnfermEfectivas.setBounds(407, 13, 330, 22);
			panel.add(lblEnfermEfectivas);
			
			JPanel panelEnferRegistradas = new JPanel();
			panelEnferRegistradas.setBounds(409, 226, 328, 116);
			panel.add(panelEnferRegistradas);
			panelEnferRegistradas.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane_1 = new JScrollPane();
			panelEnferRegistradas.add(scrollPane_1, BorderLayout.CENTER);
			
			tableRegistradas = new JTable();
			scrollPane_1.setViewportView(tableRegistradas);
			
			JLabel lblEnferDisp = new JLabel("Enfermedades registradas:");
			lblEnferDisp.setBounds(409, 192, 328, 22);
			panel.add(lblEnferDisp);
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
