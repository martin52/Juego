package servidor;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class LoginAdmin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginAdmin frame = new LoginAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginAdmin() {
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 279, 236);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblAdmin = new JLabel("Admin: ");
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a: ");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		
		JButton btnAceptar = new JButton("Ingresar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textField.getText().contentEquals("admin") && passwordField.getText().contentEquals("admin"))
				abrirPrincipalServer();
				else
					abrirDialogoError();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(45)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblContrasea)
								.addComponent(lblAdmin))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addComponent(passwordField)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(84)
							.addComponent(btnAceptar)))
					.addContainerGap(55, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(52)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAdmin)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblContrasea)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnAceptar)
					.addContainerGap(47, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void abrirPrincipalServer(){
		this.setVisible(false);
		new PrincipalServer(this);
	}
	
	private void abrirDialogoError(){
		JOptionPane.showMessageDialog(null,
				    "Usuario o contraseña incorrecta.", //Mensaje
				    "ERROR", //Título
				    JOptionPane.ERROR_MESSAGE); //Tipo de mensaje
	}

}
