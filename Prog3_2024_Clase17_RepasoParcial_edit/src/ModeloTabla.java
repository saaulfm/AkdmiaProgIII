import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class ModeloTabla extends DefaultTableModel {
	private List<String> lTitulos = Arrays.asList("DNI", "NOM", "","EDAD","NOTA");
	private List<Persona> lPersonas;

	public ModeloTabla(List<Persona> lp) {
		lPersonas = lp;
	}

	@Override
	public int getRowCount() {
		if (lPersonas == null)
			return 0;
		return lPersonas.size();
	}

	@Override
	public int getColumnCount() {
		//return 3;
		return lTitulos.size();
		
	}

	@Override
	public String getColumnName(int column) {
		return lTitulos.get(column);
	}

	@Override
	public Object getValueAt(int row, int column) {
		Persona p = lPersonas.get(row);
		switch (column) {
		case 0:
			return p.getDni();
		case 1:
			return p.getNom();
		case 2:
			return "";
		case 3:
			return p.getEdad();
		case 4:
			return p.getNota();
		default:
			return null;
		}
	}

}
