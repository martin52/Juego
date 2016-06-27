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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import base.Prueba;

public class CambiarClave extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private PreguntaSeguridad referencia;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { CambiarClave frame = new
	 * CambiarClave(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the frame.
	 */
	public CambiarClave(final PreguntaSeguridad referencia) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				habilitarPrincipal();
			}
		});
		this.referencia = referencia;
		setTitle("Cambiar clave");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 222);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nueva contrase\u00F1a: ");
		lblNewLabel.setBounds(40, 56, 117, 14);
		contentPane.add(lblNewLabel);

		JLabel lblConfirmarNuevaContrasea = new JLabel("Confirmar nueva contrase\u00F1a: ");
		lblConfirmarNuevaContrasea.setBounds(40, 81, 144, 14);
		contentPane.add(lblConfirmarNuevaContrasea);

		textField = new JTextField();
		textField.setBounds(194, 53, 201, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(194, 78, 201, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textField.getText().contentEquals(textField_1.getText())) {
					Prueba p = new Prueba();
					String user = referencia.getJug().getUsuario();
					p.actualizarPassWord(user, textField.getText());
					Jugador dePrueba = new Jugador(referencia.getJug().getUsuario());
					System.out.println("Contraseña actualziada correctamente");
					System.out.println("Nueva Contraseña :" + dePrueba.getPass());
				} else
					JOptionPane.showMessageDialog(null,
						    "NO COINCIDEN LOS CAMPOS", //Mensaje
						    "ERROR", //Título
						    JOptionPane.ERROR_MESSAGE); 
					habilitarPrincipal();
				
			}
		});
		btnAceptar.setBounds(94, 133, 89, 23);
		contentPane.add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				habilitarPrincipal();
			}
		});
		btnCancelar.setBounds(198, 133, 89, 23);
		contentPane.add(btnCancelar);
		setVisible(true);
	}

	private void habilitarPrincipal() {
		// this.referencia.referenciaPrincipal.setEnabled(true);
		// this.referencia.referenciaPrincipal.setVisible(true);
		this.dispose();
	}
}
