// A simple bitset class.
// (c) 1998, 2001 duane a. bailey

package structure5;

/**
 * Implementation of a set of numbered bits.  This class's interface
 * differs from the {@link structure.Set}, {@link java.util.Bitset},
 * and {@link java.util.Set} interfaces, so care must be taken to
 * invoke the proper methods. 
 *
 * @version $Id: BitSet.java 22 2006-08-21 19:27:26Z bailey $
 * @author, 2001 duane a. bailey
 * @see structure.CharSet
 * @see java.util.BitSet
 */
public class BitSet
{
    /**
     * The number of bits contained in a single integer.
     */
    protected final int bitsPerInt = 32;
    /**
     * The initial capacity of the set, by default.
     */
    protected final int initialCapacity = 256;
    /**
     * The array of integers that contains the set's bits
     */
    protected int data[];
    /**
     * The current number of integers allocated.
     */
    protected int allocated;

    /**
     * Constructs an empty bitset.
     *
     * @post constructs an empty set of small integers
     */
    public BitSet()
    {   
        clear(initialCapacity);
    }

    /**
     * Constructs an empty bitset with potential to hold values between
     * 0..count-1.
     *
     * @post constructs an empty set with count potential elements
     * 
     * @param count The number of distinct values possibly in set.
     */
    public BitSet(int count)
    {   
        clear(count);
    }

    /**
     * Adds a bit to the bitset, if not already there.
     * Set is potentially extended.
     *
     * @pre i >= 0
     * @post i is added to the set
     * 
     * @param i  The number of  the bit to be added.
     */
    public void add(int i)
    {
        extend(i);
        int index = indexOf(i);
        int offset = offsetOf(i);
        data[index] |= 1<<offset;
    }

    /**
     * Remove bit i from the bitset.
     *
     * @pre i >= 0
     * @post removes i from set if present
     * 
     * @param i The index of the bit to be removed.
     */
    public void remove(int i)
    {
        if (probe(i)) {
            int index = indexOf(i);
            int offset = offsetOf(i);
            data[index] &= ~(1<<offset);
        }
    }
    /**
     * Determine if a bit is a member of the set.
     *
     * @pre i >= 0
     * @post returns true iff i in set
     * 
     * @param i The bit index of potential bit.
     * @return True iff bit i is in the set.
     */
    public boolean contains(int i)
    {
        return probe(i) && (0 != (data[indexOf(i)] & (1<<offsetOf(i))));
    }

    /**
     * Remove all bits from the set.
     *
     * @post removes all values from set
     */
    public void clear()
    {
        clear(initialCapacity);
    }

    /**
     * Remove bits from set; set size to count.
     *
     * @post removes all values from set, sets set size to count
     * 
     * @param count The new capacity of the newly empty set.
     */
    public void clear(int count)
    {
        int i;
        allocated = (count+bitsPerInt-1)/bitsPerInt;
        data = new int[allocated];
        for (i = 0; i < allocated; i++)
        {
            data[i] = 0;
        }
    }

    /**
     * Returns a copy of the set.
     *
     * @post constructs a copy of the set
     * 
     * @return A new BitSet with the same values as this.
     */
    public Object clone()
    {
        BitSet duplicate = new BitSet(allocated*bitsPerInt);
        int i;
        for (i = 0; i < allocated; i++)
        {
            duplicate.data[i] = data[i];
        }
        return duplicate;
    }

    /**
     * Compute a new set that is the union of this set and other.
     * Elements of the new set appear in at least one of the two sets.
     *
     * @pre other is non-null
     * @post constructs set w/elements from this and other
     * 
     * @param other The set to be unioned with this.
     * @return The union of the two sets.
     */
    public Object union(BitSet other)
    {
        int leftSize = allocated;
        int rightSize = other.allocated;
        if (leftSize < rightSize) return other.union(this);
        BitSet result = new BitSet(leftSize*bitsPerInt);
        int i;
        for (i = 0; i < rightSize; i++)
        {
            result.data[i] = data[i] | other.data[i];
        }
        for (;i < leftSize; i++)
        {
            result.data[i] = data[i];
        }
        return result;
    }

    /**
     * Return the intersection of this set and the other.
     * A bit is in the result if it is in this set and other.
     *
     * @pre other is not null
     * @post constructs set w/elements in this and other
     * 
     * @param other The other set to be intersected with this.
     */
    public Object intersection(BitSet other)
    {
        int leftSize = allocated;
        int rightSize = other.allocated;
        if (leftSize < rightSize) return other.intersection(this);
        BitSet result = new BitSet(rightSize*bitsPerInt);
        int i;
        for (i = 0; i < rightSize; i++)
        {
            result.data[i] = data[i] & other.data[i];
        }
        return result;
    }

