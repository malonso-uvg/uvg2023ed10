// An implementation of priority queues that makes use of ordering vectors.
// (c) 1998, 2001 duane a. bailey
package structure5;

/**
 * A vector-based implementation of a priority queue.  Similar to
 * an ordered vector, except that only the smallest value may be
 * accessed in this structure.
 * <P>
 * Example usage:
 * <P>
 * To print out a list of programmers sorted by age we could use the following:
 * <pre>
 * public static void main(String[] argv){
 *      //initialize a new fib heap
 *      PriorityVector programmers = new {@link #PriorityVector()};
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
 * @see structure.OrderedVector
 * @version $Id: PriorityVector.java 22 2006-08-21 19:27:26Z bailey $
 * @author, 2001 duane a. bailey
 */
public class PriorityVector<E extends Comparable<E>> implements PriorityQueue<E>
{
    /**
     * The vector of data that is maintained in increasing order.
     */
    protected Vector<E> data;

    /**
     * Construct an empty priority queue.
     *
     * @post constructs a new priority queue
     */
    public PriorityVector()
    {
        data = new Vector<E>();
    }

    /**
     * Fetch the smallest value of the priority queue.
     *
     * @pre !isEmpty()
     * @post returns the minimum value in the priority queue
     * 
     * @return The smallest value of the structure.
     */
    public E getFirst()
    {
        return data.get(0);
    }

    /**
     * Remove the smallest value of the structure.
     *
     * @pre !isEmpty()
     * @post removes and returns minimum value in priority queue
     * 
     * @return The smallest value of the structure.
     */
    public E remove()
    {
        return data.remove(0);
    }
    /**
     * Add a comparable value to the priority queue.
     *
     * @pre value is non-null
     * @post inserts value in priority queue
     *       leaves elements in order
     * 
     * @param value The comparable value to be added.
     */
    public void add(E value)
    {
        int position = indexOf(value);
        data.add(position,value);
    }

    protected int indexOf(E target)
    {
        E midValue;
        int low = 0;  // lowest possible location
        int high = data.size(); // highest possible location
        int mid = (low + high)/2; // low <= mid <= high
        // mid == high iff low == high
        while (low < high) {
            Assert.condition(mid < high,"Middle element exists.");
            midValue = data.get(mid);
            if (midValue.compareTo(target) < 0) {
                low = mid+1;
            } else {
                high = mid;
            }
            mid = (low+high)/2;
        }
        return low;
    }

    /**
     * Determine if the priority queue is empty.
     *
     * @post returns true iff the priority queue is empty
     * 
     * @return True iff there are no elements in the priority queue.
     */
    public boolean isEmpty()
    {
        return data.size() == 0;
    }

    /**
     * Determine the size of the priority queue.
     *
     * @post returns number of elements in priority queue
     * 
     * @return The number of elements in the priority queue.
     */
    public int size()
    {
        return data.size();
    }

    /**
     * Remove all the values from the priority queue.
     *
     * @post removes all elements from priority queue
     */
    public void clear()
    {
        data.clear();
    }

    /**
     * Construct a string representation of the priority vector.
     *
     * @post returns string representation of priority vector
     * 
     * @return String describing priority vector.
     */
    public String toString()
    {
        return "<PriorityVector: "+data+">";
    }
}
