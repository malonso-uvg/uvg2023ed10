// In-order iterator for RedBlack trees.
// (c) 1998, 2001, 2002 duane a. bailey
package structure5;

/**
 * An iterator for traversing RedBlackSearchTrees constructed from
 * RedBlackTrees.  The iterator performs minimal work before
 * traversal.  Every node is considered after every left descendant,
 * but before any right descendant.  AbstractIterator finishes when
 * all descendants of the start node have been considered.
 *
 * @version $Id: RedBlackIterator.java 22 2006-08-21 19:27:26Z bailey $
 * @author, 2001 duane a. bailey & evan s. sandhaus
 */
class RedBlackIterator<E extends Comparable<E>> extends AbstractIterator<E>
{

    /**
     * The root of the subtree being traversed.
     */
    protected RedBlackTree<E> root; // root of subtree to be traversed

    /** 
     * Stack of nodes that maintain the state of the iterator.
     */
    protected Stack<RedBlackTree<E>> todo; // stack of unvisited ancestors of current


    /**
     * Construct a new inorder iterator of a tree.
     *
     * @post Constructs an iterator to traverse inorder
     * 
     * @param root The root of the subtree to be traversed.
     */
    public RedBlackIterator(RedBlackTree<E> root){
        todo = new StackList<RedBlackTree<E>>();
        this.root = root;
        reset();
    }   

    /**
     * Reset the iterator to its initial state.
     *
     * @post Resets the iterator to retraverse
     */
    public void reset(){
        todo.clear();
        // stack is empty.  Push on nodes from root to
        // leftmost descendant
        RedBlackTree<E> current = root;
        while (current != RedBlackTree.EMPTY) {
            todo.push(current);
            current = current.left();
        }
    }

    /**
     * Returns true iff the iterator has more nodes to be considered.
     *
     * @post Returns true iff iterator is not finished
     * 
     * @return True iff more nodes are to be considered.
     */
    public boolean hasNext(){
        return !todo.isEmpty();
    }

    /**
     * Return the node currently being considered.
     *
     * @pre hasNext()
     * @post Returns reference to current value
     *
     * @return The node currently under consideration.
     */
    public E get(){     
        return todo.get().value();
    }

    /**
     * Return current node, and increment iterator.
     *
     * @pre hasNext()
     * @post Returns current value, increments iterator
     * @return The value of the current node, before iterator iterated.
     */
    public E next(){
        RedBlackTree<E> old = todo.pop();
        E result = old.value();
        // we know this node has no unconsidered left children;
        // if this node has a right child, 
        //   we push the right child and its leftmost descendants:
        // else 
        //   top element of stack is next node to be visited
        if (!old.right().isEmpty()) {
            RedBlackTree<E> current = old.right();
            do {
                todo.push(current);
                current = current.left();
            } while (!current.isEmpty());
        }
        return result;
    }   
}
