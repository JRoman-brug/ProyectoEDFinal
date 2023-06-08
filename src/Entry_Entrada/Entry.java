package Entry_Entrada;

public interface Entry<K,V> {
	/**
	 * @return la clave.
	 */
    public K getKey();
    /**
	 * @return el valor.
	 */
    public V getValue();
}
