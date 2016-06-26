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
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_4;
	private JTextField textField_5;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registrarse frame = new Registrarse();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
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
		lblNombreDeUsuario.setBounds(32, 27, 112, 14);
		contentPane.add(lblNombreDeUsuario);
		
		JLabel lblNick = new JLabel("Nick:");
		lblNick.setBounds(32, 52, 87, 14);
		contentPane.add(lblNick);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a: ");
		lblContrasea.setBounds(32, 77, 112, 14);
		contentPane.add(lblContrasea);
		
		JLabel lblConfirmarContrasea = new JLabel("Confirmar contrase\u00F1a: ");
		lblConfirmarContrasea.setBounds(32, 102, 112, 14);
		contentPane.add(lblConfirmarContrasea);
		
		JLabel lblPreguntaDeSeguridad = new JLabel("Pregunta de seguridad: ");
		lblPreguntaDeSeguridad.setBounds(32, 127, 116, 14);
		contentPane.add(lblPreguntaDeSeguridad);
		
		JLabel lblRespuestaEsperada = new JLabel("Respuesta esperada: ");
		lblRespuestaEsperada.setBounds(32, 152, 116, 14);
		contentPane.add(lblRespuestaEsperada);
		
		textField = new JTextField();
		textField.setBounds(179, 24, 185, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(179, 49, 185, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(179, 124, 185, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(179, 149, 185, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (passwordField.getText().contentEquals(passwordField_1.getText()))
				habilitarInicial();
				else
					JOptionPane.showMessageDialog(null,
						    "LAS CONTRASEÑAS INGRESADAS", //Mensaje
						    "ERROR", //Título
						    JOptionPane.ERROR_MESSAGE);
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
		
		passwordField = new JPasswordField();
		passwordField.setBounds(179, 74, 185, 20);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(179, 99, 185, 20);
		contentPane.add(passwordField_1);
		setVisible(true);
	}
	
	private void habilitarInicial(){
		this.referencia.setEnabled(true);
		this.dispose();
		Prueba prueba = new Prueba();
		if (textField.getText().contentEquals("")!= true && textField_1.getText().contentEquals("")!= true && passwordField.getText().contentEquals("") != true  && textField_4.getText().contentEquals("")!= true && textField_5.getText().contentEquals("")!= true)
		prueba.agregarJugador(textField.getText(), textField_1.getText(), new String(passwordField.getText()), textField_4.getText(), textField_5.getText());
	
	}

}
