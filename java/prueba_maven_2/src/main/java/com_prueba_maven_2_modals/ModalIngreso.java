package com_prueba_maven_2_modals;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com_prueba_maven_2_exceptions.CUILInvalidoException;
import com_prueba_maven_2_exceptions.NTarjetaInvalidoException;
import com_prueba_maven_2_models.Avion;
import com_prueba_maven_2_models.Sistema;
import com_prueba_maven_2_models.Tickets;
import com_prueba_maven_2_models.Transporte;

import javax.swing.JComboBox;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModalIngreso {

	private JFrame frame;
	private JTextField tbTarjeta;
	private JTextField tbTelefono;
	private JTextField tbNombre;
	private JTextField tbCUIL;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModalIngreso window = new ModalIngreso();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Sistema gestion	= new Sistema();
	
	
	ModalResumen modalResumen;
	
	/**
	 * Create the application.
	 */
	public ModalIngreso() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		gestion.AgregarTransporte(gestion.ImportTransporte("Buenos Aires;2250;LVAHZ"));
		gestion.AgregarTransporte(gestion.ImportTransporte("Bariloche;3400;LVABB"));
		gestion.AgregarTransporte(gestion.ImportTransporte("Rosario;1200;FFF330;3"));
		gestion.AgregarTransporte(gestion.ImportTransporte("Buenos Aires;1900;AC123AA;5"));

		
		frame = new JFrame();
		frame.setBounds(100, 100, 491, 334);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 167, 455, 117);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("CUIL");
		lblNewLabel_1.setBounds(10, 11, 46, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre");
		lblNewLabel_2.setBounds(10, 47, 46, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Teléfono");
		lblNewLabel_3.setBounds(10, 72, 46, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Nro Tarjeta");
		lblNewLabel_4.setBounds(10, 97, 61, 14);
		panel.add(lblNewLabel_4);
		
		tbTarjeta = new JTextField();
		tbTarjeta.setBounds(81, 94, 160, 20);
		panel.add(tbTarjeta);
		tbTarjeta.setColumns(10);
		
		tbTelefono = new JTextField();
		tbTelefono.setColumns(10);
		tbTelefono.setBounds(81, 69, 113, 20);
		panel.add(tbTelefono);
		
		tbNombre = new JTextField();
		tbNombre.setColumns(10);
		tbNombre.setBounds(81, 44, 113, 20);
		panel.add(tbNombre);
		
		tbCUIL = new JTextField();
		tbCUIL.setColumns(10);
		tbCUIL.setBounds(81, 8, 160, 20);
		panel.add(tbCUIL);
		
		JLabel lblNewLabel_5 = new JLabel("(Sin espacios, sin guiones)");
		lblNewLabel_5.setBounds(270, 11, 135, 14);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_5_1 = new JLabel("(Sin espacios, sin guiones)");
		lblNewLabel_5_1.setBounds(270, 97, 135, 14);
		panel.add(lblNewLabel_5_1);
		
		JLabel lblNewLabel = new JLabel("Datos pasajero");
		lblNewLabel.setBounds(27, 150, 89, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JComboBox cmbDestino = new JComboBox();
		cmbDestino.setBounds(82, 22, 165, 22);
		frame.getContentPane().add(cmbDestino);
		
		JLabel lbPrecio = new JLabel("(El precio se mostrará aquí)");
		lbPrecio.setBackground(Color.WHITE);
		lbPrecio.setBounds(82, 57, 136, 14);
		frame.getContentPane().add(lbPrecio);
		
		JComboBox cmbTransporte = new JComboBox();
		cmbTransporte.setBounds(82, 82, 165, 22);
		frame.getContentPane().add(cmbTransporte);
		
		JLabel lblNewLabel_6 = new JLabel("Destino");
		lblNewLabel_6.setBounds(26, 26, 46, 14);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_6_1 = new JLabel("Precio");
		lblNewLabel_6_1.setBounds(27, 57, 46, 14);
		frame.getContentPane().add(lblNewLabel_6_1);
		
		JLabel lblNewLabel_6_1_1 = new JLabel("Transporte");
		lblNewLabel_6_1_1.setBounds(10, 88, 62, 14);
		frame.getContentPane().add(lblNewLabel_6_1_1);
		
		DefaultComboBoxModel modelTransportes = new DefaultComboBoxModel();
		DefaultComboBoxModel modelDestinos = new DefaultComboBoxModel();
		
		for(int i = 0; i < gestion.VerCantTransportes(); i++) {
			Transporte t = gestion.VerTransporte(i);
			String tipo = (t instanceof Avion) ? "Avión" : "Bús";
			modelTransportes.addElement(tipo/* + " [" + t.getPatente() + "]"*/);
			modelDestinos.addElement(t.getDestino());
		}
		cmbTransporte.setModel(modelTransportes);
		cmbDestino.setModel(modelDestinos);
		
		JButton btnGenerar = new JButton("Generar");
		btnGenerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
				String CUIL = tbCUIL.getText();
				String nombre = tbNombre.getText();
				String telefono = tbTelefono.getText();
				String tarjeta = tbTarjeta.getText();
				String tipo = (String)cmbTransporte.getSelectedItem();
				String destino = (String)cmbDestino.getSelectedItem();
				
				gestion.AgregarTickets(CUIL, nombre, telefono, tarjeta, tipo, destino);
				
				}
				catch(CUILInvalidoException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				catch(NTarjetaInvalidoException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				catch(RuntimeException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());					
				}
			}});
		btnGenerar.setBounds(291, 11, 89, 45);
		frame.getContentPane().add(btnGenerar);
		
		JButton btnResumen = new JButton("Resumen");
		btnResumen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				modalResumen = new ModalResumen();
				DefaultListModel model = new DefaultListModel();
				for(int i = 0; i < gestion.getCantTickets(); i++) {
					model.addElement(gestion.VerTicket(i).ToString());
				}
				modalResumen.jlistResumen.setModel(model);
				
				modalResumen.setVisible(true);
				
			}
		});
		btnResumen.setBounds(291, 79, 89, 23);
		frame.getContentPane().add(btnResumen);
	}
}
