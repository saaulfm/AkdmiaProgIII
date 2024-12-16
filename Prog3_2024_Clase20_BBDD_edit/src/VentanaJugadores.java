import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VentanaJugadores extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel pNorte, pCentro, pSur;
	private ModeloTablaJugadores modeloTodosLosJugadores, modeloJugadoresEquipo;
	private JTable tablaTodosLosJugadores, tablaJugadoresEquipo;
	private JScrollPane scrollTablaTodos, scrollTablaEquipo;
	private JButton btnAniadirJugador, btnQuitarJugador, btnVolver, btnFinalizarFichaje;
	private JFrame vActual, vAnterior;
	private ArrayList<Jugador> carrito;
	public VentanaJugadores(JFrame va, int idE) {
		super();
		setBounds(300, 200, 600, 400);
		
		vActual = this;
		vAnterior = va;
		pNorte = new JPanel();
		pCentro = new JPanel(new GridLayout(2,1));
		pSur = new JPanel();
		
		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		
		carrito = new ArrayList<>();
		modeloJugadoresEquipo = new ModeloTablaJugadores(carrito);
		modeloTodosLosJugadores = new ModeloTablaJugadores(BD.obtenerTodosLosJugadores());
		tablaJugadoresEquipo = new JTable(modeloJugadoresEquipo);
		tablaTodosLosJugadores = new JTable(modeloTodosLosJugadores);
		scrollTablaEquipo = new JScrollPane(tablaJugadoresEquipo);
		scrollTablaTodos = new JScrollPane(tablaTodosLosJugadores);
		
		pCentro.add(scrollTablaTodos);
		pCentro.add(scrollTablaEquipo);
		
		btnAniadirJugador = new JButton("AÑADIR JUGADOR");
		btnQuitarJugador = new JButton("QUITAR JUGADOR");
		btnFinalizarFichaje = new JButton("FINALIZAR FICHAJE");
		btnVolver = new JButton("VOLVER");
		pSur.add(btnAniadirJugador);
		pSur.add(btnQuitarJugador);
		pSur.add(btnFinalizarFichaje);
		pSur.add(btnVolver);
		
		btnVolver.addActionListener((e)->{
			vActual.setVisible(false);
			vAnterior.setVisible(true);
		});
		
		btnAniadirJugador.addActionListener((e)->{
			//Vamos a obtener la fila seleccionada
			int fila = tablaTodosLosJugadores.getSelectedRow();
			if(fila==-1) {
				JOptionPane.showMessageDialog(null, "Primero debes seleccionado una fila", "ERROR EN SELECCIÓN", JOptionPane.ERROR_MESSAGE);
			} else {
				String nom = (String) modeloTodosLosJugadores.getValueAt(fila, 0);
				int dorsal = (int) modeloTodosLosJugadores.getValueAt(fila, 1);
				float valor = (float) modeloTodosLosJugadores.getValueAt(fila, 2);
				//Object [] rowData = {nom, dorsal, valor};
				carrito.add(new Jugador(nom, dorsal, valor));
				modeloJugadoresEquipo = new ModeloTablaJugadores(carrito);
				tablaJugadoresEquipo.setModel(modeloJugadoresEquipo);
				JOptionPane.showMessageDialog(null, "Jugador añadido al carrito", "FICHAJE", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		btnQuitarJugador.addActionListener((e)->{
			int fila = tablaJugadoresEquipo.getSelectedRow();
			if(fila==-1) {
				JOptionPane.showMessageDialog(null, "Primero debes seleccionado una fila", "ERROR EN SELECCIÓN", JOptionPane.ERROR_MESSAGE);
			}else {
				carrito.remove(fila);
				modeloJugadoresEquipo = new ModeloTablaJugadores(carrito);
				tablaJugadoresEquipo.setModel(modeloJugadoresEquipo);
				JOptionPane.showMessageDialog(null, "Jugador eliminado del carrito", "FICHAJE", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		btnFinalizarFichaje.addActionListener((e)->{
			long fActual = System.currentTimeMillis();
			int i=0;
			while(i<carrito.size()) {
				Jugador j = carrito.get(i);
				BD.insertarFichaje(idE, j.getNombre(), fActual);
				carrito.remove(i);
			}
			modeloJugadoresEquipo = new ModeloTablaJugadores(carrito);
			tablaJugadoresEquipo.setModel(modeloJugadoresEquipo);
			JOptionPane.showMessageDialog(null, "Fichaje finalizado correctamente", "FICHAJE", JOptionPane.INFORMATION_MESSAGE);
		
			
		});
		setVisible(true);
	}
}
