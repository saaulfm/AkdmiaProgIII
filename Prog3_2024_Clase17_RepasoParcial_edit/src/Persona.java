
public class Persona {
	private String dni;
	private String nom;
	private int edad;
	private float nota;
	
	public Persona() {
		super();
	}
	public Persona(String dni, String nom, int edad, float nota) {
		super();
		this.dni = dni;
		this.nom = nom;
		this.edad = edad;
		this.nota = nota;
	}
	
	public float getNota() {
		return nota;
	}
	public void setNota(float nota) {
		this.nota = nota;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	@Override
	public String toString() {
		return "Persona [dni=" + dni + ", nom=" + nom + ", edad=" + edad + "]";
	}
	
	
}
