// Pre-order iterator for binary trees.
// (c) 1998, 2001 duane a. bailey
package structure5;


/**
 * This class implements an iterator that traverses a tree in pre-order.
 * Each node is considered before its descendants.
 * <P>
 * Example usage:
 * <P>
 * <pre>
 *      {@link structure.BinaryTree BinaryTree} t = new {@link structure.BinaryTree#BinaryTree() BinaryTree()};
 *      // ...tree is grown
 *      {@link java.util.Iterator Iterator} ti = t.{@link structure.BinaryTree#preorderIterator() preorderIterator()};
 *      while (ti.{@link #hasNext() hasNext()})
 *      {
 *          System.out.println(ti.{@link #next() next()});
 *      }
 *      ti.{@link #reset() reset()};
 *      while (ti.{@link #hasNext() hasNext()})
 *      { .... }
 * </pre>
 *
 * @version $Id: BTPreorderIterator.java 22 2006-08-21 19:27:26Z bailey $
 * @author, 2001 duane a. bailey
 */
class BTPreorderIterator<E> extends AbstractIterator<E>
{
    /**
     * The root of the subtree to be considered by traversal.
     */
    protected BinaryTree<E> root; // root of tree to be traversed
    /**
     * The stack that maintains the state of the iterator.
     */
    protected Stack<BinaryTree<E>> todo; // stack of unvisited nodes whose

    /**
     * Constructs a pre-order traversal of subtree rooted at root.
     *
     * @post constructs an iterator to traverse in preorder
     * 
     * @param root Root of subtree to be traversed.
     */
    public BTPreorderIterator(BinaryTree<E> root)
    {
        todo = new StackList<BinaryTree<E>>();
        this.root = root;
        reset();
    }   

    /**
     * Resets the iterator to the first node of the traversal.
     *
     * @post resets the iterator to retraverse
     */
    public void reset()
    {
        todo.clear(); // stack is empty; push on root
        if (root != null) todo.push(root);
    }

    /**
     * Returns true if some nodes of subtree have yet to be considered.
     *
     * @post returns true iff iterator is not finished
     * 
     * @return True iff more nodes to be considered in traversal.
     */
    public boolean hasNext()
    {
        return !todo.isEmpty();
    }

    /**
     * Returns the value currently being referenced by iterator.
     *
     * @pre hasNext()
     * @post returns reference to current value
     * 
     * @return The current value.
     */
    public E get()
    {   
        return todo.get().value();
    }

    /**
     * Returns the current value and increments the iterator.
     * Iterator is then incremented.
     *
     * @pre hasNext();
     * @post returns current value, increments iterator
     * 
     * @return The value currently being considered.
     */
    public E next()
    {
        BinaryTree<E> old = todo.pop();
        E result = old.value();
        
        if (!old.right().isEmpty()) todo.push(old.right());
        if (!old.left().isEmpty()) todo.push(old.left());
        return result;
    }
}
