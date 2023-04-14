// An implementation of hashtables, using external chaining
// Keys need not be comparable.
// (c) 1998, 2001 duane a. bailey

package structure5;
import java.util.Iterator;
import java.lang.Math;
/**
 * This class implements a hash table whose collisions are resolved
 * through external chaining.  Values used as keys in this structure
 * must have a hashcode method that returns the same value when two
 * keys are "equals".  Initially, a hash table of suggested size is
 * allocated.
 * <P>
 * Example Usage:
 * <P>
 * To create a hashtable by reading a collection of words and 
 * definitions from System.in we could use the following:
 * <P> 
 * <pre>
 * public static void main (String[] argv){
 *      ChainedHashtable dict = new {@link #ChainedHashtable()};
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
 * @version $Id: ChainedHashtable.java 22 2006-08-21 19:27:26Z bailey $
 * @author, 2001 duane a. bailey
 * @see Hashtable
 */
public class ChainedHashtable<K,V> extends AbstractMap<K,V> implements Map<K,V>, Iterable<V>
{
    /**
     * The array of chains used to store values.
     */
    protected Vector<List<Association<K,V>>> data;

    /**
     * The number of key-value pairs stored within the table.
     */
    protected int count;

    /**
     * Constructs a hashtable with capacity for at size elements
     * before chaining is absolutely required.
     *
     * @pre size > 0
     * @post constructs a new ChainedHashtable
     * 
     * @param size The number of entries initially allocated.
     */
    public ChainedHashtable(int size)
    {
        data = new Vector<List<Association<K,V>>>();
        data.setSize(size);
        count = 0;
    }

    /**
     * Constructs a reasonably large hashtable.
     *
     * @post constructs a new ChainedHashtable
     */
    public ChainedHashtable()
    {
        this(997);
    }

    /**
     * Removes the values from the hashtable.
     *
     * @post removes all the elements from the ChainedHashtable
     */
    public void clear()
    {
        int i;
        for (List<Association<K,V>> l : data) {
            if (l != null) l.clear();
        }
        count = 0;
    }

    /**
     * Computes the number of elements stored within the hashtable.
     *
     * @post returns number of elements in hash table
     * 
     * @return The number of elements within the hash table.
     */
    public int size()
    {
        return count;
    }

    /**
     * Returns true if no elements are stored within the table.
     *
     * @post returns true iff hash table has 0 elements
     * 
     * @return True iff size() == 0.
     */
    public boolean isEmpty()
    {
        return size() == 0;
    }

    protected List<Association<K,V>> locate(K key)
    {
        int hash = Math.abs(key.hashCode() % data.size());
        if (data.get(hash) == null) data.set(hash,new SinglyLinkedList<Association<K,V>>());
        return data.get(hash);
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
        for (V v : this) {
            if (value.equals(v)) return true;
        }
        return false;
    }

    /**
     * Returns true iff a specific key appears within the table.
     *
     * @pre value is non-null key
     * @post returns true if key appears in hash table
     * 
     * @param key The key sought.
     * @return True iff the key sought appears within table.
     */
    public boolean containsKey(K key)
    {
        List<Association<K,V>> l = locate(key);
        return l.contains(new Association<K,V>(key,null));
    }

    /**
     * Returns an iterator that traverses over the values of the
     * hashtable.
     *
     * @post returns iterator to traverse hash table
     * 
     * @return A value iterator, over the values of the table.
     */
    public Iterator<V> iterator()
    {
        return new ValueIterator<K,V>(new ChainedHashtableIterator<K,V>(data));
    }

    public Set<K> keySet()
    {
        Set<K> result = new SetList<K>();
        Iterator<K> i = new KeyIterator<K,V>(new ChainedHashtableIterator<K,V>(data));
        while (i.hasNext())
        {
            result.add(i.next());
        }
        return result;
    }

    public Set<Association<K,V>> entrySet()
    {
        Set<Association<K,V>> result = new SetList<Association<K,V>>();
        Iterator<Association<K,V>> i = new ChainedHashtableIterator<K,V>(data);
        while (i.hasNext())
        {
            result.add(i.next());
        }
        return result;
    }

    public Structure<V> values()
    {
        List<V> result = new SinglyLinkedList<V>();
        Iterator<V> i = new ValueIterator<K,V>(new ChainedHashtableIterator<K,V>(data));
        while (i.hasNext())
        {
            result.add(i.next());
        }
        return result;
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
        List<Association<K,V>> l = locate(key);
        Association<K,V> a = l.remove(new Association<K,V>(key,null));
        if (a == null) return null;
        l.addFirst(a);
        return a.getValue();
    }

    /**
     * Get an iterator over the keys of the hashtable.
     *
     * @post returns iterator to traverse the keys of hash table
     * 
     * @return An iterator over the key values appearing within table.
     */
    public Iterator<K> keys()
    {
        return new KeyIterator<K,V>(new ChainedHashtableIterator<K,V>(data));
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
        List<Association<K,V>> l = locate(key);
        Association<K,V> newa = new Association<K,V>(key,value);
        Association<K,V> olda = l.remove(newa);
        l.addFirst(newa);
        if (olda != null)
        {
            return olda.getValue();
        }
        else
        {
            count++;
            return null;
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
        List<Association<K,V>> l = locate(key);
        Association<K,V> pair = l.remove(new Association<K,V>(key,null));
        if (pair == null) return null;
        count--;
        return pair.getValue();
    }

    /**
     * Generate a string representation of the chained hash table.
     *
     * @post returns a string representation of hash table
     * 
     * @return The string representing the table.
     */
    public String toString()
    {
        StringBuffer s = new StringBuffer();
        int i;

        s.append("<ChainedHashtable:");
        Iterator<Association<K,V>> hi = new ChainedHashtableIterator<K,V>(data);
        while (hi.hasNext()) {
            Association<K,V> a = hi.next();
            s.append(" "+a.getKey()+"="+a.getValue());
        }
        s.append(">");
        return s.toString();
    }
}
