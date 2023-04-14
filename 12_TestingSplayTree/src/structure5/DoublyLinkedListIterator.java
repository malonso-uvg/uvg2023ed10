package structure5;
/**
 * An iterator for traversing the elements of a doubly linked list.
 * The iterator traverses the list beginning at the head, and heads toward
 * tail.
 *
 * Typical use:
 * <pre>
 *      List l = new DoublyLinkedList();
 *      // ...list gets built up...
 *      Iterator li = l.iterator();
 *      while (li.hasNext())
 *      {
 *          System.out.println(li.get());
 *          li.next();
 *      }
 *      li.reset();
 *      while (li.hasNext())
 *      { .... }
 * </pre>
 *
 * @version $Id: DoublyLinkedListIterator.java 31 2007-08-06 17:19:56Z bailey $
 * @author, 2001 duane a. bailey
 */
public class DoublyLinkedListIterator<E> extends AbstractIterator<E>
{
    /**
     * Reference to head of the list.
     */
    protected DoublyLinkedNode<E> head;

    /**
     * Sign of the end of the list.
     */
    protected DoublyLinkedNode<E> tail;

    /**
     * Reference to the current node in the list.
     */
    protected DoublyLinkedNode<E> current;

    /**
     * Construct an iterator over a doubly linked list hanging from head.
     *
     * @post constructs an iterator rooted at list head, h
     * 
     * @param h The head of the list to be traversed.
     */
    public DoublyLinkedListIterator(DoublyLinkedNode<E> h)
    {
        head = h;
        tail = null;
        reset();
    }


    public DoublyLinkedListIterator(DoublyLinkedNode<E> headDummy,      
                                    DoublyLinkedNode<E> tailDummy)
    {
        head = headDummy.next();
        tail = tailDummy;
        reset();
    }

    /**
     * Reset the iterator to the head of the list.
     *
     * @post resets iterator to list head
     */
    public void reset()
    {
        current = head;
    }

    /**
     * Determine if there are more elements to be considered.
     *
     * @post returns true iff current element is valid
     * 
     * @return True iff there are more elements to be considered.
     */
    public boolean hasNext()
    {
        return current != tail;
    }

    /**
     * Returns reference to the current element, then increments iterator.
     *
     * @post returns current element and increments iterator
     * 
     * @return Reference to element that was current before increment.
     */
    public E next()
    {
        E result = current.value();
        current = current.next();
        return result;
    }

    /**
     * Get reference to value that is current.
     *
     * @pre hasNext
     * @post returns current element
     * 
     * @return A reference to the value that is current.
     */
    public E get()
    {
        return current.value();
    }
}
