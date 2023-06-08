package Program;

import java.util.Iterator;

import javax.swing.table.DefaultTableModel;

import Entry_Entrada.Entry;
import Exceptions.*;
import TDAColaCP.*;
import TDADiccionario.*;
import TDALista.*;

public class Logic {
	private String nomMateria;
	private PositionList<Par> listaAlumnos;

	/**
	 * Inicializa la materia y 
	 * crea una lista de alumnos vacía.
	 * @param Nombre de la materia 
	 */
	public Logic(String materia) {
		nomMateria = materia;
		listaAlumnos = new ListaDoblementeEnlazada<Par>();
	}

	/**
	 * Si la nota esta en entre 0 y 10 verifica que no halla otro alumno con el mismo lu,
	 * crea un Par y lo agrega a la lista de alumnos, de lo contrario no agrega ningún alumno.
	 * @param lu
	 * @param nota
	 * @return true si agregó un alumno, false si no agregó ningún alumno
	 */
	public boolean agregarAlumno(int lu,int nota){
		boolean flag = false;
		if(nota>=0 && nota<=10) {
			if(!verificarAlumno(lu)) {
				Par alumnoNuevo = new Par(lu,nota);
				listaAlumnos.addFirst(alumnoNuevo);
				flag = true;
			}
		}
		return flag;
	}
	/**
	 * Retorna el nombre de la materia.
	 * @return nombre de la materia
	 */
	public String getMateria() {
		return nomMateria;
	}
	
	/**
	 * Verifica si el registro esta vacío.
	 * @return si la lista vacía
	 */
	public boolean registroIsEmpty() {
		return listaAlumnos.isEmpty();
	}
	/**
	 * Retorna un alumno mediante su lu,
	 * si no lo encuentra retorna null.
	 * @param lu
	 * @return Par null o existente
	 */
	public Par obtenerAlumno(int lu) {
		Par toReturn = null;
		if(!listaAlumnos.isEmpty()) {
			Iterator<Par> it = listaAlumnos.iterator();
			Par aux;
			while(it.hasNext() && toReturn == null) {
				aux = it.next();
				if(aux.getLu() == lu) toReturn = aux;
			}
		}
		return toReturn;
	}
	
	/**
	 * Calcula el promedio de la materia.
	 * @return promedio de la materia
	 */
	public double calcularPromedio() {
		double prom = 0;
		for(Par alumno : listaAlumnos) {
			prom = prom + alumno.getNota();
		}
		return Math.round(prom/listaAlumnos.size()*100)/100d;
	}

