/**
 * 
 */
package edu.uvg.ht9;

import java.util.Comparator;

/**
 * @author MAAG
 *
 */
public class ComparadorPalabras<T> implements Comparator<T> {

	@Override
	public int compare(T o1, T o2) {
		Palabra pal1 = (Palabra)o1;
		Palabra pal2 = (Palabra)o2;
		return pal1.getEnglish().compareTo(pal2.getEnglish());
	}

}
