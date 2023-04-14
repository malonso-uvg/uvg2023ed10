// An implementation of extensible arrays.
// (c) 1998, 2001 duane a. bailey
// $Id: Vector.java 31 2007-08-06 17:19:56Z bailey $

package structure5;
import java.util.Iterator;
import java.util.Collection;

/**
 * An implementation of extensible arrays, similar to that of {@link java.util.Vector java.util.Vector}.
 *
 * This vector class implements a basic extensible array.  It does not implement
 * any of the additional features of the Sun class, including list-like operations.
 * Those operations are available in other implementors of {@link List} in this
 * package.
 * <p>
 * Example usage:
 *
 * To put a program's parameters into a Vector, we would use the following:
 * <pre>
 * public static void main(String[] arguments)
 * {
 *    {@link #Vector Vector&lt;String&gt;} argVec = new {@link #Vector Vector&lt;String&gt;()};
 *    for (String arg : arguments)
 *    {
 *       argVec.{@link #add(Object) add(arg)};
 *    }
 *    System.out.println({@link #toString argVec});
 * }
 * </pre>
 *
 * @version $Id: Vector.java 31 2007-08-06 17:19:56Z bailey $
 * @since JavaStructures 1.0
 */
public class Vector<E> extends AbstractList<E> implements Cloneable
{
    /**
     * The data associated with the vector.  The size of the
     * array is always at least as large as the vector.  The array
     * is only extended when necessary.
     * Declared private for type safety.
     */
    private Object elementData[];       // the data
    /**
     * The actual number of elements logically stored within the
     * vector.  May be smaller than the actual length of the array.
     */
    protected int elementCount;         // number of elements in vector
    /**
     * The size of size increment, should the vector become full.
     * 0 indicates the vector should be doubled when capacity of
     * the array is reached.
     */
    protected int capacityIncrement;    // the rate of growth for vector
    /**
     * The initial value of any new elements that are appended to the
     * vector.  Normally null.  Be aware that references used in this
     * way will result in multiple references to a single object.
     */
    protected E initialValue;   // new elements have this value
    /**
     * The default size of the vector; may be overridden in
     * the {@link #Vector(int)} constructor.
     */
    protected final static int defaultCapacity = 10; // def't capacity, must be>0

    /**
     * Construct an empty vector.
     * 
     * @post constructs a vector with capacity for 10 elements
     */
    public Vector()
    {
        this(defaultCapacity); // call one-parameter constructor
    }
    
    /**
     * Construct an empty vector capable of storing <code>initialCapacity</code>
     * values before the vector must be extended.
     *
     * @pre initialCapacity >= 0
     * @post constructs an empty vector with initialCapacity capacity
     * @param initialCapacity The size of vector before reallocation is necessary
     */
    public Vector(int initialCapacity)
    {
        Assert.pre(initialCapacity >= 0,"Capacity must not be negative");
        elementData = new Object[initialCapacity];
        elementCount = 0;
        capacityIncrement = 0;
        initialValue = null;
    }

    /**
     * Construct a vector with initial capacity, and growth characteristic.
     *
     * @pre initialCapacity >= 0, capacityIncr >= 0
     * @post constructs an empty vector with initialCapacity capacity
     *    that extends capacity by capacityIncr, or doubles if 0
     * 
     * @param initialCapacity The initial number of slots in vector.
     * @param capacityIncr The size of growth of vector.
     * @see #capacityIncrement
     */
    public Vector(int initialCapacity, int capacityIncr)
    {
        Assert.pre(initialCapacity >= 0, "Capacity must not be negative.");
        Assert.pre(capacityIncr >= 0, "The capacity increment must be 0,"+
                   " for doubling, or positive for incremental growth.");
        elementData = new Object[initialCapacity];
        elementCount = 0;
        capacityIncrement = capacityIncr;
        initialValue = null;
    }

    /**
     * Construct a vector with initial size, growth rate and default
     * value.
     *
     * @pre initialCapacity, capacityIncr >= 0
     * @post constructs empty vector with capacity that begins at
     *       initialCapacity and extends by capacityIncr or doubles
     *       if 0.  New entries in vector are initialized to initValue.
     * 
     * @param initialCapacity The initial number of slots in vector.
     * @param capacityIncr The size of the increment when vector grows.
     * @param initValue The initial value stored in vector elements.
     */
    public Vector(int initialCapacity, int capacityIncr, E initValue)
    {
        Assert.pre(initialCapacity >= 0, "Nonnegative capacity.");
        capacityIncrement = capacityIncr;
        elementData = new Object[initialCapacity];
        elementCount = 0;
        initialValue = initValue;
    }
    
