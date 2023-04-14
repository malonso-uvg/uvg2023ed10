// An implementation of an OrderedDictionary.
// (c) 1998, 2001 duane a. bailey

package structure5;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * An implementation of an ordered dictionary.  Key-value pairs are 
 * kept in the structure in order.  To accomplish this, the keys of the
 * table must be comparable.
 * <P>
 * Example Usage:
 * <P>
 * To create an alphebetized dictionary by reading a collection of words and 
 * definitions from System.in we could use the following:
 * <P> 
 * <pre>
 * public static void main (String[] argv){
 *      {@link OrderedMap} dict = new {@link #Table()};
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
 * @version $Id: Table.java 22 2006-08-21 19:27:26Z bailey $
 * @author, 2001 duane a. bailey
 * @see Comparable
 */
public class Table<K extends Comparable<K>,V> extends AbstractMap<K,V> implements OrderedMap<K,V>
{
    /**
     * An ordered structure that maintains the ComparableAssociations
     * that store the key-value pairings.
     */
    protected OrderedStructure<ComparableAssociation<K,V>> data;

    /**
     * Construct a new, empty table.
     *
     * @post constructs a new table
     */
    public Table()
    {
        data = new SplayTree<ComparableAssociation<K,V>>();
    }

    public Table(Table<K,V> other)
    {
        data = new SplayTree<ComparableAssociation<K,V>>();
        Iterator<Association<K,V>> i = other.entrySet().iterator();
        while (i.hasNext())
        {
            Association<K,V> o = i.next();
            put(o.getKey(),o.getValue());
        }
    }

    /**
     * Retrieve the value associated with the key provided.
     * Be aware, the value may be null.
     *
     * @pre key is a non-null object
     * @post returns value associated with key, or null
     * 
     * @param key The key of the key-value pair sought.
     * @return The value associated with the key.
     */
    public V get(K key)
    {
        ComparableAssociation<K,V> ca =
            new ComparableAssociation<K,V>(key,null);
        ComparableAssociation<K,V> result = data.remove(ca);
        if (result == null) return null;
        data.add(result);
        return result.getValue();
    }

    /**
     * Enter a key-value pair into the table.  if the key is already
     * in the table, the old value is returned, and the old key-value
     * pair is replaced.  Otherwise null is returned.  The user is cautioned
     * that a null value returned may indicate there was no prior key-value
     * pair, or --- if null values are inserted --- that the key was 
     * previously associated with a null value.
     *
     * @pre key is non-null object
     * @post key-value pair is added to table
     * 
     * @param key The unique key in the table.
     * @param value The (possibly null) value associated with key.
     * @return The prior value, or null if no prior value found.
     */
    public V put(K key, V value)
    {
        ComparableAssociation<K,V> ca = 
            new ComparableAssociation<K,V>(key,value);
        // fetch old key-value pair
        ComparableAssociation<K,V> old = data.remove(ca);
        // insert new key-value pair
        data.add(ca);
        // return old value
        if (old == null) return null;
        else return old.getValue();
    }
    
    /**
     * Determine if the table is empty.
     *
     * @post returns true iff table is empty
     * 
     * @return True iff the table has no elements.
     */
    public boolean isEmpty()
    {
        return data.isEmpty();
    }

    /**
     * Remove all the elements of the table.
     *
     * @post removes all elements from the table
     */
    public void clear()
    {
        data.clear();
    }

    /**
     * Construct an iterator over the keys of the table.
     * The order of the keys returned is in ascending order.  It will
     * be consistent with that of the iterator from elements, provided
     * the table is not modified.
     *
     * @post returns an iterator for traversing keys of table
     * 
     * @return An iterator over the keys of the table.
     */
    public Iterator<K> keys()
    {
        return new KeyIterator<K,V>(data.iterator());
    }

    /**
     * Construct an iterator over the values of the table.
     * The order of the values returned is determined by order of keys. It will
     * be consistent with that of the iterator returned from keys, provided
     * the table is not modified.
     *
     * @post returns an iterator for traversing values in table
     * 
     * @return An iterator over the values of the table.
     */
    public Iterator<V> iterator()
    {
        return new ValueIterator<K,V>(data.iterator());
    }

    /**
     * Determine if the key is in the table.  The key should
     * not be null.
     *
     * @pre key is non-null object
     * @post returns true iff key indexes a value in table
     * 
     * @param key A non-null key sought in the table.
     * @return True iff the key is used in association with some value.
     */
    public boolean containsKey(K key)
    {
        ComparableAssociation<K,V> a =
            new ComparableAssociation<K,V>(key,null);
        return data.contains(a);
    }

    /**
     * Returns true if the value is associated with some key in the
     * table.  This is often difficult to implement efficiently.
     *
     * @pre value is non-null object
     * @post returns true iff value in table
     * 
     * @param value The value sought (possibly null).
     * @return True, if the value is associated with some key in table.
     */
    public boolean containsValue(V value)
    {
        Iterator<V> i = iterator();
        while (i.hasNext())
        {
            V nextValue = i.next();
            if (nextValue != null &&
                nextValue.equals(value)) return true;
        }
        return false;
    }

    
    /**
     * Remove a key-value pair, based on key.  The value is returned.
     *
     * @pre key is non-null object
     * @post removes value indexed in table
     * 
     * @param key The key of the key-value pair to be removed.
     * @return The value associated with key, no longer in table.
     */
    public V remove(K key)
    {
        ComparableAssociation<K,V> target = 
            new ComparableAssociation<K,V>(key,null);
        target = data.remove(target);
        if (target == null) return null;
        else return target.getValue();
    }

    /**
     * Determine the number of key-value pairs within the table.
     *
     * @post returns number of key-value pairs in table
     * 
     * @return The number of key-value pairs in the table.
     */
    public int size()
    {
        return data.size();
    }

    /**
     * Return a set containing the keys referenced
     * by this data structure.
     * 
     * @return a set containing the key referenced
     * by this data structure.
     * @post Returns a set containing the keys referenced
     * by this data structure.
     */
    public Set<K> keySet()
    {
        Set<K> result = new SetList<K>();
        Iterator<K> i = new KeyIterator<K,V>(data.iterator());
        while (i.hasNext())
        {
            result.add(i.next());
        }
        return result;
    }

    /**
     * Return a structure containing all the values referenced
     * by this data structure.
     * 
     * @return a structure containing all the values referenced
     * by this data structure.
     * @post Returns a structure containing all the values referenced
     * by this data structure.
     */
    public Structure<V> values()
    {
        List<V> result = new SinglyLinkedList<V>();
        Iterator<V> i = new ValueIterator<K,V>(data.iterator());
        while (i.hasNext())
        {
            result.add(i.next());
        }
        return result;
    }
 
    /**
     * Return a structure containing all the entries in 
     * this Table
     * 
     * @return a structure containing all the entries in 
     * this Table
     * @post Returns a structure containing all the entries in 
     * this Table
     */
    public Set<Association<K,V>> entrySet()
    {
        Set<Association<K,V>> result = new SetList<Association<K,V>>();
        Iterator<ComparableAssociation<K,V>> i = data.iterator();
        while (i.hasNext())
        {
            result.add(i.next());
        }
        return result;
    }
    


    /**
     * Construct a string representing value of table.
     *
     * @post returns string representation
     * 
     * @return String representing table.
     */
    public String toString()
    {
        StringBuffer s = new StringBuffer();
        s.append("<Table: size="+size());
        Iterator<ComparableAssociation<K,V>> ti = data.iterator();
        while (ti.hasNext()) {
            ComparableAssociation<K,V> ca = ti.next();
            s.append(" key="+ca.getKey()+", value="+ca.getValue());
        }
        s.append(">");
        return s.toString();
    }
        public static void main (String[] argv){
        OrderedMap<String,String> dict = new Table<String,String>();
        ReadStream r = new ReadStream();
        String word, def;
        System.out.println("Enter a word: ");
        while(!r.eof()){
            word = r.readLine();
            System.out.println("Enter a definition: ");
            def = r.readLine();
            dict.put(word,def);
            System.out.println("Enter a word: ");
        }
        System.out.println(dict);
    }
}
