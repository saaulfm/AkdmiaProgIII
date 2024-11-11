package domain;

public class Jugador {
	private Pais pais;
	private String nombre;
	private int goles;
	
	public Jugador() {
		super();
	}

	public Jugador(Pais pais, String nombre, int goles) {
		super();
		this.pais = pais;
		this.nombre = nombre;
		this.goles = goles;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getGoles() {
		return goles;
	}

	public void setGoles(int goles) {
		this.goles = goles;
	}

	@Override
	public String toString() {
		return "Jugador [pais=" + pais + ", nombre=" + nombre + ", goles=" + goles + "]";
	}
}
