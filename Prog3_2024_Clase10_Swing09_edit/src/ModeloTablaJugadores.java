import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class ModeloTablaJugadores extends DefaultTableModel {
	// Guardar en una lista los jugadores que queremos visualizar
	private List<Jugador> lJugadores;
	// Guardar en una lista los títulos de la cabecera de la tabla
	private List<String> lTitulos = Arrays.asList("DORSAL", "NOMBRE", "NOTA MEDIA");
	// La primera vez que vayamos a trabajar con el modelo hay que instaciarlo usando el constructor
	public ModeloTablaJugadores(List<Jugador> lj) {
		lJugadores = lj;
	}
	// El resto de veces que queramos modificar la lista podemos llamar a este método set
	public void setLPersonas(List<Jugador> lj) {
		lJugadores = lj;
	}
	
	// Modificar algunos métodos heredados de DefaultTableModel (desde Source -> Override/Implement Methods)

	/* Métodos obligatorios */
	
	@Override
	public int getRowCount() {
		// Devuelve el número de filas
		if(lJugadores == null) {
			return 0;
		}
		return lJugadores.size();
	}
	
	@Override
	public int getColumnCount() {
		// Devuelve el número de columnas de la tabla
		return lTitulos.size();
	}
	
	@Override
	public String getColumnName(int column) {
		// Devuelve el nombre de la columna cuyo número recibimos por parámemtro
		return lTitulos.get(column);
	}
	
	@Override
	public Object getValueAt(int row, int column) {
		// Devuelve el valor de la celda (row, column)
		// Obtener el jugador de la fila row
		Jugador j = lJugadores.get(row);
		// Obtener los atributos de ese jugador
		switch(column) {
			case 0: return j.getDorsal();
			case 1: return j.getNombre();
			case 2: return j.getNotaMedia();
			default: return null;
		}
	}
	
	/* Métodos opcionales */
	
	@Override
	public void addRow(Object[] rowData) {
		// Método que añade una nueva fila a la tabla
		
	}

	@Override
	public void removeRow(int row) {
		// Método que elimina una fila de la tabla
		
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		// Método que indica si la celda referenciada por row,column es o no editable
		return false;
	}

	@Override
	public void setValueAt(Object aValue, int row, int column) {
		// Método que modificar el valor de la celda referenciada por row,column
		
	}
}
