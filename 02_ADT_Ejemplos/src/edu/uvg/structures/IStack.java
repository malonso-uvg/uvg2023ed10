/**
 * 
 */
package edu.uvg.structures;

/**
 * @author MAAG
 *
 */
public interface IStack<T> {

	int count();
	
	boolean isEmpty();
	
	void push(T value);
	
	T pull();
	
	T peek();
}
