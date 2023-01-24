/**
 * 
 */
package main;

import common.IComparator;

/**
 * @author moise
 *
 */
public class CompareByYearDesc implements IComparator{

	@Override
	public int Compare(Object _object1, Object _object2) {
		Automovil auto1 = (Automovil)_object1;
		Automovil auto2 = (Automovil)_object2;
		
		if (auto1.getAnio_Fabricacion() > auto2.getAnio_Fabricacion())
			return -1;
		else if (auto1.getAnio_Fabricacion() < auto2.getAnio_Fabricacion())
			return 1;
		else
			return 0;
	}

}
