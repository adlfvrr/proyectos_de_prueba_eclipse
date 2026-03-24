package forms;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModalEncuesta extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	
	
	 public JTextField tbDistancia;
	 public JTextField tbEmail;
	 public JCheckBox chckbxBicicleta;
	 public JCheckBox chckbxAutomovil;
	 public JCheckBox chckbxTransporte;
	 public JCheckBox chckbxPuedeSerContactado;
	    
	 private boolean resultado;
	 public void setResultado(boolean r) {
		 this.resultado = r;
	 }
	 public boolean getResultado() {
		 return this.resultado;
	 }

	/**
	 * Create the dialog.
	 */
	public ModalEncuesta(JFrame modalMenu) {
		super(modalMenu, true);
		setResultado(false);
		setBounds(100, 100, 450, 379);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 0, 0);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Modo de transporte habitual");
        lblNewLabel.setBounds(10, 11, 140, 14);
        getContentPane().add(lblNewLabel);
        
        chckbxBicicleta = new JCheckBox("¿Usa bicicleta?");
        chckbxBicicleta.setBounds(156, 31, 140, 23);
        getContentPane().add(chckbxBicicleta);
        
        chckbxAutomovil = new JCheckBox("¿Usa automóvil?");
        chckbxAutomovil.setBounds(156, 57, 129, 23);
        getContentPane().add(chckbxAutomovil);
        
        chckbxTransporte = new JCheckBox("¿Usa transporte público?");
        chckbxTransporte.setBounds(156, 83, 163, 23);
        getContentPane().add(chckbxTransporte);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(0, 117, 414, 7);
        getContentPane().add(separator);
        
        JLabel lblSobreElDestino = new JLabel("Sobre el destino al trabajo/estudio");
        lblSobreElDestino.setBounds(10, 124, 176, 23);
        getContentPane().add(lblSobreElDestino);
        
        JLabel lblDistanciaEnkm = new JLabel("Distancia en (km)");
        lblDistanciaEnkm.setBounds(30, 158, 92, 14);
        getContentPane().add(lblDistanciaEnkm);
        
        tbDistancia = new JTextField();
        tbDistancia.setBounds(118, 155, 129, 20);
        getContentPane().add(tbDistancia);
        tbDistancia.setColumns(10);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(10, 183, 414, 14);
        getContentPane().add(separator_1);
        
        JLabel lblNewLabel_1 = new JLabel("Contacto");
        lblNewLabel_1.setBounds(10, 193, 46, 14);
        getContentPane().add(lblNewLabel_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("Email");
        lblNewLabel_1_1.setBounds(10, 237, 46, 14);
        getContentPane().add(lblNewLabel_1_1);
        
        tbEmail = new JTextField();
        tbEmail.setColumns(10);
        tbEmail.setBounds(170, 234, 196, 20);
        getContentPane().add(tbEmail);
        
        chckbxPuedeSerContactado = new JCheckBox("¿Puede ser contactado?");
        chckbxPuedeSerContactado.setBounds(170, 204, 149, 23);
        getContentPane().add(chckbxPuedeSerContactado);
        
        JSeparator separator_2 = new JSeparator();
        separator_2.setBounds(0, 268, 414, 7);
        getContentPane().add(separator_2);
        
        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		resultado = true;
        		setVisible(false);
        	}
        });
        btnConfirmar.setBounds(48, 306, 89, 23);
        getContentPane().add(btnConfirmar);
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setVisible(false);
        		dispose();
        	}
        });
        btnCancelar.setBounds(257, 306, 89, 23);
        getContentPane().add(btnCancelar);
	}

}
