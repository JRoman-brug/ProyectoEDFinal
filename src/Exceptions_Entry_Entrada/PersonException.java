package Exceptions_Entry_Entrada;

public class PersonException extends Exception {
	/**
	 * crea la excepcion con un mensaje asociado.
	 * @param msj mensaje asociado.
	 */
	public PersonException(String msj) {
		super(msj);
	}
}
