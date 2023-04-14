// Interface for queues.
// (c) 1998,2001 duane a. bailey
package structure5;
/**
 * Interface describing a first-in, first-out structure.  
 * Values are added at the tail, and removed from the head.  
 * Queues are typically used to process values in the order that they appear 
 * and to store the state of a buffered object.
 * The structure package provides several implementations of the Queue interface, 
 * each of which has its particular strengths and weaknesses.
 * <P>
 * Example usage:
 * <P>
 * To compute the sum of the unicode value of every character in the standard input
 * we could use the following:
 * <P>
 * <pre>
 * public static void main(String[] arguments)
 * {
 *     {@link Queue} q = new {@link QueueList#QueueList() QueueList()};
 *     int unicodeSum = 0;
 *
 *     if(arguments.length > 0){
 *         for(int i=0; i < arguments.length; i++){
 *             for(int j=0; j < arguments[i].length(); j++){
 *                 q.{@link #enqueue(Object) enqueue(new Character(arguments[i].charAt(j)))};
 *             }
 *         }
 *     }
 *
 *     while(!q.{@link #empty()}){
 *        char c = ((Character)q.{@link #dequeue()}).charValue();
 *        unicodeSum+=Character.getNumericValue(c);
 *     }
 *
 *     System.out.println("Total Value: " + unicodeSum);
 * }
 * </pre>
 * @see structure.Stack
 * @see AbstractQueue
 * @see QueueArray
 * @see QueueVector
 * @see QueueList
 * @version $Id: Queue.java 22 2006-08-21 19:27:26Z bailey $
 * @author, 2001 duane a. bailey
 */
public interface Queue<E> extends Linear<E>
{
    /**
     * Add a value to the tail of the queue.
     *
     * @post the value is added to the tail of the structure
     * 
     * @param value The value added.
     * @see #enqueue
     */
    public void add(E value);

    /**
     * Add a value to the tail of the queue.
     *
     * @post the value is added to the tail of the structure
     * 
     * @param value The value to be added.
     */
    public void enqueue(E value);

    /**
     * Remove a value form the head of the queue.
     *
     * @pre the queue is not empty
     * @post the head of the queue is removed and returned
     * 
     * @return The value actually removed.
     * @see #dequeue
     */
    public E remove();

    /**
     * Remove a value from the head of the queue.
     *
     * @pre the queue is not empty
     * @post the head of the queue is removed and returned
     * 
     * @return The value removed from the queue.
     */
    public E dequeue();

    /**
     * Fetch the value at the head of the queue.
     *
     * @pre the queue is not empty
     * @post the element at the head of the queue is returned
     * 
     * @return Reference to the first value of the queue.
     */
    public E getFirst();

    /**
     * Fetch the value at the head of the queue.
     *
     * @pre the queue is not empty
     * @post the element at the head of the queue is returned
     * 
     * @return Reference to the first value of the queue.
     */
    public E get();

    /**
     * Fetch the value at the head of the queue.
     *
     * @pre the queue is not empty
     * @post the element at the head of the queue is returned
     * 
     * @return Reference to the first value of the queue.
     */
    public E peek();

    /**
     * Returns true iff the queue is empty.  Provided for
     * compatibility with java.util.Vector.empty.
     *
     * @post returns true if and only if the queue is empty
     * 
     * @return True iff the queue is empty.
     */
    public boolean empty();

    /**
     * Returns the number of elements in the queue.
     *
     * @post returns the number of elements in the queue
     * @return number of elements in queue.
     */
    public int size();
}
