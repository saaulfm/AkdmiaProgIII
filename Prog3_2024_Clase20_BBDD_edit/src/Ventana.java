import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Ventana extends JFrame implements WindowListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel pCentro, pSur;
	private JButton btnInicioSesion, btnRegistro;
	private JLabel lblId;
	private JTextField txtId;
	private JFrame vActual;
	
	public Ventana() {
		super();
		setBounds(400, 200, 400, 100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		vActual = this;
		
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
			int idE  = Integer.parseInt(JOptionPane.showInputDialog("Introduce tu id: "));
			String nomE = JOptionPane.showInputDialog("Introduce tu nombre: ");
			if(BD.existeEquipo(idE)) {
				JOptionPane.showMessageDialog(null, "Lo sentimos, ese equipo ya existe", "Error de registro", JOptionPane.ERROR_MESSAGE);
			}else {
				BD.insertarEquipo(idE, nomE);
				JOptionPane.showMessageDialog(null, "Registro correcto", "Registro", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		
		btnInicioSesion.addActionListener((e)->{
			try {
				int idE = Integer.parseInt(txtId.getText());
				if (BD.existeEquipo(idE)) {
					JOptionPane.showMessageDialog(null, "Ongi etorri!!!", "Inicio Sesión", JOptionPane.INFORMATION_MESSAGE);
					vActual.setVisible(false);
					new VentanaJugadores(vActual, idE);
				} else {
					JOptionPane.showMessageDialog(null, "El id de equipo no es correcto", "Inicio Sesión", JOptionPane.ERROR_MESSAGE);
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "El id de usuario no es correcto", "Inicio Sesión", JOptionPane.ERROR_MESSAGE);
			}
			txtId.setText("");
		});
		setVisible(true);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}	
}
