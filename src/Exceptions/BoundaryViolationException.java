package Exceptions;

@SuppressWarnings("serial")
public class BoundaryViolationException extends Exception {
	/**
	 * Crea la excepcion con un mensaje asociado.
	 * @param msj mensaje asociado.
	 */
	public BoundaryViolationException(String msj) {
		super(msj);
	}
}
