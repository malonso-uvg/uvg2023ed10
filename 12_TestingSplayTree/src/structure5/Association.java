// A class for binding key/value pairs.
// (c) 1998,2001 duane a. bailey
package structure5;
import java.util.Map;

/*
public class Association<K,V>
*/
/**
 * A class implementing a key-value pair.  This class associates an 
 * immutable key with a mutable value.  Used in many other structures.
 * <P>
 * Example Usage:
 * <P>
 * To store the number of classes a student has taken from five different
 * professors and to output this information, we could use the following.
 * <P>
 * <pre>
 * public static void main(String[] argv){
 *      //store the number of classes taken by the student in an array of associations
 *      {@link Association} [] classesTaken = new Association[5];
 *      classesTaken[0] = new {@link #Association(Object,Object) Association("Andrea", new Integer(5))};
 *      classesTaken[1] = new Association("Barbara", new Integer(1));
 *      classesTaken[2] = new Association("Bill", new Integer(3));
 *      classesTaken[3] = new Association("Duane", new Integer(2));
 *      classesTaken[4] = new Association("Tom", new Integer(1));
 *
 *      //print out each item in the array
 *      for (int i = 0; i< classesTaken.length; i++){
 *          System.out.println("This Student has taken " + classesTaken[i].{@link #getValue()} +
 *                             " classes from " + classesTaken[i].{@link #getKey()}+ ".");
 *      }
 * }
 * </pre>
 * @version $Id: Association.java 34 2007-08-09 14:43:44Z bailey $
 * @author, 2001 duane a. bailey
 */

public class Association<K,V> implements Map.Entry<K,V>
{
    /**
     * The immutable key.  An arbitrary object.
     */
    protected K theKey; // the key of the key-value pair
    /**
     * The mutable value.  An arbitrary object.
     */
    protected V theValue; // the value of the key-value pair

    /*
      for example:
      Association<String,Integer> personAttribute =
         new Assocation<String,Integer>("Age",34);
     */
    /**
     * Constructs a pair from a key and value.
     *
     * @pre key is non-null
     * @post constructs a key-value pair
     * @param key A non-null object.
     * @param value A (possibly null) object.
     */
    public Association(K key, V value)
    {
        Assert.pre(key != null, "Key must not be null.");
        theKey = key;
        theValue = value;
    }

    /**
     * Constructs a pair from a key; value is null.
     *
     * @pre key is non-null
     * @post constructs a key-value pair; value is null
     * @param key A non-null key value.
     */
    public Association(K key)
    {
        this(key,null);
    }

    /**
     * Standard comparison function.  Comparison based on keys only.
     *
     * @pre other is non-null Association
     * @post returns true iff the keys are equal
     * @param other Another association.
     * @return true iff the keys are equal.
     */
    public boolean equals(Object other)
    {
        Association otherAssoc = (Association)other;
        return getKey().equals(otherAssoc.getKey());
    }
    
    /**
     * Standard hashcode function.
     *
     * @post return hash code association with this association
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
     * @post returns value from association
     * @return The value field of the association.
     */
    public V getValue()
    {
        return theValue;
    }

    /**
     * Fetch key from association.  Should not return null.
     *
     * @post returns key from association
     * @return Key of the key-value pair.
     */
    public K getKey()
    {
        return theKey;
    }

    /**
     * Sets the value of the key-value pair.
     *
     * @post sets association's value to value
     * @param value The new value.
     */
    public V setValue(V value)
    {
        V oldValue = theValue;
        theValue = value;
        return oldValue;
    }

    /**
     * Standard string representation of an association.
     *
     * @post returns string representation
     * @return String representing key-value pair.
     */
    public String toString()
    {
        StringBuffer s = new StringBuffer();
        s.append("<Association: "+getKey()+"="+getValue()+">");
        return s.toString();
    }
    /*
...
*/
}
