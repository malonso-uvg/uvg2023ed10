import java.util.ArrayList;
import java.util.Vector;

/**
 * 
 */

/**
 * @author MAAG
 *
 */
public class HashTableWithVector<V> implements IHashTable<V>{

	private IHashFunction<V> myFunction;
	private Vector<ArrayList<V>> internalVector;
	private int size;
	
	public HashTableWithVector(IHashFunction<V> hashFunction, int size) {
		setHashFunction(hashFunction);
		this.size = size;
		internalVector = new Vector<ArrayList<V>>();
		
		for (int i = 0; i < size; i++) {
			internalVector.add(new ArrayList<V>());
		}
	}
	
	@Override
	public void setHashFunction(IHashFunction<V> hashFunction) {
		myFunction = hashFunction;
	}

	@Override
	public void insert(V value) {
		int key = myFunction.getKey(value);
		internalVector.get(key).add(value);
	}

	@Override
	public int find(V value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<V> get(int key) {
		return  internalVector.get(key);
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
