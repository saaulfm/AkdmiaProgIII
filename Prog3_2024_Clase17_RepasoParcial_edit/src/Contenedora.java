import java.util.ArrayList;
import java.util.List;

public class Contenedora {
	private static List<Persona> lista = new ArrayList<>();
	
	
	public static List<Persona> getLista() {
		return lista;
	}


	public static void cargarLista(){
		//Método que carga la lista desde el fichero Personas.csv
		Persona p1 = new Persona("1", "A", 18, 9);
		Persona p2 = new Persona("2", "B", 19, 8);
		Persona p3 = new Persona("3", "C", 20, 7);
		lista.add(p1);
		lista.add(p2);
		lista.add(p3);
	}
}
