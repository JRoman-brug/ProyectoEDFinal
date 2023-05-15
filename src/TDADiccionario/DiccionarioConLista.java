package TDADiccionario;

import java.util.Iterator;

import Entry_Entrada.*;
import Exceptions.*;
import TDALista.*;

/**
 * Diccionario implementado con LikedList, entradas no ordenadas.
 * @param <K>
 * @param <V>
 */
public class DiccionarioConLista<K,V> implements Dictionary<K,V>{
    protected PositionList<Entry<K,V>> list;

    public DiccionarioConLista() {
        list = new ListaDoblementeEnlazada<Entry<K,V>>();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Entry<K,V> find(K key) throws InvalidKeyException {
        Entry<K,V> toRet = null;
        checkKey(key);
        Iterator<Entry<K,V>> it = list.iterator();
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
        PositionList<Entry<K,V>> toReturn = new ListaDoblementeEnlazada<Entry<K,V>>();
        checkKey(key);
        for(Entry<K,V> e : list) {
            if(key.equals(e.getKey())){
                toReturn.addLast(e);
            }
        }
        return toReturn;
    }

    @Override
    public Entry<K,V> insert(K key, V value) throws InvalidKeyException {
        checkKey(key);
        Entry<K,V> e = new Entrada<K,V>(key,value);
        list.addLast(e);
        return e;
    }

    @Override
    public Entry<K,V> remove(Entry<K,V> e) throws InvalidEntryException {
        checkEntry(e);
        
        Entry<K,V> toRet = null;
        boolean flag = false;
        Iterable<Position<Entry<K,V>>> pos = list.positions();
        Iterator<Position<Entry<K,V>>> it = pos.iterator();
        Position<Entry<K,V>> p = null;
        
        while(it.hasNext() && !flag) {
        	p = it.next();
        	if(p.element().equals(e)) {
        		try {
					toRet = list.remove(p);
				} catch (InvalidPositionException e1) {
					System.out.println(e1.getMessage());
				}
        		flag = true;
        	}
        }
        if(!flag){
            throw new InvalidEntryException("La entrada no se encuentra en el diccionario.");
        }
        return toRet;
    }

    @Override
    public Iterable<Entry<K, V>> entries() {
        return list;
    }

    private void checkKey(K key) throws InvalidKeyException {
        if(key == null) {
            throw new InvalidKeyException("Clave nula");
        }
    }

    private void checkEntry(Entry<K,V> e) throws InvalidEntryException{
        if(e == null){
            throw new InvalidEntryException("Entrada nula.");
        }
    }
}