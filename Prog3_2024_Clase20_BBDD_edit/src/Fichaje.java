import java.util.List;

public class Fichaje {
	private int id;
	private List<Jugador> jugadores;
	
	public Fichaje(int id, List<Jugador> jugadores) {
		this.id = id;
		this.jugadores = jugadores;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}
	
	public void aniadirJugador(Jugador j) {
		jugadores.add(j);
	}
}
