package Sincronizacion;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;

import servidor.DatosPartida;
import servidor.PrincipalServer;
import base.Prueba;
import cliente.Jugador;

public class Server {
	private ServerSocket servidor;
	private Socket cliente;
	private int cantActualCliente;
	private ArrayList<Socket> sockets;
	private int cantMaximaCliente;
	private int puerto;
	private String nombreHost;
	private String IPHost;
	private Prueba baseDatos;
	private ArrayList<Usuario> usuarios;
	private PrincipalServer pantallaPrincipal;
	private HashMap<String, Partida> nombresDePartidas;
	private HashMap<Partida, DatosPartida> infoPartidas;

	private ArrayList<ThreadServer> partidas;
	
	
	public Server(int puerto, int maxConexiones,
			PrincipalServer pantallaPrincipal) throws IOException {
		nombreHost = InetAddress.getLocalHost().getHostName().toString();
		IPHost = InetAddress.getLocalHost().getHostAddress().toString();
		this.pantallaPrincipal = pantallaPrincipal;
		this.puerto = puerto;
		cantMaximaCliente = maxConexiones;
		this.cantActualCliente = 0;
		sockets = new ArrayList<Socket>();
		baseDatos = new Prueba();
		servidor = new ServerSocket(this.puerto);
		usuarios = new ArrayList<Usuario>();
		this.nombresDePartidas = new HashMap<String, Partida>();
		this.infoPartidas = new HashMap<Partida, DatosPartida>();
	}

	public ServerSocket getServidor() {
		return servidor;
	}

	public void setServidor(ServerSocket servidor) {
		this.servidor = servidor;
	}

	public Socket getCliente() {
		return cliente;
	}

	public void setCliente(Socket cliente) {
		this.cliente = cliente;
	}

	public int getCantActualCliente() {
		return cantActualCliente;
	}

	public void setCantActualCliente(int cantActualCliente) {
		this.cantActualCliente = cantActualCliente;
	}

	public ArrayList<Socket> getSockets() {
		return sockets;
	}

	public void setSockets(ArrayList<Socket> sockets) {
		this.sockets = sockets;
	}

	public int getCantMaximaCliente() {
		return cantMaximaCliente;
	}

	public void setCantMaximaCliente(int cantMaximaCliente) {
		this.cantMaximaCliente = cantMaximaCliente;
	}

	public int getPuerto() {
		return puerto;
	}

	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}

	public String getNombreHost() {
		return nombreHost;
	}

	public void setNombreHost(String nombreHost) {
		this.nombreHost = nombreHost;
	}

	public String getIPHost() {
		return IPHost;
	}

	public void setIPHost(String iPHost) {
		IPHost = iPHost;
	}

	public Prueba getBaseDatos() {
		return baseDatos;
	}

	public void setBaseDatos(Prueba baseDatos) {
		this.baseDatos = baseDatos;
	}

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public PrincipalServer getPantallaPrincipal() {
		return pantallaPrincipal;
	}

	public void setPantallaPrincipal(PrincipalServer pantallaPrincipal) {
		this.pantallaPrincipal = pantallaPrincipal;
	}

	public boolean agregarUsuarioAPartida(Usuario usuario, String partida) {
		Partida p = nombresDePartidas.get(partida);
		if (p != null) {
			DatosPartida datos = infoPartida.get(p);
			if (datos.getUsuarios().size() >= 3) {
				return false;
			}
			datos.agregarUsuario(usuario);
			usuario.setPartida(partida);
			System.out.println(usuario.getNombre() + " Agregado a la partida "
					+ partida);
			return true;
		}
		return false;
	}

	public void agregarJugadorAPartida(Jugador jugador, String partida) {
		Partida p = nombresDePartidas.get(partida);
		p.agregarJugador(jugador);
	}

	public void eliminarDePartida(Usuario usuario, String partida) {
		Partida p = new nombresDePartidas.get(partida);
		DatosPartida datos = infoPartida.get(p);
		datos.removerUsuario(usuario);
		System.out.println(usuario.getNombre() + " ha abandonado la partida "
				+ partida + "\t" + datos.getUsuarios().size());
	}

	public void eliminarTodosLosJugadoresDePartida(String partida) {
		Partida p = nombresDePartida.get(partida);
		DatosPartida datos = infoPartida.get(p);
		datos.removerTodosLosUsuarios();
	}

	public Socket aceptarConexion() {
		cliente = null;
		try {
			servidor.setSoTimeout(10000);
			cliente = servidor.accept();
			cantActualCliente++;
			if (cantActualCliente > 3) {
				cliente.close();
				cantActualCliente--;
				return null;
			} else {
				sockets.add(cliente);
			}

		} catch (SocketTimeoutException e) {
			return null;
		} catch (IOException e) {
			System.out
					.println("Error al aceptar conexiones, cerrando servidor ...");
			System.exit(-1);
		}
		return cliente;
	}

	public void eliminarServidor() {
		try {
			if (!servidor.isClosed()) {
				servidor.close();
			}
		} catch (IOException e) {
			System.out.println("Error al cerrar servidor");
		}
	}
	
	public void eliminarCliente(Usuario usuario){
		if (!usuario.getSocket().isClosed()){
			try{
				usuario.getSocket().close();
				if (!usuario.getPartida().equals("")){
					Partida p = nombresDePartidas.get(usuario.getPartida());
					DatosPartida datos = infoPartidas.get(p);
					datos.removerUsuario(usuario);
					System.out.println(usuario.getNombre()+" se ha desconectado de la partida "+ usuario.getPartida());
				}
				usuarios.remove(usuario);
				cantActualClientes--;
				pantallaPrincipal.actualizarListaDeNombres();
			}catch(IOException e){
				System.out.println("El socket del cliente "+ usuario.getNombre()+" ya estaba cerrado");
			}
		}
	}

	public boolean crearPartida(String nombre) {
		// TODO Auto-generated method stub
		return false;
	}

	public String getCantUsuariosEnPartida(String partida) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<String> getPartidas() {
		// TODO Auto-generated method stub
		return null;
	}
}
