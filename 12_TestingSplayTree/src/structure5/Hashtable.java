// An implementation of Dictionaries, using hash tables. 
// Keys need not be comparable, but they must have hashcode methods.
// (c) 1998, 2001 duane a. bailey

package structure5;
import java.util.Iterator;
import java.lang.Iterable;
import java.lang.Math;
/**
 * Implements a dictionary as a table of hashed key-value pairs.
 * Collisions are resolved through linear probing.  Values used
 * as keys in this structure must have a hashcode method that returns
 * the same value when two keys are "equals".  Initially, a table of suggested
 * size is allocated.  It will be expanded as the load factor (ratio of
 * pairs to entries) grows to meet maximumLoadFactor.
 * <P>
 * Example Usage:
 * <P>
 * To create a hashtable by reading a collection of words and 
 * definitions from System.in we could use the following:
 * <P> 
 * <pre>
 * public static void main (String[] argv){
 *      Hashtable dict = new {@link #Hashtable()};
 *      ReadStream r = new ReadStream();
 *      String word, def;
 *      System.out.println("Enter a word: ");
 *      while(!r.eof()){
 *          word = r.readLine();
 *          System.out.println("Enter a definition: ");
 *          def = r.readLine();
 *          dict.{@link #put(Object,Object) put(word,def)};
 *          System.out.println("Enter a word: ");
 *      }
 *      System.out.println(dict);
 * }
 * </pre>
 * @version $Id: Hashtable.java 34 2007-08-09 14:43:44Z bailey $
 * @author, 2001 duane a. bailey
 * @see ChainedHashtable
 */
public class Hashtable<K,V> implements Map<K,V>, Iterable<V>
{
    /**
     * A single key-value pair to be used as a token
     * indicating a reserved location in the hashtable.
     * Reserved locations are available for insertion,
     * but cause collisions on lookup.
     */
    protected static final String RESERVED = "RESERVED";
    /**
     * The data associated with the hashtable.
     */
    protected Vector<HashAssociation<K,V>> data;
    /**
     * The number of key-value pairs in table.
     */
    protected int count;

    /**
     * The maximum load factor that causes rehashing of the table.
     */
    protected final double maximumLoadFactor = 0.6;

    /**
     * Construct a hash table that is capable of holding at least
     * initialCapacity values.  If that value is approached, it will
     * be expanded appropriately.  It is probably best if the capacity
     * is prime.  Table is initially empty.
     *
     * @pre initialCapacity > 0
     * @post constructs a new Hashtable
     *       holding initialCapacity elements
     * 
     * @param initialCapacity The initial capacity of the hash table.
     */
    public Hashtable(int initialCapacity)
    {
        Assert.pre(initialCapacity > 0, "Hashtable capacity must be positive.");
        data = new Vector<HashAssociation<K,V>>();
        data.setSize(initialCapacity);
        count = 0;
    }

    /**
     * Construct a hash table that is initially empty.
     *
     * @post constructs a new Hashtable
     */
    public Hashtable()
    {
        this(997);
    }

    /**
     * Remove all key-value pairs from hashtable.
     *
     * @post removes all elements from Hashtable
     */
    public void clear()
    {
        int i;
        for (i = 0; i < data.size(); i++) {
            data.set(i,null);
        }
        count = 0;
    }

    /**
     * Return the number of key-value pairs within the table.
     *
     * @post returns number of elements in hash table
     * 
     * @return The number of key-value pairs currently in table.
     */
    public int size()
    {
        return count;
    }

    /**
     * Determine if table is empty.
     *
     * @post returns true iff hash table has 0 elements
     * 
     * @return True if table is empty.
     */
    public boolean isEmpty()
    {
        return size() == 0;
    }

    /**
     * Returns true if a specific value appears within the table.
     *
     * @pre value is non-null Object
     * @post returns true iff hash table contains value
     * 
     * @param value The value sought.
     * @return True iff the value appears within the table.
     */
    public boolean containsValue(V value)
    {
        for (V tableValue : this) {
            if (tableValue.equals(value)) return true;
        }
        // no value found
        return false;
    }

    /**
     * Returns true iff a specific key appears within the table.
     *
     * @pre key is a non-null Object
     * @post returns true if key appears in hash table
     * 
     * @param key The key sought.
     * @return True iff the key sought appears within table.
     */
    public boolean containsKey(K key)
    {
        int hash = locate(key);
        return data.get(hash) != null && !data.get(hash).reserved();
    }   

    /**
     * Returns a traversal that traverses over the values of the
     * hashtable.
     *
     * @post returns traversal to traverse hash table
     * 
     * @return A value traversal, over the values of the table.
     */
    public Iterator<V> iterator()
    {
        return new ValueIterator<K,V>((AbstractIterator<Association<K,V>>)new HashtableIterator<K,V>(data));
    }

    /**
     * Get the value associated with a key.
     *
     * @pre key is non-null Object
     * @post returns value associated with key, or null
     * 
     * @param key The key used to find the desired value.
     * @return The value associated with the desired key.
     */
    public V get(K key)
    {
        int hash = locate(key);
        if (data.get(hash) == null ||
            data.get(hash).reserved()) return null;
        return data.get(hash).getValue();
    }

