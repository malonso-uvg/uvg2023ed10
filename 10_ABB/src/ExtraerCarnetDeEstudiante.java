/**
 * 
 */

/**
 * @author MAAG
 *
 */
public class ExtraerCarnetDeEstudiante<Integer, V> implements IGetKey<Integer, V> {

	@Override
	public Integer getKeyFromValue(V value) {
		return (Integer) ((Estudiante)value).getCarnet();
	}

}
