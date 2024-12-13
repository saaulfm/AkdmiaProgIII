import java.util.List;

public class Main {
	public static void main(String[] args) {
		BD bd = new BD();
		bd.initBD("saul.db");
		bd.crearTablas();
		// Ejemplos de prueba
//		bd.borrarTablas();
		bd.insertarJugador("Lamine Yamal", 19);
		bd.insertarJugador("Raphinha", 11);
		bd.insertarJugador("Lewandowsky", 9);
//		bd.borrarJugador("Lewandowsky");
		
		List<Jugador> lJugadores = bd.obtenerListaJugadores();
		Ventana v = new Ventana(lJugadores);
		bd.closeBD();
	}
}