    /**
     * Get a traversal over the keys of the hashtable.
     *
     * @post returns traversal to traverse the keys of hash table;
     * 
     * @return a traversal over the key values appearing within table.
     */
    public Iterator<K> keys()
    {
        return new KeyIterator<K,V>(new HashtableIterator<K,V>(data));
    }

    protected int locate(K key)
    {
        // compute an initial hash code
        int hash = Math.abs(key.hashCode() % data.size());
        // keep track of first unused slot, in case we need it
        int reservedSlot = -1;
        boolean foundReserved = false;
        while (data.get(hash) != null)
        {
            if (data.get(hash).reserved()) {
                // remember reserved slot if we fail to locate value
                if (!foundReserved) {
                    reservedSlot = hash;
                    foundReserved = true;
                }
            } else  {
                // value located? return the index in table
                if (key.equals(data.get(hash).getKey())) return hash;
            }
            // linear probing; other methods would change this line:
            hash = (1+hash)%data.size();
        }
        // return first empty slot we encountered
        if (!foundReserved) return hash;
        else return reservedSlot;
    }

    /**
     * Place a key-value pair within the table.
     *
     * @pre key is non-null object
     * @post key-value pair is added to hash table
     * 
     * @param key The key to be added to table.
     * @param value The value associated with key.
     * @return The old value associated with key if previously present.
     */
    public V put(K key, V value)
    {
        if (maximumLoadFactor*data.size() <= (1+count)) {
            extend();
        }
        int hash = locate(key);
        if (data.get(hash) == null || data.get(hash).reserved())
        {   // logically empty slot; just add association
            data.set(hash,new HashAssociation<K,V>(key,value));
            count++;
            return null;
        } else {
            // full slot; add new and return old value
            HashAssociation<K,V> a = data.get(hash);
            V oldValue = a.getValue();
            a.setValue(value);
            return oldValue;
        }
    }
    /**
     * Put all of the values found in another map into this map,
     * overriding previous key-value associations.
     * @param other is the source mapping
     * @pre other map is valid
     * @post this hashtable is augmented by the values found in other
     */
    public void putAll(Map<K,V> other)
    {
        for (Association<K,V> e : other.entrySet())
        {
            put(e.getKey(),e.getValue());
        }
    }


    /**
     * Remove a key-value pair from the table.
     *
     * @pre key is non-null object
     * @post removes key-value pair associated with key
     * 
     * @param key The key of the key-value pair to be removed.
     * @return The value associated with the removed key.
     */
    public V remove(K key)
    {
        int hash = locate(key);
        if (data.get(hash) == null || data.get(hash).reserved()) {
            return null;
        }
        count--;
        V oldValue = data.get(hash).getValue();
        data.get(hash).reserve(); // in case anyone depends on us
        return oldValue;
    }

    /**
     * @post expands the hashtable to reduce loading
     */
    protected void extend()
    {
        // extends the hashtable for larger capacity.
        int i;
        AbstractIterator<Association<K,V>> it = new HashtableIterator<K,V>(data);
        // BE AWARE: at this point, we can change the hash table,
        // but changes to the hashtable traversal implementation might
        // be problematic.
        int newSize = 2*data.size();
        Assert.condition(newSize > 0, "Hashtable vector size must be greater than 0.");
        data = new Vector<HashAssociation<K,V>>();
        data.setSize(newSize);
        count = 0;
        while (it.hasNext())
        {
            Association<K,V> a = it.next();
            put(a.getKey(),a.getValue());
        }
    }

    /**
     * @post returns a set of Associations associated with this Map
     */
    public Set<Association<K,V>> entrySet()
    {
        Set<Association<K,V>> result = new SetList<Association<K,V>>();
        Iterator<Association<K,V>> i = new HashtableIterator<K,V>(data);
        while (i.hasNext())
        {
            result.add(i.next());
        }
        return result;
    }

    /**
     * @post returns a Set of keys used in this Map
     */
    public Set<K> keySet()
    {
        Set<K> result = new SetList<K>();
        Iterator<K> i = new KeyIterator<K,V>(new HashtableIterator<K,V>(data));
        while (i.hasNext())
        {
            result.add(i.next());
        }
        return result;
    }

    /**
     * @post returns a Structure that contains the (possibly repeating) 
     * values of the range of this map.
     */
    public Structure<V> values()
    {
        List<V> result = new SinglyLinkedList<V>();
        Iterator<V> i = new ValueIterator<K,V>(new HashtableIterator<K,V>(data));
        while (i.hasNext())
        {
            result.add(i.next());
        }
        return result;
    }


    /**
     * Generate a string representation of the hash table.
     *
     * @post returns a string representation of hash table
     * 
     * @return The string representing the table.
     */
    public String toString()
    {
        StringBuffer s = new StringBuffer();
        int i;

        s.append("<Hashtable: size="+size()+" capacity="+data.size());
        Iterator<Association<K,V>> hi = new HashtableIterator<K,V>(data);
        while (hi.hasNext()) {
            Association<K,V> a = hi.next();
            s.append(" key="+a.getKey()+", value="+a.getValue());
        }
        s.append(">");
        return s.toString();
    }
}
