package com.proy.maven.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.proy.maven.entity.*;
import com.proy.maven.services.ProductoServices;

import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JScrollBar;
import java.awt.Font;

public class ModalRegistro {

	private JFrame frame;
	private JTextField tbNombre;
	private JTextField tbPrecio;
	private JTextField tbBuscarPorId;
	private JTextField tbStock;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModalRegistro window = new ModalRegistro();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public JButton btnListarProductos;
	private JTextField tbPeso;
	private JTextField tbURL;
	
	/**
	 * Create the application.
	 */
	public ModalRegistro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		ProductoServices service = new ProductoServices();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 770, 390);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 21, 292, 305);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JRadioButton rdbtnDigital = new JRadioButton("Digital");
		rdbtnDigital.setBounds(164, 31, 109, 23);
		panel_1.add(rdbtnDigital);
		
		JRadioButton rdbtnFisico = new JRadioButton("Fisico");
		rdbtnFisico.setBounds(164, 67, 109, 23);
		panel_1.add(rdbtnFisico);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setBounds(10, 35, 46, 14);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Precio");
		lblNewLabel_1_1.setBounds(10, 71, 46, 14);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblRectangulo = new JLabel("Producto");
		lblRectangulo.setBounds(10, 11, 65, 14);
		panel_1.add(lblRectangulo);
		
		tbNombre = new JTextField();
		tbNombre.setBounds(56, 32, 97, 20);
		panel_1.add(tbNombre);
		tbNombre.setColumns(10);
		
		tbPrecio = new JTextField();
		tbPrecio.setColumns(10);
		tbPrecio.setBounds(56, 68, 97, 20);
		panel_1.add(tbPrecio);
		
		JLabel lblNewLabel_1_2 = new JLabel("Stock");
		lblNewLabel_1_2.setBounds(10, 115, 46, 14);
		panel_1.add(lblNewLabel_1_2);
		
		tbStock = new JTextField();
		tbStock.setColumns(10);
		tbStock.setBounds(56, 112, 97, 20);
		panel_1.add(tbStock);
		
		JLabel lblNewLabel = new JLabel("Tipo producto");
		lblNewLabel.setBounds(164, 11, 76, 14);
		panel_1.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 156, 122, 96);
		panel_1.add(panel);
		panel.setLayout(null);
		
		tbURL = new JTextField();
		tbURL.setColumns(10);
		tbURL.setBounds(10, 61, 102, 20);
		panel.add(tbURL);
		
		JLabel lblDigital = new JLabel("Digital");
		lblDigital.setBounds(10, 11, 76, 14);
		panel.add(lblDigital);
		
		JLabel lblUrl = new JLabel("URL");
		lblUrl.setBounds(10, 36, 76, 14);
		panel.add(lblUrl);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(151, 156, 122, 96);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		tbPeso = new JTextField();
		tbPeso.setBounds(10, 61, 102, 20);
		panel_2.add(tbPeso);
		tbPeso.setColumns(10);
		
		JLabel lblFiscio = new JLabel("Fisico");
		lblFiscio.setBounds(10, 11, 76, 14);
		panel_2.add(lblFiscio);
		
		JLabel lblPeso = new JLabel("Peso:");
		lblPeso.setBounds(10, 36, 76, 14);
		panel_2.add(lblPeso);
		
		JButton btnCrearProducto = new JButton("Agregar producto");
		btnCrearProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Producto p;
					String nombre = tbNombre.getText();
					double precio = Double.parseDouble(tbPrecio.getText());
					int stock = Integer.parseInt(tbStock.getText());
					
					
					if(rdbtnFisico.isSelected()) {
						
						double peso = Double.parseDouble(tbPeso.getText());
						p = new ProductoFisico(nombre, precio, stock, peso);
							if(service.añadirProducto(p)) {
							JOptionPane.showMessageDialog(null, "El producto físico fue agregado con éxito");
							}
						}
						else if(rdbtnDigital.isSelected()) {
							String url = tbURL.getText();
							p = new ProductoDigital(nombre, precio, stock, url);
							if(service.añadirProducto(p)) {
								JOptionPane.showMessageDialog(null, "El producto digital fue agregado con éxito");
							}	
						}
						else {
							throw new RuntimeException("Se debe seleccionar un tipo de producto");
						}
					}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				finally {
						tbNombre.setText("");
						tbStock.setText("");
						tbPrecio.setText("");
						tbPeso.setText("");
						tbURL.setText("");
						rdbtnFisico.setSelected(false);
						rdbtnDigital.setSelected(false);
						btnListarProductos.doClick();
				}
			}
		});
		
		JList list = new JList();
		list.setFont(new Font("Tahoma", Font.PLAIN, 8));
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				tbNombre.setText("");
				tbStock.setText("");
				tbPrecio.setText("");
				tbPeso.setText("");
				tbURL.setText("");
				rdbtnFisico.setSelected(false);
				rdbtnDigital.setSelected(false);
				
				
				
				Producto p = (Producto)list.getSelectedValue();
				if (p != null) {
					if(p instanceof ProductoFisico) {
						tbPrecio.setText("" + p.getPrecio());
						tbNombre.setText("" + p.getNombre());
						tbStock.setText("" + p.getStock());
						tbPeso.setText("" + ((ProductoFisico) p).getPeso());
						rdbtnFisico.setSelected(true);
					}
					else {
						tbPrecio.setText("" + p.getPrecio());
						tbNombre.setText("" + p.getNombre());
						tbStock.setText("" + p.getStock());
						tbURL.setText(((ProductoDigital) p).getUrlDescarga());
						rdbtnDigital.setSelected(true);
						
					}
					
				}
				
			}
		});
		list.setBounds(325, 160, 292, 180);
		frame.getContentPane().add(list);
		btnCrearProducto.setBounds(627, 61, 117, 56);
		frame.getContentPane().add(btnCrearProducto);
		
		btnListarProductos = new JButton("Listar productos");
		btnListarProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultListModel model = new DefaultListModel();
				for(Producto p : service.obtenerTodos()) {
					model.addElement(p);
				}
				list.setModel(model);
			}
		});
		btnListarProductos.setBounds(627, 203, 117, 56);
		frame.getContentPane().add(btnListarProductos);
		
		JButton btnBorrar = new JButton("Borrar producto");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
				Producto p = (Producto)list.getSelectedValue();
				if(p != null) {
					int id = p.getId();
					if(service.eliminarProducto(id)) {
						JOptionPane.showMessageDialog(null, String.format("Producto con id %d borrado con éxito", p.getId()));
					}
					}
				}
				catch(RuntimeException ex) {
					JOptionPane.showMessageDialog(null, "La figura no existe");
				}
				finally {
					btnListarProductos.doClick();
				}
			}
		});
		btnBorrar.setBounds(627, 270, 117, 56);
		frame.getContentPane().add(btnBorrar);
		
		JButton btnBuscar = new JButton("Buscar producto");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//reseteamos la list
				DefaultListModel model = new DefaultListModel();
				list.setModel(model);
				
				try {
					if(!tbBuscarPorId.getText().isBlank()) {
						int id = Integer.parseInt(tbBuscarPorId.getText());
						Producto p = service.obtenerPorId(id);
						if(p != null) {
							model.addElement(p);
							JOptionPane.showMessageDialog(null, "Producto encontrado");
						}
						else {
							JOptionPane.showMessageDialog(null, "No se encontró el producto con el id " + id);
							
						}
					}
				}
				catch(RuntimeException ex) {
					JOptionPane.showMessageDialog(null, "Fallo en la búsqueda: " + ex.getMessage());
				}
				
			}
		});
		btnBuscar.setBounds(340, 74, 116, 31);
		frame.getContentPane().add(btnBuscar);
		
		JButton btnActualizarProducto = new JButton("Actualizar");
		btnActualizarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Producto p = (Producto)list.getSelectedValue();
					if(p != null) {
						String nombre = tbNombre.getText();
						double precio = Double.parseDouble(tbPrecio.getText());
						int stock = Integer.parseInt(tbStock.getText());
						
						p.setNombre(nombre);
						p.setPrecio(precio);
						p.setStock(stock);
						
						if(p instanceof ProductoDigital) {
							((ProductoDigital) p).setUrlDescarga(tbURL.getText());
							
						if(service.actualizarProducto(p)) {
							JOptionPane.showMessageDialog(null, String.format("Producto digital con id %d actualizado con éxito", p.getId()));
						}
					}
						else if(p instanceof ProductoFisico) {
							((ProductoFisico) p).setpeso(Double.parseDouble(tbPeso.getText()));
							if(service.actualizarProducto(p)) {
								JOptionPane.showMessageDialog(null, String.format("Producto fisico con id %d actualizado con éxito", p.getId()));
							}
							
						}
				}
						
			}
				catch(RuntimeException ex) {
					JOptionPane.showMessageDialog(null, "Fallo en la actualización: " + ex.getMessage());
				}
				finally {
					tbNombre.setText("");
					tbStock.setText("");
					tbPrecio.setText("");
					tbPeso.setText("");
					tbURL.setText("");
					rdbtnFisico.setSelected(false);
					rdbtnDigital.setSelected(false);
					
					btnListarProductos.doClick();
				}
			}
		});
		btnActualizarProducto.setBounds(627, 134, 117, 56);
		frame.getContentPane().add(btnActualizarProducto);
		
		tbBuscarPorId = new JTextField();
		tbBuscarPorId.setBounds(357, 43, 86, 20);
		frame.getContentPane().add(tbBuscarPorId);
		tbBuscarPorId.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Id:");
		lblNewLabel_3.setBounds(336, 46, 46, 14);
		frame.getContentPane().add(lblNewLabel_3);
	}
}

