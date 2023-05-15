package Exceptions;

public class InvalidKeyException extends Exception {
	/**
	 * crea la excepcion con un mensaje asociado.
	 * @param msj mensaje asociado.
	 */
	public InvalidKeyException(String msj) {
		super(msj);
	}
}
