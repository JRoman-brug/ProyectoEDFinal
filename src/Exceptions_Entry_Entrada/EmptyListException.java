package Exceptions_Entry_Entrada;

public class EmptyListException extends Exception {
	/**
	 * crea la excepcion con un mensaje asociado.
	 * @param msj mensaje asociado.
	 */
	public EmptyListException(String msj) {
		super(msj);
	}
}
