// Interface for lists.
// (c) 1998, 2001 duane a. bailey

package structure5;
import java.util.Iterator;
/**
 * Interface describing lists.  Lists are collections of data with
 * a head and tail.  Values may be added or removed from either end,
 * as well as by value from the middle.
 * The structure package provides several implementations of the List interface, 
 * each of which has its particular strengths and weaknesses.
 *  
 * <P>
 * Example usage:
 * <P>
 * To place a copy of every unique parameter passed to a program into a 
 * List, we could use the following:
 * <pre>
 * public static void main({@link java.lang.String String[]} arguments)
 * {
 *     {@link List} argList = new {@link SinglyLinkedList#SinglyLinkedList() SinglyLinkedList()};
 *     for (int i = 0; i < arguments.length; i++){
 *         if (!argList.{@link #contains(Object) contains(arguments[i])}){
 *             argList.{@link #add(Object) add(arguments[i])};
 *         }
 *    }
 *    System.out.println(argList);
 * }
 * </pre>
 * @version $Id: List.java 22 2006-08-21 19:27:26Z bailey $
 * @author, 2001 duane a. bailey
 * @see SinglyLinkedList
 * @see DoublyLinkedList
 * @see CircularList
 */
public interface List<E> extends Structure<E>
{
    /**
     * Determine size of list.
     *
     * @post returns number of elements in list
     * 
     * @return The number of elements in list.
     */
    public int size();

    /**
     * Determine if list is empty.
     *
     * @post returns true iff list has no elements
     * 
     * @return True if list has no elements.
     */
    public boolean isEmpty();

    /**
     * Remove all elements of list.
     *
     * @post empties list
     */
    public void clear();

    /**
     * Add a value to the head of the list.
     *
     * @post value is added to beginning of list
     * 
     * @param value The value to be added to the head of the list.
     */
    public void addFirst(E value);

    /**
     * Add a value to tail of list.
     *
     * @post value is added to end of list
     * 
     * @param value The value to be added to tail of list.
     */
    public void addLast(E value);

    /**
     * Fetch first element of list.
     *
     * @pre list is not empty
     * @post returns first value in list
     * 
     * @return A reference to first element of list.
     */
    public E getFirst();

    /**
     * Fetch last element of list.
     *
     * @pre list is not empty
     * @post returns last value in list
     * 
     * @return A reference to last element of list.
     */
    public E getLast();

    /**
     * Remove a value from first element of list.
     *
     * @pre list is not empty
     * @post removes first value from list
     * 
     * @return The value actually removed.
     */
    public E removeFirst();

    /**
     * Remove last value from list.
     *
     * @pre list is not empty
     * @post removes last value from list
     * 
     * @return The value actually removed.
     */
    public E removeLast();

    /**
     * Remove a value from list.  At most one of value
     * will be removed.
     *
     * @post removes and returns element equal to value
     *       otherwise returns null
     * 
     * @param value The value to be removed.
     * @return The actual value removed.
     */
    public E remove(E value);

    /**
     * Add an object to tail of list.
     *
     * @post value is added to tail of list
     * 
     * @param value The value to be added to tail of list.
     * @see #addLast
     */
    public void add(E value);

    /**
     * Removes value from tail of list.
     *
     * @pre list has at least one element
     * @post removes last value found in list
     * @return object removed.
     */
    public E remove();

    /**
     * Retrieves value from tail of list.
     *
     * @pre list has at least one element
     * @post returns last value found in list
     * @return object found at end of list
     */
    public E get();

    /**
     * Check to see if a value is in list.
     *
     * @pre value is not null
     * @post returns true iff list contains an object equal to value
     * 
     * @param value value sought.
     * @return True if value is within list.
     */
    public boolean contains(E value);

    /**
     * Determine first location of a value in list.
     *
     * @pre value is not null
     * @post returns (0-origin) index of value,
     *   or -1 if value is not found
     * 
     * @param value The value sought.
     * @return index (0 is first element) of value, or -1
     */
    public int indexOf(E value);

    /**
     * Determine last location of a value in list.
     *
     * @pre value is not null
     * @post returns (0-origin) index of value,
     *   or -1 if value is not found
     * 
     * @param value value sought.
     * @return index (0 is first element) of value, or -1
     */
    public int lastIndexOf(E value);

    /**
     * Get value at location i.
     *
     * @pre 0 <= i < size()
     * @post returns object found at that location
     *
     * @param i position of value to be retrieved.
     * @return value retrieved from location i (returns null if i invalid)
     */
    public E get(int i);

    /**
     * Set value stored at location i to object o, returning old value.
     *
     * @pre 0 <= i < size()
     * @post sets ith entry of list to value o;
     *    returns old value
     * @param i location of entry to be changed.
     * @param o new value
     * @return former value of ith entry of list.
     */
    public E set(int i, E o);

    /**
     * Insert value at location.
     *
     * @pre 0 <= i <= size()
     * @post adds ith entry of list to value o
     * @param i index of this new value
     * @param o value to be stored
     */
    public void add(int i, E o);

    /**
     * Remove and return value at location i.
     *
     * @pre 0 <= i < size()
     * @post removes and returns object found at that location
     *
     * @param i position of value to be retrieved.
     * @return value retrieved from location i (returns null if i invalid)
     */
    public E remove(int i);

    /**
     * Construct an iterator to traverse elements of list
     * from head to tail, in order.
     *
     * @post returns an iterator allowing 
     *   ordered traversal of elements in list
     * 
     * @return Iterator that traverses list.
     */
    public Iterator<E> iterator();
}

