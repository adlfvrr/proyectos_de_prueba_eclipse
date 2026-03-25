package forms2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;

public class ModalList extends JDialog {

	public JList jlPresupuestos;
	
	public ModalList() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		{
			JButton btnCerrar = new JButton("Cerrar");
			btnCerrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});
			btnCerrar.setActionCommand("Cancel");
			btnCerrar.setBounds(315, 234, 109, 23);
			getContentPane().add(btnCerrar);
		}
		
		jlPresupuestos = new JList();
		jlPresupuestos.setBounds(10, 26, 414, 197);
		getContentPane().add(jlPresupuestos);
		
		JLabel lblNewLabel = new JLabel("Informe presupuestos");
		lblNewLabel.setBounds(10, 11, 133, 14);
		getContentPane().add(lblNewLabel);
	}
}
