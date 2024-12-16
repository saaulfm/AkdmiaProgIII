import java.util.HashMap;
import java.util.HashSet;

public class MercadoFichajes {
	private HashMap<Integer, HashSet<Fichaje>> fichajesEquipo;
	
	public MercadoFichajes() {
		fichajesEquipo = new HashMap<>();
	}

	public HashMap<Integer, HashSet<Fichaje>> getFichajesEquipo() {
		return fichajesEquipo;
	}

	public void setFichajesEquipo(HashMap<Integer, HashSet<Fichaje>> fichajesEquipo) {
		this.fichajesEquipo = fichajesEquipo;
	}
	
	public void aniadirNuevoFichaje(int equipo, Fichaje j) {
		
	}	
}
