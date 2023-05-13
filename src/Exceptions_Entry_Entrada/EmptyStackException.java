package Exceptions_Entry_Entrada;

public class EmptyStackException extends Exception {
	/**
	 * crea la excepcion con un mensaje asociado.
	 * @param msj mensaje asociado.
	 */
	public EmptyStackException(String msj) {
		super(msj);
	}
}
