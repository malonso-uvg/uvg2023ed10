// An implementation of queues, based on vectors.
// (c) 1998, 2001 duane a. bailey
// updated for generic types Jim Teresco, October 2005

package structure5;
import java.util.Iterator;

/**
 * An implementation of queues based on vectors.
 * The head of the queue is stored at the head of the list, allowing the queue to 
 * grow and shrink in constant time. 
 * This queue implementation is ideal for applications that require a dynamically
 * resizable queue which occasionally takes a time proportional to the its
 * length to expand.
 * <P>
 * Example usage:
 * <P>
 * To compute the sum of the unicode value of every character in the standard input
 * we could use the following:
 * <P>
 * <pre>
 * public static void main(String[] arguments)
 * {
 *     {@link QueueVector} q = new {@link #QueueVector()};
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
 * @see QueueArray
 * @see QueueList
 * @version $Id: QueueVector.java 22 2006-08-21 19:27:26Z bailey $
 * @author, 2001 duane a. bailey
 */
public class QueueVector<E> extends AbstractQueue<E> implements Queue<E>
{
    /**
     * The vector that maintains the queue data
     */
    protected Vector<E> data;

    /**
     * Construct an empty queue
     *
     * @post constructs an empty queue
     */
    public QueueVector()
    {
        data = new Vector<E>();
    }

    /**
     * Constructs an empty queue with an initial allocation of size.
     *
     * @post constructs an empty queue of appropriate size
     * 
     * @param size Approximate largest queue size needed.
     */
    public QueueVector(int size)
    {
        data = new Vector<E>(size);
    }

    /**
     * Add a value to the tail of the queue
     *
     * @post the value is added to the tail of the structure
     * 
     * @param value The value added.
     */
    public void add(E value)
    {
        data.add(value);
    }
    

    /**
     * Remove a value from the head of the queue
     *
     * @pre the queue is not empty
     * @post the head of the queue is removed and returned
     * 
     * @return The value actually removed.
     * @see #dequeue
     */
    public E remove()
    {
        return data.remove(0);
    }

    /**
     * Fetch the value at the head of the queue.
     *
     * @pre the queue is not empty
     * @post the element at the head of the queue is returned
     * 
     * @return Reference to the first value of the queue.
     */
    public E get()
    {
        return data.get(0);
    }

    /**
     * Determine the number of elements within the queue.
     *
     * @post returns the number of elements in the queue
     * 
     * @return The number of elements within the queue.
     */
    public int size()
    {
        return data.size();
    }

    /**
     * Remove all the values from the queue.
     *
     * @post removes all elements from the queue
     */
    public void clear()
    {
        data.clear();
    }
    
    /**
     * Determine if the queue is empty.
     *
     * @post returns true iff the queue is empty
     * 
     * @return True iff the queue is empty.
     */
    public boolean isEmpty()
    {
        return data.isEmpty();
    }

    public Iterator<E> iterator()
    {
        return data.iterator();
    }

    /**
     * Construct a string representation of the queue.
     *
     * @post returns string representation of queue
     * 
     * @return String representing the queue.
     */
    public String toString()
    {
        StringBuffer s = new StringBuffer();
        int i;

        s.append("<QueueArray:");
        for (i = 0; i < data.size(); i++)
        {
            s.append(" "+data.get(i));
        }
        s.append(">");
        return s.toString();
    }
}
