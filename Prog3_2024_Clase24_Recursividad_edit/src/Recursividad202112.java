import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Recursividad202112 {
	
	/**
	 * Genera las combinaciones de jugadores que se pueden fichar con un importe máximo y con un
	 * sobrante inferior a 10.
	 * @param elementos List<Jugador> con todos los jugadores que se pueden fichar.
	 * @param importe double con el importe máximo a gastar.
	 * @return List<List<Jugador>> listas de jugadores que se pueden fichar con el importe dado.
	 */
	public static List<List<Jugador>> combinacionesSinRepeticion(List<Jugador> elementos, double importe) {
		List<List<Jugador>> resultado = new ArrayList<>();
		combinacionesSinRepeticionR(elementos, importe, 0, new ArrayList<>(), resultado);
		return resultado;
	}
	
	public static void combinacionesSinRepeticionR(List<Jugador> elementos, double importe, double suma, List<Jugador> aux, List<List<Jugador>> resultado) {
		if (suma > importe) {
			return;
		} else if(suma > importe - 10) {
			Comparator<Jugador> c = new Comparator<Jugador>() {

				@Override
				public int compare(Jugador o1, Jugador o2) {
					return o1.getNombre().compareTo(o2.getNombre());
					//return Integer.compare(o1.getDorsal(), o2.getDorsal());
					//return Double.compare(o1.getValorMercado(), o2.getValorMercado());
				}
			};
			List<Jugador> copia = new ArrayList<>(aux);
			copia.sort(c);
			if(!resultado.contains(copia)) {
				resultado.add(new ArrayList<>(copia));
			}
		} else {
			for(int i=0; i<elementos.size(); i++) {
				if(!aux.contains(elementos.get(i))) {
					aux.add(elementos.get(i));
					suma = suma + elementos.get(i).getValorMercado();
					combinacionesSinRepeticionR(elementos, importe, suma, aux, resultado);
					int pos = aux.size() - 1;
					suma = suma - aux.get(pos).getValorMercado();
					aux.remove(aux.size()-1);
				}
			}
		}
	}
	
	// Método para inicializar lista de jugadores
	private static List<Jugador> initJugadores() {
		List<Jugador> jugadores = new ArrayList<>();
		
		jugadores.add(new Jugador("Lamine Yamal", 19, 150.00f));
		jugadores.add(new Jugador("Raphinha", 11, 60.00f));
		jugadores.add(new Jugador("Lewandowsky", 9, 15.00f));
		jugadores.add(new Jugador("Pedri", 8, 80.00f));
		jugadores.add(new Jugador("Iñaki Williams", 9, 25.00f));
		jugadores.add(new Jugador("Nico Williams", 10, 70.00f));
		jugadores.add(new Jugador("Oihan Sancet", 8, 40.00f));
		jugadores.add(new Jugador("Dani Vivian", 3, 30.00f));
		
		return jugadores;
	}
	
	public static void main(String[] args) {
		List<Jugador> elementos = initJugadores();
		double importe = 150d;
		
		List<List<Jugador>> resultado = combinacionesSinRepeticion(elementos, importe);
		System.out.println("Combinaciones de varios jugadores que valen menos de " + importe + "M euros");
    	resultado.forEach(r -> System.out.format("\n%s", r));
	}
}
