// An interface for an ordered structure that allows you to remove min elts
// (c) 1998, 2001 duane a. bailey
package structure5;

// ideally this would extend linear, but there are problems....
/**
 * Interface describing an queue of prioritized values.
 * This linear-like structure has values that
 * are inserted in such a way as to allow them to be removed in
 * increasing order.
 * <P>
 * Example usage:
 * <P>
 * To print out a list of programmers sorted by age we could use the following:
 * <pre>
 * public static void main(String[] argv){
 *      //initialize a new fib heap
 *      {@link PriorityQueue} programmers = new {@link structure.FibHeap#FibHeap() FibHeap()};
 *
 *      //add programmers and their ages to heap
 *      //ages current of 7/22/2002
 *        programmers.{@link #add(Comparable) add(new ComparableAssociation(new Integer(22), "Evan"))};
 *      programmers.add(new ComparableAssociation(new Integer(19), "Chris"));
 *      programmers.add(new ComparableAssociation(new Integer(20), "Shimon"));
 *      programmers.add(new ComparableAssociation(new Integer(21), "Diane"));
 *      programmers.add(new ComparableAssociation(new Integer(21), "Lida"));    
 *      programmers.add(new ComparableAssociation(new Integer(20), "Rob"));     
 *      programmers.add(new ComparableAssociation(new Integer(20), "Sean"));    
 *
 *      //print out programmers 
 *      while(!programmers.{@link #isEmpty()}){
 *          ComparableAssociation p = (ComparableAssociation)programmers.{@link #remove()};
 *          System.out.println(p.getValue() + " is " + p.getKey() + " years old.");
 *      }
 * }
 * </pre>
 *
 * @version $Id: PriorityQueue.java 22 2006-08-21 19:27:26Z bailey $
 * @author, 2001 duane a. bailey
 */
public interface PriorityQueue<E extends Comparable<E>>
{
    /**
     * Fetch lowest valued (highest priority) item from queue.
     *
     * @pre !isEmpty()
     * @post returns the minimum value in priority queue
     * 
     * @return The smallest value from queue.
     */
    public E getFirst();

    /**
     * Returns the minimum value from the queue.
     *
     * @pre !isEmpty()
     * @post returns and removes minimum value from queue
     * 
     * @return The minimum value in the queue.
     */
    public E remove();

    /**
     * Add a value to the priority queue.
     *
     * @pre value is non-null comparable
     * @post value is added to priority queue
     * 
     * @param value The value to be added.
     */
    public void add(E value);

    /**
     * Determine if the queue is empty.
     *
     * @post returns true iff no elements are in queue
     * 
     * @return True if the queue is empty.
     */
    public boolean isEmpty();

    /**
     * Determine the size of the queue.
     *
     * @post returns number of elements within queue
     * 
     * @return The number of elements within the queue.
     */
    public int size();

    /**
     * Remove all the elements from the queue.
     *
     * @post removes all elements from queue
     */
    public void clear();
}