    /**
     * Computes the difference between this set and the other.
     * An element is in the difference if it is in this, but not in other.
     *
     * @pre other is not null
     * @post constructs set w/elements from this but not other
     * 
     * @param other The difference between this set and other.
     */
    public Object difference(BitSet other)
    {
        int leftSize = allocated;
        int rightSize = other.allocated;
        BitSet result = new BitSet(leftSize*bitsPerInt);
        int i;
        if (leftSize <= rightSize) {
            for (i = 0; i < leftSize; i++)
            {
                result.data[i] = data[i] & ~other.data[i];
            }
        } else {
            for (i = 0; i < rightSize; i++)
            {
                result.data[i] = data[i] & ~other.data[i];
            }
            for (i = rightSize; i < leftSize; i++)
            {
                result.data[i] = data[i];
            }
        }
        return result;
    }

    /**
     * Returns true iff this set is a subset of the other.
     * A set is a subset of another if its elements are elements
     * of the other.
     *
     * @pre other is not null
     * @post returns true iff elements of this are all in other
     * 
     * @param other The potential superset.
     * @return The difference between this and other.
     */
    public boolean subset(BitSet other)
    {
        int leftSize = allocated;
        int rightSize = other.allocated;
        int i;
        if (leftSize <= rightSize) {
            for (i = 0; i < leftSize; i++)
            {
                if (0 != (data[i] & ~other.data[i])) return false;
            }
        } else {
            for (i = 0; i < rightSize; i++)
            {
                if (0 != (data[i] & ~other.data[i])) return false;
            }
            for (i = rightSize; i < leftSize; i++)
            {
                if (0 != data[i]) return false;
            }
        }
        return true;
    }

    /**
     * Determine if a set is empty.
     *
     * @post returns true iff this set is empty
     * 
     * @return True iff this set is empty.
     */
    public boolean isEmpty()
    {
        int i;
        for (i = 0; i < allocated; i++) {
            if (data[i] != 0) return false;
        }
        return true;
    }

    /**
     * Return true iff this set and o contain the same elements.
     *
     * @pre o is not null
     * @post returns true iff this and o have same elements
     * 
     * @param o Another non-null bitset. 
     * @return True iff this set has the same elements as o.
     */
    public boolean equals(Object o)
    {
        BitSet other = (BitSet)o;
        int leftSize = allocated;
        int rightSize = other.allocated;
        if (leftSize < rightSize) return other.equals(this);
        int i;
        for (i = 0; i < rightSize; i++) {
            if (data[i] != other.data[i]) return false;
        }
        for (i = rightSize; i < leftSize; i++) {
            if (data[i] != 0) return false;
        }
        return true;
    }

    /**
     * Determine the int index associated with a bit number.
     *
     * @pre bit >= 0
     * @post returns index of integer containing bit b
     * 
     * @return the index in array of bit b.
     */
    protected int indexOf(int b)
    {
        return b/bitsPerInt;
    }   

    /**
     * Return the bit index within the associated int of bit "bit"
     *
     * @pre bit >= 0
     * @post returns bit position of bit in word
     *
     * @param bit The index of the bit in set.
     * @return The index of the bit desired, within the word.
     */
    protected int offsetOf(int bit)
    {
        return bit%bitsPerInt;
    }

    /**
     * Ensures that bit "bit" is within capacity of set.
     *
     * @pre bit >= 0
     * @post ensures set is large enough to contain bit
     */
    protected void extend(int bit)
    {
        if (!probe(bit)) {
            int index = indexOf(bit);
            int newData[];
            int newAllocated = allocated;
            int i;
            while (newAllocated <= index) newAllocated *= 2;
            newData = new int[newAllocated];
            for (i = 0; i < allocated; i++) {
                newData[i] = data[i];
            }
            for (i = allocated; i < newAllocated; i++) {
                newData[i] = 0;
            }
            data = newData;
            allocated = newAllocated;
        }
    }


    /**
     * Determines if bit is within capacity of set.
     *
     * @pre bit >= 0
     * @post Returns rue if set is large enough to contain bit
     * 
     * @param bit The index of desired bit.
     * @return True if index of bit is within array.
     */
    protected boolean probe(int bit)
    {
        int index = indexOf(bit);
        return data.length > index;
    }

    /**
     * Constructs string representing set.
     *
     * @post returns string representation of set
     * 
     * @return String representing bitset.
     */
    public String toString()
    {
        StringBuffer s = new StringBuffer();
        int i;
        s.append("<BitSet:");
        for (i = 0; probe(i); i++) {
            if (contains(i)) s.append(" "+Integer.toString(i));
        }
        s.append(">");
        return s.toString();
    }
}
