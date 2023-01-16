/**
 * 
 */
package edu.uvg.structures;

/**
 * @author MAAG
 *
 */
public interface IPriorityQueue<K, V> {
	void insert(K priority, V value);
	
	V get();
	
	V remove();
	
	int count();
	
	boolean isEmpty();
}
