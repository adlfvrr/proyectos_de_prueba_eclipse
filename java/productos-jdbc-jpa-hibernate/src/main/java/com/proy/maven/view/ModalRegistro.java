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
		frame.setBounds(100, 100, 619, 390);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JList list = new JList();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				tbNombre.setText("");
				tbStock.setText("");
				tbPrecio.setText("");
				
				Producto p = (Producto)list.getSelectedValue();
				if (p != null) {
						tbPrecio.setText("" + p.getPrecio());
						tbNombre.setText("" + p.getNombre());
						tbStock.setText("" + p.getStock());
					}
				}
		});
		list.setBounds(10, 160, 446, 180);
		frame.getContentPane().add(list);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 21, 316, 109);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
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
		lblNewLabel_1_2.setBounds(174, 35, 46, 14);
		panel_1.add(lblNewLabel_1_2);
		
		tbStock = new JTextField();
		tbStock.setColumns(10);
		tbStock.setBounds(209, 29, 97, 20);
		panel_1.add(tbStock);
		
		JLabel lbArea = new JLabel("");
		lbArea.setBackground(new Color(255, 255, 255));
		lbArea.setBounds(548, 32, 78, 31);
		frame.getContentPane().add(lbArea);
		
		JButton btnCrearProducto = new JButton("Agregar producto");
		btnCrearProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String nombre = tbNombre.getText();
					double precio = Double.parseDouble(tbPrecio.getText());
					int stock = Integer.parseInt(tbStock.getText());
					Producto p = new Producto(nombre, precio, stock);
					if(service.añadirProducto(p)) {
						JOptionPane.showMessageDialog(null, "El producto fue agregado con éxito");
					}
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				finally {
						tbNombre.setText("");
						tbStock.setText("");
						tbPrecio.setText("");
						btnListarProductos.doClick();
				}
			}
		});
		btnCrearProducto.setBounds(466, 61, 117, 56);
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
		btnListarProductos.setBounds(466, 203, 117, 56);
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
		btnBorrar.setBounds(466, 270, 117, 56);
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
							JOptionPane.showMessageDialog(null, "Figura encontrada");
						}
						else {
							JOptionPane.showMessageDialog(null, "No se encontró la figura con el id " + id);
							
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
						if(service.actualizarProducto(p)) {
							JOptionPane.showMessageDialog(null, String.format("Producto con id %d actualizado con éxito", p.getId()));
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
					btnListarProductos.doClick();
				}
			}
		});
		btnActualizarProducto.setBounds(466, 134, 117, 56);
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

