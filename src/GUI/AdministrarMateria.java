package GUI;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import Program.Main;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Month;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

@SuppressWarnings("serial")
public class AdministrarMateria extends JPanel {

	//ventana principal
	private JFrame ventana;
	private Main logica;

	//Paneles 
	private JPanel panel = new JPanel();
	private JPanel panelDatos = new JPanel();
	private JPanel panelAdminitrar = new JPanel();
	private JPanel panelTabla = new JPanel();
	private JPanel panelDatosPromedioNotaMin = new JPanel();
	private JPanel panelCabecera = new JPanel();

	private JPanel panelTitulos = new JPanel();
	//Paneles de botones
	private JPanel panelBotones = new JPanel();
	private JPanel panelBotonesSuperior = new JPanel();
	private JPanel panelBotonesInferior = new JPanel();
	
	
	private JLabel lblPhoto = new JLabel("");
	private JLabel lblPromedio = new JLabel("El promedio general es: 0");
	private JLabel lblNotaMinima = new JLabel("La nota mínima es: 0");
	private JLabel lblMateria = new JLabel("");
	private JLabel lblFecha = new JLabel("");
	
	//Separadores parte de abajo donde va promedio
	private Component horizontalStrut = Box.createHorizontalStrut(30);
	private Component horizontalStrut_1 = Box.createHorizontalStrut(40);

	//Panel de tabla
	private JScrollPane scrollPane = new JScrollPane();
	private JTable table_1 = new JTable();
	private DefaultTableModel modelo;
	
	//Botones
	private JButton btnAlumnoNuevo = new JButton("Agregar alumno");
	private JButton btnOrdenMayorMenor = new JButton("Ordenar mayor a menor");
	private JButton btnAlumnosAprobados = new JButton("Mostrar alumnos aprobados");
	private JButton btnAlumnosDesaprobados = new JButton("Mostrar alumnos desaprobados");
	private JButton btnEliminarAlumno = new JButton("Eliminar alumno");
	private JButton btnObtenerNota = new JButton("Obtener nota");
	private JButton btnTablaOriginal = new JButton("Tabla original");
	private JButton btnOrdenarNota = new JButton("Ordenar por nota");

