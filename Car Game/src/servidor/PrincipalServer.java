package servidor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import Sincronizacion.Server;

import com.google.gson.Gson;

import javax.swing.JTextArea;

public class PrincipalServer extends JFrame {

	private JPanel contentPane;
	private LoginAdmin referencia;
	private JTextField TFIP;
	private JTextField TFPuerto;
	private boolean isClicked = false;
	
	private Server servidor;
	
	private JTextArea textAreaEstadoPartida;
	private JTextArea textAreaCantJugadores;
	private JList<String> listNombresPartidas;
	private DefaultListModel<String> listModelPartidas;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalServer frame = new PrincipalServer();
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
	public PrincipalServer(LoginAdmin referencia) {
		this.referencia = referencia;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				habilitarLoginAdmin();
			}
		});
		setTitle("Server");
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 404, 323);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnAdministrador = new JMenu("Administrador");
		menuBar.add(mnAdministrador);
		
		JMenuItem mntmCerrarSesin = new JMenuItem("Cerrar sesi\u00F3n");
		mntmCerrarSesin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				habilitarLoginAdmin();
			}
		});
		
		mnAdministrador.add(mntmCerrarSesin);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnCrearPartida = new JButton("Crear Partida");
		btnCrearPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirNuevaPartida();
			}
		});
		
		JButton btnVerPartida = new JButton("Ver Partida");
		btnVerPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton btnVerTop = new JButton("Ver Top20");
		btnVerTop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirTop20();
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("IP");
		
		JLabel lblNewLabel_2 = new JLabel("Puerto");
		
		JLabel lblNewLabel_3 = new JLabel("Nombre Partida");
		
		JLabel lblJugador = new JLabel("Jugadores");
		
		JLabel lblEstado = new JLabel("Estado");
		
		TFIP = new JTextField();
		TFIP.setEditable(false);
		TFIP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!isClicked){
					TFIP.setText("");
					isClicked = true;
				}
			}
		});
		TFIP.setText("127.0.0.1");
		TFIP.setColumns(10);
		
		TFPuerto = new JTextField();
		TFPuerto.setText("5070");
		TFPuerto.setColumns(10);
		
		listNombresPartidas = new JList();
		listNombresPartidas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		textAreaCantJugadores = new JTextArea();
		textAreaCantJugadores.setEditable(false);
		
		textAreaEstadoPartida = new JTextArea();
		textAreaEstadoPartida.setEditable(false);
		textAreaEstadoPartida.setText("");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(listNombresPartidas, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textAreaCantJugadores, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textAreaEstadoPartida, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_2)
								.addComponent(lblNewLabel_1))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(TFPuerto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(TFIP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_3)
							.addGap(55)
							.addComponent(lblJugador)
							.addGap(77)
							.addComponent(lblEstado))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnCrearPartida)
							.addGap(21)
							.addComponent(btnVerPartida, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnVerTop, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)))
					.addGap(46))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1)
						.addComponent(TFIP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_2)
						.addComponent(TFPuerto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(lblJugador)
						.addComponent(lblEstado))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(listNombresPartidas, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addComponent(textAreaCantJugadores, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
						.addComponent(textAreaEstadoPartida, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCrearPartida)
						.addComponent(btnVerPartida)
						.addComponent(btnVerTop)))
		);
		contentPane.setLayout(gl_contentPane);
		setVisible(true);
	}
	
	private void habilitarLoginAdmin(){
		this.referencia.setVisible(true);
		this.dispose();
	}
	
	private void abrirTop20(){
		this.setEnabled(false);
		new Top20(this);
	}
	
	private void abrirNuevaPartida(){
		this.setEnabled(false);
		new NuevaPartida(this);
	}
	
	public void ingresarNombrePartida(){
		String s = (String)JOptionPane.showInputDialog(
                null,
                "Ingrese el nombre de la partida:",
                "Nueva Partida",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                null);

		if ((s != null) && (s.length() > 0)) {
			if(s.length() > 10) {
				JOptionPane.showMessageDialog(this,
						"El nombre de la partida no puede contener mas de 10 caracteres.",
						 "Error",
						 JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.crearPartida(s.trim());
			return;
		}
		else {
			if (s != null)
				JOptionPane.showMessageDialog(this,
					"Ingrese un nombre para la partida.",
					 "Error",
					 JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	public void crearPartida(String nombre){
		if(nombre!=null&&nombre!=""){
			if(servidor.crearPartida(nombre)) {
				actualizarListaDePartidas();
				listModelPartidas.addElement(nombre);
			}
			else {
				JOptionPane.showMessageDialog(this,
						"No se pudo crear la partida: ya existe una partida con el mismo nombre.",
						 "Error",
						 JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
	
	public void actualizarListaDePartidas(){
		textAreaCantJugadores.setText("");
		for(String partida : servidor.getPartidas()) {
			textAreaCantJugadores.append(servidor.getCantUsuariosEnPartida(partida)+"\n");
		}
	}

}
