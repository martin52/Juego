package cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import base.Prueba;

public class Registrarse extends JFrame {

	private Inicial referencia;
	private JPanel contentPane;
	private JTextField TFNombreUsuario;
	private JTextField TFNick;
	private JTextField TFPreguntaSeguridad;
	private JTextField TFRespuestaEsperada;
	private JPasswordField PFContraseña;
	private JPasswordField PFConfirmarContraseña;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { Registrarse frame = new
	 * Registrarse(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the frame.
	 */
	public Registrarse(Inicial referencia) {
		setResizable(false);
		this.referencia = referencia;
		setTitle("Registrarse");
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

		JLabel lblNombreDeUsuario = new JLabel("Nombre de usuario:");
		lblNombreDeUsuario.setBounds(32, 27, 135, 14);
		contentPane.add(lblNombreDeUsuario);

		JLabel lblNick = new JLabel("Nick:");
		lblNick.setBounds(32, 52, 87, 14);
		contentPane.add(lblNick);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a: ");
		lblContrasea.setBounds(32, 77, 112, 14);
		contentPane.add(lblContrasea);

		JLabel lblConfirmarContrasea = new JLabel("Confirmar contrase\u00F1a: ");
		lblConfirmarContrasea.setBounds(32, 102, 135, 14);
		contentPane.add(lblConfirmarContrasea);

		JLabel lblPreguntaDeSeguridad = new JLabel("Pregunta de seguridad: ");
		lblPreguntaDeSeguridad.setBounds(32, 127, 146, 14);
		contentPane.add(lblPreguntaDeSeguridad);

		JLabel lblRespuestaEsperada = new JLabel("Respuesta esperada: ");
		lblRespuestaEsperada.setBounds(32, 152, 146, 14);
		contentPane.add(lblRespuestaEsperada);

		TFNombreUsuario = new JTextField();
		TFNombreUsuario.setBounds(179, 24, 185, 20);
		contentPane.add(TFNombreUsuario);
		TFNombreUsuario.setColumns(10);

		TFNick = new JTextField();
		TFNick.setBounds(179, 49, 185, 20);
		contentPane.add(TFNick);
		TFNick.setColumns(10);

		TFPreguntaSeguridad = new JTextField();
		TFPreguntaSeguridad.setBounds(179, 124, 185, 20);
		contentPane.add(TFPreguntaSeguridad);
		TFPreguntaSeguridad.setColumns(10);

		TFRespuestaEsperada = new JTextField();
		TFRespuestaEsperada.setBounds(179, 149, 185, 20);
		contentPane.add(TFRespuestaEsperada);
		TFRespuestaEsperada.setColumns(10);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (isCamposCompletos() && PFContraseña.getText().contentEquals(PFConfirmarContraseña.getText()))
					habilitarInicial();
				else{
					if(!isCamposCompletos())
						JOptionPane.showMessageDialog(null,
								"DEBE COMPLETAR TODOS LOS CAMPOS PARA PODER REGISTRARSE", // Mensaje
								"ERROR", // Título
								JOptionPane.ERROR_MESSAGE);
					else
						JOptionPane.showMessageDialog(null,
								"LAS CONTRASEÑAS INGRESADAS NO COINCIDEN", // Mensaje
								"ERROR", // Título
								JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAceptar.setBounds(109, 202, 89, 23);
		contentPane.add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				habilitarInicial();
			}
		});
		btnCancelar.setBounds(208, 202, 89, 23);
		contentPane.add(btnCancelar);

		PFContraseña = new JPasswordField();
		PFContraseña.setBounds(179, 74, 185, 20);
		contentPane.add(PFContraseña);

		PFConfirmarContraseña = new JPasswordField();
		PFConfirmarContraseña.setBounds(179, 99, 185, 20);
		contentPane.add(PFConfirmarContraseña);
		setVisible(true);
	}
	
	private boolean isCamposCompletos(){
		if(TFNick.getText().contentEquals("") || TFNombreUsuario.getText().contentEquals("") || TFPreguntaSeguridad.getText().contentEquals("") || TFRespuestaEsperada.getText().contentEquals("") || PFContraseña.getText().contentEquals("") || PFConfirmarContraseña.getText().contentEquals(""))
			return false;
		return true;
	}

	private void habilitarInicial() {
		this.referencia.setEnabled(true);
		this.dispose();
		Prueba prueba = new Prueba();
		prueba.agregarJugador(TFNombreUsuario.getText(), TFNick.getText(),
				new String(PFContraseña.getText()),
				TFPreguntaSeguridad.getText(),
				TFRespuestaEsperada.getText());
	}
}
