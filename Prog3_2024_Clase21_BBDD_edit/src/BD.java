import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class BD {
	private static Connection conn;

	/**
	 * Método que realiza la conexión con la base de datos
	 * 
	 * @param nombreBD : Nombre de la base de datos a la que nos vamos a conectar
	 * @return 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void initBD(String nombreBD)  {
		conn = null;

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
		sql = "CREATE TABLE IF NOT EXISTS Jugador (nomJ String, dorsal int, valor float)";
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS Equipo (idE int, nomE String)";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE IF NOT EXISTS Fichaje (nomE String, nomJ String, valor float)";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void borrarTablas() {
		String sql = "DROP TABLE IF EXISTS Jugador";
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			sql = "DROP TABLE IF EXISTS Equipo";
			stmt.executeUpdate(sql);
			sql = "DROP TABLE IF EXISTS Fichaje";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Método para añadir los datos de un nuevo equipo
	public static void insertarNuevoEquipo(Equipo eq) {
		String sql = "INSERT INTO Equipo VALUES (?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, eq.getId());
			ps.setString(2, eq.getNombre());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Método que devuelve un conjunto con los equipos de la base de datos
	public static Set<Equipo> obtenerEquipos() {
		Set<Equipo> sEquipos = new HashSet<>();
		String sql = "SELECT * FROM Equipo";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int idE = rs.getInt("idE");
				String nomE = rs.getString("nomE");
				Equipo e = new Equipo(idE, nomE);
				sEquipos.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sEquipos;
	}
	
	// Método que devuelve una lista de jugadores de la base de datos
	public static List<Jugador> obtenerJugadores(){
		List<Jugador> lJugadores = new ArrayList<>();
		String sql = "SELECT * FROM Jugador";
		//Vamos a usar try con recursos
		try (Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql);){
			while(rs.next()) {
				String nom = rs.getString(1);
				int dorsal = rs.getInt(2);
				float valor = rs.getFloat(3);
				Jugador j = new Jugador(nom, dorsal, valor);
				lJugadores.add(j);
			}
			//No cerramos rs y stmt porque estamos usando un try con recursos y se cierran automáticamente
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lJugadores;
	}
	
	// Método para insertar un nuevo valor de mercado en el fichaje
	public static void insertarNuevoValor(String nomE, String nomJ, float valorMercado) {
		String sql = "INSERT INTO Fichaje VALUES (?,?,?)";
		try (PreparedStatement ps = conn.prepareStatement(sql);){
			ps.setString(1, nomE);
			ps.setString(2, nomJ);
			ps.setFloat(3, valorMercado);
			ps.execute();
			ps.close();
			//No cierro ps porque al usar un try con recursos se cierra automáticamente
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* Método que devuelve un listado con el siguiente formato
	 * NOMBRE JUGADOR		NOMBRE EQUIPO		VALOR MERCADO*/
	public static List<String> obtenerListadoV1(){
		String sql = "SELECT J.nomJ, E.nomE, F.valor FROM Jugador J, Equipo E, Fichaje F WHERE J.nomJ = F.nomJ AND E.nomE = F.nomE";
		List<String> l = new ArrayList<>();
		try (Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql);) {
			while(rs.next()) {
				String nomJ = rs.getString(1);
				String nomE = rs.getString(2);
				float valor = rs.getFloat(3);
				//String linea = nomA+"\t"+nomAl+"\t"+nota;
				String linea = String.format("%20s%20s%20.2f", nomJ, nomE, valor);
				l.add(linea);
			}
			//No cierro stmt ni rs porque al usar un try con recursos se cierran automáticamente
			} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return l;
	}
	
	/*Método que devuelve un listado con el siguiente formato
	 * NOMBRE JUGADOR		NOMBRE EQUIPO		VALOR MERCADO*/
	public static List<String> obtenerListadoV2(){
		List<String> l = new ArrayList<>();
		String sql1 = "SELECT * FROM Fichaje";
		try (Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql1);){
			while(rs.next()) {
				int idJ = rs.getInt(1);
				int idE = rs.getInt(2);
				float valor = rs.getFloat(3);
				String sql2 = "SELECT nomE FROM Equipo WHERE idE = ?";
				PreparedStatement ps1 = conn.prepareStatement(sql2);
				ps1.setInt(1, idE);
				ResultSet rs2 = ps1.executeQuery();
				String nomE = rs2.getString(1);
				rs2.close();
				ps1.close();
				
				String sql3 = "SELECT nomJ FROM Jugador WHERE idJ = ?";
				PreparedStatement ps2 = conn.prepareStatement(sql3);
				ps2.setInt(1, idJ);
				ResultSet rs3 = ps2.executeQuery();
				String nomJ = rs3.getString(1);
				rs3.close();
				ps2.close();
				
				String linea = String.format("%20s%20s%20.2f", nomJ, nomE, valor);
				l.add(linea);
				//No cierro stmt ni rs porque al usar un try con recursos se cierran automáticamente
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}	
}
