import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class VentanaTabla extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		// Añadir listeners a la tabla
		tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				Component c = null;
				// En el caso de la columna 0 queremos que salga justificada a la izquierda
				// En el caso de la columna 1 queremos que salga justificada en el centro
				// En el caso de la columna 2 queremos motrar una barra de progreso
				if(column == 0) {
					JLabel label = new JLabel(value.toString());
					label.setHorizontalAlignment(JLabel.LEFT);
					c = label;
				} else if(column == 1) {
					JLabel label = new JLabel(value.toString());
					label.setHorizontalAlignment(JLabel.CENTER);
					c = label;
				} else if(column == 2) {
					JProgressBar progressBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 10);
					progressBar.setValue((int)Float.parseFloat(value.toString()));
					progressBar.setStringPainted(true); //Que aparezcan los porcentajes
					progressBar.setForeground(new Color(0, 0, 139)); // Azul oscuro
					progressBar.setBackground(Color.RED);
					progressBar.setToolTipText("La nota es: " + Float.parseFloat(value.toString()));
					c = progressBar;
				}
				return c;
			}
		});
		// Última secuencia
		setVisible(true);
	}
	
	private void cargarTabla() {
		// Creamos una lista con los productos que queremos visualizar en la tabla
		List<Jugador> lJugador = new ArrayList<>();
		lJugador.add(new Jugador(13, "Iñaki Peña", 8.3f));
		lJugador.add(new Jugador(2, "Pau Cubarsí", 7.2f));
		lJugador.add(new Jugador(3, "Alejandro Balde", 8.0f));
		lJugador.add(new Jugador(5, "Iñigo Martinez", 8.0f));
		lJugador.add(new Jugador(23, "Jules Kounde", 7.7f));
		lJugador.add(new Jugador(8, "Pedri", 7.5f));
		lJugador.add(new Jugador(17, "Marc Casadó", 7.9f));
		lJugador.add(new Jugador(16, "Fermín López", 6.5f));
		lJugador.add(new Jugador(9, "Robert Lewandowsky", 9.2f));
		lJugador.add(new Jugador(11, "Raphinha", 8.5f));
		lJugador.add(new Jugador(19, "Lamine Yamal", 8.4f));
		// La lista la añado al modelo
		modeloTabla = new ModeloTablaJugadores(lJugador);
		// Le volvemos a asignar el modelo a la tabla
		tabla.setModel(modeloTabla);
	}
	
	public static void main(String[] args) {
		VentanaTabla vt = new VentanaTabla();	
	}
}
