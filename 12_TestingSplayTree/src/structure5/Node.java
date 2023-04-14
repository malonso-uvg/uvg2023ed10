// Implementation of a node of a singly linked list.
// (c) 1998, 2001 duane a. bailey
package structure5;

/**
 * A class supporting a singly linked list element.  Each element
 * contains a value and maintains a single reference to the next 
 * node in the list.
 *
 * @version $Id: Node.java 31 2007-08-06 17:19:56Z bailey $
 * @author, 2001 duane a. bailey
 */
public class Node<E>
{
    /**
     * The data value stored in this node.
     */
    protected E data; // value stored in this element
    /**
     * Reference to the next node in the list.
     */
    protected Node<E> nextElement; // ref to next

    /**
     * Construct a singly linked list element.
     *
     * @pre v is a value, next is a reference to remainder of list
     * @post an element is constructed as the new head of list
     * @param v The value to be referenced by this element.
     * @param next A reference to the next value in the list.
     */
    public Node(E v, Node<E> next)
    {
        data = v;
        nextElement = next;
    }

    /**
     * Constructs a node not associated with
     * any list.  next reference is set to null.
     *
     * @post constructs a new tail of a list with value v
     * 
     * @param v The value to be inserted into the node.
     */
    public Node(E v)
    {
        this(v,null);
    }

    /**
     * @post returns reference to next value in list
     * 
     */
    public Node<E> next()
    {
        return nextElement;
    }

    /**
     * Update the next element.
     *
     * @post sets reference to new next value
     * 
     * @param next The new value of the next element reference.
     */
    public void setNext(Node<E> next)
    {
        nextElement = next;
    }

    /**
     * Fetch the value associated with this element.
     *
     * @post returns value associated with this element
     * 
     * @return Reference to the value stored within this element.
     */
    public E value()
    {
        return data;
    }

    /**
     * Set the value associated with this element.
     *
     * @post sets value associated with this element
     * 
     * @param value The new value to be associated with this element.
     */
    public void setValue(E value)
    {
        data = value;
    }

    /**
     * Construct a string representation of element.
     *
     * @post returns string representation of element
     * 
     * @return The string representing element.
     */
    public String toString()
    {
        return "<Node: "+value()+">";
    }
}

