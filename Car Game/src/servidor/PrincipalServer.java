package servidor;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class PrincipalServer extends JFrame {

	private JPanel contentPane;
	private LoginAdmin referencia;
	private JTable tablePartidas;
	private JTextField TFIP;
	private JTextField TFPuerto;
	private boolean isClicked = false;

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
		setBounds(100, 100, 499, 354);
		
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
		
		tablePartidas = new JTable();
		tablePartidas.setShowVerticalLines(false);
		tablePartidas.setToolTipText("kndekqw");
		tablePartidas.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		
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
		
		JLabel lblNewLabel = new JLabel("Nombre");
		
		JLabel lblNewLabel_1 = new JLabel("IP");
		
		JLabel lblNewLabel_2 = new JLabel("Puerto");
		
		JLabel lblNombreDelServidor = new JLabel("Nombre del servidor");
		
		JLabel lblNewLabel_3 = new JLabel("Partida");
		
		JLabel lblJugador = new JLabel("Jugador");
		
		JLabel lblEstado = new JLabel("Estado");
		
		TFIP = new JTextField();
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
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(26)
							.addComponent(btnCrearPartida)
							.addGap(29)
							.addComponent(btnVerPartida, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
							.addGap(29)
							.addComponent(btnVerTop))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(28)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addComponent(lblNewLabel_2)
								.addComponent(lblNewLabel_1))
							.addGap(93)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(TFPuerto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(TFIP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNombreDelServidor)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(tablePartidas, GroupLayout.PREFERRED_SIZE, 447, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(68, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(54)
					.addComponent(lblNewLabel_3)
					.addPreferredGap(ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
					.addComponent(lblJugador)
					.addGap(117)
					.addComponent(lblEstado)
					.addGap(63))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblNombreDelServidor))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(TFIP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(TFPuerto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEstado)
						.addComponent(lblJugador)
						.addComponent(lblNewLabel_3))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tablePartidas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(51)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCrearPartida)
						.addComponent(btnVerPartida)
						.addComponent(btnVerTop))
					.addContainerGap())
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
}
