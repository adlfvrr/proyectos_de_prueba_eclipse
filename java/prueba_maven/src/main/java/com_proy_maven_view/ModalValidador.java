package com_proy_maven_view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;

import com_proy_maven_guias.Patente;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class ModalValidador {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModalValidador window = new ModalValidador();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Patente pat1 = new Patente("OXY333");
	Patente pat2 = new Patente("OYZ 013");
	Patente pat3 = new Patente("AAA 123");
	Patente pat4 = new Patente("BCD 456");
	Patente pat5 = new Patente("AB 123 CD");
	Patente pat6 = new Patente("YZ5432EF");
	Patente pat7 = new Patente("QW 3456 AB");
	/**
	 * Create the application.
	 */
	public ModalValidador() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JList listPatentes = new JList();
		listPatentes.setBounds(3, 4, 306, 251);
		frame.getContentPane().add(listPatentes);
		
		JButton btnProcesar = new JButton("Procesar");
		btnProcesar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listPatentes.removeAll();
				DefaultListModel model = new DefaultListModel();
				model.addElement(pat1.descripcion);
				model.addElement(pat2.descripcion);
				model.addElement(pat3.descripcion);
				model.addElement(pat4.descripcion);
				model.addElement(pat5.descripcion);
				model.addElement(pat6.descripcion);
				model.addElement(pat7.descripcion);
				listPatentes.setModel(model);
			}
		});
		btnProcesar.setBounds(325, 100, 89, 38);
		frame.getContentPane().add(btnProcesar);
	}
}
