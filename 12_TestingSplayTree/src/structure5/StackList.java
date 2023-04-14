// An implementation of stacks using lists.
// (c) 1998,2001 duane a. bailey

package structure5;
import java.util.Iterator;

/**
 * An implementation of a stack, based on lists.  The head of the
 * stack is stored at the head of the list, allowing the stack to grow
 * and shrink in constant time. This stack implementation is ideal for 
 * applications that require a dynamically resizable stack that expands 
 * in constant time.
 * <P>
 * Example usage:
 * <P>
 * To reverse a string, we would use the following:
 * <pre>
 * public static void main(String[] arguments)
 * {
 *     if(arguments.length > 0){
 *         {@link StackList} reverseStack = new {@link #StackList()};
 *         String s = arguments[0];
 *          
 *         for(int i=0; i < s.length(); i++){
 *             reverseStack.{@link #push(Object) push(new Character(s.charAt(i)))};
 *         }
 *
 *         while(!reverseStack.{@link #empty()}){
 *             System.out.print(reverseStack.{@link #pop()});
 *         }
 *
 *         System.out.println();
 *     }
 * }
 * </pre>
 * @see Stack 
 * @see StackVector 
 * @see StackArray
 * @see AbstractStack
 *
 * @version $Id: StackList.java 22 2006-08-21 19:27:26Z bailey $
 * @author, 2001 duane a. bailey
 */
public class StackList<E> extends AbstractStack<E> implements Stack<E>
{
    /**
     * The list that maintains the stack data.
     */
    protected List<E> data;

    /**
     * Construct an empty stack.
     *
     * @post constructs a new stack, based on lists
     */
    public StackList()
    {
        // Think about why we use singly linked lists here:
        // They're simple, and take less space.
        data = new SinglyLinkedList<E>();
    }
    
    /**
     * Remove all elements from the stack.
     *
     * @post removes all elements from stack
     */
    public void clear()
    {
        data.clear();
    }

    /**
     * Determine if the stack is empty.
     * Provided for compatibility with java.util.Stack.empty.
     *
     * @post returns true iff the stack is empty
     * 
     * @return True iff the stack is empty.
     * @see #isEmpty
     */
    public boolean empty()
    {
        return data.isEmpty();
    }

    public Iterator<E> iterator()
    {
        return data.iterator();
    }


    /**
     * Get a reference to the top value in the stack.
     *
     * @pre stack is not empty
     * @post returns the top element (most recently pushed) from stack
     * 
     * @return A reference to the top element of the top of the stack.
     */
    public E get()
    {
        return data.getFirst();
    }

    /**
     * Add a value to the top of the stack.
     *
     * @post adds an element to stack;
     *       will be next element popped if no intervening push
     * 
     * @param item The value to be added.
     * @see #push
     */
    public void add(E value)
    {
        data.addFirst(value);
    }

    /**
     * Remove a value from the top of the stack.
     *
     * @pre stack is not empty
     * @post removes and returns the top element from stack
     * 
     * @return The value removed from the top of the stack.
     * @see #pop
     */
    public E remove()
    {
        return data.removeFirst();
    }

    /**
     * Determine the number of elements in the stack.
     *
     * @post returns the size of the stack
     * 
     * @return The number of values within the stack.
     */
    public int size()
    {
        return data.size();
    }

    /**
     * Construct a string representation of the stack.
     *
     * @post returns a string representation of stack
     * 
     * @return A string representing the stack.
     */
    public String toString()
    {
        return "<StackList: "+data+">";
    }
}
