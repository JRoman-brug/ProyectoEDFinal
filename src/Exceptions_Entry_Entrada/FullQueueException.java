package Exceptions_Entry_Entrada;

public class FullQueueException extends Exception {
	/**
	 * crea la excepcion con un mensaje asociado.
	 * @param msj mensaje asociado.
	 */
	public FullQueueException(String msj) {
		super(msj);
	}
}
