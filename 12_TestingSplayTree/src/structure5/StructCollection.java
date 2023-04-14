// A class that converts a Structure to a Collection
// (c) 2001 duane a. bailey
package structure5;
import java.util.Collection;
import java.util.Iterator;
/**
 * This utility class converts a Structure to a Collection.
 * Users are advised to make use of the <code>values</code> method
 * that is part of every <code>Structure</code> that returns a <code>Collection</code>.
 * <p>
 * This class is necessary because there are certain basic differences
 * in the interfaces of the two classes.
 * <p>
 * This class works provides a facade: methods of the Collection
 * interface are directly referred to the base Structure, or simple
 * code supports the interface.
 * <P>
 * Example Usage:
 * <P>
 * <pre>
 * public static void main(String[] argv){
 *      Structure struct = new Vector();
 *      for(int i = 0; i < argv.length; i++) struct.add(argv[i]);
 *      Collection collect = new {@link #StructCollection(Structure) StructCollection(struct)};
 * } 
 * </pre>
 * @author, 2001 duane a. bailey
 * @version $Id: StructCollection.java 29 2006-11-03 16:56:32Z bailey $
 * @since Java Structures, 2nd edition
 */
@SuppressWarnings("unchecked")
public class StructCollection<E> implements Collection<E>
{
    /**
     * The subordinate Structure
     */
    protected Structure<E> base;

    /**
     * Constructs a Collection, based on the contents of the subordinate
     * Structure object.
     * @post the Collection is a facade for manipulations to a subordinate
     * Structure.
     */
    public StructCollection(Structure<E> s)
    {
        base = s;
    }

    /**
     * Add an object to the subordinate Structure, and return boolean
     * indicating success.
     * @post the object o is added to the Structure, and true is returned
     * @return true.
     */
    public boolean add(E o)
    {
        base.add(o);
        return true;
    }

    /**
     * Adds all the elements of another collection (c) into this Structure.
     * A boolean value of true is returned.
     * @pre c is a valid collection
     * @post the elements of c have been added to the Structure, and 
     *       the value true is returned if all adds returned true.
     * @return true if each of the adds returned true.
     */
    public /*<T extends E>*/ boolean addAll(Collection<? extends E> c)
    {
        Iterator<?> i = c.iterator();
        boolean result = false;
        while (i.hasNext())
        {
            result |= add((E)i.next());
        }
        return result;
    }

    /**
     * Remove all elements from the Structure.
     * @post the subordinate structure is empty
     */
    public void clear()
    {
        base.clear();
    }

    /**
     * Returns true iff object o is contained within the subordinate
     * structure.
     * @pre o is a non-null object
     * @post returns true if o is equal to some object in the subordinate
     * structure.
     */
    public boolean contains(Object o)
    {
        return base.contains((E)o);
    }

    /**
     * Returns true if all of the elements of c are contained within
     * the subordinate structure.
     * @pre c is a valid Collection
     * @post returns true if all elements of c appear in this Structure
     * @return true if c is a subset of this
     */
    public /*<T>*/ boolean containsAll(Collection<?> c)
    {
        // assume yes
        boolean contained = true;
        Iterator i = c.iterator();
        while (contained && i.hasNext())
        {
            // possibly realize some value is not in structure
            contained &= contains((E)i.next());
        }
        return contained;
    }

    /**
     * Compare one StructCollection with another.
     * @pre o is of similar type to base Structure
     * @post returns true if both objects appear equal as Structures
     * @return true iff Structures are equals
     */
    public boolean equals(Object o)
    {
        return base.equals(o);
    }
    
    /**
     * Return hash code.
     * @post hash code returned is the hashcode of the subordinate
     * structure.
     * @return hash code of subordinate structure.
     */
    public int hashCode()
    {
        return base.hashCode();
    }

    /**
     * Detect an empty structure.
     * @post returns true iff subordinate structure is empty
     * @return true iff subordinate structure is empty.
     */
    public boolean isEmpty()
    {
        return base.isEmpty();
    }

    /**
     * Return an iterator to traverse the subordinate structure.
     * @post returns an iterator over subordinate strucutre
     * @return the iterator over the subordinate structure.
     */
    public Iterator<E> iterator()
    {
        return base.iterator();
    }

    /**
     * Removes an instance of object o, if it appears within structure.
     * @pre o is non-null
     * @post removes up to one instance of o from structure
     * @return returns true if instance was found and removed.
     */
    public boolean remove(Object o)
    {
        E removed = base.remove((E)o);
        return removed != null;
    }

    /**
     * Removes all objects found within collection c from this collection.
     * @pre c is a valid collection
     * @post a copy of each element c is removed from this, if it exists
     * @return true if every element of c could be removed from this.
     */
    public /*<T>*/ boolean removeAll(Collection<?> c)
    {
        boolean result = true;
        Iterator i = c.iterator();
        while (i.hasNext())
        {
            result &= remove((E)i.next());
        }
        return result;
    }

    /**
     * Retains elements of this Collection that are also in c.
     * @pre c is a valid Collection
     * @post this collection retains only those elements found within
     * both this and c.
     * @return true iff this Collection was changed by this operation
     */
    public /*<T>*/ boolean retainAll(Collection<?> c)
    {
        // if change is necessary, vector contains result
        java.util.Vector<E> v = new java.util.Vector<E>();
        Iterator<E> i = iterator();
        boolean mustChange = false;
        // iterate across elements of this; assume they're in c
        while (i.hasNext())
        {
            E e = i.next();
            if (c.contains(e)) v.addElement(e);
            else mustChange = true; // woops, don't include, force change
        }
        if (mustChange)
        {
            clear();
            addAll(v);
        }
        return mustChange;
    }

    /**
     * Determine the number of elements in this collection.
     * @post returns the number of elements in this collection
     * @return the number of elements in this collection.
     */
    public int size()
    {
        return base.size();
    }

    /**
     * Construct an array of values found in the subordinate Structure.
     * @post returns an array of objects containing the values of structure
     * @return array containing exactly the elements of this structure
     */
    public Object[] toArray()
    {
        int size = size();
        Object[] result = new Object[size];
        return toArray(result);
    }

    /**
     * Copy elements of this structure into target array.
     * @pre target is valid
     * @post values are copied to target; target is enlarged if necessary
     * to contain the values of this collection; ultimate target is returned.
     * @return the array of values
     */
    public <T> T[] toArray(T[] target)
    {
        int size = size();
        if (size > target.length)
        {
            target = (T[])java.lang.reflect.Array.newInstance(target.getClass().getComponentType(),size);
        }
        Iterator<E> i = iterator();
        int pos = 0;
        while (i.hasNext() && (pos < size))
        {
            target[pos++] = (T)i.next();
        }
        Assert.pre(!i.hasNext(),"Collection size() consistent with iterator.");
        if (pos < size) target[pos] = null;
        return target;
    }

    /**
     * Construct a string representation of this class.
     * @pre base class can stringify itself
     * @post string representation of this class is returned
     * @return string representing this class
     */
    public String toString()
    {
        return "<StructCollection wrapping "+base+">";
    }
}
