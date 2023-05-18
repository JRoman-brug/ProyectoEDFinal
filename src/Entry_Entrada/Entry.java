package Entry_Entrada;

public interface Entry<K,V> {
	/**
	 * regunta cual es la clave de la entrada.
	 * @return La entrada.
	 */
    public K getKey();
    /**
	 * pregunta cual es el valor de la entrada. 
	 * @return El valor.
	 */
    public V getValue();
}
