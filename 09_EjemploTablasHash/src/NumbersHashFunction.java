/**
 * 
 */

/**
 * @author MAAG
 *
 */
public class NumbersHashFunction<Integer> implements IHashFunction<Integer> {

	private int tableSize = 0;
	
	public NumbersHashFunction(int _tableSize) {
		tableSize = _tableSize;
	}
	
	@Override
	public int getKey(Integer value) {
		return (int)value % tableSize;
	}

}
