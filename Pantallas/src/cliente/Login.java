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
import javax.swing.JPasswordField;

public class Login extends JFrame {

	public Inicial referencia;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private Jugador jug;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsuario = new JLabel("Nombre de usuario o Nick");
		lblUsuario.setBounds(26, 55, 153, 14);
		contentPane.add(lblUsuario);

		textField = new JTextField();
		textField.setBounds(202, 52, 181, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(26, 107, 117, 14);
		contentPane.add(lblContrasea);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Prueba prueba = new Prueba();
				jug = new Jugador(textField.getText());
				if (prueba.verificarPassword(textField.getText(), new String(passwordField.getPassword())))
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
				Jugador jug = new Jugador(textField.getText());
				setJug(jug);
				abrirPreguntaSeguridad(jug);
				// si no existe -> "Usuario Inexistente"
			}
		});
		lblOlvideMiContrasea.setBounds(54, 148, 197, 14);
		contentPane.add(lblOlvideMiContrasea);

		passwordField = new JPasswordField();
		passwordField.setBounds(202, 104, 181, 20);
		contentPane.add(passwordField);

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
}
