package structure5;
/**
 * Interface describing mergeable min heaps.  
 * Min heaps are collections of Comparable data that guarantee
 * efficient access to the smallest element in the structure.
 * Mergeable Min heaps, also provide an efficient technique for
 * merging two mergeable heaps of the same type.
 * Mergeable heaps are quite similar to {@link structure.PriorityQueue}.
 * <P>
 * Example Usage: 
 * </P>
 * <pre>
 * public static void main(String[] argv){
 *      //initialize a new fib heap
 *      MergeableHeap programmers = new {@link structure.FibHeap#FibHeap() FibHeap()};
 *
 *      //add programmers and their ages to heap
 *      //ages current of 7/22/2002
 *        programmers.{@link #add(Comparable) add(new ComparableAssociation(new Integer(22), "Evan"))};
 *      programmers.add(new ComparableAssociation(new Integer(19), "Chris"));
 *      programmers.add(new ComparableAssociation(new Integer(20), "Shimon"));
 *      programmers.add(new ComparableAssociation(new Integer(21), "Diane"));
 *      programmers.add(new ComparableAssociation(new Integer(21), "Lida"));    
 *      programmers.add(new ComparableAssociation(new Integer(20), "Rob"));     
 *      programmers.add(new ComparableAssociation(new Integer(20), "Sean"));    
 *
 *      //print out programmers 
 *      while(!programmers.{@link #isEmpty()}){
 *          ComparableAssociation p = (ComparableAssociation)programmers.{@link #remove()};
 *          System.out.println(p.getValue() + " is " + p.getKey() + " years old.");
 *      }
 * }
 * </pre>
 *
 * @version $Id: MergeableHeap.java 22 2006-08-21 19:27:26Z bailey $
 * @author, 2001 duane a. bailey
 */
public interface MergeableHeap<E extends Comparable<E>> extends PriorityQueue<E> {
    /**
     * Merge this heap with <code>otherHeap</code>, destroying
     * <code>otherHeap</code> in the process.
     *
     * @param otherHeap Heap to be merged into this heap.
     * @post This heap contains all of the elements formerly contained
     * by <code>otherHeap</code>. <code>otherHeap</code> is destroyed in
     * in the process.
     */
    public void merge(MergeableHeap<E> otherHeap);
}


