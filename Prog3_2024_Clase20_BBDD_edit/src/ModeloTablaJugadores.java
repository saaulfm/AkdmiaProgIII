import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class ModeloTablaJugadores extends DefaultTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Jugador> lJugadores;
	private List<String> lTitulos = Arrays.asList("NOMBRE","DORSAL","VALOR MERCADO");
	
	public ModeloTablaJugadores(List<Jugador> lj) {
		lJugadores = lj;
	}
	
	@Override
	public void addRow(Object[] rowData) {
		// rowData ["Lamine Yamal", 19, 150.00f]
		String nom = rowData[0].toString();
		int dorsal = Integer.parseInt(rowData[1].toString());
		float valor = Float.parseFloat(rowData[2].toString());
		Jugador j = new Jugador(nom, dorsal, valor);
		lJugadores.add(j);
		System.out.println(lJugadores.size());
	}

	@Override
	public int getRowCount() {
		if(lJugadores == null)
			return 0;
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
			case 2: return j.getValorMercado();
			default: return null;
		}
	}
}
