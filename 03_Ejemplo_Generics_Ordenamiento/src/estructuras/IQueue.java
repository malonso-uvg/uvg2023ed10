/**
 * 
 */
package estructuras;

/**
 * @author moise
 *
 */
public interface IQueue<E> {
	
	   public void enqueue(E value);
	   // post: the value is added to the tail of the structure

	   public E dequeue();
	   // pre: the queue is not empty
	   // post: the head of the queue is removed and returned
	   
	   public E peek();
	   // pre: the queue is not empty
	   // post: the element at the head of the queue is returned
	   
	   public boolean empty();
	   // post: returns true if and only if the queue is empty
	   
	   public int size();
	   // post: returns the number of elements in the queue
}
