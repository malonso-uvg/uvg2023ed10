// Interface for container classes that manipulated ordered structures.
// (c) 1998, 2001 duane a. bailey

package structure5;

/**
 * An interface that supports a Collection whose values are kept
 * in increasing order.  Values stored within ordered structures
 * should implement Comparable; ie. they should have an implemented
 * compareTo method.
 * 
 * @see java.lang.Comparable
 * @see java.lang.Comparable#compareTo
 * @version $Id: OrderedStructure.java 35 2007-08-09 20:38:38Z bailey $
 * @author, 2001 duane a. bailey
 */
public interface OrderedStructure<K extends Comparable<K>>
         extends Structure<K>
{
}
