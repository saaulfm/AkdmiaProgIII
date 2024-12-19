import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
	
	public static List<List<Integer>> variaciones(List<Integer> al) {
		List<List<Integer>> resultado = new ArrayList<>();
		
		
//		                 		resultado = []
//		   aux = [1,1,1,1]      resultado = [ [1,1,1,1] ]
//		   aux = [1,1,1,2]	  	resultado = [ [1,1,1,1], [1,1,1,2] ]
//		   aux = [1,1,1,3]	  	resultado = [ [1,1,1,1], [1,1,1,2], [1,1,1,3] ]
		   
		  
		
		variacionesR(al, new ArrayList<>(), resultado);
		return resultado;
	}
	
//	Ejecución paso a paso:
//
//  Imagina que empiezas con aux = [].
//	Se agrega el número 1: ahora aux = [1].
//	Luego se agrega otro número 1: ahora aux = [1, 1].
//	Esto continúa hasta que aux = [1, 1, 1, 1], y se guarda esa combinación.
//	Después, el último 1 se quita y se prueba con el número 2, formando [1, 1, 1, 2], y así sucesivamente.
		
	public static void variacionesR(List<Integer> al, List<Integer> aux, List<List<Integer>> resultado) {
		if(aux.size() == 4) {
			resultado.add(new ArrayList<>(aux));
			// Añadir al resultado una copia de aux, porque en caso contrario, todas las variaciones
			// serían la misma, el último valor que tenga aux cuando este método termine de ejecutarse
		} else {
			for(int i=0; i<al.size(); i++) {
				aux.add(al.get(i));
				variacionesR(al, aux, resultado);
				aux.remove(aux.size()-1);
			}
		}
	}
	
	public static List<List<Integer>> variacionesSinRepeticion(List<Integer> al) {
		List<List<Integer>> resultado = new ArrayList<>() ;
		variacionesSinRepeticionR(al, new ArrayList<>(), resultado);
		return resultado;
	}
	
	public static void variacionesSinRepeticionR(List<Integer> al, List<Integer> aux, List<List<Integer>> resultado) {
		if(aux.size() == 4) {
			resultado.add(new ArrayList<>(aux));
			// Añadir al resultado una copia de aux, porque en caso contrario, todas las variaciones
			// serían la misma, el último valor que tenga aux cuando este método termine de ejecutarse
		} else {
			for(int i=0; i<al.size(); i++) {
				if(!aux.contains(al.get(i))) {
					aux.add(al.get(i));
					variacionesSinRepeticionR(al, aux, resultado);
					aux.remove(aux.size()-1);
				}
			}
		}
	}
	
	public static List<List<Integer>> combinaciones(List<Integer> al) {
		List<List<Integer>> resultado = new ArrayList<>() ;
		combinacionesR(al, new ArrayList<>(), resultado);
		return resultado;
	}
	
	public static void combinacionesR(List<Integer> al, List<Integer> aux, List<List<Integer>> resultado) {
		if(aux.size() == 4) {
			
//			resultado = [[1,1,1,1],[1,1,1,2],[1,1,1,3],[1,1,1,4]]
//			aux = [1,1,2,1] ->  copia [1,1,2,1] -> [1,1,1,2]
			
			List<Integer> copia = new ArrayList<>(aux);
			Comparator<Integer> c = new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					return Integer.compare(o1, o2); // Orden ascendente
					//return Integer.compare(o2, o1); // Orden descendente
				}	
			};
			
			copia.sort(c);
			if(!resultado.contains(copia)) {
				resultado.add(new ArrayList<>(aux));
			}		
		} else {
			for(int i=0; i<al.size(); i++) {
				aux.add(al.get(i));
				combinacionesR(al, aux, resultado);
				aux.remove(aux.size()-1);
			}
		}
	}
	
	public static List<List<Integer>> combinacionesSinRepeticion(List<Integer> al) {
		List<List<Integer>> resultado = new ArrayList<>() ;
		combinacionesSinRepeticionR(al, new ArrayList<>(), resultado);
		return resultado;
	}
	
	public static void combinacionesSinRepeticionR(List<Integer> al, List<Integer> aux, List<List<Integer>> resultado) {
		if(aux.size() == 4) {
			
//			resultado = [[1,1,1,1],[1,1,1,2],[1,1,1,3],[1,1,1,4]]
//			aux = [1,1,2,1] ->  copia [1,1,2,1] -> [1,1,1,2]
			
			List<Integer> copia = new ArrayList<>(aux);
			Comparator<Integer> c = new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					return Integer.compare(o1, o2); // Orden ascendente
					//return Integer.compare(o2, o1); // Orden descendente
				}	
			};
			
			copia.sort(c);
			if(!resultado.contains(copia)) {
				resultado.add(new ArrayList<>(aux));
			}		
		} else {
			for(int i=0; i<al.size(); i++) {
				if(!aux.contains(al.get(i))) {
					aux.add(al.get(i));
					combinacionesSinRepeticionR(al, aux, resultado);
					aux.remove(aux.size()-1);
				}
			}
		}
	}
	
	public static List<List<Integer>> combinacionesSinRepeticion2(List<Integer> al) {
		List<List<Integer>> resultado = new ArrayList<>() ;
		combinacionesSinRepeticionR2(al, new ArrayList<>(), resultado);
		return resultado;
	}
	
	public static void combinacionesSinRepeticionR2(List<Integer> al, List<Integer> aux, List<List<Integer>> resultado) {
		if(aux.size() == 3) {
			
//			resultado = [[1,1,1,1],[1,1,1,2],[1,1,1,3],[1,1,1,4]]
//			aux = [1,1,2,1] ->  copia [1,1,2,1] -> [1,1,1,2]
			
			List<Integer> copia = new ArrayList<>(aux);
			Comparator<Integer> c = new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					return Integer.compare(o1, o2); // Orden ascendente
					//return Integer.compare(o2, o1); // Orden descendente
				}	
			};
			
			copia.sort(c);
			if(!resultado.contains(copia)) {
				resultado.add(new ArrayList<>(aux));
			}		
		} else {
			for(int i=0; i<al.size(); i++) {
				if(!aux.contains(al.get(i))) {
					aux.add(al.get(i));
					combinacionesSinRepeticionR2(al, aux, resultado);
					aux.remove(aux.size()-1);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		List<Integer> al = Arrays.asList(1,2,3,4);
		
		// Variaciones
		List<List<Integer>> resultadoV = variaciones(al);
		System.out.println("VARIACIONES: "+ resultadoV.size());
		for(List<Integer> l: resultadoV) {
			System.out.println(l);
		}
		
		// Variaciones sin repetición
		List<List<Integer>> resultadoVSR = variacionesSinRepeticion(al);
		System.out.println("VARIACIONES SIN REPETICIÓN: "+ resultadoVSR.size());
		for(List<Integer> l: resultadoVSR) {
			System.out.println(l);
		}
		
		// Combinaciones
		List<List<Integer>> resultadoC = combinaciones(al);
		System.out.println("COMBINACIONES: "+ resultadoC.size());
		for(List<Integer> l: resultadoC) {
			System.out.println(l);
		}
		
		// Combinaciones sin repetición
		List<List<Integer>> resultadoCSR = combinacionesSinRepeticion(al);
		System.out.println("COMBINACIONES SIN REPETICIÓN: "+ resultadoCSR.size());
		for(List<Integer> l: resultadoCSR) {
			System.out.println(l);
		}
		
		// Combinaciones sin repetición de 3 en 3
		List<List<Integer>> resultadoCSR2 = combinacionesSinRepeticion2(al);
		System.out.println("COMBINACIONES SIN REPETICIÓN DE 4 ELEMENTOS COGIDOS DE 3 EN 3: "+ resultadoCSR2.size());
		for(List<Integer> l: resultadoCSR2) {
			System.out.println(l);
		}
	}
}
