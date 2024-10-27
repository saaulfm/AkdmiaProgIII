package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import clases.Jugador;

public class VentanaDatosJugador extends JFrame {
	// Declarar componentes de la ventana
	private JPanel pCentro, pSur, pInfo;
	private JButton btnElegir, btnCerrar;
	private JLabel lblFoto, lblDorsal, lblNombre, lblPosicion, lblAnioNacimiento;
	
	public VentanaDatosJugador(Jugador j) {
		// Primera secuencia
		super();
		// Tamaño y posición inicial de la ventana
		setBounds(1100, 100, 300, 400);
		// Inicializar paneles
		pInfo = new JPanel(new GridLayout(4, 1));
		pCentro = new JPanel(new GridLayout(1, 2));
		pSur = new JPanel();
		// Inicializar componentes
		btnElegir = new JButton("ELEGIR");
		btnCerrar = new JButton("CERRAR");
		// Añadir los paneles a la ventana
		getContentPane().add(pCentro, BorderLayout.CENTER);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		
		// Obtener los elementos de la ventana
		ImageIcon im = new ImageIcon(j.getRutaFoto());
		lblFoto = new JLabel(im);
		pCentro.add(lblFoto);
		
		lblDorsal = new JLabel(String.valueOf(j.getDorsal()));
		lblNombre = new JLabel(j.getNombre());
		lblPosicion = new JLabel(j.getPosicion());
		lblAnioNacimiento = new JLabel(String.valueOf(j.getAnioNacimiento()));
		// Añadir componentes a la ventana
		pInfo.add(lblDorsal);
		pInfo.add(lblNombre);
		pInfo.add(lblPosicion);
		pInfo.add(lblAnioNacimiento);
		pCentro.add(pInfo);
		
		pSur.add(btnElegir);
		pSur.add(btnCerrar);
		
		// Añadir los listeners a los componentes
		btnCerrar.addActionListener((e)-> {
			dispose();
		});
		
		// Última secuencia
		setVisible(true);
		
	}
}
