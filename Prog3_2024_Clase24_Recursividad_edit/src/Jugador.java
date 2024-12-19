import java.util.Objects;

public class Jugador {
	private String nombre; 
	private int dorsal;
	private double valorMercado;
	
	public Jugador(String nombre, int dorsal, double valorMercado) {
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

	public double getValorMercado() {
		return valorMercado;
	}

	public void setValorMercado(double valorMercado) {
		this.valorMercado = valorMercado;
	}

	@Override
	public String toString() {
		return "Jugador [nombre=" + nombre + ", dorsal=" + dorsal + ", valorMercado=" + valorMercado + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jugador other = (Jugador) obj;
		return dorsal == other.dorsal && Objects.equals(nombre, other.nombre)
				&& Double.doubleToLongBits(valorMercado) == Double.doubleToLongBits(other.valorMercado);
	}
	
	
		
}