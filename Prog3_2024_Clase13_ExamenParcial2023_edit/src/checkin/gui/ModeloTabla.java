package checkin.gui;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import checkin.domain.Seat;

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

public class ModeloTabla extends DefaultTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<String> lTitulos = Arrays.asList("","A","B","C","","D","E","F");
	private List<Seat> lAsientos;
	
	public ModeloTabla(List<Seat> la) {
		lAsientos = la;
	}

	@Override
	public int getRowCount() {
		if(lAsientos==null) {
			return 0;
		} return lAsientos.size() / 6;
	}


	@Override
	public int getColumnCount() {
		return lTitulos.size();
	}

	@Override
	public String getColumnName(int column) {
		return lTitulos.get(column);
	}
	
	@Override
	public Object getValueAt(int row, int column) {
		//Devuelve el valor que hay que visualizar en un determinada celda
		switch(column) {
			case 0: return row+1;
			case 1: return lAsientos.get(row*6+column-1); //Restamos 1 porque tenemos una columna vacía antes
			case 2: return lAsientos.get(row*6+column-1);
			case 3: return lAsientos.get(row*6+column-1);
			case 4: return "";
			case 5: return lAsientos.get(row*6+column-2); //Restamos 2 porque tenemos 2 columnas sin asientos antes
			case 6: return lAsientos.get(row*6+column-2);
			case 7: return lAsientos.get(row*6+column-2);
			default: return null;
		}
	}
}
