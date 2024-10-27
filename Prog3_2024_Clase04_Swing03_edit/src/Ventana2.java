import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Ventana2 extends JFrame{
	// Declarar componentes de la ventana
	private JButton btnVolver, btnMatricularUna, btnMatricularTodas, btnDesmatricularUna, btnDesmatricularTodas;
	private JPanel pNorte, pSur, pEste, pOeste, pCentro, pMedio;
	// Declarar panel actual y ventana anterior:
	// ⦿ vActual va a guardar el acceso a esta ventana
	// ⦿ vAnterior va a guardar el acceso a la ventana desde la que se ha abierto esta
	private JFrame vActual, vAnterior;
	// Declarar lista desplegable --> JComboBox
	private JComboBox<String> cbColores;
	// Declarar lista desplegada (no desplegable) --> JList
	private JList<String> lColores;
	// Declarar listas
	private JList<String> lTodasLasAsignaturas, lAsignaturasMatriculadas;
	private DefaultListModel<String> mTodasLasAsignaturas, mAsignaturasMatriculadas;
	
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
		pCentro.setLayout(new GridLayout(1, 3));
		pMedio = new JPanel();
		pMedio.setLayout(new GridLayout(4, 1));
		// Añadir los paneles al panel principal de la ventana
		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		getContentPane().add(pEste, BorderLayout.EAST);
		getContentPane().add(pOeste, BorderLayout.WEST);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		// Inicializar componentes (botones, etiquetas, texto)
		btnVolver = new JButton("VOLVER");
		btnVolver.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		btnMatricularUna = new JButton(" > ");
		btnDesmatricularUna = new JButton(" < ");
		btnMatricularTodas = new JButton(" >> ");
		btnDesmatricularTodas = new JButton(" << ");
		// Instanciar los componentes de tipo lista
		lTodasLasAsignaturas = new JList<>(new DefaultListModel<>());
		cargarFicheroDeAsignaturasALista();
		lAsignaturasMatriculadas = new JList<>(new DefaultListModel<>());
		// Añadir un scroll a las lista
		JScrollPane sListaTodasLasAsignaturas = new JScrollPane(lTodasLasAsignaturas);
		JScrollPane sListaAsignaturasMatriculadas = new JScrollPane(lAsignaturasMatriculadas);
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
		pMedio.add(btnMatricularUna);
		pMedio.add(btnDesmatricularUna);
		pMedio.add(btnMatricularTodas);
		pMedio.add(btnDesmatricularTodas);
		pCentro.add(sListaTodasLasAsignaturas);
		pCentro.add(pMedio);
		pCentro.add(sListaAsignaturasMatriculadas);
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
		
		btnMatricularUna.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String asig = lTodasLasAsignaturas.getSelectedValue();
				if(asig==null) {
					JOptionPane.showMessageDialog(null, "Primero tienes que seleccionar una asignatura", "ERROR", JOptionPane.ERROR_MESSAGE);
				}else {
					// Obtener acceso a los modelos de la lista
					mTodasLasAsignaturas = (DefaultListModel<String>) lTodasLasAsignaturas.getModel();
					mAsignaturasMatriculadas = (DefaultListModel<String>) lAsignaturasMatriculadas.getModel();
					mAsignaturasMatriculadas.addElement(asig);
					mTodasLasAsignaturas.removeElement(asig);
				}
			}
		});
		
		btnMatricularTodas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mTodasLasAsignaturas = (DefaultListModel<String>) lTodasLasAsignaturas.getModel();
				mAsignaturasMatriculadas = (DefaultListModel<String>) lAsignaturasMatriculadas.getModel();
				// Ir quitando asignaturas de la lista de todas las asignaturas y añadirlas a la lista de matriculadas
				// hasta que la lista que contenía todas se vacíe
				while(!mTodasLasAsignaturas.isEmpty()) {
					// Obtener el acceso a la primera asignatura
					String asig = mTodasLasAsignaturas.getElementAt(0);
					// Añadir esta asignatura a la de matriculados
					mAsignaturasMatriculadas.addElement(asig);
					// Boorar esa asignatura de la lista de todas las asignaturas
					mTodasLasAsignaturas.removeElement(asig);
				}
			}
			
			
		});
		
		btnDesmatricularUna.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String asig = lAsignaturasMatriculadas.getSelectedValue(); // Obtener la asignatura seleccionada
		        if(asig == null) {
		            JOptionPane.showMessageDialog(null, "Primero tienes que seleccionar una asignatura", "ERROR", JOptionPane.ERROR_MESSAGE);
		        } else {
		            // Obtener acceso a los modelos de la lista
		            mTodasLasAsignaturas = (DefaultListModel<String>) lTodasLasAsignaturas.getModel();
		            mAsignaturasMatriculadas = (DefaultListModel<String>) lAsignaturasMatriculadas.getModel();
		            mTodasLasAsignaturas.addElement(asig); // Añadir a la lista de todas las asignaturas
		            mAsignaturasMatriculadas.removeElement(asig); // Remover de la lista de asignaturas matriculadas
		        }
			}
		});
		
		btnDesmatricularTodas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mTodasLasAsignaturas = (DefaultListModel<String>) lTodasLasAsignaturas.getModel();
		        mAsignaturasMatriculadas = (DefaultListModel<String>) lAsignaturasMatriculadas.getModel();
		        // Ir quitando asignaturas de la lista de matriculadas y añadirlas a la lista de todas
		        // hasta que la lista de matriculadas esté vacía
		        while(!mAsignaturasMatriculadas.isEmpty()) {
		            // Obtener el acceso a la primera asignatura matriculada
		            String asig = mAsignaturasMatriculadas.getElementAt(0);
		            // Añadir esta asignatura a la de todas
		            mTodasLasAsignaturas.addElement(asig);
		            // Borrar esa asignatura de la lista de matriculadas
		            mAsignaturasMatriculadas.removeElement(asig);
		        }
			}
		});
		// Última secuencia
		setVisible(true);
	}
	
	// Cargar fichero
	private void cargarFicheroDeAsignaturasALista() {
		//Como vamos a hacer cambios en la información que contiene la lista, primero obtenemos acceso a su modelo
		mTodasLasAsignaturas = (DefaultListModel<String>) lTodasLasAsignaturas.getModel();
		
		File f = new File("ficheros/asignaturas.txt"); //La variable f me da acceso al fichero cuyo nombre hemos puesto entre ()
		//Como queremos leer el fichero declaramos una variable de tipo Scanner
		try { //Intenta abrir el fichero
			Scanner sc = new Scanner(f);
			while(sc.hasNextLine()) { //Mientras queden lineas por leer
				String linea = sc.nextLine(); //Leemos una linea
				//La linea contiene el nombre de una asignatura
				//¿Qué quiero hacer con ese nombre de asignatura? -> Añadirlo a la lista de asignaturas
				mTodasLasAsignaturas.addElement(linea);
			}
			sc.close();
		} catch (FileNotFoundException e) { //Si no se puede abrir, muestra un error en consola
			e.printStackTrace();
		}  
	}
}
	
