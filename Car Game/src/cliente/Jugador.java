package cliente;

import base.Prueba;

public class Jugador {
	private String usuario;
	private String pass;
	private String nick;
	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	private String pregunta;
	private String respuesta;
    private int puntaje;

	public Jugador(String usuario) {
		super();
		Prueba p = new Prueba();
		this.usuario= usuario;
		p.obtenerDatosUsuario(this);
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}
