import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Ventana extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel pCentro, pSur;
	private JButton btnInicioSesion, btnRegistro;
	private JLabel lblId;
	private JTextField txtId;
	
	public Ventana() {
		super();
		setBounds(400, 200, 400, 100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		lblId = new JLabel("Introduce tu id: ");
		txtId = new JTextField();
		btnInicioSesion = new JButton("INICIO SESIÓN");
		btnRegistro = new JButton("REGISTRO");
		pCentro = new JPanel(new GridLayout(1, 2, 0, 10));
		pSur = new JPanel();
		pCentro.add(lblId);
		pCentro.add(txtId);
		pSur.add(btnInicioSesion);
		pSur.add(btnRegistro);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		
		
		btnRegistro.addActionListener((e)->{
			int idU  = Integer.parseInt(JOptionPane.showInputDialog("Introduce tu id: "));
			String nomU = JOptionPane.showInputDialog("Introduce tu nombre: ");
			if(BD.existeEquipo(idU)) {
				JOptionPane.showMessageDialog(null, "Lo sentimos, ese equipo ya existe", "Error de registro", JOptionPane.ERROR_MESSAGE);
			}else {
				BD.insertarEquipo(idU, nomU);
				JOptionPane.showMessageDialog(null, "Registro correcto", "Registro", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		
		btnInicioSesion.addActionListener((e)->{
			try {
				int idE = Integer.parseInt(txtId.getText());
				if(BD.existeEquipo(idE)) {
					JOptionPane.showMessageDialog(null, "Ongi etorri!!!", "Inicio Sesión", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "El id de equipo no es correcto", "Inicio Sesión", JOptionPane.ERROR_MESSAGE);
				}
			}catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "El id de usuario no es correcto", "Inicio Sesión", JOptionPane.ERROR_MESSAGE);
			}
			txtId.setText("");
		});
		setVisible(true);
	}
}
