package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import clases.Jugador;
import clases.Plantilla;

public class VentanaPlantilla extends JFrame{
	// Declarar componentes de la ventana
	private JPanel pNorte, pCentro;
	private JLabel lblNombrePlantilla;
	
	public VentanaPlantilla() {
		// Primera secuencia
		super();
		// Tamaño y posición inicial de la ventana
		setBounds(100, 100, 1000, 500);
		// Título de la ventana´
		setTitle("PLANTICA F.C BARCELONA");
		// Inicializar paneles
		pNorte = new JPanel();
		pCentro = new JPanel(new GridLayout(0, 3));
		// Cargar jugadores
		cargarJugadores();
		// Inicializar componentes
		JScrollPane scroll = new JScrollPane(pCentro);
		lblNombrePlantilla = new JLabel("PLANTILLA F.C BARCELONA");
		// Añadir los componentes a la ventana
		getContentPane().add(scroll, BorderLayout.CENTER);
		getContentPane().add(lblNombrePlantilla, BorderLayout.NORTH);
		// Añadir los listeners a los componentes
		pCentro.addMouseListener(new MouseListener() {
			
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
				// Este método se ejecuta cuando click (pulsar y soltar)
				// Queremos saber sobre qué jugador hemos hecho click
				// Obtener el putno sobre el que he hecho click
				Point punto = e.getPoint();
				// Obtener la JLabel que hay en ese punto
				JLabel lblFoto = (JLabel) pCentro.getComponentAt(punto);
				// Obtener la imagen que contiene la JLabel
				ImageIcon im = (ImageIcon) lblFoto.getIcon();
				// Obtener la ruta de la foto asociada a esa imagen
				String rutaFoto = im.getDescription();
				// Buscar en el ArrayList el jugador que tiene esa ruta
				Jugador j = Plantilla.buscarJugador(rutaFoto);
				// Abrir la ventana que muestra los datos del jugador j
				new VentanaDatosJugador(j);
			}
		});
		
		// Última secuencia
		setVisible(true);
	}
	
	private void cargarJugadores() {
		// Recorrer el ArrayList que hemos declarado en la clase Plantilla y cada jugador
		// que contiene la mostramos en el pCentro
		for(Jugador j: Plantilla.getAlJugadores()) {
			//Quiero mostrar la imagen de la película p en el panel central
			//Para poder mostrar una imagen tenemos que crear un ImageIcon con la ruta de la foto
			ImageIcon im = new ImageIcon(j.getRutaFoto());
			im.setDescription(j.getRutaFoto());
			// Cargar la imagen en un JLabel
			JLabel lblFoto = new JLabel(im);
			// Cargar JLabel en el pCentro
			pCentro.add(lblFoto);
		}
	}
}
