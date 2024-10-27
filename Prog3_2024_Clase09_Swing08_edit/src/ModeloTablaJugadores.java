import java.util.Arrays;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ModeloTablaJugadores extends DefaultTableModel {
	// Guardar en una lista los jugadores que queremos visualizar
	private List<Jugador> lJugadores;
	// Guardar en una lista los títulos de la cabecera de la tabla
	private List<String> lTitulos = Arrays.asList("DORSAL", "NOMBRE", "POSICIÓN", "AÑO NACIMIENTO");
	
	public ModeloTablaJugadores(List<Jugador> lJugadores) {
		this.lJugadores = lJugadores;
	}
	// Modificar algunos métodos heredados de DefaultTableModel (desde Source -> Override/Implement Methods)
	@Override
	public void addRow(Object[] rowData) {
		// Añadir una nueva fila a la tabla
		// Creo un jugador con la información que ha recibido el método (rowData)
		// rowData[0] = dorsal
		// rowData[1] = nombre
		// rowData[2] = posicion
		// rowData[3] = anyoNacimiento
		
		Jugador newJugador = new Jugador((int) rowData[0], rowData[1].toString(), rowData[2].toString(), (int) rowData[3]);
		// Añadir el nuevo jugador a la lista
		lJugadores.add(newJugador);
	}
	@Override
	public void removeRow(int row) {
		// Borrar el jugador de la fila row de la tabla
		if(row>0 && row<lJugadores.size()) {
			lJugadores.remove(row);
		}
	}
	@Override
	public int getRowCount() {
		// Devuelve el número de filas de la tabla
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
	public boolean isCellEditable(int row, int column) {
		// Devolver true si queremos que la celda sea editable
		// Por defecto, todas las celdas son editables
		// En nuestro ejemplo, la columna 0 que no sea editable y el resto sí 
		if(column == 0) {
			return false;
		}
		return true;
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
			case 2: return j.getPosicion();
			case 3: return j.getAnioNacimiento();
			default: return null;
		}
	}
	@Override
	public void setValueAt(Object aValue, int row, int column) {
		// Modifica el valor de una determinada celda
		// La fila(row) nos sirve para saber qué jugador queremos modificar
		// La columna(column) nos sirve para saber qué atributo del jugador queremos modificar
		// El valor aValue es la nueva información
		
		// Obtener el jugador a modificar
		Jugador j = lJugadores.get(row);
		switch(column) {
			case 0: j.setDorsal(Integer.parseInt((String) aValue));
				break;
			case 1: j.setNombre(aValue.toString());
				break;
			case 2: j.setPosicion(aValue.toString());
				break;
			case 3: j.setAnioNacimiento(Integer.parseInt((String) aValue));
				break;
		}
	}
}
