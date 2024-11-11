package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CadenaEquipos {
	private Map<Pais, List<Jugador>> mapaJugadores;
	
	public CadenaEquipos() {
		// Inicialización de mapaJugadores con un HashMap
        mapaJugadores = new HashMap<>();
		for(Pais p: Pais.values()) {
			// Por cada país, añadir una entrada en el mapa con una lista vacía de jugadores
			mapaJugadores.put(p, new ArrayList<>());
		}
	}

	public Map<Pais, List<Jugador>> getMapaJugadores() {
		return mapaJugadores;
	}

	public void cargarJugadores() {
		// Crear jugadores
	    Jugador j1 = new Jugador(Pais.ARGENTINA, "Messi",  91); 
	    Jugador j2 = new Jugador(Pais.BRASIL, "Neymar",  43);   
	    Jugador j3 = new Jugador(Pais.ESPAÑA, "Ramos",  15);   
	    Jugador j4 = new Jugador(Pais.ESPAÑA, "Lamine Yamal",  20);  
	    Jugador j5 = new Jugador(Pais.ARGENTINA, "Di María",  11); 
	    Jugador j6 = new Jugador(Pais.BRASIL, "Ronaldinho",  21);  

	    // Agregar jugadores a la lista del país correspondiente
	    mapaJugadores.get(j1.getPais()).add(j1);
	    mapaJugadores.get(j2.getPais()).add(j2);
	    mapaJugadores.get(j3.getPais()).add(j3);
	    mapaJugadores.get(j4.getPais()).add(j4);
	    mapaJugadores.get(j5.getPais()).add(j5);
	    mapaJugadores.get(j6.getPais()).add(j6);
	}
}
