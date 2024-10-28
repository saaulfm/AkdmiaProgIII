
public class Jugador {
	private int dorsal;
	private String nombre;
	private float notaMedia;
	
	public Jugador() {
		super();
	}

	public Jugador(int dorsal, String nombre, float notaMedia) {
		super();
		this.dorsal = dorsal;
		this.nombre = nombre;
		this.notaMedia = notaMedia;
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

	public float getNotaMedia() {
		return notaMedia;
	}

	public void setNotaMedia(float notaMedia) {
		this.notaMedia = notaMedia;
	}

	@Override
	public String toString() {
		return "Jugador [dorsal=" + dorsal + ", nombre=" + nombre + ", notaMedia=" + notaMedia + "]";
	}
}
