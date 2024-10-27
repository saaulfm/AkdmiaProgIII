package clases;

public class Jugador {
	private int dorsal;
	private String nombre;
	private String posicion;
	private int anioNacimiento;
	private String rutaFoto;
	
	public Jugador() {
		super();
	}

	public Jugador(int dorsal, String nombre, String posicion, int anioNacimiento, String rutaFoto) {
		super();
		this.dorsal = dorsal;
		this.nombre = nombre;
		this.posicion = posicion;
		this.anioNacimiento = anioNacimiento;
		this.rutaFoto = rutaFoto;
	}

	public int getDorsal() {
		return dorsal;
	}

	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public int getAnioNacimiento() {
		return anioNacimiento;
	}

	public void setAnioNacimiento(int anioNacimiento) {
		this.anioNacimiento = anioNacimiento;
	}

	public String getRutaFoto() {
		return rutaFoto;
	}

	public void setRutaFoto(String rutaFoto) {
		this.rutaFoto = rutaFoto;
	}

	@Override
	public String toString() {
		return "Jugador [dorsal=" + dorsal + ", nombre=" + nombre + ", posicion=" + posicion + ", añoNacimiento=" + anioNacimiento
				+ ", rutaFoto=" + rutaFoto + "]";
	}	
}
