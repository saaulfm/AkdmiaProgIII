package clases;

import java.util.ArrayList;

public class Plantilla {
	private static ArrayList<Jugador> alJugadores = new ArrayList<>();
	
	public static void cargarJugadores() {
		Jugador j1 = new Jugador(1, "Ter Stegen", "POR", 1992, "img/j1.jpg");
		Jugador j2 = new Jugador(2, "Pau Cubarsí", "DFC", 2006, "img/j2.jpg");
		Jugador j3 = new Jugador(3, "Alejandro Balde", "LI", 2003, "img/j3.jpg");
		Jugador j4 = new Jugador(5, "Iñigo Martinez", "DFC", 1991, "img/j4.jpg");
		Jugador j5 = new Jugador(23, "Jules Kounde", "LD", 1998, "img/j5.jpg");
		Jugador j6 = new Jugador(8, "Pedri", "MC", 2002, "img/j6.jpg");
		Jugador j7 = new Jugador(6, "Gavi", "MC", 2004, "img/j7.jpg");
		Jugador j8 = new Jugador(20, "Dani Olmo", "MCO", 1998, "img/j8.jpg");
		Jugador j9 = new Jugador(9, "Robert Lewandowsky", "DC", 1988, "img/j9.jpg");
		Jugador j10 = new Jugador(11, "Raphinha", "EI", 1996, "img/j10.jpg");
		Jugador j11 = new Jugador(19, "Lamine Yamal", "ED", 2007, "img/j11.jpg");
		alJugadores.add(j1);
		alJugadores.add(j2);
		alJugadores.add(j3);
		alJugadores.add(j4);
		alJugadores.add(j5);
		alJugadores.add(j6);
		alJugadores.add(j7);
		alJugadores.add(j8);
		alJugadores.add(j9);
		alJugadores.add(j10);
		alJugadores.add(j11);
	}
	
	public static ArrayList<Jugador> getAlJugadores() {
		return alJugadores;
	}
	
	// Método que dada la ruta de la foto de un jugador devuelva el objeto 
	// Jugador con esa rutaFoto
	public static Jugador buscarJugador(String rutaFoto) {
		boolean enc = false;
		int pos = 0;
		Jugador j = null;
		while(!enc && pos<alJugadores.size()) {
			j = alJugadores.get(pos);
			if(j.getRutaFoto().equals(rutaFoto)) {
				enc = true;
			} else {
				pos++;
			}
		}
		if(enc) {
			return j;
		} else {
			return null;
		}
	}
	
}