    public Vector(Vector<E> that)
    {
        this(that.values());
    }

    public Vector(Collection<E> c)
    {
        this(c.size());
        Iterator<E> i = c.iterator();
        while (i.hasNext())
        {
            add(i.next());
        }
    }


    /**
     * Ensure that the vector is capable of holding at least
     * minCapacity values without expansion.
     *
     * @post the capacity of this vector is at least minCapacity
     * 
     * @param minCapacity The minimum size of array before expansion.
     */
    public void ensureCapacity(int minCapacity)
    {
        if (elementData.length < minCapacity) {
            int newLength = elementData.length; // initial guess
            if (capacityIncrement == 0) {
                // increment of 0 suggests doubling (default)
                if (newLength == 0) newLength = 1;
                while (newLength < minCapacity) {
                    newLength *= 2;
                }
            } else {
                // increment != 0 suggests incremental increase
                while (newLength < minCapacity)
                {
                    newLength += capacityIncrement;
                }
            }
            // assertion: newLength > elementData.length.
            Object newElementData[] = new Object[newLength];
            int i;
            // copy old data to array
            for (i = 0; i < elementCount; i++) {
                newElementData[i] = elementData[i];
            }
            elementData = newElementData;
            // garbage collector will (eventually) pick up old elementData
        }
        // assertion: capacity is at least minCapacity
    }

    /**
     * Add an element to the high end of the array, possibly expanding
     * vector.
     *
     * @post adds new element to end of possibly extended vector
     * 
     * @param obj The object to be added to the end of the vector.
     */
    public void add(E obj)
    {
        ensureCapacity(elementCount+1);
        elementData[elementCount] = obj;
        elementCount++;
    }
    
    /**
     * Add an element to the high end of the array, possibly expanding
     * vector.
     *
     * @post adds new element to end of possibly extended vector
     * 
     * @param obj The object to be added to the end of the vector.
     */
    public void addElement(E o)
    {
        add(o);
    }
    
    /**
     * Remove an element, by value, from vector.
     *
     * @post element equal to parameter is removed and returned
     * @param element the element to be removed.
     * @return the element actually removed, or if none, null.
     */
    public E remove(E element)
    {
        E result = null;
        int i = indexOf(element);
        if (i >= 0)
        {
            result = get(i);
            remove(i);
        }
        return result;
    }

    /**
     * Determine the capacity of the vector.  The capacity is always
     * at least as large as its size.
     *
     * @post returns allocated size of the vector
     * 
     * @return The size of the array underlying the vector.
     */
    public int capacity()
    {
        return elementData.length;
    }

    /**
     * Construct a shallow copy of the vector.  The vector
     * store is copied, but the individual elements are shared
     * objects.
     *
     * @post returns a copy of the vector, using same objects
     * 
     * @return A copy of the original vector.
     */
@SuppressWarnings("unchecked")
    public Object clone()
    {
        Vector<E> copy = null;
        try {
            copy = (Vector<E>)super.clone();
            copy.elementData = elementData.clone();
        } catch (java.lang.CloneNotSupportedException e) { Assert.fail("Vector cannot be cloned."); }
        return copy;
    }

    /**
     * Determine if a value appears in a vector.
     *
     * @post returns true iff Vector contains the value
     *       (could be faster, if orderedVector is used)
     * 
     * @param elem The value sought.
     * @return True iff the value appears in the vector.
     */
    public boolean contains(E elem)
    {
        int i;
        for (i = 0; i < elementCount; i++) {
            if (elem.equals(elementData[i])) return true;
        }
        return false;
    }

    /**
     * Copy the contents of the vector into an array.
     * The array must be large enough to accept all the values in
     * the vector.
     *
     * @pre dest has at least size() elements
     * @post a copy of the vector is stored in the dest array
     * 
     * @param dest An array of size at least size(). 
     */
    public void copyInto(Object dest[])
    {
        int i;
        for (i = 0; i < elementCount; i++) {
            dest[i] = elementData[i];
        }
    }

