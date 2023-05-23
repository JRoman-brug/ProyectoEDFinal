package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Program.Logic;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;

@SuppressWarnings("serial")
public class AgregarAlumno extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel;
	private AdministrarMateria adminMateria;
	private Logic logica;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	}

	/**
	 * Create the dialog.
	 */
	public AgregarAlumno(JFrame ventana,AdministrarMateria adminM, Logic l, boolean modal) {
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
		{
			lblNewLabel = new JLabel("Nota");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		{
			textField_1 = new JTextField();
			textField_1.setColumns(10);
		}
		
		JLabel lblNewLabel_2 = new JLabel("Ingresar registro");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(32)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addComponent(lblNewLabel_1))
							.addGap(44)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField_1)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)))
						.addComponent(lblNewLabel_2))
					.addContainerGap(73, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(35)
					.addComponent(lblNewLabel_2)
					.addGap(37)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(55, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);{
			//Creo el boton
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
		//Creo el mensaje
		JOptionPane mensaje = new JOptionPane();
		//verifico que los textfields no esten vacios
		if(textField.getText().length()>0 && textField_1.getText().length()>0) {
			try {
				int lu = Integer.parseInt(textField.getText());
				int nota = Integer.parseInt(textField_1.getText());
				//Verifico que la nota este en el rango 1-10
				if(nota <=10 && nota >= 1) {
					if(logica.agregarAlumno(lu, nota)) {
						adminMateria.actualizarDatos();
						dispose();
					}else {
						//mensaje que el alumno no existe
						JOptionPane.showMessageDialog(mensaje,"Ya existe un alumno con LU: "+lu);
					}
				}else {
					//Mensaje que ingrese una nota entre 1 y 10
					JOptionPane.showMessageDialog(mensaje,"Ingrese una nota entre 1 y 10");
				}
				
			}catch(NumberFormatException e) {
				//Mensaje que en el caso que ingreso un string
				JOptionPane.showMessageDialog(mensaje,"Ingrese un número entero");
			}
		}else {
			//Mensaje que los textFields esten vacíos
			JOptionPane.showMessageDialog(mensaje,"Complete los campos");
		}
	}
}
