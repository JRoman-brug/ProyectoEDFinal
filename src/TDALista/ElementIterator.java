package TDALista;

import java.util.*;

import Exceptions.*;

public class ElementIterator<E> implements Iterator<E> {
	protected PositionList<E> list;
	protected Position<E> cursor;
	
	/**
	 * Crea un elemento iterador.
	 * @param lista para iterar
	 */
	public ElementIterator(PositionList<E> l) {
		list = l; 
		if (list.isEmpty())
			cursor = null; 
		else
			try {
				cursor = list.first();
			} catch (EmptyListException e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * @return si tiene o no siguiente elemento.
	 */
	public boolean hasNext() {
		return cursor != null;
	}
	
	/**
	 * @return el siguiente elemento del iterador.
	 */
	public E next() throws NoSuchElementException {
		if (cursor == null)
			throw new NoSuchElementException("Error: No hay siguiente");
		E elem = cursor.element();
		try {
			cursor = (cursor == list.last()) ? null : list.next(cursor);

		} catch (EmptyListException e) {
			e.printStackTrace();
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		} catch (BoundaryViolationException e) {
			e.printStackTrace();
		}
		return elem;
	}
}
