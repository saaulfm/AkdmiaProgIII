import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*TABLA CON MODELO POR DEFECTO*/
public class Ventana extends JFrame{
	private DefaultTableModel modeloTabla;
	private JTable tabla;
	private JScrollPane scrollTabla;
	
	
	public Ventana() {
		super();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(300, 200, 600, 400);
		
		String [] titulos = {"DNI","NOM","EDAD","NOTA"};
		modeloTabla = new DefaultTableModel();
		modeloTabla.setColumnIdentifiers(titulos);
		tabla = new JTable(modeloTabla);
		scrollTabla = new JScrollPane(tabla);
		getContentPane().add(scrollTabla, BorderLayout.CENTER);
		Contenedora.cargarLista();
		cargarTabla();
		setVisible(true);
	}

	private void cargarTabla() {
		//Vamos a cargar la información de la lista de personas que tenemos en la Contenedora
		modeloTabla = (DefaultTableModel) tabla.getModel();
		for(Persona p: Contenedora.getLista()) {
			Object [] rowData = {p.getDni(),p.getNom(),p.getEdad(),p.getNota()};
			modeloTabla.addRow(rowData);
		}
		tabla.setModel(modeloTabla);
	}
	public static void main(String[] args) {
		new Ventana();
	}

}












