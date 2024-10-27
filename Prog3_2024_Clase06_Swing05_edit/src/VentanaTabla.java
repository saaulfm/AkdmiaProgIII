import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class VentanaTabla extends JFrame {
	// Declaramos vActual y vAnterior
	private JFrame vActual, vAnterior;
	// Declaramos los botones
	private JButton btnVolver, btnAniadirAsignatura;
	// Declaramos los paneles
	private JPanel pCentro, pSur;
	// Declaramos una tabla
	private JTable tabla;
	private DefaultTableModel modeloTabla;
	private JScrollPane scrollTabla;
	// Declaramos un área de texto
	private JTextArea area;	
	// Declaramos los atributos fila y columna
	private int fila, columna;
	
	public VentanaTabla(JFrame vAnterior) {
		// Primera secuencia
		super();
		// Inicializar a 0 fila y columna
		fila = -1;
		columna = -1;
		// Instanciar vActual
		vActual = this;
		this.vAnterior = vAnterior;
		// Tamaño y posición inicial de la ventana
		setBounds(400, 200, 600, 400);
		// Título de la ventana
		setTitle("DEUSTO - Ventana inicial");
		// Icono de la ventana
		ImageIcon icono = new ImageIcon("img/icono.png");
		setIconImage(icono.getImage());
		// Inicializar paneles
		pCentro = new JPanel(new GridLayout(2, 1));
		pSur = new JPanel();
		// Inicializar botones
		btnVolver = new JButton("VOLVER");
		btnAniadirAsignatura = new JButton("AÑADIR ASIGNATURA");
		// Añadir los paneles al panel principal de la ventana
		getContentPane().add(pCentro, BorderLayout.CENTER);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		// Añadir los botones a la ventana
		pSur.add(btnVolver);
		pSur.add(btnAniadirAsignatura);
				
		/* Crear la tabla */
		// 1º paso ==> Creamos el modelo que va a contener la información
		modeloTabla = new DefaultTableModel();
		// 2º paso ==> Creamos la tabla que va a mostrar la información que hay en el modelo
		tabla = new JTable(modeloTabla);
		//3º paso ==> Añadimos un scroll a la tabla
		scrollTabla = new JScrollPane(tabla);
		// Añadimos un scroll vertical y horizontal
		scrollTabla.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollTabla.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		// Añadimos los títulos de las columnas 
		String[] titulos = {"CÓDIGO", "NOMBRE", "NÚMERO DE CRÉDITOS"};
		modeloTabla.setColumnIdentifiers(titulos);
		// Añadimos el scroll de la tabla al panel central
		pCentro.add(scrollTabla);
		// Cargar la información (asignaturas) en la tabla
		cargarTabla();
		//Impedir que se puedan cambiar las columnas de orden
		tabla.getTableHeader().setReorderingAllowed(false);
				
		// Crear el área de texto
		area = new JTextArea();
		pCentro.add(scrollTabla);
		pCentro.add(area);
		// Modificar la fuente del JTextArea
		area.setFont(new Font(Font.DIALOG, Font.ITALIC, 22));
		area.setBackground(Color.LIGHT_GRAY);
		area.setForeground(Color.DARK_GRAY);
		// Listeners
		btnVolver.addActionListener((e)-> {
			vActual.dispose();
			vAnterior.setVisible(true);
		});
		
		btnAniadirAsignatura.addActionListener((e)->{
			try {
				// Obtener la información del JTextArea
				String texto = area.getText();
				String [] datos = texto.split(";");
				if(datos.length == 3) {
					String codigo = datos[0];
					String nombre = datos[1];
					int numCreditos = Integer.parseInt(datos[2]);
					// Crear una fila con los datos que queremos añadir a la tabla
					Object [] fila = {codigo, nombre, numCreditos};
					// Añadir la fila a la tabla
					modeloTabla.addRow(fila);
					// Borrar la información del JTextArea
					area.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "El número de columnas/datos no es correcto");
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "El formato del número de créditos no es correcto");
			}
			area.setText("");
		});
		
		/*Modificar el Renderer de la tabla*/
		//Ejemplo1: Que la primera columna de la tabla (columna 0) tenga color de fondo gris claro
		/*tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				// Lo que escribamos aquí se va a aplicar a cada una de las celdas de la tabla
				//table -> La referencia a la tabla sobre la que aplicamos el Renderer
				//value -> El contenido de la celda en la que actualmente se está aplicando el renderer
				//isSelected -> Indica si la celda actual está seleccionada
				//hasFocus -> Indica si la celda actual tiene el foco
				//row -> Indica la fila de la celda actual
				//column -> Indica la columna de la celda actual
				
				// Declaro una variable Component
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				// Aplico las modificaciones al componente
				if(column == 0) {
					c.setBackground(Color.LIGHT_GRAY);
				} else {
					c.setBackground(Color.WHITE);
				}
				return c;
			}
		});*/
		
		//Ejemplo 2: Las celdas que estén en la columna 2 tendrán el siguiente formato:
		// Si el número de créditos es 3 -> color de fondo verde
		// Si el número de créditos es 6 -> color de fondo amarillo
		// Si el número de créditos es 9 -> color de fondo rosa
		// En cualquier otro caso, color de fondo blanco
		/*tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				// Declaro una variable Component
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				// Aplico las modificaciones al componente
				if(column == 2) {
					// Convertir el Object a int
					int numCreditos = Integer.parseInt(value.toString());
					if(numCreditos == 3) {
						c.setBackground(Color.GREEN);
					} else if(numCreditos == 6) {
						c.setBackground(Color.YELLOW);
					} else if(numCreditos == 9) {
						c.setBackground(Color.PINK);
					} else {
						c.setBackground(Color.WHITE);
					}
				} else {
					c.setBackground(Color.WHITE);
				}
				return c;
			}
		});*/
		
		//Ejemplo3: Igual que el ejemplo 2, pero en lugar de modificar sólo la columna 2 en función del
		//número de créditos, modificamos toda la fila
		/*tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				// Declaro una variable Component
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				// Como tengo que modificar toda la fila, voy a trabajar con el modelo de la tabla
				// Para obtener el modelo de la tabla, me fijo en el modelo que le he asignado al crearla
				DefaultTableModel modelo = (DefaultTableModel) table.getModel();
				// Ahora compruebo el valor del número de créditos de la fila
				Object valor = modelo.getValueAt(row, 2);
				// Convertir el Object a int
				int numCreditos = Integer.parseInt(valor.toString());
				if(numCreditos == 3) {
					c.setBackground(Color.GREEN);
				} else if(numCreditos == 6) {
					c.setBackground(Color.YELLOW);
				} else if(numCreditos == 9) {
					c.setBackground(Color.PINK);
				} else {
					c.setBackground(Color.WHITE);
				}
				return c;
			}
		});*/
		
		//Ejemplo 4: El color de fondo de la celda cambiará a Rosa cuando el puntero del ratón pase sobre ella
		//Lo primero que necesitamos saber es en qué celda está el puntero del ratón
		tabla.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// Obtener las coordenadas donde está el puntero del ratón
				Point p = e.getPoint();
				// Obtener la fila de la tabla situada en el punto p
				fila = tabla.rowAtPoint(p);
				// Obtener la columna de la tabla situada en el punto p
				columna = tabla.columnAtPoint(p);
				// Imprimir la fila y columna cada vez que se mueve el ratón
				System.out.println(fila + " - " + columna);
				// Forzar a que se vuelva a aplicar el Renderer
				tabla.repaint();	
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
			}
		});
		
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
				// Se ejecuta cuando el puntero del ratón se sale de la tabla
				fila = -1;
				columna = -1;
				tabla.repaint();
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				// Declaro una variable Component
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if(row == fila && column == columna) {
					c.setBackground(Color.PINK);
				} else {
					c.setBackground(Color.WHITE);
				}
				return c;
			}
		});
		
		// Última secuencia
		setVisible(true);
	}
	
	private void cargarTabla() {
		//Vamos a cargar a tabla con la información del fichero asignaturas.txt
		File f = new File("ficheros/asignaturas.txt");
		if(f.exists()) {
			try {
				Scanner sc = new Scanner(f);
				while(sc.hasNextLine()) { //Mientras queden líneas por leer
					String linea = sc.nextLine(); //Leemos una línea del fichero
					//linea = "1;ASIG 1;3"
					//Vamos a dividir la línea en 3 partes, que están separadas por ;
					String [] datos = linea.split(";");
					//datos[0] = "1"
					//datos[1] = "ASIG 1"
					//datos[2] = "3" -> Lo convertiremos a int
					//Vamos a crear una fila para la tabla y le vammos a asignar los 3 datos
					Object [] fila = {datos[0], datos[1], Integer.parseInt(datos[2])};
					//Añadimos la fila al modelo de la tabla
					modeloTabla.addRow(fila);
				}
				sc.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
