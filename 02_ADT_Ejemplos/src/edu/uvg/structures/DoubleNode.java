/**
 * 
 */
package edu.uvg.structures;

/**
 * @author MAAG
 *
 */
public class DoubleNode<T> {
	private T value;
	private DoubleNode<T> next;
	private DoubleNode<T> previous;
	
	public DoubleNode(T value) {
		this.setValue(value);
	}
	
	/**
	 * @return the value
	 */
	public T getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(T value) {
		this.value = value;
	}
	/**
	 * @return the next
	 */
	public DoubleNode<T> getNext() {
		return next;
	}
	/**
	 * @param next the next to set
	 */
	public void setNext(DoubleNode<T> next) {
		this.next = next;
	}
	/**
	 * @return the previous
	 */
	public DoubleNode<T> getPrevious() {
		return previous;
	}
	/**
	 * @param previous the previous to set
	 */
	public void setPrevious(DoubleNode<T> previous) {
		this.previous = previous;
	}
	
	
}
