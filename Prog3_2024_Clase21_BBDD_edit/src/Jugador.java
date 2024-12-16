
public class Jugador {
	private String nombre;
	private int dorsal;
	private float valorMercado;
	
	public Jugador(String nombre, int dorsal, float valorMercado) {
		super();
		this.nombre = nombre;
		this.dorsal = dorsal;
		this.valorMercado = valorMercado;
	}

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

	public float getValorMercado() {
		return valorMercado;
	}

	public void setValorMercado(float valorMercado) {
		this.valorMercado = valorMercado;
	}

	@Override
	public String toString() {
		return "Jugador [nombre=" + nombre + ", dorsal=" + dorsal + ", valorMercado=" + valorMercado + "M €" + "]";
	}
}
