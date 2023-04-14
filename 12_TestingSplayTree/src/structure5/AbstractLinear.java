// An abstract implementation of LIFO/FIFO structures.
// (c) 1998,2001 duane a. bailey

package structure5;
/**
 * An abstract implemtation of linear data structures. Linear structures have 
 * completely determined  add and remove methods.  
 * Linear structures are often used to store the the state of a recursively
 * solved problem and stacks and queues are classic examples of such structures.
 *
 * @version $Id: AbstractLinear.java 22 2006-08-21 19:27:26Z bailey $
 * @author, 2001 duane a. bailey
 * @see structure.Stack
 * @see structure.Queue
 */
abstract public class AbstractLinear<E> extends AbstractStructure<E>
    implements Linear<E>
{
    /**
     * Determine if there are elements within the linear.
     *
     * @post return true iff the linear structure is empty
     * @return true if the linear structure is empty; false otherwise
     */
    public boolean empty()
    {
        return isEmpty();
    }

    /**
     * Removes value from the linear structure.
     * Not implemented (by default) for linear classes.
     *
     * @param value value matching the value to be removed
     * @pre value is non-null
     * @post value is removed from linear structure, if it was there
     * @return returns the value that was replaced, or null if none.
     */
    public E remove(E o)
    {
        Assert.fail("Method not implemented.");
        // never reaches this statement:
        return null;
    }
}
