package structure5;
import java.util.Iterator;
/**
 * An iterator over all edges.  Every directed/undirected
 * edge is considered exactly once.  Order is not guaranteed.
 * <P>
 * Typical use:
 * <P>
 * <pre>
 *      Graph g = new GraphList();
 *      // ...list gets built up...
 *      Iterator ei = g.edges();
 *      while (ei.{@link #hasNext() hasNext()})
 *      {
 *          System.out.println(ei.{@link #next() next()});
 *      }
 * </pre>
 *
 * @version $Id: GraphListEIterator.java 22 2006-08-21 19:27:26Z bailey $
 * @author, 2001 duane a. bailey
 */
class GraphListEIterator<V,E> extends AbstractIterator<Edge<V,E>>
{
    protected AbstractIterator<Edge<V,E>> edges;

    /**
     * @post constructs a new iterator across edges of
     *       vertices within dictionary
     * 
     * @param dict 
     */
    public GraphListEIterator(Map<V,GraphListVertex<V,E>> dict)
    {
        List<Edge<V,E>> l = new DoublyLinkedList<Edge<V,E>>();
        Iterator<GraphListVertex<V,E>> dictIterator = dict.values().iterator();
        while (dictIterator.hasNext())
        {
            GraphListVertex<V,E> vtx =
                (GraphListVertex<V,E>)dictIterator.next();
            Iterator<Edge<V,E>> vtxIterator = vtx.adjacentEdges();
            while (vtxIterator.hasNext())
            {
                Edge<V,E> e = vtxIterator.next();
                if (vtx.label().equals(e.here())) l.addLast(e);
            }
        }
        edges = (AbstractIterator<Edge<V,E>>)l.iterator();
    }

    /**
     * @post resets the iterator to first edge
     * 
     */
    public void reset()
    {
        edges.reset();
    }

    /**
     * @post returns true iff current element is valid
     * 
     * @return True iff current element is valid
     */
    public boolean hasNext()
    {
        return edges.hasNext();
    }

    /**
     * @pre hasNext()
     * @post returns the current element
     * 
     * @return The current element
     */
    public Edge<V,E> get()
    {
        return edges.get();
    }

    /**
     * @pre hasNext()
     * @post returns current value and increments iterator
     * 
     * @return Current value and increments iterator
     */
    public Edge<V,E> next()
    {
        return edges.next();
    }
}

