package Logic;

import java.util.Iterator;

import javax.swing.table.DefaultTableModel;
import Exceptions_Entry_Entrada.*;
import TDAColaCP.*;
import TDADiccionario.*;
import TDALista.*;

public class Main {
	private String nomMateria;
	private PositionList<Par> listaAlumnos;

	/**
	 * 
	 * @param Nombre de la materia 
	 */
	public Main(String materia) {
		nomMateria = materia;
		listaAlumnos = new ListaDoblementeEnlazada<Par>();
	}

	/**
	 * Crea un Par y lo agrega a la lista de alumnos,
	 * si la nota esta en entre 0 y 10 verifica que no halla otro alumno con el mismo lu,
	 * de lo contrario no agrega nada
	 * @param lu
	 * @param nota
	 */
	public void agregarAlumno(int lu,int nota){
		if(nota>=0 && nota<=10) {
			if(!this.masDeUnaNota(lu)) {
				Par alumnoNuevo = new Par(lu,nota);
				listaAlumnos.addFirst(alumnoNuevo);
			}
		}
	}
	/**
	 * Retorna el nombre de la materia
	 * @return nombre de la materia
	 */
	public String getMateria() {
		return nomMateria;
	}
	
	/**
	 * Verifica si el registro esta vacio
	 * @return si la lista vacia
	 */
	public boolean registroIsEmpty() {
		return listaAlumnos.isEmpty();
	}
	/**
	 * Retorna un alumno mediante su lu,
	 * si no lo encuentra retorna null
	 * @param lu del alumno
	 */
	public Par obtenerAlumno(int lu) {
		Par toReturn = null;
		if(!listaAlumnos.isEmpty()) {
			Iterator<Par> it = listaAlumnos.iterator();
			Par aux;
			//Recorro la lista 

			while(it.hasNext() && toReturn == null) {
				aux = it.next();
				if(aux.getLu() == lu) toReturn = aux;
			}
		}
		return toReturn;
	}

	/**
	 * Si existe alumno retorna la nota del mismo sino retorna -1
	 * @param lu
	 * @return nota del alumno
	 */
	public int verNota(int lu) {
		int toRet=-1;
		if(!listaAlumnos.isEmpty()) {
			Iterator<Par> it = listaAlumnos.iterator();
			Par alumno = null;
			boolean flag = false;
			while(it.hasNext() && !flag) {
				alumno = it.next();
				if(alumno.getLu()==lu) {
					flag = true;
					toRet = alumno.getNota();
				}
			}
		}
		return toRet;
	}

	/**
	 * Calcula el promedio de la materia
	 * @return promedio de la materia
	 */
	public double calcularPromedio() {
		double prom=0;
		if(!listaAlumnos.isEmpty()) {
			for(Par alumno : listaAlumnos) {
				prom = prom + alumno.getNota();
			}
		}
		return Math.round(prom/listaAlumnos.size()*100)/100d;
	}

	/**
	 * Recorre la lista de alumnos si coincide materia con lu lo elimina y tira true
	 * sino tira false
	 * @param lu
	 * @param materia
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
						System.out.println(e.getMessage());
					}
				}
			}
		}
		return flag;
	}

	/**
	 * Si existe alumno retorna true sino false (si es falso la gui lo crea) 
	 * @param lu
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
	 * Si existe alumno con mas de una nota retorna true sino false
	 * @param lu
	 */
	private boolean masDeUnaNota(int lu) {
		boolean flag = false;
		int count = 0;
		if(!listaAlumnos.isEmpty()) {
			Iterator<Par> it = listaAlumnos.iterator();
			while ( it.hasNext() && !flag) { 
				Par alumno = it.next();
				if(alumno.getLu() == lu) {
					count++;
				}
				flag = count > 1;
			}
		}
		return flag;
	}

	/**
	 * Busca y agrega a una lista a todos alumnos con notas mayores o igual a 6,
	 * si la lisa de alumnos esta vacia retorna null
	 * @return PositionList<Integer> de Lu's
	 */
	private PositionList<Par> alumnosAprobados(){
		PositionList<Par> alumnosAprobados = null;
		if(!listaAlumnos.isEmpty()) {
			alumnosAprobados = new ListaDoblementeEnlazada<Par>();
			for(Par alumno : listaAlumnos) {
				if(alumno.getNota()>=6) alumnosAprobados.addFirst(alumno);
			}
		}
		return alumnosAprobados;
	}

