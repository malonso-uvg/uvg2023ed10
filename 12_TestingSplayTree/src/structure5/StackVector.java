// An implementation of stacks, using vectors.
// (c) 1998, 2001 duane a. bailey

package structure5;
import java.util.Iterator;
/**
 * An implementation of a stack, based on extensible arrays.  The head of the
 * stack is stored in the first position of the list, allowing the stack to grow
 * and shrink in constant time. This stack implementation is ideal for 
 * applications that require a dynamically resizable stack which occasionally takes
 * a time proportional to the its length to expand.
 * <P>
 * Example usage:
 * <P>
 * To reverse a string with a stack array, we would use the following:
 * <pre>
 * public static void main(String[] arguments)
 * {
 *     if(arguments.length > 0){
 *         {@link StackVector} reverseStack = new {@link #StackVector()};
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
 * @see StackList 
 * @see StackArray
 * @see AbstractStack
 *
 * @version $Id: StackVector.java 22 2006-08-21 19:27:26Z bailey $
 * @author, 2001 duane a. bailey
 */
public class StackVector<E> extends AbstractStack<E> implements Stack<E>
{
    /**
     * The vector containing the stack data.
     */
    protected Vector<E> data;

    /**
     * Construct an empty stack.
     *
     * @post an empty stack is created
     */
    public StackVector()
    {
        data = new Vector<E>();
    }

    /**
     * Construct a stack with initial capacity
     * Vector will grow if the stack fills vector.
     *
     * @post an empty stack with initial capacity of size is created
     * 
     * @param size The initial capacity of the vector.
     */
    public StackVector(int size)
    {
        data = new Vector<E>(size);
    }

    /**
     * Add an element from the top of the stack.
     *
     * @post item is added to stack
     *       will be popped next if no intervening add
     * 
     * @param item The element to be added to the stack top.
     */
    public void add(E item)
    {
        data.add(item);
    }

    /**
     * Remove an element from the top of the stack.
     *
     * @pre stack is not empty
     * @post most recently added item is removed and returned
     * 
     * @return The item removed from the top of the stack.
     * @see #pop
     */
    public E remove()
    {
        return data.remove(size()-1);
    }

    /**
     * Fetch a reference to the top element of the stack.
     *
     * @pre stack is not empty
     * @post top value (next to be popped) is returned
     * 
     * @return A reference to the top element of the stack.
     */
    public E get()
    {
        // raise an exception if stack is already empty
        return data.get(size()-1);
    }

    /**
     * Returns true iff the stack is empty.  Provided for
     * compatibility with java.util.Vector.empty.
     *
     * @post returns true if and only if the stack is empty
     * 
     * @return True iff the stack is empty.
     */
    public boolean isEmpty()
    {
        return size() == 0;
    }

    /**
     * Determine the number of elements in stack.
     * 
     * @post returns the number of elements in stack
     * 
     * @return The number of elements in stack.
     */
    public int size()
    {
        return data.size();
    }

    /**
     * Remove all elements from stack.
     *
     * @post removes all elements from stack
     */
    public void clear()
    {
        data.clear();
    }

    public Iterator<E> iterator()
    {
        return data.iterator();
    }

    /**
     * Construct a string representation of stack.
     *
     * @post returns a string representation of stack
     * 
     * @return A string representing the stack.
     */
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        int i;

        sb.append("<StackVector:");
        for (i = data.size()-1; i >= 0; i--)
        {
            sb.append(" "+i);
        }
        return sb.toString()+">";
    }
}
