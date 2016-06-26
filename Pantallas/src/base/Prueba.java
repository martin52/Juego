package base;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cliente.Jugador;

import java.sql.PreparedStatement;

public class Prueba {

	private Connection conn;

	public Prueba() {
		conn = MySQLConnection.getConnection();
	}

	public void actualizarPassword(String Usuario) {

	}

	public void listarJugadores() {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("Select * from JUGADOR");
			while (rs.next()) {
				String usuario = rs.getString(1);
				String pass = rs.getString(2);
				String nick = rs.getString(3);
				String pregunta = rs.getString(4);
				String respuesta = rs.getString(5);
				String puntaje = rs.getString(6);

				System.out.println("Usuario: " + usuario + "\tPass: " + pass + "\tNick: " + nick + "\tPregunta: "
						+ pregunta + "\tRespuesta: " + respuesta + "\tPuntaje:" + puntaje);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void agregarJugador(String usuario, String nick, String pass, String pregunta, String respuesta) {
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement("Insert into Jugador values(?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, usuario);
			pstmt.setString(2, pass);
			pstmt.setString(3, nick);
			pstmt.setString(4, pregunta);
			pstmt.setString(5, respuesta);
			pstmt.setInt(6, 0);
			pstmt.execute();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void actualizarPassWord(String usuario, String nueva) {
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement("Update JUGADOR set Pass = ? where Usuario = ?");
			pstmt.setString(1, nueva);
			pstmt.setString(2, usuario);
			pstmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean verificarPassword(String usuario, String passWord) {
		Statement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = conn.createStatement();

			rs = pstmt
					.executeQuery("Select * from JUGADOR Where Usuario='" + usuario + "' AND Pass='" + passWord + "'");
			if (rs.first()) {
				pstmt.close();
				return true;
			} else {
				pstmt.close();
				return false;
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				pstmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public void desconectar() {
		MySQLConnection.close();
	}

	public void registrarse(String nombre, String nick, String pass, String pregunta, String respuesta) {
		Statement st = null;
		try {
			st = conn.createStatement();

			st.executeQuery("Insert into JUGADOR(Usuario,Pass,Nick,Pregunta,Respuesta,Puntaje) values(" + nombre + ","
					+ pass + "," + nick + "," + pregunta + "," + respuesta + ",0");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String obtenerPregunta(Jugador jugador) {
		Statement stmt = null;
		ResultSet rs = null;
		String nombre = jugador.getUsuario();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("Select * from JUGADOR where Usuario='" + nombre + "'");
			if (rs.first())
				return rs.getString(4);
			return "";
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	public void obtenerDatosUsuario(Jugador jug) {

		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("Select * from JUGADOR where Usuario='" + jug.getUsuario() + "'");
			if (rs.first()) {
				jug.setUsuario(rs.getString(1));
				jug.setPass(rs.getString(2));
				jug.setNick(rs.getString(3));
				jug.setPregunta(rs.getString(4));
				jug.setRespuesta(rs.getString(5));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {

		Prueba prueba = new Prueba();
		System.out.println("Lista de Jugadores");
		prueba.listarJugadores();
		prueba.desconectar();
	}
}