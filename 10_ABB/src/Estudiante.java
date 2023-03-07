/**
 * 
 */

/**
 * @author MAAG
 *
 */
public class Estudiante {
	private Integer carnet;
	private String nombre;
	
	public Estudiante(Integer carnet, String nombre) {
		setCarnet(carnet);
		setNombre(nombre);
	}
	
	/**
	 * @return the carnet
	 */
	public Integer getCarnet() {
		return carnet;
		
	}
	/**
	 * @param carnet the carnet to set
	 */
	public void setCarnet(Integer carnet) {
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
	
	
}
