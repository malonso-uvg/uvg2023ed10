// A start at the implementation of a Set.
// (c) 1998, 2001 duane a. bailey

package structure5;
import java.util.Iterator;

/**
 * Methods common to an Implementation of a set of elements irrespective
 * of the underlying storage mechanism.
 * As with the mathematical object, the elements of the set are
 * not duplicated.  No order is implied or enforced in this structure, but
 * simple set operations such as intersection, union, difference, and subset
 * are provided. 
 * <P>
 * Example Usage:
 * Given a list of students who completed a computer science thesis in the
 * 2001-2002 academic year at Williams College and a list of graduating 
 * computer science majors who are continuing on to graduate school, we could
 * determine which thesis students are planning to attend graduate school
 * as follows:
 * <P>
 * <pre>
 * public static void main(String[] argv){
 *      //thesis students in the class of '02
 *      String[] thesis = new String[]{"Doug", "Evan", "Feng"};
 *      
 *      //students continuing on to grad school
 *      String[] grad = new String[]{"Doug", "Feng", "Lida"};
 *
 *      //instantiate our sets
 *      Set thesisSet = new {@link structure.SetVector#SetVector()}, 
 *          gradSet = new {@link structure.SetVector#SetVector()};
 *              
 *      //build sets up
 *      for(int i = 0; i < thesis.length; i++) thesisSet.{@link #add(Object) add(thesis[i])};
 *      for(int i = 0; i < grad.length; i++) gradSet.{@link #add(Object) add(grad[i])};
 *      
 *      //calculate the intersection of the two sets
 *      thesisSet.{@link #retainAll(Structure) retainAll(gradSet)};
 *      System.out.println(thesisSet);
 * }
 * </pre>
 *
 * @version $Id: AbstractSet.java 22 2006-08-21 19:27:26Z bailey $
 * @author, 2001 duane a. bailey
 */
public abstract class AbstractSet<E> extends AbstractStructure<E> implements Set<E>
{
    /**
     * Union other set into this set.
     * @pre other is non-null
     * @post values from other are added into this set
     */
    public void addAll(Structure<E> other)
    {
        Iterator<E> i = other.iterator();
        while (i.hasNext())
        {
            add(i.next());
        }
    }

    /**
     * Check to see if this set is contained in the other structure.
     * @pre other is non-null
     * @post returns true if every value in this set is contained in the
     *       other
     */
    public boolean containsAll(Structure<E> other)
    {
        Iterator<E> i = other.iterator();
        while (i.hasNext())
        {
            if (!contains(i.next())) return false;
        }
        return true;
    }

    /**
     * Computes the difference between this set and the other structure
     * @pre other is non-null
     * @post values of this set contained in other are removed
     */
    public void removeAll(Structure<E> other)
    {
        Iterator<E> i = other.iterator();
        while (i.hasNext())
        {
            remove(i.next());
        }
    }

    /**
     * Computes the intersection between this set and the other structure.
     * @pre other is non-null
     * @post values not appearing in the other structure are removed
     */
    public void retainAll(Structure<E> other)
    {
        List<E> drop = new SinglyLinkedList<E>();
        Iterator<E> i = other.iterator();
        while (i.hasNext())
        {
            E o = i.next();
            if (!other.contains(o)) drop.add(o);
        }
        while (!drop.isEmpty())
        {
            remove(drop.removeFirst());
        }
    }
}
