package Sincronizacion;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import cliente.Jugador;

public class Usuario {
	private int IDenJuego;
	private Jugador jugador;
	private String nombre;
	private Socket socket;
	private ThreadServer sesion;
	private String partida;
	private ObjectOutputStream outputStream;
	private boolean ready;

	// REENTRANT LOCK SEMAFORO STREAM
	public Usuario(Socket socket) {
		this.socket = socket;
		this.partida = "";
		try {
			this.outputStream = new ObjectOutputStream(new DataOutputStream(
					this.socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// this.semaforoStream = new ReentranLock();
	}

	public int getIDenJuego() {
		return IDenJuego;
	}

	public void setIDenJuego(int iDenJuego) {
		IDenJuego = iDenJuego;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public ThreadServer getSesion() {
		return sesion;
	}

	public void setSesion(ThreadServer sesion) {
		this.sesion = sesion;
	}

	public String getPartida() {
		return partida;
	}

	public void setPartida(String partida) {
		this.partida = partida;
	}

	public ObjectOutputStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(ObjectOutputStream outputStream) {
		this.outputStream = outputStream;
	}

	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}
}
