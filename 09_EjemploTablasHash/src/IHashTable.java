import java.util.ArrayList;

/**
 * 
 */

/**
 * @author MAAG
 *
 */
public interface IHashTable<V> {

	void setHashFunction(IHashFunction<V> hashFunction);
	
	void insert(V value);
	
	int find(V value);
	
	ArrayList<V> get(int key);
	
	boolean isEmpty();
	
	int count();
	
	int size();
}