    /**
     * Fetch the element at a particular index.
     * The index of the first element is zero.
     *
     * @pre 0 <= index && index < size()
     * @post returns the element stored in location index
     * 
     * @param index The index of the value sought.
     * @return A reference to the value found in the vector.
     */
    public E elementAt(int index)
    {
        Assert.pre(0 <= index && index < size(),"index is within bounds");
        return get(index);
    }


    /**
     * Fetch the element at a particular index.
     * The index of the first element is zero.
     *
     * @pre 0 <= index && index < size()
     * @post returns the element stored in location index
     * 
     * @param index The index of the value sought.
     * @return A reference to the value found in the vector.
     */
@SuppressWarnings("unchecked")
    public E get(int index)
    {
        return (E)elementData[index];
    }

    /**
     * Construct a iterator over the elements of the vector.
     * The iterator considers elements with increasing
     * index.
     *
     * @post returns an iterator allowing one to
     *       view elements of vector
     * @return an iterator to traverse the vector.
     */
    public Iterator<E> iterator()
    {
        return new VectorIterator<E>(this);
    }

    /**
     * Get access to the first element of the vector.
     *
     * @pre vector contains an element
     * @post returns first value in vector
     * 
     * @return Access to the first element of the vector.
     */
    public E firstElement()
    {
        return get(0);
    }

    /**
     * Assuming the data is not in order, find the index of a
     * value, or return -1 if not found.
     *
     * @post returns index of element equal to object, or -1; starts at 0
     * 
     * @param elem The value sought in vector.
     * @return The index of the first occurrence of the value.
     */
    public int indexOf(E elem)
    {
        return indexOf(elem,0);
    }

    /**
     * Assuming the data is not in order, find the index of a value
     * or return -1 if the value is not found.  Search starts at index.
     *
     * @post returns index of element equal to object, or -1; starts at index
     * 
     * @param elem The value sought.
     * @param index The first location considered.
     * @return The index of the first location, or -1 if not found.
     */
    public int indexOf(E elem, int index)
    {
        int i;
        for (i = index; i < elementCount; i++)
        {
            if (elem.equals(elementData[i])) return i;
        }
        return -1;
    }

    /**
     * Insert an element at a particular location.
     * Vector is grown as needed
     *
     * @pre 0 <= index <= size()
     * @post inserts new value in vector with desired index,
     *   moving elements from index to size()-1 to right
     * 
     * @param obj The value to be inserted.
     * @param index The location of the new value.
     *
     */
    public void insertElementAt(E obj, int index)
    {
        add(index,obj);
    }

    /**
     * Insert an element at a particular location.
     * Vector is grown as needed
     *
     * @pre 0 <= index <= size()
     * @post inserts new value in vector with desired index,
     *   moving elements from index to size()-1 to right
     * 
     * @param obj the value to be inserted.
     * @param index the location of the new value.
     */
    public void add(int index, E obj)
    {
        int i;
        ensureCapacity(elementCount+1);
        // must copy from right to left to avoid destroying data
        for (i = elementCount; i > index; i--) {
            elementData[i] = elementData[i-1];
        }
        // assertion: i == index and element[index] is available
        elementData[index] = obj;
        elementCount++;
    }
    /* A recursive version of insertion of element at
    public void add(int index, E value)
    // pre: 0 <= index <= size()
    // post: inserts new value in vector with desired index
    //   moving elements from index to size()-1 to right
    {
        if (index >= size()) {
            add(value); // base case: add at end
        } else {
            E previous = get(index); // work
            add(index+1,previous);  // progress through recursion
            set(index,value);  // work
        }
    }
    */

    /**
     * Determine if the Vector contains no values.      
     *
     * @post returns true iff there are no elements in the vector
     * 
     * @return True iff the vector is empty.
     */
    public boolean isEmpty()
    {
        return size() == 0;
    }

    /**
     * Fetch a reference to the last value in the vector.
     *
     * @pre vector is not empty
     * @post returns last element of the vector
     * 
     * @return A reference to the last value.
     */
    public E lastElement()
    {
        return get(elementCount-1);
    }

    /**
     * Search for the last occurrence of a value within the
     * vector.  If none is found, return -1.
     *
     * @post returns index of last occurrence of object in the vector, or -1
     * 
     * @param obj The value sought.
     * @return The index of the last occurrence in the vector.
     */
    public int lastIndexOf(E obj)
    {
        return lastIndexOf(obj,elementCount-1);
    }

