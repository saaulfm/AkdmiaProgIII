package checkin.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import checkin.domain.Aircraft;
import checkin.domain.Seat;
import checkin.domain.Seat.SeatClass;

public class JFrameCheckIn extends JFrame {

	private static final long serialVersionUID = 1L;
	protected Aircraft aircraft;	
	private JTable seatsTable;
	
	public JFrameCheckIn(Aircraft aircraft) {
		this.aircraft = aircraft;
		
		initTable();
		
		JScrollPane seatsScrollPane = new JScrollPane(this.seatsTable);
		seatsScrollPane.setBorder(new TitledBorder("Seats"));
		this.seatsTable.setFillsViewportHeight(true);

		//TAREA 3. Implementa un evento de teclado [1 punto]
		//Al pulsar la combinación de teclas CTRL + C cuando el foco se encuentre
		//en la tabla, debes abrir el cuadro de diálogo JDialogSeatAllocator para
		//buscar y confirmar asientos (será suficiente con llamar al constructor 
		//de esta ventana de diálogo. El cuadro de diálogo tiene toda la funcionalidad
		//implementada y hace uso de los métodos de la clase SeatAllocator.
		
		KeyListener myKeyListener = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) { }

			@Override
			public void keyReleased(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_C && e.isControlDown()) {
					System.out.println("Has pulsado CTRL+C");
				} else if (e.getKeyCode() == KeyEvent.VK_X && e.isControlDown()) {
					System.out.println("Has pulsado CTRL+X");
				}
			}
		};

		this.seatsTable.addKeyListener(myKeyListener);
		
		this.setTitle("'" + aircraft.getName() + "' check-in window");		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		this.getContentPane().setLayout(new BorderLayout());		
		this.getContentPane().add(seatsScrollPane, BorderLayout.CENTER);				

		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setVisible(true);		
	}
	
	private void initTable() {
		//TAREA 1. Crea un modelo de datos para un JTable [2 puntos]
		//Debes crear un modelo de datos para mostrar la información de los asientos
		//fila a fila. Adapta la carga del modelo a los asientos del avión: el que 
		//se usa como referencia tiene 38 filas, cada una de ellas con 6 asientos 
		//identificados con letras de la A a la F: suponemos que todos los aviones
		//tienen estos 6 asientos por cada fila.
		//Por lo tanto, el modelo de datos debe permitir cargar los asientos por filas
		//(en el ejemplo, desde la 1 a la 38) y devolver la información en 8 columnas:
		//la columna 0 devuelve el número de fila (Integer), las columnas 1-3 los 
		//asientos de la A a la C (Seat), la columna 4 será una columna vacía para
		//simular el pasillo (String vacío) y finalmente las columnas 5-7 los asientos
		//de la D a la F (Seat). Haz que el constructor del modelo reciba una lista
		//List<Seat> de los asientos para inicializarlo. La fila de tabla 0 corresponde
		//a la fila 1 del avión, y así sucesivamente. Todas las celdas deben ser no editables.
		//Fíjate cómo se crean los asientos en el método initPlane() de la clase 
		//Main.java para saber el orden en que están almacenados en la lista 
		//List<Seat> seats de la clase Aircraft.java.
		
//		Vector<String> header = new Vector<String>(Arrays.asList( "", "A", "B", "C", "", "D", "E", "F"));
//		this.seatsTable = new JTable(new DefaultTableModel(new Vector<Vector<Object>>(), header));	
		
		// Crear y cargar el modelo que acabo de crear
		ModeloTabla modelo = new ModeloTabla(aircraft.getSeats());
		this.seatsTable = new JTable(modelo);
		
		//TAREA 2. Implementa un Renderer personalizado para un JTable [2 puntos]
		//Debes modificar la manera en que se dibuja la tabla
		//- En la cabecera debe aparecer la imagen de las letras con color de fondo
		//  RGB(237, 237, 237). Encontrarás las imágenes en resources/images/A.png
		//  (B.png, C.png, etc.)
		//- La 1.ª y 4.ª columna de la cabecera aparecen vacías y con el color de
		//  fondo por defecto de la tabla.
		//- En la 1.ª columna de datos aparecen los números de fila con el color
		//  de fondo por defecto de la tabla.
		//- Los asientos en las filas FIRST_CLASS y EMERGENCY tienen colores de 
		//  fondo personalizados RGB(245, 247, 220) y RGB(252, 191, 183)
		//  respectivamente.
		//- Cada asiento se pinta con la imagen Occupied.png o Available.png según
		//  se encuentre (en resources/images/Available.png y Occupied.png).
		//- La columna 4ª, que representa el pasillo, debe aparecer vacía con el 
		//  color de fondo por defecto de la tabla.
		//- La altura por defecto de todas las filas de la tabla debe ser 32 píxeles.
		
		this.seatsTable.getTableHeader().setDefaultRenderer((table,value,isSelected,hasFocus,row,column) -> {
			JLabel l = new JLabel();
			l.setOpaque(true);
			l.setHorizontalAlignment(JLabel.CENTER);
			if(column == 0 || column == 4) {
				l.setBackground(table.getBackground());
			} else {
				l.setBackground(new Color(237, 237, 237));
				ImageIcon im = new ImageIcon("resources/images/" + value.toString()+".png");
				l.setIcon(im);
			}
			return l;
		});
		
		this.seatsTable.setDefaultRenderer(Object.class, (table,value,isSelected,hasFocus,row,column) -> {
			JLabel l = new JLabel();
			l.setOpaque(true);
			l.setHorizontalAlignment(JLabel.CENTER);
			if(column == 0 || column == 4) {
				l.setBackground(table.getBackground());
				l.setText(value.toString());
			} else {
				Seat s = (Seat) value;
				if(s.getSeatClass().equals(SeatClass.FIRST_CLASS)) {
					l.setBackground(new Color(245, 247, 220));
				} else if(s.getSeatClass().equals(SeatClass.EMERGENCY )) {
					l.setBackground(new Color(252, 191, 183));
				}
				
				if(s.isOccupied()) {
					ImageIcon im = new ImageIcon("resources/images/Occupied.png");
					l.setIcon(im);
				} else {
					ImageIcon im = new ImageIcon("resources/images/Available.png");
					l.setIcon(im);
				}
			}
			return l;
		});
		this.seatsTable.setRowHeight(32);
	}
}