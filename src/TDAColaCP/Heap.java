package TDAColaCP;

import java.util.Comparator;

import Exceptions_Entry_Entrada.*;

public class Heap<K,V> implements PriorityQueue<K,V> {
    protected Entrada<K,V> [] elems;
    protected int size;
    protected Comparator<K> comp;

    public Heap(int maxElems, Comparator<K> comp) {
        elems = (Entrada<K, V> []) new Entrada[maxElems];
        size = 0;
        this.comp = comp;
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
    public Entry<K, V> min() throws EmptyPriorityQueueException {
        if(isEmpty()){
            throw new EmptyPriorityQueueException("Cola vacia.");
        }
        return elems[1];
    }

    @Override
    public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
    	checkKey(key);
		if(size >= elems.length-1){
            reSize();
        }
		Entrada<K,V> entrada = new Entrada<K,V>(key, value); 
		elems[++size] = entrada; 
		int i = size;
		boolean seguir = true; 
		while ( i>1 && seguir ) {
			Entrada <K,V> elemActual = elems[i];
			Entrada <K,V> elemPadre = elems[i/2]; 
			if( comp.compare(elemActual.getKey(), elemPadre.getKey()) < 0) {
				Entrada<K,V> aux = elems[i];
				elems[i] = elems[i/2];
				elems[i/2] = aux;
				i /= 2; 
			} else
				seguir = false;
		}
        
        return entrada;
    }

    @Override
    public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
        if(isEmpty()) {
            throw new EmptyPriorityQueueException("Cola vacia.");
        }
        Entry<K,V> entrada = min();
        int m = 0;
        if( size == 1 ) { 
        	elems[1] = null; size = 0; return entrada; 
        } else {
        	elems[1] = elems[size]; elems[size] = null; size--;
	        int i = 1;
	        boolean seguir = true;
	        while ( seguir ) {
	        	int hi = i*2; 
	        	int hd = i*2+1;
	        	boolean tieneHijoIzquierdo = hi <= size(); 
	        	boolean tieneHijoDerecho = hd <= size();
	        	if( !tieneHijoIzquierdo ) {
	        		seguir = false;
	        	} else {
	        		if( tieneHijoDerecho ) {
	        			if( comp.compare( elems[hi].getKey(), elems[hd].getKey()) < 0 ) m = hi;
	        			else m = hd;
	        		} else m = hi;
	        	}
        		if(m!= 0 && comp.compare(elems[i].getKey(), elems[m].getKey()) > 0 ) {
	        		Entrada<K,V> aux = elems[i];
	        		elems[i] = elems[m];
	        		elems[m] = aux;
	        		i = m;
        		} else seguir = false;
	        }
	        return entrada;
        }
        
    }

    private void reSize() {
        Entrada<K,V> [] arr = (Entrada<K, V> []) new Entrada[elems.length*2];
        int i = 0;
        for(Entrada<K,V> e : elems){
            arr[i++]= e;
        }
        elems = arr;
    }

    private void checkKey(K key) throws InvalidKeyException {
        if(key == null) {
            throw new InvalidKeyException("Clave incorrecta.");
        }
    }
}
