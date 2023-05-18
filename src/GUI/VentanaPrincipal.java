package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.BoxLayout;
import java.awt.Toolkit;

public class VentanaPrincipal {

	public JFrame frmGestionMateria;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.frmGestionMateria.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//Configuro el frame
		frmGestionMateria = new JFrame();
		frmGestionMateria.setTitle("Gestión de Matería");
		frmGestionMateria.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/Recs/logo-uns2.png")));
		frmGestionMateria.setResizable(false);
		frmGestionMateria.setBounds(100, 100, 1000, 700);
		//Inicio el JPanel 
		IngresarMateria ingresarMateria = new IngresarMateria(frmGestionMateria);
		frmGestionMateria.getContentPane().add(ingresarMateria);
		ingresarMateria.setLayout(new BoxLayout(ingresarMateria, BoxLayout.Y_AXIS));
		frmGestionMateria.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
