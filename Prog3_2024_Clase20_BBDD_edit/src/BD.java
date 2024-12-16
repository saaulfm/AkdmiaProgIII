import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
			sql = "CREATE TABLE IF NOT EXISTS Fichajes (idE int, nombreJ String, fecha long)";
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
	
	// Método para cargar los jugadores del fichero "jugadores.csv"
	public static void cargarJugadores(String nomFich) {
		try {
			String sql = "INSERT INTO Jugador VALUES (?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			Scanner sc = new Scanner(new File(nomFich)); // Abrimos el fichero
			while(sc.hasNextLine()) { // Leemos todas las líneas del fichero
				String linea = sc.nextLine(); // Leo una línea
				String [] partes = linea.split(";"); // Separo los datos por ;
				ps.setString(1, partes[0]);
				ps.setInt(2, Integer.parseInt(partes[1]));
				ps.setFloat(3, Float.parseFloat(partes[2]));
				ps.execute();
			}
			ps.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Método para obtener todos los jugadores de la lista de jugadores
	public static List<Jugador> obtenerTodosLosJugadores() {
		List<Jugador> lj = new ArrayList<Jugador>();
		String sql = "SELECT * FROM Jugador";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) { // Recorremos todas las tuplas/filas del ResultSet
				String nom = rs.getString("nombre");
				int dorsal = rs.getInt("dorsal");
				float valor = rs.getFloat("valor");
				Jugador j = new Jugador(nom, dorsal, valor);
				lj.add(j);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lj;
	}
	
	// Método para insertar el fichaje
	public static void insertarFichaje(int idE, String nom, long fecha) {
		String sql = "INSERT INTO Fichajes VALUES (?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, idE);
			ps.setString(2, nom);
			ps.setLong(3, fecha);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
