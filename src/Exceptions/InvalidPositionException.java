package Exceptions;

public class InvalidPositionException extends Exception {
	/**
	 * Crea la excepcion con un mensaje asociado.
	 * @param msj mensaje asociado.
	 */
	public InvalidPositionException(String msj) {
		super(msj);
	}
}
