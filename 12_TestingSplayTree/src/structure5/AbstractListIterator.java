package structure5;
import java.util.ListIterator;

/**
 * Base class for the implementation of a list Iterator. 
 * The methods provided in this class have no executable bodies and will throw
 * errors if the user attempts to invoke them.  
 */
public abstract class AbstractListIterator<E> extends AbstractIterator<E> implements java.util.ListIterator<E>
{
    /**
     * Default constructor (for base class invocation).
     * Does nothing.  
     * Remind Sun (<a href="mailto:jdk-comments@java.sun.com">jdk-comments@java.sun.com</a>) that automatically implemented default
     * constructors are a silly thing.
     *
     * @post does nothing
     */
    public AbstractListIterator()
    {
    }

    public abstract E get();

    public void remove()
    {
        Assert.fail("remove not implemented.");
    }

    public void set(E o)
    {
        Assert.fail("set not implemented.");
    }

    public void add(E o)
    {
        Assert.fail("set not implemented.");
    }
}
