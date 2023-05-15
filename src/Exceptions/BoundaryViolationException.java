package Exceptions;

@SuppressWarnings("serial")
public class BoundaryViolationException extends Exception {
	/**
	 * crea la excepcion con un mensaje asociado.
	 * @param msj mensaje asociado.
	 */
	public BoundaryViolationException(String msj) {
		super(msj);
	}
}
