import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
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
import javax.swing.table.DefaultTableModel;

public class VentanaTabla extends JFrame{
	// Declaramos vActual y vAnterior
	private JFrame vActual, vAnterior;
	// Declaramos los botones
	private JButton btnVolver, btnAniadirAsignatura;
	// Declaramos los paneles
	private JPanel pCentro, pSur;
	// Declaramos una tabla
	private JTable tablaAsignaturas;
	private DefaultTableModel modeloTablaAsignaturas;
	private JScrollPane scrollTablaAsignaturas;
	// Declaramos un área de texto
	private JTextArea area;
	
	public VentanaTabla(JFrame vAnterior) {
		// Primera secuencia
		super();
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
		modeloTablaAsignaturas = new DefaultTableModel();
		// 2º paso ==> Creamos la tabla que va a mostrar la información que hay en el modelo
		tablaAsignaturas = new JTable(modeloTablaAsignaturas);
		//3º paso ==> Añadimos un scroll a la tabla
		scrollTablaAsignaturas = new JScrollPane(tablaAsignaturas);
		// Añadimos un scroll vertical y horizontal
		scrollTablaAsignaturas.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollTablaAsignaturas.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		// Añadimos los títulos de las columnas 
		String[] titulos = {"CÓDIGO", "NOMBRE", "NÚMERO DE CRÉDITOS"};
		modeloTablaAsignaturas.setColumnIdentifiers(titulos);
		// Añadimos el scroll de la tabla al panel central
		pCentro.add(scrollTablaAsignaturas);
		// Cargar la información (asignaturas) en la tabla
		cargarTabla();
		
		// Crear el área de texto
		area = new JTextArea();
		pCentro.add(area);
		// Modificar la fuente del JTextArea
		area.setFont(new Font(Font.DIALOG, Font.ITALIC, 22));
		area.setBackground(new Color(255, 255, 0));
		area.setForeground(Color.DARK_GRAY);
		// Listeners
		btnVolver.addActionListener((e)->{
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
					modeloTablaAsignaturas.addRow(fila);
					// Borrar la información del JTextArea
					area.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "El número de columnas/datos no es correcto");
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "El formato del número de créditos no es correcto");
			}
		});
		// Última secuencia
		setVisible(true);
	}
	
	private void cargarTabla() {
		// Leer cada línea del fichero asignaturas.txt y cargarlo en la tabla
		File f = new File("ficheros/asignaturas.txt");
		try {
			Scanner sc = new Scanner(f); // Abrir el fichero asignaturas.txt
			while(sc.hasNextLine()) { // Mientras haya líneas para leer
				String linea = sc.nextLine(); // Leer una línea del fichero
				String [] datos = linea.split(";");
				String codigo = datos[0];
				String nombre = datos[1];
				int numCreditos = Integer.parseInt(datos[2]);
				// Crear una nueva fila con esa información
				Object [] fila = {codigo, nombre, numCreditos};
				// Añadir esta fila a la tabla
				modeloTablaAsignaturas.addRow(fila);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
