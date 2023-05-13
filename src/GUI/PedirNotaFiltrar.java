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


@SuppressWarnings("serial")
public class PedirNotaFiltrar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNota;
	private JLabel lblLu;

	private AdministrarMateria adminMateria;
	/**
	 * Create the dialog.
	 */
	public PedirNotaFiltrar(JFrame ventana,AdministrarMateria adminM, boolean modal) {
		super(ventana,modal);
		adminMateria = adminM;
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblLu = new JLabel("Nota");
			lblLu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		{
			textFieldNota = new JTextField();
			textFieldNota.setColumns(10);
		}
		
		JLabel lblFiltrarNota = new JLabel("Ingrese nota");
		lblFiltrarNota.setFont(new Font("Tahoma", Font.PLAIN, 35));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(50)
					.addComponent(lblLu)
					.addGap(29)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(textFieldNota, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFiltrarNota))
					.addContainerGap(99, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(35)
					.addComponent(lblFiltrarNota, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGap(34)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLu)
						.addComponent(textFieldNota, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(79, Short.MAX_VALUE))
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

		JOptionPane mensaje = new JOptionPane();
		if(textFieldNota.getText().length()>0) {
			try {
				
				int nota = Integer.parseInt(textFieldNota.getText());
				
				adminMateria.filtrarNota(nota);
				dispose();
			}catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(mensaje,"Ingrese un dato valido");
			}
			
		}else {
			JOptionPane.showMessageDialog(mensaje,"Complete los campos");
		}
	}

}
