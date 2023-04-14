// This is an implementation of binary search trees.
// (c) 1998, 2001 duane a. bailey
package structure5;
import java.util.Iterator;
import java.util.Comparator;
import java.util.Random;

/**
 * Red black trees, are binary trees that guarantee the following three properties. <BR>
 * 1. Every child of every leaf is considered black<BR> 
 * 2. Every red node has two black children<BR>
 * 3. Every path from a node to a descendant leaf contains the same
 *    number of black nodes. <BR>
 * <P>
 * These properties ensure that elements can be inserted, deleted, and 
 * located in logorithmic time. 
 * <P>
 * Example usage:
 * <P>
 * To create a red-black tree containing the months of the year
 * and to print out this tree as it grows we could use the following
 * <P>
 * <pre>
 * public static void main(String[] argv){
 *     RedBlackSearchTree test = new {@link #RedBlackSearchTree()};
 *       
 *     //declare an array of months
 *     String[] months = new String[]{"March", "May", "November", "August", 
 *                                    "April", "January", "December", "July",
 *                                    "February", "June", "October", "September"};
 *      
 *     //add the months to the tree and print out the tree as it grows
 *     for(int i=0; i < months.length; i++){
 *        test.{@link #add(Object) add(months[i])};
 *        System.out.println("Adding: " + months[i] + "\n" +test.{@link #treeString()});
 *      }
 *  }
 * </pre>
 *
 * @version $Id: RedBlackSearchTree.java 22 2006-08-21 19:27:26Z bailey $
 * @author, 2001 duane a. bailey & evan s. sandhaus
 * @see AVLTree
 * @see SplayTree
 * @see BinaryTree
 */
public class RedBlackSearchTree<E extends Comparable<E>> extends AbstractStructure<E> implements OrderedStructure<E>
{
    /**
     * A reference to the root of the tree
     */
    protected RedBlackTree<E> root;

    /**
     * The number of nodes in the tree
     */
    protected int count;

    /**
     * Constructs a red-black search tree with no data
     * @post Constructs an empty red-black tree
     */
    public RedBlackSearchTree()
    {
        root = new RedBlackTree<E>();
        count = 0;
    }
    
    /**
     * Checks for an empty binary search tree
     *
     * @post Returns true iff the binary search tree is empty
     * @return True iff the tree contains no data
     */
    public boolean isEmpty()
    {
        return root.isEmpty();
    }

    /**
     * Removes all data from the binary search tree
     *
     * @post Removes all elements from binary search tree
     */
    public void clear()
    {
        root = new RedBlackTree<E>();
        count = 0;
    }

    /**
     * Determines the number of data values within the tree
     *
     * @post Returns the number of elements in binary search tree
     * 
     * @return The number of nodes in the binary search tree
     */
    public int size()
    {
        return count;
    }
    
    /**
     * Add a (possibly duplicate) value to the red-black  tree, and ensure
     * that the resulting tree is a red-black tree.
     * 
     * @post Adds a value to binary search tree
     * @param val A reference to non-null object
     */
    public void add(E value)
    {
        //Assert.pre(value instanceof Comparable,"value must implement Comparable");
        root = root.add(value);
        count++;
    }
   
    /**
     * Remove an value "equals to" the indicated value.  Only one value
     * is removed, and no guarantee is made concerning which of duplicate
     * values are removed.  Value returned is no longer part of the
     * structure
     *
     * @post Removes one instance of val, if found
     * 
     * @param val Value sought to be removed from tree
     * @return Value to be removed from tree or null if no value removed
     */
    public E remove(E value){
        // Assert.pre(value instanceof Comparable,"value must implement Comparable");
        if (root.contains(value)){
            root = root.remove(value);
            count--;
            return value;
        }
        return null;
    }

    /**
     * Determines if the red-black search tree contains a value
     *
     * @post Returns true iff val is a value found within the tree
     * 
     * @param val The value sought.  Should be non-null
     * @return True iff the tree contains a value "equals to" sought value
     */
    public boolean contains(E value){
        //Assert.pre(value instanceof Comparable,"value must implement Comparable");
        return root.contains(value);
    }
    
    /**
     * Returns true iff this tree is a red-black tree.
     * <font color="#FF0000">WARNING:</font> This method executes in linear time
     * and should not be frequently called during the process of insertion and 
     * deletion if the user wants
     * @return True iff this tree is a red-black tree.
     */
    public boolean isRedBlack()
    {
        return root.consistency();
    }
  
    /**
     * Returns an iterator over the red-black search tree.  Iterator should
     * not be used if tree is modified, as behavior may be unpredicatable
     * Traverses elements using in-order traversal order
     *
     * @post Returns iterator to traverse red-blackST
     * @return An iterator over red-black search tree
     */
    public Iterator<E> iterator()
    {
        return root.iterator();
    }

    /**
     * Returns a (possibly long) string representing tree.  Differs
     * from {@link #toString()} in that {@link #toString()} outputs 
     * a single line representation of the contents of the tree.
     * <code>treeString</code>, however, prints out a graphical 
     * representations of the tree's <i>structure</i>.
     * 
     * @post Generates a string representation of the AVLST
     * @return String representation of tree
     */
    public String treeString(){
        return root.treeString();
    }

    /**
     * Returns a string representing tree
     *
     * @post Generates a string representation of the AVLST
     * @return String representation of tree
     */
    public String toString(){
        return root.toString();
    }
    
    /**
     * Returns the hashCode of the value stored by this object.
     *
     * @return The hashCode of the value stored by this object.
     */
    public int hashCode(){
        return root.hashCode();
    } 

    /*
    public static void main(String[] argv){
        for(int i=0; i< 5000; i++){
            test1(i);
        }
    }

    public static void test1(int size){
        RedBlackSearchTree test = new RedBlackSearchTree();
        Long r = new Long(1);
        Object[] store = new Object[size];

        for (int i = 0; i < size; i++){
            r = new Long(Math.round(Math.random() * 5 * size));
            test.add(r);
        }
        
        int j=0;
        for(Iterator i = test.iterator(); i.hasNext();){
            store[j++] = i.next();
        }

        shuffle(store);

        for (int i = 0; i < store.length; i++){
            Object next = store[i];
            test.remove(next);
            }
        
        System.out.println("Did we work: " + test.isRedBlack() + "\t Size: " + size
                           +"\t"+test.size());
    }

    public static void shuffle(Object[] a){
        Random rand = new Random();
        int i1 = 0;
        int i2 = 0;
        Object temp;
        
        for (int i=0; i < (a.length/2); i++){
            i1 = rand.nextInt(a.length);
            i2 = rand.nextInt(a.length);
            temp = a[i1];
            a[i1] = a[i2];
            a[i2] = temp;
        } 
        }*/
}

