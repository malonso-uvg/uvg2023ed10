/**
 * 
 */
package edu.uvg.structures;

import java.util.Comparator;

/**
 * @author MAAG
 *
 */
public class IntegerComparator<K> implements Comparator<K>{

	@Override
	public int compare(K o1, K o2) {
		int oi1 = Integer.parseInt(o1.toString());
		int oi2 = Integer.parseInt(o2.toString());
		if (oi1 > oi2) {
			return 1;
		} else if (oi1 < oi2) {
			return -1;
		} else {
			return 0;
		}
	}

	
}
