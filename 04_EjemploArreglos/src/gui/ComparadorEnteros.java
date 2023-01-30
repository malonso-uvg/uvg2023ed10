/**
 * 
 */
package gui;

import java.util.Comparator;

/**
 * @author MAAG
 *
 */
public class ComparadorEnteros<T> implements Comparator<T> {

	@Override
	public int compare(T o1, T o2) {
		//return o1 - o2;
		int numero1 = Integer.parseInt(o1.toString());
		int numero2 = Integer.parseInt(o2.toString());
		if (numero1 > numero2) {
			return 1;
		} else if (numero1 < numero2) {
			return -1;
		} else {
			return 0;
		}
	}

}
