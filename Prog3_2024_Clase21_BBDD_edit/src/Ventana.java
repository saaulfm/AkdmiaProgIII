
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Ventana extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JPanel pNorte, pCentro, pSur, pOeste, pEste;
	JComboBox<Equipo> cbEquipos;
	JComboBox<Jugador> cbJugadores;
	JButton btnAniadirFichaje, btnVerListadoEquiposYFichajes;
	
	public Ventana() {
		super();
		setBounds(300, 200, 600, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		pNorte = new JPanel();
		pCentro = new JPanel();
		pSur = new JPanel();
		pOeste = new JPanel(new GridLayout(2, 1, 0, 50));
		pEste = new JPanel();
		
		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		getContentPane().add(pEste, BorderLayout.EAST);
		getContentPane().add(pOeste, BorderLayout.WEST);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		
		
		cbEquipos = new JComboBox<Equipo>();
		cbJugadores = new JComboBox<Jugador>();
		pOeste.add(cbEquipos);
		pOeste.add(cbJugadores);
		
		btnAniadirFichaje = new JButton("AÑADIR FICHAJE");
		pSur.add(btnAniadirFichaje);
		btnVerListadoEquiposYFichajes = new JButton("VER LISTADO");
		pSur.add(btnVerListadoEquiposYFichajes);
		
		cargarComboEquipos();
		cargarComboJugadores();
		
		/*LISTENERS*/
		btnVerListadoEquiposYFichajes.addActionListener((e)->{
			/*NOMBRE JUGADOR		NOMBRE EQUIPO		VALOR MERCADO*/
			System.out.println(String.format("%20s%20s%20s", "NOMBRE JUGADOR", "NOMBRE EQUIPO", "VALOR MERCADO"));
			//List<String> l = BD.obtenerListadoV1();
			List<String> l = BD.obtenerListadoV1();
			for(String s: l) {
				System.out.println(s);
			}
		});
		
		btnAniadirFichaje.addActionListener((e)->{
			Equipo eq = (Equipo) cbEquipos.getSelectedItem(); //Si no se ha seleccionado nada, devuelve null
			Jugador j = (Jugador) cbJugadores.getSelectedItem(); //Si no se ha seleccionado nada, devuelve null
			if (eq==null || j==null) {
				JOptionPane.showMessageDialog(null, "Primero debes seleccionar el equipo y el jugador");
			} else {
				float valor = Float.parseFloat(JOptionPane.showInputDialog("Introduce el valor de mercado del fichaje: "));
				BD.insertarNuevoValor(eq.getNombre(), j.getNombre(), valor);
			}
		});
		
		this.addWindowListener(new WindowAdapter() { //No lo podemos implementar con Java funcional
			
			@Override
			public void windowClosing(WindowEvent e) {
				BD.closeBD();
				System.out.println("Conexión con la bbdd cerrada");
			}
			
		});
		
		setVisible(true);
	}
	
	private void cargarComboEquipos() {
		Set<Equipo> sEquipos = BD.obtenerEquipos();
		for(Equipo e: sEquipos) {
			cbEquipos.addItem(e);
		}
	}
	
	private void cargarComboJugadores() {
		List<Jugador> lJugadores = BD.obtenerJugadores();
		for(Jugador j: lJugadores){
			cbJugadores.addItem(j);
		}
	}
}
