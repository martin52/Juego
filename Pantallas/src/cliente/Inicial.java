package cliente;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Inicial extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicial frame = new Inicial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/*
	public static void main(String[] args) {
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager
					.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		new Inicial();
	}*/

	/**
	 * Create the frame.
	 */
	public Inicial() {
		setResizable(false);
		setTitle("Inicio");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				AbreDialogoConfirmacion();
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLogin = new JButton("Iniciar sesi\u00F3n");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirLogin();
			}
		});
		btnLogin.setBounds(156, 57, 134, 23);
		contentPane.add(btnLogin);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirRegistrarse();
			}
		});
		btnRegistrarse.setBounds(156, 113, 134, 23);
		contentPane.add(btnRegistrarse);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AbreDialogoConfirmacion();
			}
		});
		btnSalir.setBounds(156, 172, 134, 23);
		contentPane.add(btnSalir);
	}
	
	private void abrirLogin() {
		this.setEnabled(false);
		new Login(this);
	}
	
	private void abrirRegistrarse() {
		this.setEnabled(false);
		new Registrarse(this);
	}
	
	private void AbreDialogoConfirmacion() {
		int respuesta = JOptionPane.showConfirmDialog(this,
				"Desea salir?", getTitle(),
				JOptionPane.YES_NO_OPTION);
		if (respuesta == JOptionPane.YES_OPTION)
			System.exit(0);

	}
}
