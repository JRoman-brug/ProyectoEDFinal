package Program;

public class Par {
	private int lu;
	private int nota;
	
	/**
	 * Crea un Par e inicializa sus atributos con los pasados por parámetros.
	 * @param lu
	 * @param nota
	 */
	public Par(int lu, int nota) {
		this.lu=lu;
		this.nota=nota;
	}
	
	/**
	 * Establece el número de lu del alumno.
	 */
	public void setLu(int lu) {
		this.lu=lu;
	}
	
	/**
	 * Establece la nota del alumno.
	 * @param materia 
	 * @param nota de la materia
	 */
	public void setNota(int nota) {
		if(nota>=0 && nota<=10)this.nota=nota;
	}
	
	/**
	 * @return Retorna el lu del alumno.
	 */
	public int getLu() {
		return lu;
	}
	
	/**
	 * @return Retorna la nota del alumno.
	 */
	public int getNota() {
		return nota;
	}
}
