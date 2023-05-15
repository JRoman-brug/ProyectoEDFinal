package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

import Program.Main;

@SuppressWarnings("serial")
public class EliminarAlumno extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JLabel lblNewLabel_1;
	private AdministrarMateria adminMateria;
	private Main logica;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	}

	/**
	 * Create the dialog.
	 */
	public EliminarAlumno(JFrame ventana,AdministrarMateria adminM, Main l, boolean modal) {
		super(ventana,modal);
		logica = l;
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		adminMateria = adminM;
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblNewLabel_1 = new JLabel("LU");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		{
			textField = new JTextField();
			textField.setColumns(10);
		}
		
		JLabel lblNewLabel_2 = new JLabel("Ingresar registro");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(32)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_2)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addGap(42)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(89, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(75)
					.addComponent(lblNewLabel_2)
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(74, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						aceptar();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	public void aceptar() {
		//Mensaje 
		JOptionPane mensaje = new JOptionPane();
		//verifico que el textfield tenga contenido
		if(textField.getText().length()>0) {
			try {
				int lu = Integer.parseInt(textField.getText());
				if(logica.eliminarRegistro(lu)) {
					//Si existe el alumno
					adminMateria.actualizarDatos();
					dispose();
				}else {
					//si no existe el alumno
					JOptionPane.showMessageDialog(mensaje,"No hay alumno registrado con ese LU");
				}
			}catch(NumberFormatException e) {
				//Si ingresa un string
				JOptionPane.showMessageDialog(mensaje,"Ingrese un dato valido");
			}
		}else {
			//popup si el textfield esta vacio
			JOptionPane.showMessageDialog(mensaje,"Complete los campos");
		}
	}

}
