package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Program.Main;
import Program.Par;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;

@SuppressWarnings("serial")
public class ObtenerAlumno extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldLu;
	private JLabel lblLu;
	private Main logica;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	}

	/**
	 * Create the dialog.
	 */
	public ObtenerAlumno(JFrame ventana,Main l, boolean modal) {
		super(ventana,modal);
		logica = l;
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblLu = new JLabel("LU");
			lblLu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		{
			textFieldLu = new JTextField();
			textFieldLu.setColumns(10);
		}
		
		JLabel lblNewLabel = new JLabel("Consultar nota");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(34)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblLu)
							.addGap(18)
							.addComponent(textFieldLu, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(109, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addGap(51)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLu)
						.addComponent(textFieldLu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(77))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
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
		if(textFieldLu.getText().length()>0) {
			try {
				int lu = Integer.parseInt(textFieldLu.getText());
				Par alumno = logica.obtenerAlumno(lu);
				if(alumno != null){
					JOptionPane.showMessageDialog(mensaje,"La nota de: "+textFieldLu.getText()+" es: "+alumno.getNota());
				}else {
					JOptionPane.showMessageDialog(mensaje,"No hay alumno registrado con ese LU");
				}
			}catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(mensaje,"Ingrese un dato valido");
			}
			
			
		}else {
			JOptionPane.showMessageDialog(mensaje,"Complete los campos");
		}
	}
}
