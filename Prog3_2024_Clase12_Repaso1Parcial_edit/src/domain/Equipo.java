package domain;

public class Equipo {
	private String nombre;
	private int anioNacimiento;
	
	public Equipo() {
		super();
	}

	public Equipo(String nombre, int anioNacimiento) {
		super();
		this.nombre = nombre;
		this.anioNacimiento = anioNacimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getAnioNacimiento() {
		return anioNacimiento;
	}

	public void setAnioNacimiento(int anioNacimiento) {
		this.anioNacimiento = anioNacimiento;
	}

	@Override
	public String toString() {
		return "Equipo [nombre=" + nombre + ", anioNacimiento=" + anioNacimiento + "]";
	}
		
}
