/**
 * 
 */
package main;

import common.IComparator;

/**
 * @author MAAG
 *
 */
public class CompareStringsAsc<T> implements IComparator<T> {

	@Override
	public int Compare(T _object1, T _object2) {
		
		String string1 = (String)_object1;
		String string2 = (String)_object2;
		
		return string1.compareTo(string2);
	}

}
