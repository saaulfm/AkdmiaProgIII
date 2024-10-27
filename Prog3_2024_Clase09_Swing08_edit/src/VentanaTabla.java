import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VentanaTabla extends JFrame {
	// Declarar componentes de la ventana
	private ModeloTablaJugadores modeloTabla;
	private JTable tabla;
	private JScrollPane scrollTabla;
	
	public VentanaTabla() {
		// Primera secuencia
		super();
		// Tamaño y posición inicial de la ventana
		setBounds(300, 200, 600, 400);
		// Instanciar los componentes de la JTable
		modeloTabla = new ModeloTablaJugadores(null);
		tabla = new JTable(modeloTabla);
		scrollTabla = new JScrollPane(tabla);
		// Añadir la tabla a la ventana
		getContentPane().add(scrollTabla, BorderLayout.CENTER);
		// Cargar la tabla con jugadores
		cargarTabla();
		// Añadir listener a la tabla
		tabla.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					// Obtener el punto (x, y) sobre el que he hecho doble click
					Point p = e.getPoint();
					// Obtener la fila de la tabla correspondiente a ese punto
					int fila = tabla.rowAtPoint(p);
					// Eliminar esa fila
					modeloTabla.removeRow(fila);
					// Actualizar el modelo de la tabla
					tabla.setModel(modeloTabla);
					// Redibujar la tabla
					tabla.repaint();
				}
				// Cuando el usuario haga click derecho, preguntar por 
				// los datos de un nuevo jugador y añadirlos a la tabla
				if(e.getButton() == MouseEvent.BUTTON3) {
					int dorsal = Integer.parseInt(JOptionPane.showInputDialog("Introduce el dorsal del nuevo jugador: "));
					String nombre = JOptionPane.showInputDialog("Introduce el nombre del nuevo jugador: ");
					String posicion = JOptionPane.showInputDialog("Introduce la posición del nuevo jugador: ");
					int anioNacimiento = Integer.parseInt(JOptionPane.showInputDialog("Introduce el año de nacimiento del nuevo jugador: "));
					
					Object[] nuevaFila = {dorsal, nombre, posicion, anioNacimiento};
					modeloTabla.addRow(nuevaFila);
					tabla.repaint();
				}
				
			}
		});
		
		// Última secuencia
		setVisible(true);
	}
	
	private void cargarTabla() {
		// Creamos una lista con los productos que queremos visualizar en la tabla
		List<Jugador> lJugador = new ArrayList<>();
		lJugador.add(new Jugador(1, "Ter Stegen", "POR", 1992));
		lJugador.add(new Jugador(2, "Pau Cubarsí", "DFC", 2006));
		lJugador.add(new Jugador(3, "Alejandro Balde", "LI", 2003));
		lJugador.add(new Jugador(5, "Iñigo Martinez", "DFC", 1991));
		lJugador.add(new Jugador(23, "Jules Kounde", "LD", 1998));
		lJugador.add(new Jugador(8, "Pedri", "MC", 2002));
		lJugador.add(new Jugador(6, "Gavi", "MC", 2004));
		lJugador.add(new Jugador(20, "Dani Olmo", "MCO", 1998));
		lJugador.add(new Jugador(9, "Robert Lewandowsky", "DC", 1988));
		lJugador.add(new Jugador(11, "Raphinha", "EI", 1996));
		lJugador.add(new Jugador(19, "Lamine Yamal", "ED", 2007));
		// La lista la añado al modelo
		modeloTabla = new ModeloTablaJugadores(lJugador);
		// Le volvemos a asignar el modelo a la tabla
		tabla.setModel(modeloTabla);
	}
	public static void main(String[] args) {
		VentanaTabla vt = new VentanaTabla();
	}
}
