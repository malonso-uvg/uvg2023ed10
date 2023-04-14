package structure5;
import java.util.Map;
/**
 * An implementation of the the java.util.Map.Entry interface, Entry
 * is a simple key value pair, from which both the key and the value
 * can be accessed. {@link structure.Association} and related classes
 * also implement the Map interface and have expanded functionality. 
 * <P>
 * Typical Usage:
 * <P>
 * <pre>
 * ...
 *     Entry e = new {@link #Entry(Object,Object) Entry(aKey, aValue)};
 *     Object key = e.{@link #getKey()};
 *     Object value = e.{@link #getValue()};
 *     e.{@link #setValue(Object) setValue(newValue)};
 * ...
 * </pre>
 * 
 */
public class Entry<K,V> implements java.util.Map.Entry<K,V>
{
    protected K theKey; // the key of the key-value pair
    /**
     * The mutable value.  An arbitrary object.
     */
    protected V theValue; // the value of the key-value pair

    /**
     * Constructs a pair from a key and value.
     *
     * @pre Key is non-null
     * @post Constructs a key-value pair
     * 
     * @param key A non-null object.
     * @param value A (possibly null) object.
     */
    public Entry(K key, V value)
    {
        Assert.pre(key != null, "Key must not be null.");
        theKey = key;
        theValue = value;
    }

    /**
     * Constructs a pair from a key; value is null.
     *
     * @pre Key is non-null
     * @post Constructs a key-value pair
     * 
     * @param key A non-null key value.
     */
    public Entry(K key)
    {
        this(key,null);
    }

    /**
     * Standard comparison function.  Comparison based on keys only.
     *
     * @pre Other is non-null Entry
     * @post Returns true iff the keys are equal
     * 
     * @param other Another association.
     * @return True iff the keys are equal.
     */
    public boolean equals(Object other)
    {
        Map.Entry<?,?> otherEntry = (Map.Entry<?,?>)other;
        return getKey().equals(otherEntry.getKey());
    }
    
    /**
     * Standard hashcode function.
     *
     * @post Return hash code association with this association
     * 
     * @return A hash code for association.
     * @see Hashtable
     */
    public int hashCode()
    {
        return getKey().hashCode();
    }
    
    /**
     * Fetch value from association.  May return null.
     *
     * @post Returns value from association
     * 
     * @return The value field of the association.
     */
    public V getValue()
    {
        return theValue;
    }

    /**
     * Fetch key from association.  Should not return null.
     *
     * @post Returns key from association
     * 
     * @return Key of the key-value pair.
     */
    public K getKey()
    {
        return theKey;
    }

    /**
     * Sets the value of the key-value pair.
     *
     * @post Sets association's value to value
     * 
     * @param value The new value.
     */
    public V setValue(V value)
    {
        V result = theValue;
        theValue = value;
        return result;
    }

    /**
     * Standard string representation of an association.
     *
     * @post Returns string representation
     * 
     * @return String representing key-value pair.
     */
    public String toString()
    {
        StringBuffer s = new StringBuffer();
        s.append("<Entry: "+getKey()+"="+getValue()+">");
        return s.toString();
    }
}
