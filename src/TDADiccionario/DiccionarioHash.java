package TDADiccionario;

import java.util.Iterator;

import Entry_Entrada.*;
import Exceptions.*;
import TDALista.*;

public class DiccionarioHash<K,V> implements Dictionary<K,V> {
    protected PositionList<Entrada<K,V>>[] buckets ;
    protected int size;
    protected int N;
    protected static final float factor = 0.5f;

    public DiccionarioHash() {
        N=11;
        size = 0;
        buckets = new ListaDoblementeEnlazada[N];
        for(int i=0;i<N;i++) {
			buckets[i]=new ListaDoblementeEnlazada<Entrada<K,V>>();
		}
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
    public Entry<K,V> find(K key) throws InvalidKeyException {
        checkKey(key);
        Entry<K,V> toRet = null;
        Iterator<Entrada<K,V>> it = buckets[hashThisKey(key)].iterator();
        Entry<K,V> e = null;
        boolean flag = false;
        while(it.hasNext() && !flag) {
        	e = it.next();
        	if(e.getKey().equals(key)) {
        		toRet = e;
        		flag = true;
        	}
        }
        return toRet;
    }

    @Override
    public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
    	checkKey(key);
        PositionList<Entry<K,V>> toRet = new ListaDoblementeEnlazada<Entry<K,V>>();
        for(Entrada<K,V> e : buckets[hashThisKey(key)]) {
    	   if(e.getKey().equals(key)) {
    		   toRet.addLast(e);
    	   }
        }
        return toRet;
    }

    @Override
    public Entry<K,V> insert(K key, V value) throws InvalidKeyException {
    	checkKey(key);
        Entry<K,V> toRet = new Entrada<K,V>(key,value);
        buckets[hashThisKey(key)].addLast((Entrada<K, V>) toRet);
        size++;
		if(size / N >= factor){
            reHash();
        }
		return toRet;
    }

    @Override
    public Entry<K,V> remove(Entry<K,V> e) throws InvalidEntryException {
    	if(e == null)throw new InvalidEntryException("Entrada nula.");
    	int i = hashThisKey(e.getKey());
        Entry<K,V> toRet = null;
        Iterable<Position<Entrada<K, V>>> pos = buckets[i].positions();	
        Iterator<Position<Entrada<K,V>>> it = pos.iterator();
		Position<Entrada<K,V>> aux = null;
		boolean encontre =false;
		while(it.hasNext() && !encontre) {
			aux = it.next();
			if(aux.element().equals(e)) {
				try {
					toRet = buckets[i].remove(aux);
				} catch (InvalidPositionException e2) {
					e2.printStackTrace();
				}
				size--;
				encontre=true;
			}
		}
		if(!encontre)throw new InvalidEntryException("La entrada no se encuentra en el diccionario.");
        return toRet;
    }

    @Override
    public Iterable<Entry<K,V>> entries() {
    	PositionList<Entry<K,V>> entries = new ListaDoblementeEnlazada<Entry<K,V>>();
        for(PositionList<Entrada<K, V>> l : buckets){
        	for(Position<Entrada<K, V>> p : l.positions()) {
        		entries.addLast(p.element());
        	}
        }
        return entries;
    }
    
    /**
     * Amplia el diccionario si se supera el factor de carga
     */
    private void reHash(){
    	Iterable<Entry<K, V>> entradas = entries();
    	N = nextPrimo(N*2);
    	size=0;
        buckets = new ListaDoblementeEnlazada[N];
        for(int i=0;i<N;i++) {
			buckets[i]=new ListaDoblementeEnlazada<Entrada<K,V>>();
		}
        for(Entry<K,V> e : entradas) {
        	try {
				this.insert(e.getKey(), e.getValue());
			} catch (InvalidKeyException e1) {
				e1.printStackTrace();
			}
        }
    }
    
    /**
     * Retorna el numero de bucket correspondiente a la key pasada como parametro.
     * @param key
     * @return número entero
     */
    private int hashThisKey(K key) {
    	try {
			checkKey(key);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
        return Math.abs(key.hashCode() % N);
    }
    
    /**
     * Chequea que la key pasada como parametro no sea nula.
     * @param key
     * @throws InvalidKeyException
     */
    private void checkKey(K key) throws InvalidKeyException {
        if(key == null)throw new InvalidKeyException("Clave nula.");
    }
	
    /**
     * Busca el siguiente número primo.
     * @param num
     * @return número primo
     */
	private int nextPrimo(int num) {
        int toReturn = 0;
        boolean isPrime = false;
        while(!isPrime) {
            isPrime = true;
            for (int j = 2; (j<=Math.sqrt(num)) && (isPrime); j++) {
                if((num % j) == 0) {
                    isPrime=false;
                    num++;
                }
            }
            if(isPrime)
                toReturn= num;
        }
        return toReturn;
    }
}
