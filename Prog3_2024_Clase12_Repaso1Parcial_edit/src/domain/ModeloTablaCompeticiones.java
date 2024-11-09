package domain;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class ModeloTablaCompeticiones extends DefaultTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<String> lTitulos = Arrays.asList("NOMBRE DE COMPETICIÓN","AÑO DE TEMPORADA","NÚMERO DE EQUIPOS");
	private List<Competicion> lCompeticiones;
	
	public ModeloTablaCompeticiones(List<Competicion> lc) {
		lCompeticiones = lc;
	}

	@Override
	public int getRowCount() {
		if(lCompeticiones != null) {
			return lCompeticiones.size();
		}
		return 0;
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
		// Primero obtener el equipo de la fila row
		Competicion c = lCompeticiones.get(row);
		// Devolver el atributo que quiero visualizar en fución de la columna recibida
		switch(column) {
			case 0: return c.getNombreCompeticion();
			case 1: return c.getAnioTemporada();
			case 2: return c.getTsEquipos().size();
			default: return null;
		}
	}
	
	

}
