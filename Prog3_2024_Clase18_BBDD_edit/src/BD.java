import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BD {
	private Connection conn;
	
	public void initBD(String nombreBD) {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + nombreBD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void closeBD() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void crearTablas() {
		String sql;
		sql = "CREATE TABLE IF NOT EXISTS Jugador (nombre varchar(20), dorsal integer(2))";
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void borrarTablas() {
		String sql;
		sql = "DROP TABLE Jugador";
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*TIPOS DE DATOS PARA String.format
	 
	  int ->  %d
	  long -> %ld
	  float -> %f  NO FUNCIONA
	  double -> %lf NO FUNCIONA
	  char -> %c
	  String -> %s
	  
	*/
	
	// Método para insertar un jugador en la tabla JUGADOR
	public void insertarJugador(String nombre, int dorsal) {
		String sql;
		sql = String.format("INSERT INTO JUGADOR VALUES ('%s','%d')", nombre, dorsal);
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Método para borrar el jugador de la tabla JUGADOR cuyo nombre le pase por parámetro
	public void borrarJugador(String nombre) {
		String sql;
		sql = String.format("DELETE FROM JUGADOR WHERE nombre = '%s'", nombre);
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Método que devuelve un ArrayList con todos los jugadores que haya en la tabla JUGADOR
	public List<Jugador> obtenerListaJugadores() {
		List<Jugador> lJugadores = new ArrayList<>();
		String sql = "SELECT * FROM Jugador";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				// Vamos a recuperar la información a la que apunta rs
				String nombre = rs.getString("nombre");
				int dorsal = rs.getInt("dorsal");
				Jugador j = new Jugador(nombre, dorsal);
				lJugadores.add(j);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lJugadores;
	}
	
	
}
