package Sincronizacion;

import base.Prueba;

public class ThreadServer extends Thread {
	private Server servidor;
	private Prueba baseDatos;
	private Usuario user;
	private String partida;
	private boolean running;
	private boolean partidaCorriendo;

	public ThreadServer(Server server, Usuario usuario) {
		this.servidor = server;
		this.baseDatos = servidor.getBaseDatos();
		usuario.setNombre("");
		user = usuario;
		running = true;
		partidaCorriendo = false;
	}
	
	public synchronized void run(){}
}
