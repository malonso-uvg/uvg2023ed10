/**
 * 
 */
package gui;

import java.util.Comparator;

/**
 * @author MAAG
 *
 */
public class ComparadorEstudiantes<T> implements Comparator<T> {

	@Override
	public int compare(T o1, T o2) {
		Estudiante estudiante1 = (Estudiante)o1;
		Estudiante estudiante2 = (Estudiante)o2;
		
		if (estudiante1.getNota() > estudiante2.getNota()) {
			return 1;
		} else if (estudiante1.getNota() < estudiante2.getNota()) {
			return -1;
		} else {
			int result = estudiante1.getNombre().compareTo(estudiante2.getNombre());
			
			if (result == 0) {
				if (estudiante1.getCarnet() > estudiante2.getCarnet()) {
					return 1;
				} else if (estudiante1.getCarnet() < estudiante2.getCarnet()){
					return -1;
				} else {
					return 0;
				}
			}
			
			return result;
		}
		
	}

}
