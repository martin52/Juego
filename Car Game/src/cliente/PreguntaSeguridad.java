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

public class PreguntaSeguridad extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private Login referenciaLogin;
	public Principal referenciaPrincipal;
    public JLabel label;
    private Jugador jug;
	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { PreguntaSeguridad frame = new
	 * PreguntaSeguridad(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	public Jugador getJug() {
		return jug;
	}

	public void setJug(Jugador jug) {
		this.jug = jug;
	}

	/**
	 * Create the frame.
	 */
	public PreguntaSeguridad(Login referenciaLogin,
			Principal referenciaPrincipal) {
		setJug(referenciaLogin.getJug());
		
		this.referenciaLogin = referenciaLogin;
		this.referenciaPrincipal = referenciaPrincipal;
		setTitle("Pregunta de seguridad");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				habilitarVentanaPreviaCancelar();
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 208);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPregunta = new JLabel("Pregunta: ");
		lblPregunta.setBounds(40, 27, 69, 14);
		contentPane.add(lblPregunta);

		textField = new JTextField();
		textField.setBounds(143, 62, 267, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		this.label= new JLabel("");
		label.setBounds(143, 27, 267, 14);
		contentPane.add(label);
		
		label.setText("¿Cuántos goles le hizo Alemania a Brasil en el 2014?");
		
		JLabel lblRespuesta = new JLabel("Respuesta: ");
		lblRespuesta.setBounds(40, 65, 69, 14);
		contentPane.add(lblRespuesta);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textField.getText().contentEquals(jug.getRespuesta()))
					{
					habilitarVentanaPreviaAceptar();
					new CambiarClave( new PreguntaSeguridad(referenciaLogin,referenciaPrincipal));
					System.out.println("Pregunta correcta");}
				else
					abrirDialogoError();
			}
		});
		btnAceptar.setBounds(128, 109, 89, 23);
		contentPane.add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					habilitarVentanaPreviaCancelar();
			}
		});
		btnCancelar.setBounds(227, 109, 89, 23);
		contentPane.add(btnCancelar);
	}

	private void habilitarVentanaPreviaAceptar() {
		if (this.referenciaPrincipal == null) 
			this.referenciaLogin.setEnabled(true);
		 else {
			//this.referenciaPrincipal.setEnabled(true);
			abrirCambiarClave();
		 }
		this.dispose();

	}
	
	private void habilitarVentanaPreviaCancelar() {
		if (this.referenciaPrincipal == null) 
			this.referenciaLogin.setEnabled(true);
		 else {
			this.referenciaPrincipal.setEnabled(true);
		 }
		this.dispose();

	}
	
	private void abrirCambiarClave(){
		this.setEnabled(false);
		new CambiarClave(this);
	}
	
	private void abrirDialogoError(){
		JOptionPane.showMessageDialog(null,
				    "Respuesta incorrecta.", //Mensaje
				    "ERROR", //Título
				    JOptionPane.ERROR_MESSAGE); //Tipo de mensaje
	}
}
