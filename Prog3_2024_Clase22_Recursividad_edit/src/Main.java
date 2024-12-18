import java.util.Arrays;
import java.util.List;

/*RECURSIVIDAD
 * 
 * Un método recursivo es aquel que consigue ejecutar un bucle con llamadas a sí mismo*/
public class Main {
	
	/*Método que, dado un número n, visualiza los números desde el n hasta el 1*/
	public static void visualizarR1(int n) {
		if(n>=1) {
			System.out.println(n);
			visualizarR1(n-1);
		}
	}
	
	/*Método que, dado un número n, visualiza los números desde el 1 hasta el n*/
	/*Al ejecutar primero la llamada recursiva, se ejecuta al revés*/
	public static void visualizarR2(int n) {
		if(n>=1) {
			visualizarR2(n-1);
			System.out.println(n);
		}
	}

	/*Método que, dado un número n, devuelve la suma de los valores desde n hasta el 1*/
	/*Si un método recursivo devuelve un valor, hay que tener todos los casos contemplados.
	 * Es decir, tiene que devolver algo, pase lo que pase.
	 * Nunca inicializamos variables en un método recursivo*/
	public static int calcularSuma(int n) {
		if(n>=1) { // Caso recursivo
			return n + calcularSuma(n-1);
		} else { // Caso base: finaliza el bucle 
			return 0; // En el caso base devolvemos SIEMPRE el elemento neutro de la operación
		}
	}
	
	/*Método que, dado un número n, devuelve el producto de los valores desde n hasta el 1*/
	public static int calcularProducto(int n) {
		if(n>=1) { 
			return n * calcularSuma(n-1);
		} else { 
			return 1; 
		}
	}
	
	/*Método recursivo que, dada una lista de enteros, devuelve la suma de todos los elementos*/
	public static int calcularSumaLista1(List<Integer> l, int i) {
		if(i<l.size()) {
			return l.get(i) + calcularSumaLista1(l, i+1);
		} else {
			return 0;
		}
	}
	
	/*Método recursivo que, dada una lista de enteros, devuelve la suma de todos los elementos*/
	public static int calcularSumaLista2(List<Integer> l, int i) {
		if(i>=0) {
			return l.get(i) + calcularSumaLista2(l, i-1);
		} else {
			return 0;
		}
	}
	
	public static void main(String[] args) {
		visualizarR1(3);
		System.out.println("--------");
		visualizarR2(3);
		System.out.println("--------");
		System.out.println(calcularSuma(3));
		System.out.println("--------");
		System.out.println(calcularProducto(4));
		System.out.println("--------");
		List<Integer> al = Arrays.asList(1,2,3,4,5);
		System.out.println(calcularSumaLista1(al, 0));
		System.out.println("--------");
		System.out.println(calcularSumaLista2(al, al.size()-1));
	}
}


















