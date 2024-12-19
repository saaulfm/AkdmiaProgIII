import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Recursividad202202 {
	
	/**
	 * Calcula las sumas sucesivas de un array tal que, el primer nivel tenga todos 
	 * los elementos de la matriz. A partir de entonces, en cada nivel el número de
	 * elementos es uno menos que el nivel anterior y los elementos en el nivel son
	 * la suma de dos elementos consecutivos en el nivel anterior.
	 * 
	 * Si tenemos un array de entrada [1,2,3,4] la salida sera:
	 * 
	 * 20
	 * 8 12
	 * 3 5 7
	 * 1 2 3 4
	 *
	 * Esto se calcula de la siguiente manera:
	 * 20 -> 20 = 8 + 12
	 * 8 12 -> 8 = 3 + 5, 12 = 5 + 7
	 * 3 5 7 -> 3 = 1 + 2, 5 = 2 + 3, 7 = 3 + 4 
	 * 1 2 3 4 -> Es el array de entrada
	 * 
	 * @param array int[] array de números enteros de entrada
	 */
	public static void sumasSucesivas(int[] array) {
		List<int[]> resultado = new ArrayList<>();
		sumasSucesivasR(array, resultado);
		for(int i=resultado.size()-1; i>=0; i--) {
			int a[] = resultado.get(i);
			for(int j=0; j<a.length; j++) {
				System.out.print(a[j]+" ");
			}
			System.out.println();
		}
	}
	
	public static void sumasSucesivasR(int [] array, List<int []> result) {
		if(array.length == 0) {
			return;
		}else {
			result.add(array);
			int copia[] = new int[array.length-1];
			for(int i=0;i<array.length-1;i++) {
				copia[i] = array[i] + array[i+1];
			}
			sumasSucesivasR(copia, result);
		}
	}
	public static void main(String[] args) {
		int[] array = {1, 2, 3, 4};		
		System.out.format("Sumas sucesivas del array: %s\n", Arrays.toString(array));
		sumasSucesivas(array);
		
		array = new int[]{9, 5, 6, 4, 3, 1};		
		System.out.format("\n\nSumas sucesivas del array: %s\n", Arrays.toString(array));
		sumasSucesivas(array);
		
		array = new int[]{9, 5, 6, 4, 3};		
		System.out.format("\n\nSumas sucesivas del array: %s\n", Arrays.toString(array));
		sumasSucesivas(array);
	}
}