	/**
	 * Recorre la lista de alumnos, si encuentra un lu igual al del parametro 
	 * elimina ese alumno y retorna true,
	 * sino retorna false.
	 * @param lu
	 * @return si se eliminó el alumno
	 */
	public boolean eliminarRegistro(int lu) {
		boolean flag = false;
		if(!listaAlumnos.isEmpty()) {
			Iterable<Position<Par>> lpos = listaAlumnos.positions();
			Iterator<Position<Par>> it = lpos.iterator();
			while( it.hasNext() && !flag ) {
				Position<Par> p = it.next();
				if (p.element().getLu() == lu) {
					flag = true;
					try {
						listaAlumnos.remove(p);
					} catch (InvalidPositionException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return flag;
	}

	/**
	 * Si existe alumno retorna true de lo contrario false.
	 * @param lu
	 * @return si se verificó la existencia del alumno
	 */
	private boolean verificarAlumno(int lu) {
		boolean flag = false;
		if(!listaAlumnos.isEmpty()) {
			Iterator<Par> it = listaAlumnos.iterator();
			while ( it.hasNext() && !flag) { 
				Par alumno = it.next();
				flag = alumno.getLu() == lu;
			}
		}
		return flag;
	}

	/**
	 * Busca y agrega a una lista a todos alumnos con notas mayores o igual a 6,
	 * si la lista de alumnos esta vacía retorna una lista vacía.
	 * @return lista de Lu's
	 */
	public Iterable<Par> alumnosAprobados() {
		PositionList<Par> alumnosAprobados = new ListaDoblementeEnlazada<Par>();
		if(!listaAlumnos.isEmpty()) {
			for(Par alumno : listaAlumnos) {
				if(alumno.getNota()>=6) alumnosAprobados.addFirst(alumno);
			}
		}
		return alumnosAprobados;
	}

	/**
	 * Busca y agrega a una lista a todos los alumnos con notas menores a 6,
	 * si la lisa de alumnos esta vacía retorna una lista vacía.
	 * @return lista de Lu's
	 */
	public Iterable<Par> alumnosDesaprobados(){
		PositionList<Par> alumnosDesprobados = new ListaDoblementeEnlazada<Par>();
		if(!listaAlumnos.isEmpty()) {
			for(Par alumno : listaAlumnos) {
				if(alumno.getNota()<6) alumnosDesprobados.addFirst(alumno);
			}
		}
		return alumnosDesprobados;
	}

	/**
	 * Ordena los datos y busca la mínima nota,
	 * si lista alumnos esta vacía retorna 0.
	 * @return nota mínina 
	 */
	public int notaMinima() {
		int toRet = 0;
		PriorityQueue<Integer,Integer> cola = new Heap<Integer,Integer>(listaAlumnos.size(), new ComparadorNota<Integer>());
		if(!listaAlumnos.isEmpty()) {
			for (Par alumno : listaAlumnos) {
				try {
					cola.insert(alumno.getNota(),alumno.getLu());
				} catch (InvalidKeyException e) {
					e.printStackTrace();
				}
			}
			try {
				toRet = cola.min().getKey();
			} catch (EmptyPriorityQueueException e) {
				e.printStackTrace();
			}
		}
		return toRet;
	}

	/**
	 * Ordena a todos los alumnos a en una lista segun su nota,
	 * si la lista de alumnos esta vacía retorna un lista vacía.
	 * @return lista ordenada de mayor a menor
	 */
	public Iterable<Par> ordenarMayorMenor(){
		PositionList<Par> toRet = new ListaDoblementeEnlazada<Par>();
		if(!listaAlumnos.isEmpty()) {
			int sizeListaAlumnos = listaAlumnos.size();
			PriorityQueue<Integer,Integer> colaCP = new Heap<Integer,Integer>(sizeListaAlumnos, new ComparadorNota<Integer>());
			for (Par alumno : listaAlumnos) {
				try {
					colaCP.insert(alumno.getNota(),alumno.getLu());
				} catch (InvalidKeyException e) {
					e.printStackTrace();
				}
			}
			for (int i=0;i<sizeListaAlumnos;i++) {
				Entry<Integer, Integer> e;
				try {
					e = colaCP.removeMin();
					Par alumno = new Par(e.getValue(),e.getKey());
					toRet.addLast(alumno);
				} catch (EmptyPriorityQueueException e1) {
					e1.printStackTrace();
				}
			}
		}
		return toRet;
	}

	/**
	 * Busca y agrega a una lista a todos los alumnos con nota 'n',
	 * si no hay alumnos con nota 'n' retorna una lita vacía.
	 * @param nota
	 * @return lista con alumnos con nota 'n'
	 */
	public Iterable<Par> buscarPorNota(int n){
		PositionList<Par> toRet = new ListaDoblementeEnlazada<Par>();
		if(!listaAlumnos.isEmpty()) {
			Dictionary<Integer,Integer> dic = new DiccionarioHash<Integer,Integer>();
			for (Par alumno : listaAlumnos) {
				try {
					dic.insert(alumno.getNota(),alumno.getLu());
				} catch (InvalidKeyException e) {
					e.printStackTrace();
				}
			}
			try {
				for(Entry<Integer, Integer> alumno : dic.findAll(n)) {
					Par aux = new Par(alumno.getValue(),alumno.getKey());
					toRet.addFirst(aux);
				}
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			}
		}
		return toRet;
	}

	//Métodos para la tabla 
	/**
	 * Crea el modelo de una tabla con los elementos de la lista que se pasa por parámetro.
	 * @param lista que se quiera imprimir en la tabla 
	 * @return modelo de tabla con los datos de lista
	 */
	private DefaultTableModel actualizarTabla(PositionList<Par> lista) {
		//Reseteo la tabla
		DefaultTableModel modelo = getModelo();
		for(Par elem:lista) {
			modelo.addRow(new Object[] {elem.getLu(),elem.getNota()});
		}
		return modelo;
	}
	/**
	 * @return modelo con todos los alumnos
	 */
	public DefaultTableModel tablaOriginal() {
		return actualizarTabla(listaAlumnos);
	}
	/**
	 * @return modelo con alumnos aprobados
	 */
	public DefaultTableModel tablaAprobados() {
		return actualizarTabla(alumnosAprobados());
	}
	/**
	 * @return modelo con todos los alumnos desaprobados
	 */
	public DefaultTableModel tablaDesaprobados() {
		return actualizarTabla(alumnosDesaprobados());
	}

	/**
	 * @return modelo con todos los datos ordenados
	 */
	public DefaultTableModel tablaOrdenada() {
		return actualizarTabla(ordenarMayorMenor());
	}
	/**
	 * @param nota que se quiera filtrar
	 * @return modelo con todos los alumnos con la misma nota
	 */
	public DefaultTableModel tablaPorNota(int nota) {
		return actualizarTabla(buscarPorNota(nota));
	}
	/**
	 * Crea modelo de tabla vacio con el formato de LU-MATERIA.
	 * @return modelo de tabla vacía
	 */
	private DefaultTableModel getModelo() {
		//Reseteo la tabla
		@SuppressWarnings("serial")
		DefaultTableModel toReturn = new DefaultTableModel(0,0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		String[] columnsName = new String[] {"LU","Nota"};
		toReturn.setColumnIdentifiers(columnsName);
		return toReturn;
	}
}
