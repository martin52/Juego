package cliente;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import base.Prueba;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JPasswordField;

public class Login extends JFrame {

	public Inicial referencia;
	private JPanel contentPane;
	private JTextField TfUsuario;
	private JPasswordField PfContraseña;
	private Jugador jug;
	private JTextField TfPuerto;
	private JTextField TfIPServer;
	
	private Cliente cliente;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { Login frame = new Login();
	 * frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }
	 * }); }
	 */

	/**
	 * Create the frame.
	 */
	public Login(Inicial referencia) {
		setResizable(false);
		this.referencia = referencia;
		setTitle("Iniciar sesi\u00F3n");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				habilitarInicial();
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 422, 253);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsuario = new JLabel("Nombre de usuario:");
		lblUsuario.setBounds(26, 85, 153, 14);
		contentPane.add(lblUsuario);

		TfUsuario = new JTextField();
		TfUsuario.setBounds(202, 82, 181, 20);
		contentPane.add(TfUsuario);
		TfUsuario.setColumns(10);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(26, 121, 117, 14);
		contentPane.add(lblContrasea);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(conectar())
				jug = new Jugador(TfUsuario.getText());
				if (cliente.iniciarSesion(TfUsuario.getText(), new String (PfContraseña.getPassword())))
					abrirPrincipal();
				else
					abrirDialogoError();

				// prueba.desconectar();
			}

		});
		btnAceptar.setBounds(54, 182, 89, 23);
		contentPane.add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				habilitarInicial();
			}
		});
		btnCancelar.setBounds(162, 182, 89, 23);
		contentPane.add(btnCancelar);

		JLabel lblOlvideMiContrasea = new JLabel("Olvide mi contrase\u00F1a...");
		lblOlvideMiContrasea.setForeground(Color.BLUE);
		lblOlvideMiContrasea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// if la consulta del usuario existe
				Jugador jug = new Jugador(TfUsuario.getText());
				setJug(jug);
				abrirPreguntaSeguridad(jug);
				// si no existe -> "Usuario Inexistente"
			}
		});
		lblOlvideMiContrasea.setBounds(54, 148, 197, 14);
		contentPane.add(lblOlvideMiContrasea);

		PfContraseña = new JPasswordField();
		PfContraseña.setBounds(202, 115, 181, 20);
		contentPane.add(PfContraseña);
		
		TfPuerto = new JTextField();
		TfPuerto.setBounds(202, 48, 181, 22);
		contentPane.add(TfPuerto);
		TfPuerto.setColumns(10);
		
		TfIPServer = new JTextField();
		TfIPServer.setBounds(202, 13, 181, 22);
		contentPane.add(TfIPServer);
		TfIPServer.setColumns(10);
		
		JLabel lblPuerto = new JLabel("Puerto:");
		lblPuerto.setBounds(26, 51, 56, 16);
		contentPane.add(lblPuerto);
		
		JLabel lblIpDelServer = new JLabel("IP del Server:");
		lblIpDelServer.setBounds(26, 16, 95, 16);
		contentPane.add(lblIpDelServer);

		setVisible(true);
	}

	private void habilitarInicial() {
		this.referencia.setEnabled(true);
		this.dispose();
	}

	private void abrirPrincipal() {
		this.referencia.setVisible(false);
		this.setVisible(false);
		new Principal(this);
	}

	private void abrirPreguntaSeguridad(Jugador jug) {
		Prueba p = new Prueba();

		this.setEnabled(false);
		PreguntaSeguridad ventana = new PreguntaSeguridad(this, null);
		if (p.obtenerPregunta(jug).contentEquals("") == false) {
			this.jug = jug;
			ventana.label.setText(p.obtenerPregunta(jug));
			ventana.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "Usuario no registrado", // Mensaje
					"ERROR", // Título
					JOptionPane.ERROR_MESSAGE); // Tipo de mensaje
			this.setVisible(true);
			this.setEnabled(true);
		}
	}

	public Jugador getJug() {
		return jug;
	}

	public void setJug(Jugador jug) {
		this.jug = jug;
	}

	private void abrirDialogoError() {
		JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecta.", // Mensaje
				"ERROR", // Título
				JOptionPane.ERROR_MESSAGE); // Tipo de mensaje
	}
	
	private boolean conectar(){
		if(TfIPServer.getText().equals(null) || TfIPServer.getText().equals("")) {
			JOptionPane.showMessageDialog(null,
					"Ingrese un servidor al que conectarse.",
					 "Error",
					 JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else if(TfPuerto.getText().equals(null) || TfPuerto.getText().equals("")) {
			JOptionPane.showMessageDialog(null,
					"Ingrese un puerto al que conectarse.",
					 "Error",
					 JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else {
			String server = TfIPServer.getText();
			int puerto;
			try {
				puerto = Integer.parseInt(TfPuerto.getText());
			}
			catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null,
						"Los datos del puerto son invalidos.\nIngrese un numero entero",
						 "Error",
						 JOptionPane.ERROR_MESSAGE);
				return false;
			}
			try {
				cliente = new Cliente(server, puerto);
				return true;
			}
			catch(UnknownHostException e1) {
	        	JOptionPane.showMessageDialog(null,
	        			"No se pudo conectar con el servidor.\nPuede que este ocupado o no este en linea.",
						 "Error",
						 JOptionPane.ERROR_MESSAGE);
				return false;
			}
	        catch (IOException e2) {
	            JOptionPane.showMessageDialog(null,
	            		"No se pudo crear el socket.\nIntentelo nuevamente.",
						 "Error",
						 JOptionPane.ERROR_MESSAGE);
				return false;
	        }
		}
	}
}
