package main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import domain.Competicion;
import domain.Contenedora;
import domain.ModeloTablaCompeticiones;

public class Ventana extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel pSur,pNorte,pCentro,pEste,pOeste;
	private JButton btnAniadirCompeticion;
	private JComboBox<Integer> cbAnios;
	
	private ModeloTablaCompeticiones modeloTabla;
	private JTable tabla;
	private JScrollPane scrollTabla;
	
	public Ventana() {
		super();
		setBounds(300, 200, 600, 400);
		pSur = new JPanel();
		pNorte = new JPanel();
		pCentro = new JPanel(new GridLayout(2, 1));
		pEste = new JPanel();
		pOeste = new JPanel();
		
		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		getContentPane().add(pEste, BorderLayout.EAST);
		getContentPane().add(pOeste, BorderLayout.WEST);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		
		btnAniadirCompeticion = new JButton("AÑADIR COMPETICIÓN");
		pSur.add(btnAniadirCompeticion);
		
		cbAnios = new JComboBox<>();
		pOeste.add(cbAnios);
		
		
		modeloTabla = new ModeloTablaCompeticiones(null);
		tabla = new JTable(modeloTabla);
		scrollTabla = new JScrollPane(tabla);
		pCentro.add(scrollTabla);
		
		// Añadir listener al botón 'AÑADIR COMPETICIÓN'
		btnAniadirCompeticion.addActionListener((e)->{
			String nombre = JOptionPane.showInputDialog("Introduce el nombre de la nueva competición: ");
			int anio = Integer.parseInt(JOptionPane.showInputDialog("Introduce el año de temporada de la nueva competición: "));
			Competicion c = new Competicion(nombre, anio);
			//Vamos a añadir la competición al mapa de competiciones
			//Para ello, primero comprobar si existe en el mapa la clave asociada a esa competición
			if(Contenedora.hmCompeticiones.containsKey(anio)) { //Si la clave "anio" está en el mapa
				//Accedemos a la lista de competiciones del año "anio" y le añadimos la nueva competición
				Contenedora.hmCompeticiones.get(anio).add(c);
			}else { //Si la clave "anio" no está en el mapa
				//Primero: creamos la clave
				//Segundo: añadimos la competición
				//Tercero: como es un nuevo año, también añadimos el año al JComboBox
				Contenedora.hmCompeticiones.put(anio, new ArrayList<>());
				Contenedora.hmCompeticiones.get(anio).add(c);
				cbAnios.addItem(anio);
			}
			JOptionPane.showMessageDialog(null, "Competición añadida correctamente");
		});
		
		// Añadir listener al ComboBox 'cbAnios'
		cbAnios.addActionListener((e)->{
			int anio = Integer.parseInt(cbAnios.getSelectedItem().toString());
			modeloTabla = new ModeloTablaCompeticiones(Contenedora.hmCompeticiones.get(anio));
			tabla.setModel(modeloTabla);
		});
		
		// Añadir renderer a la tabla
		tabla.setDefaultRenderer(Object.class, (table,value,isSelected,hasFocus,row,column)->{
			JLabel lblCelda = new JLabel();
			lblCelda.setOpaque(true);
			if(column == 0) {
				lblCelda.setText(value.toString()); //como value es de tipo Object lo convertimos a String
				lblCelda.setHorizontalAlignment(JLabel.LEFT);
				lblCelda.setBackground(Color.PINK);
			}else if(column == 1) {
				lblCelda.setText(value.toString());
				lblCelda.setHorizontalAlignment(JLabel.CENTER);
				lblCelda.setBackground(Color.WHITE);
			}else {
				lblCelda.setHorizontalAlignment(JLabel.CENTER);
				int num = Integer.parseInt(value.toString());
				if(num==0) {
					ImageIcon im = new ImageIcon("img/cero.png");
					lblCelda.setIcon(im);
				}else if(num==1) {
					ImageIcon im = new ImageIcon("img/uno.png");
					lblCelda.setIcon(im);
				}else if(num==2) {
					ImageIcon im = new ImageIcon("img/dos.jpg");
					lblCelda.setIcon(im);
				}
			}
			return lblCelda;
		});
		
		// Hacer visible la ventana
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Ventana();
	}
}
