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

import com.proy.maven.service.*;

import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModalRegistro {

	private JFrame frame;
	private JTextField tbLargo;
	private JTextField tbAncho;
	private JTextField tbRadio;

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
		FiguraService service = new FiguraService();
		frame = new JFrame();
		frame.setBounds(100, 100, 805, 390);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JList list = new JList();
		list.setBounds(10, 160, 629, 180);
		frame.getContentPane().add(list);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 21, 143, 109);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JRadioButton rdbtnRectangulo = new JRadioButton("Rectángulo");
		rdbtnRectangulo.setBounds(6, 31, 79, 23);
		panel.add(rdbtnRectangulo);
		
		JRadioButton rdbtnCirculo = new JRadioButton("Círculo");
		rdbtnCirculo.setBounds(6, 65, 109, 23);
		panel.add(rdbtnCirculo);
		
		JLabel lblNewLabel = new JLabel("Tipo Figura:");
		lblNewLabel.setBounds(6, 10, 65, 14);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(163, 21, 163, 109);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Largo");
		lblNewLabel_1.setBounds(10, 35, 46, 14);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Ancho");
		lblNewLabel_1_1.setBounds(10, 71, 46, 14);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblRectangulo = new JLabel("Rectángulo");
		lblRectangulo.setBounds(10, 11, 65, 14);
		panel_1.add(lblRectangulo);
		
		tbLargo = new JTextField();
		tbLargo.setBounds(45, 32, 108, 20);
		panel_1.add(tbLargo);
		tbLargo.setColumns(10);
		
		tbAncho = new JTextField();
		tbAncho.setColumns(10);
		tbAncho.setBounds(45, 68, 108, 20);
		panel_1.add(tbAncho);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBounds(336, 21, 163, 109);
		frame.getContentPane().add(panel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Radio");
		lblNewLabel_1_2.setBounds(10, 35, 46, 14);
		panel_1_1.add(lblNewLabel_1_2);
		
		JLabel lblCrculo = new JLabel("Círculo");
		lblCrculo.setBounds(10, 11, 65, 14);
		panel_1_1.add(lblCrculo);
		
		tbRadio = new JTextField();
		tbRadio.setColumns(10);
		tbRadio.setBounds(45, 32, 108, 20);
		panel_1_1.add(tbRadio);
		
		JLabel lblNewLabel_2 = new JLabel("Área:");
		lblNewLabel_2.setBounds(509, 67, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lbArea = new JLabel("");
		lbArea.setBackground(new Color(255, 255, 255));
		lbArea.setBounds(548, 61, 78, 31);
		frame.getContentPane().add(lbArea);
		
		JButton btnCrearFigura = new JButton("Crear figura");
		btnCrearFigura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				Figura f = null;
				if(rdbtnRectangulo.isSelected()) {
					double largo = Double.parseDouble(tbLargo.getText());
					double ancho = Double.parseDouble(tbAncho.getText());
					f = new Rectangulo(1);
					f.setAncho(ancho); f.setLargo(largo);
					lbArea.setText("" + f.getArea());
					service.Crear(f);
				}
				else if(rdbtnCirculo.isSelected()) {
					double radio = Double.parseDouble(tbRadio.getText());
					f = new Circulo(2);
					f.setRadio(radio);
					lbArea.setText("" + f.getArea());
					service.Crear(f);
				}
				else {
					JOptionPane.showMessageDialog(null,"No se eligió el tipo de figura");
				}
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				finally {
						tbLargo.setText("");
						tbRadio.setText("");
						tbAncho.setText("");
						rdbtnRectangulo.setSelected(false);
						rdbtnCirculo.setSelected(false);
				}
			}
		});
		btnCrearFigura.setBounds(653, 49, 103, 50);
		frame.getContentPane().add(btnCrearFigura);
		
		JButton btnListarFiguras = new JButton("Listar figuras");
		btnListarFiguras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultListModel model = new DefaultListModel();
				for(Figura f : service.ObtenerTodas()) {
					model.addElement(f);
				}
				list.setModel(model);
			}
		});
		btnListarFiguras.setBounds(653, 212, 103, 50);
		frame.getContentPane().add(btnListarFiguras);
		
		JButton btnBorrar = new JButton("Borrar figura");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
				Figura f = (Figura)list.getSelectedValue();
				if(f != null) {
					int id = f.getId();
					service.Eliminar(id);
					}
				}
				catch(RuntimeException ex) {
					JOptionPane.showMessageDialog(null, "La figura no existe");
				}
				finally {
					btnListarFiguras.doClick();
				}
			}
		});
		btnBorrar.setBounds(653, 284, 103, 39);
		frame.getContentPane().add(btnBorrar);
	}
}
