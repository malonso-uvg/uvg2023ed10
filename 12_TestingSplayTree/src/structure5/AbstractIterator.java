// Abstract base class for portable iterator and enumeration implementation.
// (c) 2001 duane a. bailey

package structure5;
import java.util.Enumeration;
import java.util.Iterator;
/**
 * Abstract base class for portable iterator and enumeration implementation.
 * <p>
 * A general purpose iterator that implements both java.util.Iterator
 * and java.util.Enumeration.  This class also implements the {@link #value}
 * method, providing multiple accesses, and the {@link #reset} method, which
 * allows the user to restart the traversal.
 * <p>
 * Typical usage:
 * <pre>
 *     {@link structure.Vector Vector} v = new {@link structure#Vector() Vector};
 *     String s = "target";
 *     ...
 *     {@link Iterator} t = v.{@link Vector#iterator() iterator()}();
 *     // print members of vector that are greater than s
 *     for (t.{@link #reset reset}(); t.{@link #hasNext hasNext}(); t.{@link #next next}())
 *     {
 *         if (s.compareTo(t.{@link #value value}()) < 0)
 *               System.out.println(t.{@link #value value}());
 *     }
 * </pre>
 * <p>
 * Users of Java's <code>Enumeration</code> or <code>Iterator</code>
 * may treat the <code>AbstractIterator</code> as an <code>Enumeration</code> or 
 * <code>Iterator</code>, respectively, without ill effect.
 * <p>
 * Appropriate care should be taken to make sure that the underlying data
 * structure is not modified while the traversal is active.  Some
 * extensions of this class may choose to provide methods that modify
 * the structure --- the <code>remove</code> method of <code>iterator</code>
 * is one such possibility.  Care should be taken to make sure that such
 * modifications are carefully synchronized, or limited to one active
 * traversal.
 *
 * @author, 2001 duane a. bailey
 * @version $Id: AbstractIterator.java 26 2006-08-24 14:29:13Z bailey $
 * @see java.util.Iterator
 * @see java.util.Enumeration
 */
public abstract class AbstractIterator<E>
    implements Enumeration<E>, Iterator<E>, Iterable<E>
{
    /**
     * Default constructor (for base class invocation).
     * Does nothing.  Remind Sun (<a href="mailto:jdk-comments@java.sun.com">jdk-comments@java.sun.com</a>) that automatically implemented default
     * constructors are a silly thing.
     *
     * @post does nothing
     */
    public AbstractIterator()
    {
    }

    /**
     * Reset iterator to the beginning of the structure.
     * This method is not required of <code>Iterator</code> or 
     * <code>Enumeration</code> implementation, but some traversals
     * may allow efficient multi-pass implementations with little
     * overhead.  The user is encouraged to implement this method.
     *
     * @pre iterator may be initialized or even amid-traversal
     * @post reset iterator to the beginning of the structure
     */
    public abstract void reset();

    /**
     * Returns true if the iterator has more elements to visit.
     * The method <code>hasMoreElements</code> is an
     * <code>Enumeration</code>-required call to this method.  The user
     * should override only this method.
     *
     * @post true iff the iterator has more elements to visit
     * @return true iff the iterator has more elements to visit
     * @see #hasMoreElements
     */
    public abstract boolean hasNext();

    /**
     * Returns the value currently being considered by the AbstractIterator.
     * This method is required by neither <code>Iterator</code> nor
     * <code>Enumeration</code>.  This method should be implemented,
     * however, to provide better support for <code>for</code>-loops.
     *
     * @pre there are more elements to be considered; hasNext()
     * @return the next value to be considered in iterator
     * @post returns current value; ie. value next() will return
     */
    public abstract E get();

    /**
     * Returns the value currently being considered by the AbstractIterator.
     * This method is required by neither <code>Iterator</code> nor
     * <code>Enumeration</code>.  This method should be implemented,
     * however, to provide better support for <code>for</code>-loops.
     *
     * @deprecated This method was deprecated in version 2 of the structure
     * package. Use the get method.
     * @pre there are more elements to be considered; hasNext()
     * @return the next value to be considered in iterator
     * @post returns the current value; the value next() will return
     */
    @Deprecated final public E value()
    {
        return get();
    }

    /**
     * Moves, bumps, or "increments" the iterator along the traversal;
     * returns the next value considered.
     * This method should only be called if the iterator has a next value.
     * To get a value from an iterator multiple times, use the
     * <code>value</code> method.
     * <p>
     * This method is preferred over the <code>nextElement</code> method.
     *
     * @pre hasNext()
     * @post returns current value, and then increments iterator
     * @return the current value
     * @see #hasMoreElements
     * @see #value
     */
    public abstract E next();

    /**
     * If implemented, removes the currently visited value from the structure.
     * <code>remove</code> should not be called unless it is overridden.
     *
     * @pre hasNext() is true and get() has not been called
     * @post the value has been removed from the structure
     */
    public void remove()
    {
        Assert.fail("Remove not implemented.");
    }

    /**
     * An Enumeration method that is equivalent to {@link #hasNext}.
     * Extensions to this class should provide a <code>hasNext</code>
     * method.
     *
     * @post returns true iff there are more elements
     * @return returns true iff there are more elements
     */
    final public boolean hasMoreElements()
    {
        return hasNext();
    }

    /**
     * An Enumeration method that is equivalent to {@link #next}.
     * Extensions to this class should provide a <code>next</code> method.
     *
     * @pre hasNext()
     * @post returns the current value and "increments" the iterator
     * @return the current value before iterator is incremented
     */
    final public E nextElement()
    {
        return next();
    }

    /**
     * Support for for-iteration.
     *
     * @post returns this iterator as a subject for a for-loop
     * @return this iterator
     */
    final public Iterator<E> iterator()
    {
        return this;
    }

    
}
