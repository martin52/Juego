package cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CambiarNick extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private Principal referencia;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CambiarNick frame = new CambiarNick();
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
	public CambiarNick(Principal referencia) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				habilitarPrincipal();
			}
		});
		this.referencia = referencia;
		setTitle("Cambiar Nick");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 162);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNuevoNick = new JLabel("Nuevo Nick: ");
		lblNuevoNick.setBounds(28, 36, 100, 14);
		contentPane.add(lblNuevoNick);
		
		textField = new JTextField();
		textField.setBounds(138, 33, 209, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				habilitarPrincipal();
			}
		});
		btnAceptar.setBounds(89, 78, 89, 23);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				habilitarPrincipal();
			}
		});
		btnCancelar.setBounds(188, 78, 89, 23);
		contentPane.add(btnCancelar);
		setVisible(true);
	}

	private void habilitarPrincipal() {
		this.referencia.setEnabled(true);
		this.dispose();
	}
}
