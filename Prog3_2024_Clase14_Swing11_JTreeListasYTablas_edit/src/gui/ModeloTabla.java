package gui;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import domain.Jugador;

public class ModeloTabla extends DefaultTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	List<Jugador> lJugadores;
	private List<String> lTitulos = Arrays.asList("PAÍS","NOMBRE","GOLES");
	
	public ModeloTabla(List<Jugador> lj) {
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
	public Object getValueAt(int row, int column) {
		Jugador j = lJugadores.get(row);
		switch(column) {
			case 0: return j.getPais();
			case 1: return j.getNombre();
			case 2: return j.getGoles();
			default: return null;
		}
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
