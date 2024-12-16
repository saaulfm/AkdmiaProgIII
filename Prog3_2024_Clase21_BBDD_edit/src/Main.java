
public class Main {
	public static void main(String[] args) {
		BD.initBD("MiBD.db");
		BD.crearTablas();
		//BD.closeBD();
		Ventana v = new Ventana();
	}
}
