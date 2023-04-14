package structure5;
import java.util.Iterator;

/**
 * A private implementation of a vertex for use in graphs that
 * are internally represented as a list.  A vertex
 * is capable of holding a label and has a flag that can be set  
 * to mark it as visited.   
 * <P>
 * Typical Usage:
 * <P>
 * <pre>
 *     Vertex v = new {@link #GraphListVertex(Object) Vertex(someLabel)};
 *     //...several graph related operations occur
 *     if(!v.{@link #isVisited() isVisited()}){
 *         Object label = v.label();
 *         v.{@link #visit() visit()};
 *     }
 * </pre>
 * @see GraphListVertex
 * @see Vertex
 * @version $Id: GraphListVertex.java 22 2006-08-21 19:27:26Z bailey $
 * @author, 2001 duane a. bailey
 */
class GraphListVertex<V,E> extends Vertex<V>
{
    protected Structure<Edge<V,E>> adjacencies; // adjacent edges
    /**
     * @post constructs a new vertex, not incident to any edge
     * 
     * @param key 
     */
    public GraphListVertex(V key)
    {
        super(key); // init Vertex fields
        adjacencies = new SinglyLinkedList<Edge<V,E>>(); // new list
    }

    /**
     * @pre e is an edge that mentions this vertex
     * @post adds edge to this vertex's adjacency list
     * 
     * @param e 
     */
    public void addEdge(Edge<V,E> e)
    {
        if (!containsEdge(e)) adjacencies.add(e);
    }

    /**
     * @post returns true if e appears on adjacency list
     * 
     * @param e 
     * @return 
     */
    public boolean containsEdge(Edge<V,E> e)
    {
        return adjacencies.contains(e);
    }

    /**
     * @post removes and returns adjacent edge "equal" to e
     * 
     * @param e 
     * @return 
     */
    public Edge<V,E> removeEdge(Edge<V,E> e)
    {
        return adjacencies.remove(e);
    }

    /**
     * @post returns the edge that "equals" e, or null
     * 
     * @param e 
     * @return 
     */
    public Edge<V,E> getEdge(Edge<V,E> e)
    {
        Iterator<Edge<V,E>> edges = adjacencies.iterator();
        while (edges.hasNext())
        {
            Edge<V,E> adjE = edges.next();
            if (e.equals(adjE)) return adjE;
        }
        return null;
    }

    /**
     * @post returns the degree of this node
     * 
     * @return 
     */
    public int degree()
    {
        return adjacencies.size(); 
    }

    /**
     * @post returns iterator over adj. vertices
     * 
     * @return 
     */
    public Iterator<V> adjacentVertices()
    {
        return new GraphListAIterator<V,E>(adjacentEdges(), label());
    }

    /**
     * @post returns iterator over adj. edges
     * 
     * @return 
     */
    public Iterator<Edge<V,E>> adjacentEdges()
    {
        return adjacencies.iterator();
    }

    /**
     * @post returns string representation of vertex
     * 
     * @return String representation of vertex
     */
    public String toString()
    {
        return "<GraphListVertex: "+label()+">";
    }
}

