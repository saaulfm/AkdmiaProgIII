
public class Main {
	public static void main(String[] args) {
		BD.initBD("MiBD.db");
		BD.crearTablas();
		//BD.closeBD();
		Ventana v = new Ventana();
		/*CÓDIGO CLASE 22*/
		String jugador = "Nico Williams";
		String equipo = "FC Barcelona";
		
		System.out.println("Valor de mercado promedio de los fichajes: " + BD.obtenerValorMedia() + "M €");
		System.out.println("Jugadores que han costado más que la media: " + BD.cuantosMayorValorQueMedia());
		System.out.println("Media del valor de mercado de los fichajes de " + jugador + ": " + BD.obtenerMediaJugador(jugador) + "M €");
		System.out.println("Media del valor de mercado de los fichajes del " + equipo + ": "  + BD.obtenerMediaEquipo(equipo) + "M €");
		System.out.println("Mapa de clave nombre de jugador y de valor, lista de todos los equipos en los que ha estado:");
		System.out.println(BD.obtenerMapa());
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");

	}
}
