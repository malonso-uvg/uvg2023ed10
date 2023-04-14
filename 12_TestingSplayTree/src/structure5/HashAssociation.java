// An implemention of an association that can be marked "reserved".
// Keys are not comparable.
// (c) 2006 duane a. bailey

package structure5;

/**
 * Implements an association that can be marked "reserved".  Reserved
 * associations can not be read without exception.  Unreserved associations
 * act normally.
 *
 * @version $Id: HashAssociation.java 22 2006-08-21 19:27:26Z bailey $
 * @author 2006 duane a. bailey
 * @see Hashtable, Association
 */
public class HashAssociation<K,V> extends Association<K,V>
{
    protected boolean reserved;
    public HashAssociation(K key, V value)
    {
        super(key,value);
        reserved = false;
    }

    public HashAssociation(K key)
    {
        this(key,null);
    }

    public V getValue()
    {
        Assert.pre(!reserved, "Reserved HashAssociations may not be read.");
        return super.getValue();
    }

    public K getKey()
    {
        Assert.pre(!reserved, "Reserved HashAssociations may not be read.");
        return super.getKey();
    }

    public V setValue(V value)
    {
        Assert.pre(!reserved, "Reserved HashAssociations may not be written.");
        return super.setValue(value);
    }

    public boolean reserved()
    {
        return reserved;
    }

    public void reserve()
    {
        Assert.pre(!reserved,"HashAssociation reserved twice.");
        reserved = true;
    }
    public String toString()
    {
        StringBuffer s = new StringBuffer();
        if (reserved()) {
            s.append("<ReservedAssociation: RESERVED>");
        } else {
            s.append("<ReservedAssociation: "+getKey()+"="+getValue()+">");
        }
        return s.toString();
    }
}

