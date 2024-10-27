
public class Jugador {
	private String nombre;
	private int dorsal;
	private Posicion posicion;
	private String equipo;
	
	/**
	 * Método constructor por defecto
	 */
	public Jugador() {
		super();
	}

	/**
	 * Método constructor con parámetros
	 */
	public Jugador(String nombre, int dorsal, Posicion posicion, String equipo) {
		super();
		this.nombre = nombre;
		this.dorsal = dorsal;
		this.posicion = posicion;
		this.equipo = equipo;
	}

	/**
	 * Métodos getter y setter
	 */
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDorsal() {
		return dorsal;
	}

	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}

	public Posicion getPosicion() {
		return posicion;
	}

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}

	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	@Override
	public String toString() {
		return "Clase1 [nombre=" + nombre + ", dorsal=" + dorsal + ", posicion=" + posicion + ", equipo=" + equipo
				+ "]";
	}
}
