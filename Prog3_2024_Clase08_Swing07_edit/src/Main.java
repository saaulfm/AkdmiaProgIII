import clases.Plantilla;
import gui.VentanaPlantilla;

public class Main {
	public static void main(String[] args) {
		Plantilla.cargarJugadores();
		System.out.println(Plantilla.getAlJugadores());
		VentanaPlantilla vp = new VentanaPlantilla();
	}
}
