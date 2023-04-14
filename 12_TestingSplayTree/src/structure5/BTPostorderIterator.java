// Post-order iterator for binary trees.
// (c) 1998, 2001 duane a. bailey
package structure5;

/**
 * This class implements a post-order traversal of a binary tree.
 * This iterator considers every node after its non-trivial descendants.
 * <P>
 * Example usage:
 * <P>
 * <pre>
 *      {@link structure.BinaryTree BinaryTree} t = new {@link structure.BinaryTree#BinaryTree() BinaryTree()};
 *      // ...tree is grown
 *      {@link java.util.Iterator Iterator} ti = t.{@link structure.BinaryTree#postorderIterator() postorderIterator()};
 *      while (ti.{@link #hasNext() hasNext()})
 *      {
 *          System.out.println(ti.{@link #next() next()});
 *      }
 *      ti.{@link #reset() reset()};
 *      while (ti.{@link #hasNext() hasNext()})
 *      { .... }
 * </pre>
 *
 * @see BinaryTree#PostorderElements
 * @version $Id: BTPostorderIterator.java 35 2007-08-09 20:38:38Z bailey $
 * @author, 2001 duane a. bailey
 */
class BTPostorderIterator<E> extends AbstractIterator<E>
{
    /**
     * The root of the tree currently being traversed.
     */
    protected BinaryTree<E> root; // root of traversed subtree
    /**
     * The stack the maintains the state of the iterator.
     * Elements of the stack are nodes whose descendants are still being
     * considered.
     */
    protected Stack<BinaryTree<E>> todo;  // stack of nodes
    // whose descendants are currently being visited

    /**
     * Construct an iterator to traverse subtree rooted at root
     * in post-order.
     *
     * @post constructs an iterator to traverse in postorder
     * 
     * @param root The root of the subtree to be traversed.
     */
    public BTPostorderIterator(BinaryTree<E> root)
    {
        todo = new StackList<BinaryTree<E>>();
        this.root = root;
        reset();
    }   

    /**
     * Reset the iterator to the first node of the traversal.
     *
     * @post Resets the iterator to retraverse
     */
    public void reset()
    {
        todo.clear();
        // stack is empty; push on nodes from root to
        // leftmost descendant
        BinaryTree<E> current = root;
        while (!current.isEmpty()) {
            todo.push(current);
            if (!current.left().isEmpty())
                current = current.left();
            else
                current = current.right();
        }
    }

    /**
     * Return true iff more nodes are to be considered in traversal.
     *
     * @post Returns true iff iterator is not finished
     * 
     * @return True iff more nodes are to be considered in traversal.
     */
    public boolean hasNext()
    {
        return !todo.isEmpty();
    }

    /**
     * Return the value of the current node.
     *
     * @pre hasNext()
     * @post returns reference to current value
     * 
     * @return The value referenced by current node.
     */
    public E get()
    {   
        return todo.get().value();
    }

    /**
     * Return current value and increment iterator.
     *
     * @pre hasNext();
     * @post returns current value, increments iterator
     * 
     * @return The value currently considered by iterator, increment.
     */
    public E next()
    {
        BinaryTree<E> current = todo.pop();
        E result = current.value();
        if (!todo.isEmpty())
        {
            BinaryTree<E> parent = todo.get();
            if (current == parent.left()) {
                current = parent.right();
                while (!current.isEmpty())
                {
                    todo.push(current);
                    if (!current.left().isEmpty())
                         current = current.left();
                    else current = current.right();
                }
            }
        }
        return result;
    }
}
