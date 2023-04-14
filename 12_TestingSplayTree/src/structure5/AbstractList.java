package structure5;
import java.util.Iterator;
/**
 * An abstract structure implementing features common to all
 * list-like structures in this package.
 * <p>       
 * Lists are typically used to store data of unknown or varying length.
 * The structure package provides several extensions of the AbstractList class, 
 * each of which has its particular strengths and weaknesses.
 * <p>
 * Example usage:
 *
 * To place a copy of every unique parameter passed to a program into a 
 * List, we could use the following:
 * <pre>
 * public static void main({@link java.lang.String String[]} arguments)
 * {
 *     {@link AbstractList} argList = new {@link SinglyLinkedList#SinglyLinkedList() SinglyLinkedList()};
 *     for (int i = 0; i < arguments.length; i++){
 *         if (!argList.{@link #contains(Object) contains(arguments[i])}){
 *             argList.{@link #add(Object) add(arguments[i])};
 *         }
 *    }
 *    System.out.println(argList);
 * }
 * </pre>
 * @version $Id: AbstractList.java 22 2006-08-21 19:27:26Z bailey $
 * @author, 2001 duane a. bailey
 * @see DoublyLinkedList
 * @see CircularList
 * @see SinglyLinkedList
 */
public abstract class AbstractList<E>
    extends AbstractStructure<E> implements List<E>
{
    /**
     * Default constructor for AbstractLists
     * @post does nothing
     */
    public AbstractList()
    {
    }

    /**
     * Determine if list is empty.
     *
     * @post returns true iff list has no elements
     * 
     * @return True if list has no elements.
     */
    public boolean isEmpty()
    {
        return size() == 0;
    }

    /**
     * Add a value to head of list.
     *
     * @post value is added to beginning of list
     * 
     * @param value The value to be added to head of list.
     */
    public void addFirst(E value)
    {
        add(0,value);
    }

    /**
     * Add a value to tail of list.
     *
     * @post value is added to end of list
     * 
     * @param value The value to be added to tail of list.
     */
    public void addLast(E value)
    {
        add(size(),value);
    }

    /**
     * Fetch first element of list.
     *
     * @pre list is not empty
     * @post returns first value in list
     * 
     * @return A reference to first element of list.
     */
    public E getFirst()
    {
        return get(0);
    }

    /**
     * Fetch last element of list.
     *
     * @pre list is not empty
     * @post returns last value in list
     * 
     * @return A reference to last element of list.
     */
    public E getLast()
    {
        return get(size()-1);
    }

    /**
     * Remove a value from first element of list.
     *
     * @pre list is not empty
     * @post removes first value from list
     * 
     * @return value actually removed.
     */
    public E removeFirst()
    {
        return remove(0);
    }

    /**
     * Remove last value from list.
     *
     * @pre list is not empty
     * @post removes last value from list
     * 
     * @return value actually removed.
     */
    public E removeLast()
    {
        return remove(size()-1);
    }

    /**
     * Add an object to tail of list.
     *
     * @post value is added to tail of list
     * 
     * @param value The value to be added to tail of list.
     * @see #addLast
     */
    public void add(E value)
    {
        addLast(value);
    }

    /**
     * Removes value from tail of list.
     *
     * @pre list has at least one element
     * @post removes last value found in list
     * @return object removed.
     */
    public E remove()
    {
        return removeLast();
    }

    /**
     * Retrieves value from tail of list.
     *
     * @pre list has at least one element
     * @post returns last value found in list
     * @return object found at end of list
     */
    public E get()
    {
        return getLast();
    }

    /**
     * Check to see if a value is in list.
     *
     * @pre value is not null
     * @post returns true iff list contains an object equal to value
     * 
     * @param value value sought.
     * @return True if value is within list.
     */
    public boolean contains(E value)
    {
        return -1 != indexOf(value);
    }
}

