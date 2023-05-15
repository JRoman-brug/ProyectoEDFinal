package Exceptions;

public class InvalidPositionException extends Exception {
	/**
	 * crea la excepcion con un mensaje asociado.
	 * @param msj mensaje asociado.
	 */
	public InvalidPositionException(String msj) {
		super(msj);
	}
}
