import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class VentanaInicial extends JFrame{
	// Declarar componentes de la ventana
	private JButton btnIniciarSesion, btnCerrarSesion;
	private JPanel pNorte, pSur, pEste, pOeste, pCentro;
	private JLabel lblTitulo, lblNombreUsuario, lblContraseniaUsuario;
	private JTextField txtNombreUsuario;
	private JPasswordField txtContraseniaUsuario;
	
	public VentanaInicial(){
		// Primera secuencia
		super(); 
		// Tamaño y posición inicial de la ventana
		setBounds(400, 200, 600, 400);
		// Inicializar paneles
		pNorte = new JPanel();
		pSur = new JPanel();
		pEste = new JPanel();
		pOeste = new JPanel();
		pCentro = new JPanel();
		// Modificar el panel centro
		pCentro.setLayout(new GridLayout(2, 2));
		// Añadir los paneles al panel principal de la ventana
		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		getContentPane().add(pEste, BorderLayout.EAST);
		getContentPane().add(pOeste, BorderLayout.WEST);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		// Inicializar componentes (botones, etiquetas, texto)
		btnIniciarSesion = new JButton("INICIAR SESIÓN");
		btnCerrarSesion = new JButton("CERRAR SESIÓN");
		
		lblTitulo = new JLabel("VENTANA DE SAÚL");
		lblNombreUsuario = new JLabel("Introduce tu nombre: ");
		lblContraseniaUsuario = new JLabel("Introduce tu contraseña: ");
		
		txtNombreUsuario = new JTextField(20);
		txtContraseniaUsuario = new JPasswordField(20);	
		// Añadir los componentes a la ventana
		pSur.add(btnIniciarSesion);
		pSur.add(btnCerrarSesion);
		
		pNorte.add(lblTitulo);
		// Añadir en el el panel central (GridLayout) 
		// en orden en el que quiero que aparezca, de izda a dcha y de arriba a abajo
		pCentro.add(lblNombreUsuario);
		pCentro.add(txtNombreUsuario);
		pCentro.add(lblContraseniaUsuario);
		pCentro.add(txtContraseniaUsuario);
		// Añadir los listeners a los componentes
		btnCerrarSesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Código que se va a ejecutar cuando pulse el botón
				System.exit(0);
				
			}
		});
		
		btnIniciarSesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Comprobar que en el JTextField txtNombreUsuario ha escrito ""
				// y que en el JPasswordField txtContraseniaUsuario ha escrito ""
				String usuario = txtNombreUsuario.getText();
				String contrasenia = txtContraseniaUsuario.getText();
				if(usuario.equals("") && contrasenia.equals("")) {
					JOptionPane.showMessageDialog(null, "Has iniciado sesión correctamente");
					vaciarCampos();
				} else {
					JOptionPane.showMessageDialog(null, "Nombre de usuario y/o contraseña incorrectos");
					vaciarCampos();
				}
			}
		});
		// Última secuencia
		setVisible(true);
		
	}
	
	//Para no repetir código a la hora de vaciar los campos, vamos a crear un método que se encargue de ello
	public void vaciarCampos() {
		txtNombreUsuario.setText("");
		txtContraseniaUsuario.setText("");
	}
	
	public static void main(String[] args) {
		VentanaInicial v = new VentanaInicial();
	}
	
}
