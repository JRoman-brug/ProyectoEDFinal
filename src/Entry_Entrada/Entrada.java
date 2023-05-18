package Entry_Entrada;

public class Entrada<K,V> implements Entry<K,V> {
    protected K key;
    protected V value;
    /**
	 * Crea una entrada
	 * @param clave Es la clave de la entrada.
	 * @param valor	Es el valor de la entrada.
	 */
    public Entrada(K key, V value) {
        this.key = key;
        this.value = value;
    }
    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }
    /**
	 * setea la clave del mapeo.
	 * @param clave Clave a setear.
	 */
    public void setKey(K key) {
        this.key = key;
    }
    /**
	 * setea el valor del mapeo.
	 * @param valor Valor a setear
	 */
    public void setValue(V value) {
        this.value = value;
    }
    /**
	 * devuelve un string con los atributos del mapeo.
	 */
    public String toString() {
        return "{"+getKey()+","+getValue()+"}";
    }
}
