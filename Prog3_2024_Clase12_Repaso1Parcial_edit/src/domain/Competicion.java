package domain;
import java.util.TreeSet;

public class Competicion {
	private String nombreCompeticion;
	private int anioTemporada;
	private TreeSet<Equipo> tsEquipos;
	
	public Competicion() {
		super();
		tsEquipos = new TreeSet<>();
	}

	public Competicion(String nombreCompeticion, int anioTemporada) {
		super();
		this.nombreCompeticion = nombreCompeticion;
		this.anioTemporada = anioTemporada;
		this.tsEquipos = new TreeSet<>();
	}

	public String getNombreCompeticion() {
		return nombreCompeticion;
	}

	public void setNombreCompeticion(String nombreCompeticion) {
		this.nombreCompeticion = nombreCompeticion;
	}

	public int getAnioTemporada() {
		return anioTemporada;
	}

	public void setAnioTemporada(int anioTemporada) {
		this.anioTemporada = anioTemporada;
	}

	public TreeSet<Equipo> getTsEquipos() {
		return tsEquipos;
	}

	public void setTsEquipos(TreeSet<Equipo> tsEquipos) {
		this.tsEquipos = tsEquipos;
	}

	@Override
	public String toString() {
		return "Competicion [nombreCompeticion=" + nombreCompeticion + ", anioTemporada=" + anioTemporada
				+ ", tsEquipos=" + tsEquipos + "]";
	}
	
}
