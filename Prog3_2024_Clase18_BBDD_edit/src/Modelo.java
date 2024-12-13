import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class Modelo extends DefaultTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	List<Jugador> lJugadores;
	private List<String> lTitulos = Arrays.asList("NOMBRE","DORSAL");
	
	public Modelo(List<Jugador> lj) {
		lJugadores = lj;
	}

	@Override
	public int getRowCount() {
		if(lJugadores == null) {
			return 0;
		}
		return lJugadores.size();
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
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	@Override
	public Object getValueAt(int row, int column) {
		Jugador j = lJugadores.get(row);
		switch(column) {
			case 0: return j.getNombre();
			case 1: return j.getDorsal();
			default: return null;
		}
	}
}
