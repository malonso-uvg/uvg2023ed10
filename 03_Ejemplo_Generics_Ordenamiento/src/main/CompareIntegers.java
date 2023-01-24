/**
 * 
 */
package main;

import common.IComparator;

/**
 * @author MAAG
 *
 */
public class CompareIntegers<T> implements IComparator<T> {

	@Override
	public int Compare(T _object1, T _object2) {
		int valor1 = Integer.parseInt(_object1.toString());
		int valor2 = Integer.parseInt(_object2.toString());
		
		if (valor1 > valor2)
			return 1;
		else if (valor2 > valor1)
			return -1;
		else 
			return 0;
	}

}
