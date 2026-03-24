package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import models.Encuesta;
import models.ProcesoEncuestas;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModalMenu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModalMenu window = new ModalMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private ModalEncuesta modalEncuesta;
	
	private ProcesoEncuestas encuestas = new ProcesoEncuestas();
	
	private JButton btnRegistroEncuesta;
	private JButton btnListado;
	
	private ModalInforme modalInforme;
	/**
	 * Create the application.
	 */
	public ModalMenu() {
		initialize();
	}
	
	private ModalEncuesta abrirModalEncuesta() {
		modalEncuesta = new ModalEncuesta(frame);
		modalEncuesta.setVisible(true);
		return modalEncuesta;
	}
	private void abrirModalInforme(ModalInforme modalin) {
		modalin.setVisible(true);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 392, 255);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnRegistroEncuesta = new JButton("Registro de Encuesta");
		btnRegistroEncuesta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modalEncuesta = abrirModalEncuesta();
				if(modalEncuesta.getResultado())
				{
					boolean seleccionoTransporte = false;
					Encuesta encuesta = new Encuesta();
					//chequeo de seleccion de transporte
					if(modalEncuesta.chckbxBicicleta.isSelected()) { encuesta.setUsaBicicleta(true); seleccionoTransporte = true;}
					
					if(modalEncuesta.chckbxAutomovil.isSelected()) {encuesta.setUsaAuto(true); seleccionoTransporte = true;}
					
					if(modalEncuesta.chckbxTransporte.isSelected()) {encuesta.setUsaTransportePublico(true); seleccionoTransporte = true;}
					//aviso de error en caso de no seleccionar transporte
					if (!seleccionoTransporte){
						JOptionPane.showMessageDialog(frame, "Completar porfavor la encuesta","Campo requerido",
						JOptionPane.WARNING_MESSAGE);
						return;
					}
					//chequeo de parseo de distancia
					if(!modalEncuesta.tbDistancia.getText().trim().isEmpty()) {
					encuesta.setDistancia(Double.parseDouble(modalEncuesta.tbDistancia.getText()));}
					else {JOptionPane.showMessageDialog(frame, "Completar porfavor la distancia","Campo requerido",
							JOptionPane.WARNING_MESSAGE);}
					//chequeo de email
					if(!modalEncuesta.tbEmail.getText().trim().isEmpty()) {encuesta.setEmail(modalEncuesta.tbEmail.getText());}
					else {encuesta.setEmail("");}
					//registro
					encuestas.RegistrarEncuesta(encuesta, modalEncuesta.chckbxPuedeSerContactado.isSelected());
					}
				}
		});
		btnRegistroEncuesta.setBounds(107, 32, 166, 44);
		frame.getContentPane().add(btnRegistroEncuesta);
		
		btnListado = new JButton("Listado contactables");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModalInforme modal = new ModalInforme();
				DefaultListModel<String> model = new DefaultListModel<>();
				model.addElement("Cantidad encuestados: " + encuestas.getCantidadEncuestados());
				model.addElement("Cantidad encuestados contactables: " + encuestas.getCantContactables());
				model.addElement("- - - - - - -");
				for(int i = 0; i < encuestas.getCantContactables();i++) {
					String auto = (encuestas.VerContactable(i).getUsaAuto()) ? "Automóvil| " : "";
					String bici = (encuestas.VerContactable(i)).getUsaBicicleta() ? "Bicicleta| " : "";
					String trans = (encuestas.VerContactable(i)).getUsaTransportePublico() ? "Transporte público" : "";
					model.addElement("Vehículo/s: " + auto + bici + trans);
					model.addElement("Distancia recorrida: " + encuestas.VerContactable(i).getDistancia() + "km");
					model.addElement("Email: " + encuestas.VerContactable(i).getEmail());
					model.addElement("- - - - - - -");
				}
				modal.jlInforme.setModel(model);
				abrirModalInforme(modal);
			}
		});
		btnListado.setBounds(107, 108, 166, 44);
		frame.getContentPane().add(btnListado);
	}
}