	/**
	 * Creo el panel donde se adminitra las notas
	 * @param v Frame donde se contiene la aplicacion
	 * @param l Objeto de logica
	 */
	public AdministrarMateria(JFrame v, Main l) {
		ventana = v;
		logica = l;

		//Configuro panel principal 
		setSize(1000,700);

		add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {700, 300};
		gbl_panel.rowHeights = new int[] {700};
		gbl_panel.columnWeights = new double[]{1.0, 0.0};
		gbl_panel.rowWeights = new double[]{1.0};
		panel.setLayout(gbl_panel);

		//Configurar panelDatos
		GridBagConstraints gbc_panelDatos = new GridBagConstraints();
		gbc_panelDatos.anchor = GridBagConstraints.NORTH;
		gbc_panelDatos.gridx = 0;
		gbc_panelDatos.gridy = 0;
		panel.add(panelDatos, gbc_panelDatos);
		GridBagLayout gbl_panelDatos = new GridBagLayout();
		gbl_panelDatos.columnWidths = new int[] {700};
		gbl_panelDatos.rowHeights = new int[] {150, 450, 50};
		gbl_panelDatos.columnWeights = new double[]{1.0};
		gbl_panelDatos.rowWeights = new double[]{1.0, 1.0, 1.0};
		panelDatos.setLayout(gbl_panelDatos);


		//Grid de panel adminitrar
		GridBagConstraints gbc_panelAdminitrar = new GridBagConstraints();
		gbc_panelAdminitrar.insets = new Insets(0, 30, 5, 0);
		gbc_panelAdminitrar.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelAdminitrar.gridx = 0;
		gbc_panelAdminitrar.gridy = 0;
		panelAdminitrar.setBackground(new Color(255, 255, 0));
		panelDatos.add(panelAdminitrar, gbc_panelAdminitrar);
		panelAdminitrar.setLayout(new BoxLayout(panelAdminitrar, BoxLayout.Y_AXIS));

		//Configurar panel cabecera
		panelAdminitrar.add(panelCabecera);
		GridBagLayout gbl_panelCabecera = new GridBagLayout();
		gbl_panelCabecera.columnWidths = new int[] {700};
		gbl_panelCabecera.rowHeights = new int[] {75, 50, 0};
		gbl_panelCabecera.columnWeights = new double[]{1.0};
		gbl_panelCabecera.rowWeights = new double[]{0.0, 0.0, 1.0};
		panelCabecera.setLayout(gbl_panelCabecera);
		
		GridBagConstraints gbc_panelTitulos = new GridBagConstraints();
		gbc_panelTitulos.insets = new Insets(0, 0, 5, 0);
		gbc_panelTitulos.gridx = 0;
		gbc_panelTitulos.gridy = 0;
		panelCabecera.add(panelTitulos, gbc_panelTitulos);
		panelTitulos.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panelTitulos2 = new JPanel();
		panelTitulos2.setLayout(new BoxLayout(panelTitulos2, BoxLayout.Y_AXIS));

		//Label materia
		lblMateria.setText(logica.getMateria());
		panelTitulos2.add(lblMateria);
		lblMateria.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblMateria.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 30));
		
		//Label tiempo
		Month mes = LocalDate.now().getMonth();
		int anio = LocalDate.now().getYear();
		int dia = LocalDate.now().getDayOfMonth();
		
		lblFecha.setText(dia+" "+mes+" "+anio+ " - Primer Cuatrimestre");
		lblFecha.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelTitulos2.add(lblFecha);
		panelTitulos.add(panelTitulos2);

		GridBagConstraints gbc_panelBotones = new GridBagConstraints();
		gbc_panelBotones.insets = new Insets(0, 0, 5, 0);
		gbc_panelBotones.anchor = GridBagConstraints.WEST;
		gbc_panelBotones.gridx = 0;
		gbc_panelBotones.gridy = 1;
		panelCabecera.add(panelBotones, gbc_panelBotones);
		panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
		//Botones
		panelBotones.add(panelBotonesSuperior);
		btnAlumnoNuevo.setFont(new Font("Tahoma", Font.PLAIN, 9));

		panelBotonesSuperior.add(btnAlumnoNuevo);
		btnOrdenMayorMenor.setFont(new Font("Tahoma", Font.PLAIN, 9));

		panelBotonesSuperior.add(btnOrdenMayorMenor);
		btnAlumnosAprobados.setFont(new Font("Tahoma", Font.PLAIN, 9));

		panelBotonesSuperior.add(btnAlumnosAprobados);
		btnAlumnosDesaprobados.setFont(new Font("Tahoma", Font.PLAIN, 9));

		panelBotonesSuperior.add(btnAlumnosDesaprobados);
		btnAlumnosDesaprobados.setFont(new Font("Tahoma", Font.PLAIN, 9));
		
		FlowLayout fl_panelBotonesInferior = (FlowLayout) panelBotonesInferior.getLayout();
		fl_panelBotonesInferior.setAlignment(FlowLayout.LEFT);

		//Botones panel inferior
		panelBotones.add(panelBotonesInferior);
		btnEliminarAlumno.setFont(new Font("Tahoma", Font.PLAIN, 9));

		panelBotonesInferior.add(btnEliminarAlumno);
		btnObtenerNota.setFont(new Font("Tahoma", Font.PLAIN, 9));

		panelBotonesInferior.add(btnObtenerNota);
		btnTablaOriginal.setFont(new Font("Tahoma", Font.PLAIN, 9));
		
		panelBotonesInferior.add(btnTablaOriginal);
		btnOrdenarNota.setFont(new Font("Tahoma", Font.PLAIN, 9));
		
		panelBotonesInferior.add(btnOrdenarNota);
		
		//Datos
		GridBagConstraints gbc_panelTabla = new GridBagConstraints();
		gbc_panelTabla.insets = new Insets(0, 20, 5, 0);
		gbc_panelTabla.gridx = 0;
		gbc_panelTabla.gridy = 1;
		panelTabla.setBackground(new Color(240, 240, 240));
		panelDatos.add(panelTabla, gbc_panelTabla);


		//Creo una tabla
		table_1.setBounds(10,38,400,200);

		//Setup del modelo de la tabla
		modelo = logica.tablaOriginal();
		table_1.setModel(modelo);
		table_1.getTableHeader().setReorderingAllowed(false);
		table_1.getTableHeader().setResizingAllowed(false);
			
		scrollPane.setViewportView(table_1);

		scrollPane.setBounds(10, 38, 414, 212);
		GroupLayout gl_panelTabla = new GroupLayout(panelTabla);
		gl_panelTabla.setHorizontalGroup(
			gl_panelTabla.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelTabla.createSequentialGroup()
					.addContainerGap(22, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 688, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panelTabla.setVerticalGroup(
			gl_panelTabla.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTabla.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
					.addContainerGap())
		);
		panelTabla.setLayout(gl_panelTabla);


		//Configuro panel datos con el promedio y nota minima

		GridBagConstraints gbc_panelDatosPromedioNotaMin = new GridBagConstraints();
		gbc_panelDatosPromedioNotaMin.anchor = GridBagConstraints.WEST;
		gbc_panelDatosPromedioNotaMin.insets = new Insets(0, 20, 5, 0);
		gbc_panelDatosPromedioNotaMin.gridx = 0;
		gbc_panelDatosPromedioNotaMin.gridy = 2;
		panelDatosPromedioNotaMin.setBackground(new Color(240, 240, 240));
		panelDatos.add(panelDatosPromedioNotaMin, gbc_panelDatosPromedioNotaMin);


		panelDatosPromedioNotaMin.add(horizontalStrut);
		panelDatosPromedioNotaMin.add(lblPromedio);
		panelDatosPromedioNotaMin.add(horizontalStrut_1);
		panelDatosPromedioNotaMin.add(lblNotaMinima);



		//Grid foto
		GridBagConstraints gbc_lblPhoto = new GridBagConstraints();
		gbc_lblPhoto.insets = new Insets(0, 0, 5, 0);
		gbc_lblPhoto.gridx = 1;
		gbc_lblPhoto.gridy = 0;
		panel.add(lblPhoto, gbc_lblPhoto);

		//foto izquierda
		Image imagen = new ImageIcon(IngresarMateria.class.getResource("/Recs/photoAlem2.png")).getImage().getScaledInstance(300, 700, Image.SCALE_SMOOTH);
		lblPhoto.setIcon(new ImageIcon(imagen));

		//Listener de botones
		//Agregar alumno
		btnAlumnoNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llamarVentanaAgregarAlumno();
			}
		});
		//Ordenar (momentariamente lo utilizare para rellenar la tabla)
		btnOrdenMayorMenor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ordenarMayorMenor();
			}
		});
		//Alumnos aprobados
		btnAlumnosAprobados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aprobados();
			}
		});
		//Alumnos desaprobados
		btnAlumnosDesaprobados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desaprobados();
			}
		});

		//Eliminar un alumno
		btnEliminarAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llamarVentanaEliminarAlumno();
			}
		});
		//Obtener nota de un alumno
		btnObtenerNota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llamarVentanaObtenerAlumno();
			}
		});
		//Muestra la tabla con todos los alumnos y sus notas
		btnTablaOriginal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarTabla();
			}
		});
		//Filtrar por nota
		btnOrdenarNota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llamarVentanaPedirNotaFiltrar();
			}
		});
		

	}

	//Llamar a ventanas emergentes
	private void llamarVentanaAgregarAlumno() {
		AgregarAlumno agregarAlumno = new AgregarAlumno(ventana,this,logica,true);
		agregarAlumno.setVisible(true);
	}

	private void llamarVentanaEliminarAlumno() {
		EliminarAlumno eliminarAlumno = new EliminarAlumno(ventana,this,logica,true);
		eliminarAlumno.setVisible(true);
	}

	private void llamarVentanaObtenerAlumno(){
		ObtenerAlumno obtenerAlumno = new ObtenerAlumno(ventana,logica,true);
		obtenerAlumno.setVisible(true);
	}
	
	private void llamarVentanaPedirNotaFiltrar() {
		PedirNotaFiltrar ordenarNotas = new PedirNotaFiltrar(ventana,this,true);
		ordenarNotas.setVisible(true);
	}
	//Llamar a metodos del main
	
	/**
	 * Borra y actualiza la tabla en caso de agregar o eliminar
	 * @param la lista que se mostrara en la tabla
	 */
	private void actualizarTabla() {
		//Reseteo la tabla
		table_1.setModel(logica.tablaOriginal());
	}
	private void actualizarPromedioNotaMinima() {
		lblPromedio.setText("El promedio general es: "+logica.calcularPromedio());
		lblNotaMinima.setText("La nota mínima es: "+logica.notaMinima());
			}
	public void actualizarDatos() {
		actualizarTabla();
		actualizarPromedioNotaMinima();
	}
	/**
	 * Filtra la tabla por aprobados
	 */
	private void aprobados() {
		//Obtengo el registro de alumnos
		table_1.setModel(logica.tablaAprobados());
	}
	/**
	 * Filtra la tabla por aprobados
	 */
	private void desaprobados() {
		//Obtengo el registro de alumnos
		table_1.setModel(logica.tablaDesaprobados());
	}
	private void ordenarMayorMenor() {
		table_1.setModel(logica.tablaOrdenada());
	}

	public void filtrarNota(int nota) {
		table_1.setModel(logica.tablaPorNota(nota));
	}
}
