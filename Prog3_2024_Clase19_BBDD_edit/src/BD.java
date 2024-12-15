import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BD {
private static Connection conn;
	
	public static void initBD(String nombreBD) {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + nombreBD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void closeBD() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void crearTablas() {
		String sql;
		sql = "CREATE TABLE IF NOT EXISTS Jugador (nombre String, dorsal int, valor float)";
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS Equipo (id int, nombre String)";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS Fichajes (nombreE String, dorsal int, nombreJ String)";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void borrarTablas() {
		String sql;
		sql = "DROP TABLE IF EXISTS Jugador";
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			sql = "DROP TABLE IF EXISTS Equipo";
			stmt.executeUpdate(sql);
			sql = "DROP TABLE IF EXISTS Fichajes";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Método que añade un jugador en la tabla Jugador
	public static void insertarJugador(Jugador j) {
		String sql;
		sql = "INSERT INTO Jugador VALUES (?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, j.getNombre());
			ps.setInt(2, j.getDorsal());
			ps.setFloat(3, j.getValorMercado());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Método que borra de la tabla Jugador todos aquellos cuyo valor de mercado sea mayor que el valor recibido por parámetro
	public static void borrarJugadores(float valorMercado) {
		String sql;
		sql = "DELETE FROM Jugador WHERE valor > ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setFloat(1, valorMercado);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void borrarJugadoresV2(float valorMercado) {
		String sql;
		sql = "DELETE FROM Jugador WHERE precio >" + valorMercado;
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Método que inserta en la tabla Equipo un equipo 
	public static void insertarEquipo(int idE, String nomE) {
		String sql = "INSERT INTO Equipo VALUES (?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, idE);
			ps.setString(2, nomE);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Método que comprueba si se ha registrado previamente un equipo con ese id
	public static boolean existeEquipo(int idE) {
		boolean existe = false;
		String sql = "SELECT * FROM Equipo WHERE id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, idE);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) { // Si la SELECT SÍ ha devuelto información
				existe = true;
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return existe;
	}
	
	
}
