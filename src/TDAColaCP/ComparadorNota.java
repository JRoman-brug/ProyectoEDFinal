package TDAColaCP;


public class ComparadorNota<E> implements java.util.Comparator<E> { 


	/**
	 * Crea comparador de nota.
	 */
	public ComparadorNota() {
	}
	/**
	 * Compara dos alumnos para el orden.
	 * @param a Primer alumno para comparar.
	 * @param b Segundo alumno para comparar.
	 * @return Retorna -1 si el primer alumno tiene la nota más alta,
	 *  cero si ambos alumnos tienen la misma nota
	 *  o 1 si el segundo alumno tiene la nota más alta.
	 */
	public int compare(E a, E b) { 
		return ((Comparable) b).compareTo(a);
	}
} 

