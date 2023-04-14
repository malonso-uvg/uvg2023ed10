package structure5;
import java.util.Iterator;
/**
 * Associations establish a link between a key and a value.  
 * An associative array or map is a structure that allows a disjoint
 * set of keys to become associated with an arbitrary set of values.  
 * The convenience of an associative array is that the values used to 
 * index the elements need not be comparable and their range need not 
 * be known ahead of time.  Furthermore, there is no upper bound on 
 * the size of the structure.  It is able to maintain an arbitrary number 
 * of different pieces of information simultaneously.  Maps are sometimes 
 * called dictionaries because of the uniqueness of the association of
 * words and definitions in a household dictionary.  
 * <P>
 * This class implements methods common to all maps and should be
 * extended by classes that wish to implement the map interface.
 */
public abstract class AbstractMap<K,V> implements Map<K,V>
{
    /**
     * @pre other is a valid map
     * @post adds the map entries of other map into this, possibly
     * replacing value
     */
    public void putAll(Map<K,V> other)
    {
        Iterator<K> i = other.keySet().iterator();
        while (i.hasNext())
        {
            K k = i.next();
            put(k,other.get(k));
        }
    }

    /**
     * Compute the hashCode for elements of this map
     */
    public int hashCode()
    {
        return values().hashCode();
    }
}
