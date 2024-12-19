import java.util.ArrayList;
import java.util.List;

public class Recursividad202201 {
	
	/**
	 * ENUM que representa las tres operaciones posibles a aplicar.
	 */
	public enum Operation {
		PLUS_FIVE("+5"), 	// +5
		MINUS_THREE("-3"), 	// -3
		TIMES_TWO("x2"); 	// x2
		
		private String text;
		
		private Operation(String text) {
			this.text = text;
		}
		
		@Override
		public String toString() {
			return text; 
		}
		
		/**
		 * Aplica la operación sobre un valor entero.
		 * @param value int con el valor sobre el que se aplica la operación.
		 * @return int con el resultado tras aplicar la operación.
		 */
		public int apply(int value) {
			switch (this) {
				case PLUS_FIVE: return value + 5;
				case MINUS_THREE: return value - 3;
				case TIMES_TWO: return value * 2;
				default: return value;
			}
			
		}
	}
	
	public final static int MAX_OPS = 5;
		
	/**
	 * Imprime por pantalla la secuencia (o secuencias) de un máximo de 5 
	 * operaciones que permiten transformar el número inicial en el número final.
	 * @param start número inicial desde el que transformar
	 * @param end número a obtener al aplicar las transformaciones
	 */
	public static void imprimirSecuencia(int start, int end) {
		List<List<Operation>> resultado = new ArrayList<>();
		imprimirSecuenciaR(start, end, new ArrayList<>(), resultado);
		for(List<Operation> l: resultado) {
			System.out.println(l);
		}
	}
	
	public static void imprimirSecuenciaR(int start, int end, List<Operation> aux, List<List<Operation>> resultado) {
		if(aux.size() > MAX_OPS) {
			return;
		} else {
			if(start == end) {
				resultado.add(new ArrayList<>(aux));
			} else {
				for(Operation op: Operation.values()) {
					aux.add(op);
					imprimirSecuenciaR(op.apply(start), end, aux, resultado);
					aux.remove(aux.size()-1);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Secuencia de operaciones entre 5 y 29:");
		imprimirSecuencia(5, 29);
		System.out.println("\nSecuencia de operaciones entre 3 y 18:");
		imprimirSecuencia(3, 18);
		System.out.println("\nSecuencia de operaciones entre 5 y 38:");
		imprimirSecuencia(5, 38);
		System.out.println("\nSecuencia de operaciones entre 2 y -8:");
		imprimirSecuencia(2, -8);
		System.out.println("\nSecuencia de operaciones entre 5 y 5:");
		imprimirSecuencia(5, 5);
		System.out.println("\nSecuencia de operaciones entre 4 y 100:");
		imprimirSecuencia(4, 100);
	}
}
