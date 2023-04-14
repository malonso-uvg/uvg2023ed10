// Implementation of an iterator for elements of a singly linked list.
// (c) 1998, 2001 duane a. bailey
package structure5;
/**
 * An iterator for traversing the elements of a singly linked list.
 * The iterator traverses the list beginning at the head, and heads toward
 * tail.
 * <P>
 * Typical use:
 * <P>
 * <pre>
 *      List l = new SinglyLinkedList();
 *      // ...list gets built up...
 *      AbstractIterator li = l.iterator();
 *      while (li.hasNext())
 *      {
 *          System.out.println(li.get());
 *          li.next();
 *      }
 *      li.reset();
 *      while (li.hasNext())
 *      { .... }
 * </pre>
 * @version $Id: SinglyLinkedListIterator.java 31 2007-08-06 17:19:56Z bailey $
 * @author, 2001 duane a. bailey
 */
class SinglyLinkedListIterator<E> extends AbstractIterator<E>
{
    /**
     * The reference to currently considered element within list.
     */
    protected Node<E> current;
    /**
     * The head of list.
     */
    protected Node<E> head;

    /**
     * Construct an iterator that traverses list beginning at t.
     *
     * @post returns an iterator that traverses a linked list
     * 
     * @param t The first element of list to be traversed.
     */
    public SinglyLinkedListIterator(Node<E> t)
    {
        head = t;
        reset();
    }
    
    /**
     * Reset iterator to beginning of the structure.
     *
     * @post iterator is reset to beginning of traversal
     */
    public void reset()
    {
        current = head;
    }

    /**
     * Determine if the iteration is finished.
     *
     * @post returns true if there is more structure to be viewed:
     *       i.e., if value (next) can return a useful value.
     * 
     * @return True if the iterator has more elements to be considered.
     */
    public boolean hasNext()
    {
        return current != null;
    }

    /**
     * Return current value and increment Iterator.
     *
     * @pre traversal has more elements
     * @post returns current value and increments iterator
     * 
     * @return The current value, before increment.
     */
    public E next()
    {
        E temp = current.value();
        current = current.next();
        return temp;
    }

    /**
     * Return structure's current object reference.
     *
     * @pre traversal has more elements
     * @post returns current value referenced by iterator 
     * 
     * @return E currently referenced.
     */
    public E get()
    {
        return current.value();
    }
}
