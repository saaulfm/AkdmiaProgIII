import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.TreeSet;

public class Colecciones {
	public static void main(String[] args) {
		/*LISTAS*/
		ArrayList<String> lEquipos = new ArrayList<>();
		lEquipos.add("Barcelona");
		lEquipos.add("Real Madrid");
		lEquipos.add("Athletic Club");
		
		Collections.sort(lEquipos);
		System.out.println(lEquipos);
		
		/*CONJUNTOS*/
		TreeSet<String> sJugadores = new TreeSet<>();
		sJugadores.add("Lamine Yamal");
		sJugadores.add("Raphinha");
		sJugadores.add("Alejandro Balde");
		sJugadores.add("Ter Stegen");
		
		System.out.println(sJugadores);
		
		// Conjunto de jugadores
		Jugador j1 = new Jugador("Lamine Yamal", 19, Posicion.DELANTERO, "Barcelona");
		Jugador j2 = new Jugador("Lionel Messi", 10, Posicion.DELANTERO, "Inter Miami");
		Jugador j3 = new Jugador("Yamal Musiala", 42, Posicion.CENTROCAMPISTA, "Bayern Munich");
		
		// Comparador de jugadores
		Comparator<Jugador> c = new Comparator<Jugador>() {

			@Override
			public int compare(Jugador o1, Jugador o2) {
				// Ordenar por nombre (String)
				// return o1.getNombre().compareTo(o2.getNombre());
				
				// Ordenar por número de dorsal (int)
				return Integer.compare(o1.getDorsal(), o2.getDorsal());
			}
		};
		
		TreeSet<Jugador> sJugadores2 = new TreeSet<>(c);
		sJugadores2.add(j1);
		sJugadores2.add(j2);
		sJugadores2.add(j3);
		System.out.println(sJugadores2);
		
		/*MAPAS*/
		 TreeMap<String, Jugador> mJugadores = new TreeMap<>();
	     mJugadores.put("Barcelona", j1);
	     mJugadores.put("Inter Miami", j2);
	     mJugadores.put("Bayern Munich", j3);
	     
	     System.out.println(mJugadores);
	}
}
