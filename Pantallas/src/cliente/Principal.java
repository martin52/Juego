package cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import base.Prueba;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

public class Principal extends JFrame {

	public Login referencia;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public Principal(Login referencia) {
		this.referencia = referencia;
		setTitle("Principal");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				habilitarInicial();
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 548, 222);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnUsuario = new JMenu("Usuario");
		menuBar.add(mnUsuario);
		
		mnUsuario.setText(referencia.getJug().getNick());
		JMenuItem mntmCambiarContrasea = new JMenuItem("Cambiar contrase\u00F1a...");
		mntmCambiarContrasea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jugador jug = new Jugador(referencia.getJug().getUsuario());
				abrirPreguntaSeguridad(jug);
			}
		});
		mnUsuario.add(mntmCambiarContrasea);
		
		JMenuItem mntmCambiarNick = new JMenuItem("Cambiar Nick...");
		mntmCambiarNick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirCambiarNick();
			}
		});
		mnUsuario.add(mntmCambiarNick);
		
		JMenuItem mntmCerrarSesion = new JMenuItem("Cerrar sesi\u00F3n");
		mntmCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				habilitarInicial();
			}
		});
		mnUsuario.add(mntmCerrarSesion);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowVerticalLines(false);
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(true);
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"", null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Nombre", "Jugadores", "Estado"
			}
		));
		table.setBounds(29, 40, 473, 48);
		contentPane.add(table);
		
		JButton btnUnirseAPartida = new JButton("Unirse a partida");
		btnUnirseAPartida.setBounds(31, 101, 142, 23);
		contentPane.add(btnUnirseAPartida);
		
		JButton btnNewButton = new JButton("Estad\u00EDsticas");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirEstadisticas();
			}
		});
		btnNewButton.setBounds(183, 101, 142, 23);
		contentPane.add(btnNewButton);
		
		JButton btnTop = new JButton("Top 20");
		btnTop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirTop20();
			}
		});
		btnTop.setBounds(335, 101, 89, 23);
		contentPane.add(btnTop);
		
		JLabel lblPartida = new JLabel("Partida");
		lblPartida.setBounds(77, 11, 56, 16);
		contentPane.add(lblPartida);
		
		JLabel lblCantidadDeJugadores = new JLabel("Cantidad de Jugadores");
		lblCantidadDeJugadores.setBounds(210, 1, 130, 37);
		contentPane.add(lblCantidadDeJugadores);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(413, 11, 56, 16);
		contentPane.add(lblEstado);
		
		setVisible(true);
	}
	
	private void habilitarInicial(){
		this.referencia.referencia.setEnabled(true);
		this.referencia.referencia.setVisible(true);
		this.referencia.dispose();
		this.dispose();
	}
	private void abrirPreguntaSeguridad(Jugador jug) {
		Prueba p = new Prueba();

		this.setEnabled(false);
		PreguntaSeguridad ventana = new PreguntaSeguridad(this.referencia, null);
		if (p.obtenerPregunta(jug).contentEquals("") == false) {
			this.referencia.setJug( jug);
			ventana.label.setText(p.obtenerPregunta(jug));
			ventana.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "Usuario no registrado", // Mensaje
					"ERROR", // Título
					JOptionPane.ERROR_MESSAGE); // Tipo de mensaje
		}
	}
	
	private void abrirPreguntaSeguridad(){
		this.setEnabled(false);
		new PreguntaSeguridad(this.referencia,this);
	}
	
	private void abrirCambiarNick(){
		this.setEnabled(false);
		new CambiarNick(this);
	}
	
	private void abrirEstadisticas(){
		this.setEnabled(false);
		new EstadisticasJugador(this);
	}
	
	private void abrirTop20(){
		this.setEnabled(false);
		new Top20(this);
	}
}
