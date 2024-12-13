import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Ventana extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Modelo modelo;
	private JTable tabla;
	private JScrollPane scroll;
	
	public Ventana(List<Jugador> lJugadores) {
		super();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(300, 200, 600, 400);
		
		modelo = new Modelo(lJugadores);
		tabla = new JTable(modelo);
		scroll = new JScrollPane(tabla);
		getContentPane().add(scroll, BorderLayout.CENTER);
		setVisible(true);
	}
}

