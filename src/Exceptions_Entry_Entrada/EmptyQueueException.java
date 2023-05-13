package Exceptions_Entry_Entrada;

public class EmptyQueueException extends Exception {
	/**
	 * crea la excepcion con un mensaje asociado.
	 * @param msj mensaje asociado.
	 */
	public EmptyQueueException(String msj) {
		super(msj);
	}
}
