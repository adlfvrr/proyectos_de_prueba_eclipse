package com_prueba_maven_2_modals;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;

public class ModalResumen extends JDialog {

	private static final long serialVersionUID = 1L;

	public JList jlistResumen;
	
	public ModalResumen() {
		setBounds(100, 100, 853, 300);
		getContentPane().setLayout(null);
		
		jlistResumen = new JList();
		jlistResumen.setBounds(20, 11, 797, 205);
		getContentPane().add(jlistResumen);
		
		JButton btnNewButton = new JButton("Cerrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton.setBounds(342, 227, 127, 23);
		getContentPane().add(btnNewButton);

	}
}
