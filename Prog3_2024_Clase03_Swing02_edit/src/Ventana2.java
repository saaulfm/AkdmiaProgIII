import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Ventana2 extends JFrame{
	// Declarar componentes de la ventana
	private JButton btnVolver;
	private JPanel pNorte, pSur, pEste, pOeste, pCentro;
	// Declarar panel actual y ventana anterior:
	// ⦿ vActual va a guardar el acceso a esta ventana
	// ⦿ vAnterior va a guardar el acceso a la ventana desde la que se ha abierto esta
	private JFrame vActual, vAnterior;
	// Declarar lista desplegable --> JComboBox
	private JComboBox<String> cbColores;
	// Declarar lista desplegada (no desplegable) --> JList
	private JList<String> lColores;
	
	public Ventana2(JFrame vAnterior) {
		// Primera secuencia
		super();
		// Tamaño y posición inicial de la ventana
		setBounds(400, 200, 600, 400);
		// Título de la ventana
		setTitle("DEUSTO - Ventana 2");
		// Icono de la ventana
		ImageIcon icono = new ImageIcon("img/lamineXrapha.jpg");
		setIconImage(icono.getImage());
		// Instanciar vActual y vAnterior
		vActual = this;
		this.vAnterior = vAnterior;
		// Inicializar paneles
		pNorte = new JPanel();
		pSur = new JPanel();
		pEste = new JPanel();
		pOeste = new JPanel();
		pCentro = new JPanel();
		// Añadir los paneles al panel principal de la ventana
		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		getContentPane().add(pEste, BorderLayout.EAST);
		getContentPane().add(pOeste, BorderLayout.WEST);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		// Inicializar componentes (botones, etiquetas, texto)
		btnVolver = new JButton("VOLVER");
		// Crear array con los items que vamos a cargar en JComboBox
		String[] items = {"ROJO", "AZUL", "AMARILLO"};
		cbColores = new JComboBox<>(items);
		cbColores.setSelectedItem(null);
		
		String[] elementos = {"ROSA", "NEGRO"};
		lColores = new JList<>(elementos);
		// Añadir los componentes a la ventana
		pSur.add(btnVolver);
		pOeste.add(cbColores);
		pEste.add(lColores);
		// Añadir los listeners a los componentes
		btnVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Código que se va a ejecutar cuando pulse el botón
				vActual.dispose();
				vAnterior.setVisible(true);
				
			}
		});
		
		cbColores.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Como los items del JComboBox son de tipo String, declaro una variable de tipo String
				String item = cbColores.getSelectedItem().toString();
				if(item.equals("ROJO")) {
					pCentro.setBackground(Color.RED);
				}else if(item.equals("AZUL")) {
					pCentro.setBackground(Color.BLUE);
				}else if(item.equals("AMARILLO")) {
					pCentro.setBackground(Color.YELLOW);
				}
			}
		});
		
		lColores.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// Obtengo el elemento seleccionado por el usuario
				// Como los elementos de la lista son de tipo String, tengo que declarar una variable de tipo String
				String elemento = lColores.getSelectedValue();
				if(elemento.equals("ROSA")) {
					pNorte.setBackground(Color.PINK);
				}else if(elemento.equals("NEGRO")){
					pNorte.setBackground(Color.BLACK);
				}
			}
		});
		// Última secuencia
		setVisible(true);
	}
}
	
