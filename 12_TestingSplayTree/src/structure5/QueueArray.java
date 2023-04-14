// An implementation of queues, based on arrays.
// (c) 1998, 2001 duane a. bailey

package structure5;
import java.util.Iterator;

/**
 * An implementation of queues based on arrays.
 * The head of the queue starts out at the head of the array, allowing the queue to 
 * grow and shrink in constant time. 
 * This queue implementation is ideal for 
 * applications that require a queue with a known maximum size that expands 
 * in constant time.
 * <P>
 * Example usage:
 * <P>
 * To compute the sum of the unicode value of every character in the standard input
 * we could use the following:
 * <P>
 * <pre>
 * public static void main(String[] arguments)
 * {
 *     int charsInInput = QueueExample.countChars(argument);
 *     {@link QueueArray} q = new {@link #QueueArray(int) QueueArray(charsInInput)};
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
 * @see QueueList
 * @see QueueVector
 * @version $Id: QueueArray.java 35 2007-08-09 20:38:38Z bailey $
 * @author, 2001 duane a. bailey
 */
public class QueueArray<E> extends AbstractQueue<E> implements Queue<E>
{
    /**
     * The references to values stored within the queue.
     */
    protected Object data[]; // an array of the data
    /**
     * index of the head of queue.
     */
    protected int head; // next dequeue-able value
    /**
     * current size of queue
     */
    protected int count; // current size of queue

    /**
     * Construct a queue holding at most size elements.
     *
     * @post create a queue capable of holding at most size values
     * 
     * @param size The maximum size of the queue.
     */
    public QueueArray(int size)
    {
        data = new Object[size];
        head = 0;
        count = 0;
    }

    /**
     * Add a value to the tail of the queue.
     *
     * @pre the queue is not full
     * @post the value is added to the tail of the structure
     * 
     * @param value The value added.
     * @see #enqueue
     */
    public void add(E value)
    {
        Assert.pre(!isFull(),"Queue is not full.");
        int tail = (head + count) % data.length;
        data[tail] = value;
        count++;
    }

    /**
     * Remove a value from the head of the queue.
     *
     * @pre the queue is not empty
     * @post the head of the queue is removed and returned
     * 
     * @return The value actually removed.
     * @see #dequeue
     */
    @SuppressWarnings("unchecked")
    public E remove()
    {
        Assert.pre(!isEmpty(),"The queue is not empty.");
        E value = (E)data[head];
        head = (head + 1) % data.length;
        count--;
        return value;
    }

    /**
     * Fetch the value at the head of the queue.
     *
     * @pre the queue is not empty
     * @post the element at the head of the queue is returned
     * 
     * @return Reference to the first value of the queue.
     */
    @SuppressWarnings("unchecked")
    public E get()
    {
        Assert.pre(!isEmpty(),"The queue is not empty.");
        return (E)data[head];
    }

    /**
     * Determine the number of elements within the queue
     *
     * @post returns the number of elements in the queue
     * 
     * @return The number of elements within the queue.
     */
    public int size()
    {
        return count;
    }

    /**
     * Remove all the values from the queue.
     *
     * @post removes all elements from the queue
     */
    public void clear()
    {
        // we could remove all the elements from the queue
        count = 0;
        head = 0;
    }
    
    /**
     * Determines if the queue is not able to accept any new values.
     *
     * @post returns true if the queue is at its capacity
     * 
     * @return True iff the queue is full.
     */
    public boolean isFull()
    {
        return count == data.length;
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
        return count == 0;
    }

    public Iterator<E> iterator()
    {
        return new ArrayIterator<E>(data,head,count);
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
        int i,l;

        s.append("<QueueArray:");
        for (i = 0, l = head; i < count; i++, l = (l+1)%data.length)
        {
            s.append(" "+data[l]);
        }
        s.append(">");
        return s.toString();
    }
}
