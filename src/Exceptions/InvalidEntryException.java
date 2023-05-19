package Exceptions;

public class InvalidEntryException extends Exception{
	/**
	 * crea la excepcion con un mensaje asociado.
	 * @param msj mensaje asociado.
	 */
    public InvalidEntryException(String msg) {
        super(msg);
    }
}