	/**
	 * Busca y agrega a una lista a todos los alumnos con notas menores a 6,
	 * si la lisa de alumnos esta vacia retorna null
	 * @return PositionList<Integer> de Lu's
	 */
	private PositionList<Par> alumnosDesaprobados(){
		PositionList<Par> alumnosDesprobados = null;
		if(!listaAlumnos.isEmpty()) {
			alumnosDesprobados = new ListaDoblementeEnlazada<Par>();
			for(Par alumno : listaAlumnos) {
				if(alumno.getNota()<6) alumnosDesprobados.addFirst(alumno);
			}
		}
		return alumnosDesprobados;
	}

	/**
	 * Utiliza una cola con prioridad para ordenar los datos y buscar la minima nota,
	 * si lista alumnos esta vacia retorna -1
	 * @return nota mina 
	 */
	public int notaMinima() {
		int toRet = -1;
		PriorityQueue<Integer,Integer> cola = null;
		if(!listaAlumnos.isEmpty()) {
			cola = new Heap<Integer,Integer>(listaAlumnos.size(), new ComparadorNota<Integer>());if(!listaAlumnos.isEmpty()) {
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
		}
		return toRet;
	}

	/**
	 * Ordena a todos los alumnos en una lista ordenada de mayor a menor,
	 * si la lista de alumnos esta vacia retorna null
	 * @return PositionList<Integer>
	 */
	private PositionList<Par> ordenarMayorMenor(){
		//usa una CCP
		PositionList<Par> toRet = null;
		int sizeColaCP = 0;
		if(!listaAlumnos.isEmpty()) {
			toRet = new ListaDoblementeEnlazada<Par>();
			PriorityQueue<Integer,Integer> colaCP = new Heap<Integer,Integer>(listaAlumnos.size(), new ComparadorNota<Integer>());
			for (Par alumno : listaAlumnos) {
				try {
					colaCP.insert(alumno.getNota(),alumno.getLu());
				} catch (InvalidKeyException e) {
					e.printStackTrace();
				}
			}
			sizeColaCP = colaCP.size();
			for (int i=0;i<sizeColaCP;i++) {
				Entry<Integer, Integer> e;
				try {
					e = colaCP.removeMin();
					Par alumno = new Par(e.getValue(),e.getKey());
					toRet.addFirst(alumno);
				} catch (EmptyPriorityQueueException e1) {
					e1.printStackTrace();
				}
			}
		}
		return toRet;
	}

	/**
	 * Busca y agrega a una lista a todos los alumnos con nota 'n',
	 * si la lisa de alumnos esta vacia retorna null
	 * @param nota
	 * @return PositionList<Integer>
	 */
	private PositionList<Par> buscarPorNota(int n){
		//usa un diccionario
		// de la listaAlumnos pasa a un diccionario y de ahi pasa el iterable correspondiente
		PositionList<Par> toRet = null;
		if(!listaAlumnos.isEmpty()) {
			toRet = new ListaDoblementeEnlazada<Par>();
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

	//Metodos para la tabla 
	/**
	 * Crea el modelo de una tabla con los elementos de la lista que se pasa por parametros
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
	 * Retorna un modelo de tabla con todos los datos de la lista
	 * @return modelo con todos los alumnos
	 */
	public DefaultTableModel tablaOriginal() {
		return actualizarTabla(listaAlumnos);
	}
	/**
	 * Retorna un modelo de tabla con los alumnos aprobados
	 * @return modelo con alumnos aprobados
	 */
	public DefaultTableModel tablaAprobados() {
		return actualizarTabla(alumnosAprobados());
	}
	/**
	 * Retorna un modelo de tabla con los alumnos desaprobados
	 * @return modelo con todos los alumnos desaprobados
	 */
	public DefaultTableModel tablaDesaprobados() {
		return actualizarTabla(alumnosDesaprobados());
	}

	/**
	 * Retorna un modelo de tabla con todos los datos de la lista ordenadas
	 * @return modelo con todos los datos ordenados
	 */
	public DefaultTableModel tablaOrdenada() {
		return actualizarTabla(ordenarMayorMenor());
	}
	/**
	 * Retorna un modelo de tabla con todo los alumnos con la nota que se pasa por parametro
	 * @param nota que se quiera filtrar
	 * @return modelo con todos los alumnos con cierta nota
	 */
	public DefaultTableModel tablaPorNota(int nota) {
		return actualizarTabla(buscarPorNota(nota));
	}
	/**
	 * Crea modelo de tabla vacio con el formato de LU-MATERIA
	 * @return modelo de tabla vacia
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
