package TDALista;

import java.util.Iterator;

import Exceptions.*;

public class ListaDoblementeEnlazada<E> implements PositionList<E> {
    protected Nodo<E> header;
    protected Nodo<E> trailer;
    protected int size;
    
    /**
     * Crea una lista doblemente enlazada
     */
    public ListaDoblementeEnlazada() {
        header = new Nodo<E>(null);
        trailer = new Nodo<E>(null);
        
        header.setSiguiente(trailer);
        trailer.setAnterior(header);
        
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Position<E> first() throws EmptyListException {
    	if(size==0) throw new EmptyListException("Lista vacia");
		return header.getSiguiente();
    }

    @Override
    public Position<E> last() throws EmptyListException {
    	if(size==0) throw new EmptyListException("Lista vacia");
		return trailer.getAnterior();
    }

    @Override
    public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
    	Nodo<E> nodo = checkPosition(p);
        if(p==trailer.getAnterior())throw new BoundaryViolationException("La posicion es del útlimo elemento de la lista.");
        return nodo.getSiguiente();
    }

    @Override
    public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
    	Nodo<E> nodo = checkPosition(p);
        if(p==header.getSiguiente())throw new BoundaryViolationException("La posicion es del primer elemento de la lista.");
        return nodo.getAnterior();
    }

    @Override
    public void addFirst(E element) {
    	Nodo<E> aux = new Nodo<E>(element,header.getSiguiente(),header);
		header.getSiguiente().setAnterior(aux);
		header.setSiguiente(aux);
		size++;
    }

    @Override
    public void addLast(E element) {
    	Nodo<E> aux = new Nodo<E>(element,trailer,trailer.getAnterior());
		trailer.getAnterior().setSiguiente(aux);
		trailer.setAnterior(aux);
		size++;
    }

    @Override
    public void addAfter(Position<E> p, E element) throws InvalidPositionException {
    	Nodo<E> aux = checkPosition(p);
		Nodo<E> nuevo = new Nodo<E>(element);
		
		nuevo.setAnterior(aux);
		nuevo.setSiguiente(aux.getSiguiente());
		
		aux.getSiguiente().setAnterior(nuevo);
		aux.setSiguiente(nuevo);
		
		size++;
    }

    @Override
    public void addBefore(Position<E> p, E element) throws InvalidPositionException {
    	Nodo<E> aux = checkPosition(p);
		Nodo<E> nuevo = new Nodo<E>(element);
		
		nuevo.setAnterior(aux.getAnterior());
		nuevo.setSiguiente(aux);
		
		aux.getAnterior().setSiguiente(nuevo);
		aux.setAnterior(nuevo);
		
		size++;
    }

    @Override
    public E remove(Position<E> p) throws InvalidPositionException {
    	Nodo<E> aux = checkPosition(p);
		E toReturn = p.element();
		
		aux.getSiguiente().setAnterior(aux.getAnterior());
		aux.getAnterior().setSiguiente(aux.getSiguiente());
		
		aux.setElement(null);
		aux.setSiguiente(null);
		aux.setAnterior(null);
		size--;
		return toReturn;
    }

    @Override
    public E set(Position<E> p, E element) throws InvalidPositionException {
    	Nodo<E> aux = checkPosition(p);
		E toReturn = p.element();
		
		aux.setElement(element);
		
		return toReturn;
    }

    @Override
    public Iterator<E> iterator() {
        return new ElementIterator<E>(this);
    }

    @Override
    public Iterable<Position<E>> positions() {
    	PositionList<Position<E>> p = new ListaDoblementeEnlazada<Position<E>>();
		try {
			if(!isEmpty()) {
				
				Position<E> pos= first();
				while(pos != last()) {
					p.addLast(pos);
					pos = next(pos);
				}
				p.addLast(pos);
			}
		}catch(EmptyListException |InvalidPositionException |BoundaryViolationException e) {
			e.printStackTrace();
		}
		return p;
    }
    
    /**
     * Verifica que sea una posición válida.
     * @param p
     * @return
     * @throws InvalidPositionException
     */
    private Nodo<E> checkPosition(Position<E> p) throws InvalidPositionException {
    	try{
			//Si es nulo lanza que p es una posicion invalida
			if(p==null) throw new InvalidPositionException("Posicion nula");
			//Asumimos que si el elemento de la posicion es null, decimos que fue eliminado
			if(p.element() == null) throw new InvalidPositionException("La posicion fue eliminada previamente");
			
			return (Nodo<E>) p;
		}catch(ClassCastException e) {
			throw new InvalidPositionException("p no es un nodo de lista doblemente enlazada");
		}
    }

    /**
     * Clase Nodo anidada, permite evitar modificar la lista mediante casting.
     * @param <E> generico.
     */
    private class Nodo<E> implements Position<E> {
        private E element;
        private Nodo<E> siguiente;
        private Nodo<E> anterior;

        public Nodo(E element, Nodo<E> siguiente, Nodo<E> anterior) {
            this.element = element;
            this.anterior = anterior;
            this.siguiente = siguiente;
        }
        
        public Nodo(E element) {
            this(element, null, null);
        }
        
        /**
         * @return elemento del nodo
         */
        public E element() {
            return element;
        }
        
        /**
         * Asigna un elemento al nodo.
         * @param element
         */
        public void setElement(E element) {
            this.element = element;
        }
        
        /**
         * @return el nodo siguiente
         */
        public Nodo<E> getSiguiente() {
            return siguiente;
        }
        
        /**
         * Asigna el nodo pasado como parametro como nodo siguiente.
         * @param siguiente
         */
        public void setSiguiente(Nodo<E> siguiente) {
            this.siguiente = siguiente;
        }
        
        /**
         * @return el nodo anterior 
         */
        public Nodo<E> getAnterior() {
            return anterior;
        }
        
        /**
         * Asigna el nodo pasado como parametro como nodo anterior.
         * @param anterior
         */
        public void setAnterior(Nodo<E> anterior) {
            this.anterior = anterior;
        }
    }
}
