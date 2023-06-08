package Exceptions;

public class EmptyPriorityQueueException extends Exception {
	/**
	 * Crea la excepcion con un mensaje asociado.
	 * @param msj mensaje asociado.
	 */
	public EmptyPriorityQueueException(String msj) {
		super(msj);
	}
}
