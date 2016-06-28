package cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.AbstractMap;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import base.Prueba;

import javax.swing.JList;

public class Principal extends JFrame {

	public Login referencia;
	private JPanel contentPane;
	
	private JList<String> listPartidas;
	private DefaultListModel<String> listModelPartidas;

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
	public Principal(final Login referencia) {
		setResizable(false);
		this.referencia = referencia;
		setTitle("Principal");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				habilitarInicial();
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 540, 201);
		
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
		
		JButton btnUnirseAPartida = new JButton("Unirse a partida");
		btnUnirseAPartida.setBounds(12, 101, 142, 23);
		contentPane.add(btnUnirseAPartida);
		
		JButton btnNewButton = new JButton("Estad\u00EDsticas");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirEstadisticas();
			}
		});
		btnNewButton.setBounds(166, 101, 142, 23);
		contentPane.add(btnNewButton);
		
		JButton btnTop = new JButton("Top 20");
		btnTop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirTop20();
			}
		});
		btnTop.setBounds(320, 101, 89, 23);
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
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnActualizar.setBounds(421, 101, 97, 24);
		contentPane.add(btnActualizar);
		
		listModelPartidas = new DefaultListModel<String>();
		listPartidas = new JList<String>();
		listPartidas.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				String s = listPartidas.getSelectedValue();
				System.out.println("Seleccionaste la partida "+listPartidas.getSelectedValue());
				Integer cant = 0;
				for(AbstractMap.SimpleImmutableEntry<String, Integer> partida : datos) {
					if(partida.getKey().equals(s)) {
						cant = partida.getValue();
					}
				}
				lblCantJugadores.setText(cant.toString());
			}
		});
		listPartidas.setBounds(12, 31, 510, 67);
		listPartidas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		contentPane.add(listPartidas);
		
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
