package forms2;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import exceptions.PresupuestoInexistenteException;
import models2.Mesa;
import models2.Presupuesto;
import models2.Producto;
import exceptions.ProductoInexistenteException;
import models2.Banco;


public class FormDatos {

	private JFrame frame;
	private JTextField tbNombre;
	private JTextField tbDireccion;
	private JTextField tbCodigo;
	private JTextField tbGrosor;
	private JTextField tbLargo;
	private JTextField tbPrecioBase;
	private JTextField tbAncho;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormDatos window = new FormDatos();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private Presupuesto presupuesto;
	ModalList modalInforme;
	
	/**
	 * Create the application.
	 */
	public FormDatos() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 591, 306);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 23, 266, 233);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(10, 11, 41, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Dirección:");
		lblNewLabel_1_1.setBounds(10, 35, 47, 14);
		panel.add(lblNewLabel_1_1);
		
		tbNombre = new JTextField();
		tbNombre.setBackground(SystemColor.inactiveCaption);
		tbNombre.setBounds(61, 8, 176, 20);
		panel.add(tbNombre);
		tbNombre.setColumns(10);
		
		tbDireccion = new JTextField();
		tbDireccion.setColumns(10);
		tbDireccion.setBackground(SystemColor.inactiveCaption);
		tbDireccion.setBounds(61, 32, 176, 20);
		panel.add(tbDireccion);
		
		JButton btnIniciarPresupuesto = new JButton("Iniciar presupuesto");
		btnIniciarPresupuesto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				String nombre = tbNombre.getText();
				String dir = tbDireccion.getText();
				presupuesto = new Presupuesto(nombre,dir);
				}
				catch(PresupuestoInexistenteException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		btnIniciarPresupuesto.setToolTipText("");
		btnIniciarPresupuesto.setFont(new Font("Arial", Font.PLAIN, 11));
		btnIniciarPresupuesto.setBounds(71, 63, 125, 33);
		panel.add(btnIniciarPresupuesto);
		
		JButton btnCerrarPresupuesto = new JButton("Cerrar presupuesto");
		btnCerrarPresupuesto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modalInforme = new ModalList();
				DefaultListModel model = new DefaultListModel();
				String[] lines = presupuesto.Resumen();
				for(String line : lines) {
					model.addElement(line);
				}
				modalInforme.jlPresupuestos.setModel(model);
				modalInforme.setVisible(true);
			}
		});
		btnCerrarPresupuesto.setBounds(71, 107, 125, 33);
		panel.add(btnCerrarPresupuesto);
		
		JComboBox cmbCodigos = new JComboBox();
		cmbCodigos.setBounds(10, 165, 143, 22);
		panel.add(cmbCodigos);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object productoSeleccionado = cmbCodigos.getSelectedItem();
				int codigo = Integer.parseInt(productoSeleccionado.toString());
				if(presupuesto.QuitarProducto(codigo)) {
					cmbCodigos.removeItem(productoSeleccionado);
					JOptionPane.showMessageDialog(null, "El producto fue borrado con éxito del presupuesto.");
				}
				else {
					JOptionPane.showMessageDialog(null, "El producto no fue encontrado");
				}
				
			}
		});
		btnBorrar.setBounds(167, 160, 89, 33);
		panel.add(btnBorrar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(286, 23, 279, 233);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Precio base:");
		lblNewLabel_2.setBounds(10, 11, 59, 14);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Ancho:");
		lblNewLabel_2_1.setBounds(10, 37, 59, 14);
		panel_1.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Código:");
		lblNewLabel_2_1_1.setBounds(10, 63, 59, 14);
		panel_1.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("Largo:");
		lblNewLabel_2_1_2.setBounds(152, 11, 59, 14);
		panel_1.add(lblNewLabel_2_1_2);
		
		JLabel lblNewLabel_2_1_3 = new JLabel("Grosor:");
		lblNewLabel_2_1_3.setBounds(152, 37, 59, 14);
		panel_1.add(lblNewLabel_2_1_3);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				Producto producto = null;
				int codigo = Integer.parseInt(tbCodigo.getText());
				double precioBase = Double.parseDouble(tbPrecioBase.getText());
				
				if(!tbAncho.getText().isBlank() && !tbGrosor.getText().isBlank() && !tbLargo.getText().isBlank()) {
				double ancho = Double.parseDouble(tbAncho.getText());
				double largo = Double.parseDouble(tbLargo.getText());
				double grosor = Double.parseDouble(tbGrosor.getText());
					producto = new Mesa(precioBase, largo, ancho, grosor);
				}
				else if(!tbLargo.getText().isBlank() && !tbPrecioBase.getText().isBlank()) {
					double largo = Double.parseDouble(tbLargo.getText());
					producto = new Banco(precioBase,largo);
				}
					producto.setCodigo(codigo);
					presupuesto.AgregarProducto(producto);
					cmbCodigos.addItem(producto.getCodigo());
					}
				catch(ProductoInexistenteException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				}
		});
		btnAgregar.setBounds(122, 98, 118, 34);
		panel_1.add(btnAgregar);
		
		tbCodigo = new JTextField();
		tbCodigo.setBounds(70, 60, 111, 20);
		panel_1.add(tbCodigo);
		tbCodigo.setColumns(10);
		
		tbGrosor = new JTextField();
		tbGrosor.setColumns(10);
		tbGrosor.setBounds(187, 34, 82, 20);
		panel_1.add(tbGrosor);
		
		tbLargo = new JTextField();
		tbLargo.setColumns(10);
		tbLargo.setBounds(187, 8, 82, 20);
		panel_1.add(tbLargo);
		
		tbPrecioBase = new JTextField();
		tbPrecioBase.setColumns(10);
		tbPrecioBase.setBounds(72, 8, 70, 20);
		panel_1.add(tbPrecioBase);
		
		tbAncho = new JTextField();
		tbAncho.setColumns(10);
		tbAncho.setBounds(72, 34, 70, 20);
		panel_1.add(tbAncho);
		
		JLabel lblNewLabel = new JLabel("Cliente");
		lblNewLabel.setBounds(23, 11, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblProductos = new JLabel("Productos");
		lblProductos.setBounds(294, 11, 61, 14);
		frame.getContentPane().add(lblProductos);
	}
}
