/**
 * 
 */
package gui;

/**
 * @author MAAG
 *
 */
public class Estudiante implements Comparable{

	
	private int carnet;
	private String nombre;
	private double nota;
	
	public Estudiante(int carnet, String nombre, double nota) {
		setCarnet(carnet);
		setNombre(nombre);
		setNota(nota);
	}
	/**
	 * @return the carnet
	 */
	public int getCarnet() {
		return carnet;
	}
	/**
	 * @param carnet the carnet to set
	 */
	public void setCarnet(int carnet) {
		this.carnet = carnet;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the nota
	 */
	public double getNota() {
		return nota;
	}
	/**
	 * @param nota the nota to set
	 */
	public void setNota(double nota) {
		this.nota = nota;
	}
	@Override
	public String toString() {
		
		return "Estudiante " + getNombre() + " Carnet: " + getCarnet() + " Nota: " + getNota();
	}
	
	@Override
	public int compareTo(Object o) {
		Estudiante otroEstudiante = (Estudiante)o;
		
		if (this.getNota() < otroEstudiante.getNota()) {
			return 1;
		} else if (this.getNota() > otroEstudiante.getNota()){
			return -1;
		} else {
			int result = this.getNombre().compareTo(otroEstudiante.getNombre());
			
			if (result == 0) {
				if (this.getCarnet() < otroEstudiante.getCarnet()) {
					return 1;
				} else if (this.getCarnet() > otroEstudiante.getCarnet()){
					return -1;
				} else {
					return 0;
				}
			}
			
			return result;
		}
	}
	
	
}
