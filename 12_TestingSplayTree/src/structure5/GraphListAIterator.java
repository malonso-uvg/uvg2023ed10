package structure5;
import java.util.Iterator;
/**
 * An adjacent vertex iterator.   Adjacent vertices
 * (those on destination of edge, if directed) are considered,
 * but not in any guaranteed order.
 * Typical use:
 * <P>
 * <pre>
 *      Graph g = new GraphList();
 *      // ...list gets built up...
 *      Iterator ai = g.neighbors(someVertex);
 *      while (ai.{@link #hasNext() hasNext()})
 *      {
 *          System.out.println(ai.{@link #next() next()});
 *      }
 * </pre>
 * @version $Id: GraphListAIterator.java 22 2006-08-21 19:27:26Z bailey $
 * @author, 2001 duane a. bailey
 */
class GraphListAIterator<V,E> extends AbstractIterator<V>
{
    protected AbstractIterator<Edge<V,E>> edges;
    protected V vertex;

    /**
     * @pre i is an edge iterator
     * @post returns iterator over vertices adjacent to v
     * 
     * @param i 
     * @param v 
     */
    public GraphListAIterator(Iterator<Edge<V,E>> i, V v)
    {
        edges = (AbstractIterator<Edge<V,E>>)i;
        vertex = v;
    }

    /**
     * @post resets iterator
     * 
     */
    public void reset()
    {
        edges.reset();
    }

    /**
     * @post returns true if more adj. vertices to traverse
     * 
     * @return True if more adj. vertices to traverse
     */
    public boolean hasNext()
    {
        return edges.hasNext();
    }

    /**
     * @pre hasNext
     * @post returns the next adjacent vertex
     * 
     * @return The next adjacent vertex
     */
    public V next()
    {
        Edge<V,E> e = edges.next();
        if (vertex.equals(e.here())) 
        {
            return e.there();
        } else { // N.B could be vertex if self-loop edge
            return e.here();
        }
    }

    /**
     * @pre hasNext
     * @post returns the current adj. vertex
     * 
     * @return The current adj. vertex
     */
    public V get()
    {
        Edge<V,E> e = edges.get();
        if (vertex.equals(e.here())) 
        {
            return e.there();
        } else { // NB. could be vertex if self-loop edge
            return e.here();
        }
    }
}
