package structure5;
import java.util.Iterator;

/**
 * A conveniece class that provies a mechanism to iterate over 
 * arrays that is analogous to the iteration techniques employed by
 * the structures in this package. 
 * <P>
 * Example Usage:
 * <P>
 * To prove that loops are faster than iteration we could use the 
 * following:
 * <pre>
 * public static void main(String[] argv){
 *      //a randomly generated test string
 *      String testString = "For loops are much faster than iterators";
 *
 *      //an array over which to iterate
 *      String[] test = new String[10000000];
 *      
 *      //longs to calculate lenght of computation
 *      long start, finish, iteration, looping;
 *
 *      //populate test array with our test string
 *      for(int i=0; i&lt;test.length; i++) test[i] = testString;
 *      
 *      //compute test for iteration
 *      start = System.nanoTime();
 *      for({@link java.util.Iterator Iterator} i = new {@link #ArrayIterator(Object[]) ArrayIterator(test)}; i.{@link #hasNext()};i.{@link #next()}){}
 *      finish = System.nanoTime();
 *      iteration = finish - start;
 *      System.out.println("Iteration over array took " + iteration + 
 *                         " nanoseconds to perform.");
 *
 *      //compute test for looping
 *      start = System.nanoTime();
 *      for(int i=0; i&lt;test.length; i++){}
 *      finish = System.nanoTime();
 *      looping = finish - start;
 *      System.out.println("Looping over array took " + (finish-start) + 
 *                         " nanoseconds to perform.");
 *      
 *      System.out.println("Iterators are " + (iteration/(double)looping) + " times " +
 *                         "slower than loops.");
 * }
 * </pre>
 */
public class ArrayIterator<E> extends AbstractIterator<E>
{
    /** The array over which this iterator iterates */
    protected E data[];

    /** The index of the first item in the iteration*/
    protected int head;
    
    /** The number of elements in the array over which to iterate */
    protected int count;
    
    /** The current item in the iteration */
    protected int current;

    /** The number of items remaining in the itearation*/
    protected int remaining;

    /** 
     * Construct an iterator that iterates over the entire contents  
     * of an array.
     * 
     * @param source The array over which to iterate.
     * @return An iterator over the entire array.
     */
    public ArrayIterator(Object source[])
    {
        this(source,0,source.length);
    }

    /**
     * Constructs an iterator that will iterate over
     * a specified portion of the source array.
     *
     * @param source The array over which to iterate.
     * @param first The index at which we will start our iteration.
     * @param size The number of elements following that start index 
     *        that are to be iterated over.
     * @return An iterator over a specified poriton of the array.
     */
    @SuppressWarnings("unchecked")
    public ArrayIterator(Object source[], int first, int size)
    {
        data = (E[])source;
        head = first;
        count = size;
        reset();
    }
    
    /**
     * Return the iteration to the original state specified by the 
     * constructor.
     *
     * @post The iteration is returned to its original state.
     */
    public void reset()
    {
        current = head;
        remaining = count;
    }
    
    /**
     * Returns true iff there are elements specified by our iterator
     * that have not been visited by the current iteration.
     * 
     * @return True iff there are elements specified by our iterator
     * that have not been visited by the current iteration.
     */
    public boolean hasNext()
    {
        return remaining > 0;
    }

    /**
     * Return the next object in our iteration and advance
     * the iterator to the next object in the iteration.
     *
     * @return The next object in our iteration.
     */
    public E next()
    {
        E temp = data[current];
        current = (current+1)%data.length;
        remaining--;
        return temp;
    }

    /**
     * Return the object currently specified by the iteration
     * without advancing the iterator to the next object.
     *
     * @return The next object in our iteration.
     */
    @SuppressWarnings("unchecked")
    public E get()
    {
        return (E)data[current];
    }

    /**
     * test code to prove that iterators are slower than for loops
     */
    public static void main(String[] argv){
        //a randomly generated test string
        String testString = "For loops are much faster than iterators";

        //an array over which to iterate
        String[] test = new String[10000000];
        
        //longs to calculate lenght of computation
        long start, finish, iteration, looping;

        //populate test array with our test string
        for(int i=0; i<test.length; i++) test[i] = testString;
        
        //compute test for iteration
        start = System.nanoTime();
        for(Iterator<String> i = new ArrayIterator<String>(test); i.hasNext();i.next()){}
        finish= System.nanoTime();
        iteration = finish - start;
        System.out.println("Iteration over array took " + iteration + 
                           " nanoseconds to perform.");

        //compute test for looping
        start = System.nanoTime();
        for(int i=0; i<test.length; i++){}
        finish= System.nanoTime();
        looping = finish - start;
        System.out.println("Looping over array took " + (finish-start) + 
                           " nanoseconds to perform.");
        
        System.out.println("Iterators are " + (iteration/(double)looping) + " times " +
                           "slower than loops.");
    }
}

