// A private class implementing an iterator over a Vector.
// (c) 1998, 2001 duane a. bailey

package structure5;
/**
 * A private class for implementing an iterator over a Vector.
 * <p>
 * Typical usage:
 * <pre>
 *     import structure.Vector;
 *     import java.util.Iterator;
 *     public static void main(String[] args)
 *     {
 *         Vector<String> argVec = new Vector<String>();
 *         for (int i = 0; i < args.length; i++)
 *         {
 *             argVec.add(args[i]);
 *         }
 *         Iterator<String> it = argVec.iterator();
 *         while (it.hasNext())
 *         {
 *             System.out.println(it.next());
 *         }
 *     }
 * </pre>
 * @version $Id: VectorIterator.java 22 2006-08-21 19:27:26Z bailey $
 * @author, 2001 duane a. bailey
 */
class VectorIterator<E> extends AbstractIterator<E>
{
    /**
     * The associated vector
     */
    protected Vector<E> theVector;
    /**
     * The index of the current value.
     */
    protected int current;

    /**
     * Construct a vector iterator to traverse vector v
     *
     * @post constructs an initialized iterator associated with v
     * 
     * @param v The underlying vector.
     */
    public VectorIterator(Vector<E> v)
    {
        theVector = v;
        reset();
    }

    /**
     * Reset the vector iterator to the first value in the vector.
     *
     * @post the iterator is reset to the beginning of the traversal
     */
    public void reset()
    {
        current = 0;
    }

    /**
     * Determine if some of the elements have yet to be considered.
     *
     * @post returns true if there is more structure to be traversed
     * 
     * @return True if more elements are to be considered.
     */
    public boolean hasNext()
    {
        return current < theVector.size();
    }

    /**
     * Fetch a reference to the current value.
     *
     * @pre traversal has more elements
     * @post returns the current value referenced by the iterator 
     * 
     * @return A reference to the current value being considered.
     */
    public E get()
    {
        return theVector.get(current);
    }
    
    /**
     * Return current value, and increment iterator.
     *
     * @pre traversal has more elements
     * @post increments the iterated traversal
     * 
     * @return A reference to the current value, before increment.
     */
    public E next()
    {
        return theVector.get(current++);
    }
}

