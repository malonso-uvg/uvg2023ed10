// A comparator that implements the reverse of another Comparator.
// (c) 2001 duane a. bailey

package structure5;

import java.util.Comparator;
/**
 * Implementation of the {@link java.util.Comparator} interface that
 * provides a {@link #compare(Object,Object)} method that compares
 * two objects using those objects default compareTo methods. Reverse
 * comparator however, reverses the natural comparison as follows:
 * <P>
 * Were we to call compare(a,b), ReverseComparator would return 
 * the following:
 * <pre>
 *     >0 if a < b
 *      0 if a = b
 *     <0 if a > b
 * </pre>
 * <P>
 * Example usage:
 * <P>
 * To print out the equality relationship between two randomly generated integers
 * we could use the following:
 * <pre>
 * public static void main(String[] argv){
 *     Random  rand = new Random();
 *     Comparator c = new {@link #ReverseComparator()};
 *      
 *     //generate two random Integers
 *     Integer a = new Integer(rand.nextInt(100));
 *     Integer b = new Integer(rand.nextInt(100));
 *      
 *     //print out the proper equality relationship between these integers
 *     if(c.{@link #compare(Object,Object) compare(a,b)} > 0) System.out.println("A:" + a + " > B:" + b);
 *     else if (c.{@link #compare(Object,Object) compare(a,b) < 0) System.out.println("A:" + a + " < B:" + b);
 *     else System.out.println("A:" + a + " = B:" + b);
 * }
 *
 * </pre>
 * @author, 2001 duane a. bailey
 */
public class ReverseComparator<E extends Comparable<E>>
                  implements Comparator<E>
{
    protected Comparator<E> base; // comparator whose ordering is reversed

    /**
     * Construct a comparator that generates reverse natural comparison
     * @post constructs a comparator that orders in reverse order
     */
    public ReverseComparator()
    {
        base = new NaturalComparator<E>();
    }

    /**
     * Construct a comparator that generates reverse of another comparator
     * @param base the ordering to be reversed
     * @post constructs a Comparator that orders in reverse order of base
     */
    public ReverseComparator(Comparator<E> base)
    {
        this.base = base;
    }

    /**
     * Compare two values, a and b.  Simply calls the default
     * compareTo method for a on b.
     * @param a object performing the compare
     * @param b the object being compared
     * @pre a, b non-null, and b is of type of a
     * @post returns value <, ==, > 0 if a <, ==, > b
     * @return value <, ==, > 0 if a <, ==, > b using a.compareTo
     */
    public int compare(E a, E b)
    {
        return -base.compare(a,b);
    }

    /**
     * Returns true if the other object is a NaturalComparator.
     * @param b a possible NaturalComparator
     * @post returns true if b is a NaturalComparator
     * @return true if b is a NaturalComparator
     */
    public boolean equals(Object b)
    {
        if (b == null) return false;
        if (!(b instanceof ReverseComparator)) return false;
        ReverseComparator<?> that = (ReverseComparator<?>)b;
        return this.base.equals(that.base);
    }
}
