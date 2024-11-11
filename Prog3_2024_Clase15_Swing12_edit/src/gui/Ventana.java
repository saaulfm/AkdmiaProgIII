package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

import domain.CadenaEquipos;
import domain.Jugador;
import domain.Pais;

public class Ventana extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel pOeste;
	private CadenaEquipos ce;
	
	/*Atributos para el JTree*/
	private DefaultTreeModel modeloArbol;
	private JTree arbol;
	private JScrollPane scrollArbol;
	
	/*Atributos para la JList*/
	private DefaultListModel<Pais> modeloLista;
	private JList<Pais> lista;
	private JScrollPane scrollLista;
	
	/*Atributos para la JTable*/
	private ModeloTabla modeloTabla;
	private JTable tabla;
	private JScrollPane scrollTabla;
	
	public Ventana() {
		super();
		setBounds(300, 200, 600, 400);
		
		ce = new CadenaEquipos();
		ce.cargarJugadores();
		pOeste = new JPanel(new GridLayout(2, 1));
		getContentPane().add(pOeste, BorderLayout.WEST);
		
		/*Creación del JTree*/
		//Creamos el nodo raíz del arbol
		DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("PAÍS");
		modeloArbol = new DefaultTreeModel(raiz);
		arbol = new JTree(modeloArbol);
		scrollArbol = new JScrollPane(arbol);
		pOeste.add(scrollArbol);
		//Cargamos las provincias en el árbol
		cargarArbol();
		
		/*Creación de la JList*/
		modeloLista = new DefaultListModel<>();
		lista = new JList<>(modeloLista);
		scrollLista = new JScrollPane(lista);
		pOeste.add(scrollLista);
		cargarLista();
		
		/*Creación de la JTable*/
		modeloTabla = new ModeloTabla(null);
		tabla = new JTable(modeloTabla);
		scrollTabla = new JScrollPane(tabla);
		getContentPane().add(scrollTabla, BorderLayout.CENTER);
		
		/*LISTENERS*/
		arbol.addTreeSelectionListener((e)->{
			TreePath tp = e.getPath();
			DefaultMutableTreeNode n = (DefaultMutableTreeNode) tp.getLastPathComponent();
			String s = n.toString();
			if(!s.equals("PAÍS")) {
				//Queremos mostrar en la tabla los jugadores de ese país
				//¿Dónde están los jugadores de cada país? En el mapa
				//Vamos a obtener los jugadores del país seleccionado
				List<Jugador> lj = ce.getMapaJugadores().get(Pais.valueOf(s));
				modeloTabla = new ModeloTabla(lj);
				tabla.setModel(modeloTabla);
			}
		});
		
		lista.addListSelectionListener((e)->{
			/*Cuando el usuario seleccione un país de la lista, queremos mostrar en la tabla
			 * todos los jugadores de ese país
			 * Primero, obtenemos el país seleccionado en la lista*/
			Pais p = lista.getSelectedValue();
			
			/*En el atributo cs de la ventana, está toda la información que contiene el HashMap*/
			List<Jugador> ls = ce.getMapaJugadores().get(p);
			modeloTabla = new ModeloTabla(ls);
			tabla.setModel(modeloTabla);;
		});
		
		tabla.setRowHeight(30);

		/*DEBERES
		 * 
		 * Añadir el listener a la JList
		 * Los países de la lista que empiecen por E tienen que tener color de letra ROJO
		 * Los que empiecen por A color de letra AZUL
		 * Los que empiecen por B color de letra VERDE
		 * 
		 * En la tabla, aquellos jugadores que tengan más de 20 goles tienen que aparecen con fondo AMARILLO
		 * 
		 * Cuando hagamos SHIFT+R sobre la tabla tiene que salir por pantalla el mensaje "El Barcelona va a ganar la sexta"*/
		
		lista.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
		    // Creamos un JLabel para representar cada elemento de la lista
		    JLabel label = new JLabel(value.toString());
		    
		    // Cambiamos el color de la letra según la inicial de la provincia
		    if (value.toString().startsWith("E")) {
		        label.setForeground(Color.RED);
		    } else if (value.toString().startsWith("A")) {
		        label.setForeground(Color.CYAN);
		    } else if (value.toString().startsWith("B")) {
		        label.setForeground(Color.GREEN);
		    }
		    
		    // Si la celda está seleccionada, mantenemos el fondo de selección
		    if (isSelected) {
		        label.setBackground(list.getSelectionBackground());
		        label.setOpaque(true);
		    }
		    
		    return label;
		});
		
		tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				// Para acceder a los datos de la tabla obtener el modelo
				ModeloTabla modelo = (ModeloTabla) table.getModel();
				Jugador j = modelo.lJugadores.get(row);
				
				// Comprobar que el jugador tiene más de 20 goles
				if(j.getGoles() > 20) {
					c.setBackground(Color.YELLOW);
				} else {
					c.setBackground(Color.WHITE);
				}
				
				// Mantener el color de selección cuando la fila está seleccionada
		        if (isSelected) {
		            c.setBackground(table.getSelectionBackground());
		        }
				return c;
			}
		});
		
		KeyListener myKeyListener = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) { }

			@Override
			public void keyReleased(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_R && e.isShiftDown()) {
					System.out.println("El Barcelona va a ganar la sexta");
				}
			}
		};

		this.tabla.addKeyListener(myKeyListener);
		
		/*FIN DE DEBERES*/
		
		// Renderer para cabecera de la tabla
		
		tabla.getTableHeader().setBorder(null);
		tabla.setIntercellSpacing(new Dimension(0, 0));
		tabla.setBorder(null);
		tabla.getTableHeader().setDefaultRenderer((table,value,isSelectd,hasFocus,row,column)->{
			JLabel l = new JLabel();
			l.setHorizontalAlignment(JLabel.CENTER);
			l.setBackground(table.getBackground());
			if(column==0) {
				ImageIcon im = new ImageIcon("img/P.png");
				ImageIcon ime = new ImageIcon(im.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
				l.setIcon(ime);
			}else {
				l.setText(value.toString());
			}
			return l;
		});
		
		// Renderer para la tabla
		tabla.setDefaultRenderer(Object.class, (table,value,isSelected,hasFocus,row,column)->{
			JLabel l = new JLabel();
			l.setHorizontalAlignment(JLabel.CENTER);
			l.setOpaque(true);
			if(column == 2) {
				l.setText(value.toString());
				int goles = Integer.valueOf(value.toString());
				if(goles>20) {
					l.setBackground(Color.YELLOW);
				} else {
					l.setBackground(table.getTableHeader().getBackground());
				}
			} else if(column == 0) {
				if(value.toString().equals("ESPAÑA")) {
					ImageIcon im = new ImageIcon("img/españa.png");
					ImageIcon ime = new ImageIcon(im.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
					l.setIcon(ime);
				}else if(value.toString().equals("ARGENTINA")) {
					ImageIcon im = new ImageIcon("img/argentina.png");
					ImageIcon ime = new ImageIcon(im.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
					l.setIcon(ime);
			
				}else {
					ImageIcon im = new ImageIcon("img/brasil.png");
					ImageIcon ime = new ImageIcon(im.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
					l.setIcon(ime);
			
				}
				
				l.setBackground(table.getTableHeader().getBackground());
			}else {
				l.setText(value.toString());
				l.setBackground(table.getTableHeader().getBackground());
			}
			
			
			return l;
		});
		
		setVisible(true);
	}
	
	private void cargarLista() {
		//Recorrer los países de la enumeración
		for(Pais p: Pais.values()) {
			modeloLista.addElement(p);
		}
	}
	
	private void cargarArbol() {
		//Recorrer los países de la enumeración
		int pos = 0;
		for(Pais p: Pais.values()) {
			DefaultMutableTreeNode nuevo = new DefaultMutableTreeNode(p);
			modeloArbol.insertNodeInto(nuevo, (MutableTreeNode) modeloArbol.getRoot(), pos);
			pos++;
		}
	}
	
	public static void main(String[] args) {
		new Ventana();
	}
}
