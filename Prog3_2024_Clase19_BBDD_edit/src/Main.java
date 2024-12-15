
public class Main {
	public static void main(String[] args) {
		/*Si los atributos y métodos de la clase BD no son static
		BD bd = new BD();
		bd.initBD("newton.db");
		bd.closeBD();
		*/
		
		/*Si los atributos y métodos de la clase BD son static*/
		BD.initBD("saul.db");
		BD.crearTablas();
		BD.insertarJugador(new Jugador("Lamine Yamal", 19, 150.00f));
		BD.insertarJugador(new Jugador("Raphinha", 11, 60.00f));
		BD.insertarJugador(new Jugador("Lewandowsky", 9, 15.00f));
		BD.insertarJugador(new Jugador("Pedri", 8, 80.00f));
		//BD.borrarJugadores(10.00f);
		//BD.borrarJugadores(100.00f);
		
		Ventana v = new Ventana();
		//BD.borrarTablas();
		//BD.closeBD();
	}
}