    /**
     * Find the index of the last occurrence of the value in the vector before
     * the indexth position.
     *
     * @pre index >= 0
     * @post returns the index of last occurrence of object at or before
     *       index
     * 
     * @param obj The value sought.
     * @param index The last acceptable index.
     * @return The index of the last occurrence of the value, or -1 if none.
     */
    public int lastIndexOf(E obj, int index)
    {
        int i;
        for (i = index; i >= 0; i--) {
            if (obj.equals(elementData[i])) return i;
        }
        return -1;
    }

    /**
     * Remove all the values of the vector.
     *
     * @post vector is empty
     */
    public void clear()
    {
        setSize(0);
    }

    /**
     * Remove all the elements of the vector.
     * Kept for compatibility with java.util.Vector.
     *
     * @post vector is empty
     *
     * @see #clear
     */
    public void removeAllElements()
    {
        setSize(0);
    }

    /*
     * Remove an element, by value, from vector.
     *
     * @post element equal to parameter is removed
     * 
     * @param element The element to be removed.
     * @return The element actually removed, or if none, null.

    public boolean removeElement(E element)
    {
        int where = indexOf(element);
        if (where == -1) return false;
        remove(where);
        return true;
    }
*/

    /**
     * Remove an element at a particular location.
     *
     * @pre 0 <= where && where < size()
     * @post indicated element is removed, size decreases by 1
     * 
     * @param where The location of the element to be removed.
     */
    public void removeElementAt(int where)
    {
        remove(where);
    }

    /**
     * Remove an element at a particular location.
     *
     * @pre 0 <= where && where < size()
     * @post indicated element is removed, size decreases by 1
     * 
     * @param where The location of the element to be removed.
     */
    public E remove(int where)
    {
        E result = get(where);
        elementCount--;
        while (where < elementCount) {
            elementData[where] = elementData[where+1];
            where++;
        }
        elementData[elementCount] = null; // free reference
        return result;
    }

    /**
     * Change the value stored at location index.
     *
     * @pre 0 <= index && index < size()
     * @post element value is changed to obj
     * 
     * @param obj The new value to be stored.
     * @param index The index of the new value.
     */
    public void setElementAt(E obj, int index)
    {
        set(index,obj);
    }

    /**
     * Change the value stored at location index.
     *
     * @pre 0 <= index && index < size()
     * @post element value is changed to obj; old value is returned
     * 
     * @param obj The new value to be stored.
     * @param index The index of the new value.
     */
@SuppressWarnings("unchecked")
    public E set(int index, E obj)
    {
        Assert.pre(0 <= index && index < elementCount,"index is within bounds");
        E previous = (E)elementData[index];
        elementData[index] = obj;
        return previous;
    }

    /**
     * Explicitly set the size of the array.
     * Any new elements are initialized to the default value.
     *
     * @pre newSize >= 0
     * @post vector is resized, any new elements are initialized
     * 
     * @param newSize The ultimate size of the vector.
     */
    public void setSize(int newSize)
    {
        int i;
        if (newSize < elementCount) {
            for (i = newSize; i < elementCount; i++) elementData[i] = null;
        } else {
            ensureCapacity(newSize);
            for (i = elementCount; i < newSize; i++)
                elementData[i] = initialValue;
        }
        elementCount = newSize;
    }

    /**
     * Determine the number of elements in the vector.
     *
     * @post returns the size of the vector
     * 
     * @return The number of elements within the vector.
     */
    public int size()
    {
        return elementCount;
    }

    /**
     * Trim the vector to exactly the correct size.
     *
     * @post minimizes allocated size of vector
     */
    public void trimToSize()
    {
        Object newElementData[] = new Object[elementCount];
        copyInto(newElementData);
        elementData = newElementData;
    }

    /**
     * Determine a string representation for the vector.
     *
     * @post returns a string representation of vector
     * 
     * @return A string representation for the vector.
     */
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        int i;

        sb.append("<Vector:");
        for (i = 0; i < size(); i++)
        {
            sb.append(" "+get(i));
        }
        sb.append(">");
        return sb.toString();
    }
    /*
    public void print()
    // post: print the elements of the vector
    {
        printFrom(0);
    }
    
    protected void printFrom(int index)
    // pre: index <= size()
    // post: print elements indexed between index and size()
    {
        if (index < size()) {
            System.out.println(get(index));
            printFrom(index+1);
        }
    }
    */
}

