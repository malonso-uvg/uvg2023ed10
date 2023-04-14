// Generic base class for describing edges in graphs.
// (c) 1998, 2001 duane a. bailey

package structure5;

/**
 * A class implementing common edge type among graphs.  This class
 * supports both directed and undirected edges.  Edge may also have
 * visited flags set and cleared. ComparableEdge differs from
 * edge, however, in that it requires that its lable be of a comparable
 * type.
 * <P>
 * Typical usage:
 * <P>
 * <pre>
 *     Graph g = new GraphListDirected();
 *     g.add("harry");
 *     g.add("sally");
 *     g.addEdge("harry","sally","friendly");
 *     ComparableEdge e = new {@link #ComparableEdge(Edge) ComparableEdge(g.getEdge("harry","sally"))};
 *     Comparable label = e.{@link #label() label()};
 *     Vertex source = e.{@link #here() here()};
 *     Vertex destination = e.{@link #there() there()};
 *     ComparableEdge e2 = e;
 *     if(e.{@link #compareTo(Object) compareTo(e2)} == 0){
 *         e.{@link #visit() visit()};
 *         e.{@link #reset() reset()};
 *     }
 *     ...
 * </pre>
 *
 * @version $Id: ComparableEdge.java 26 2006-08-24 14:29:13Z bailey $
 * @author, 2001 duane a. bailey
 * @see structure.Graph
 */
public class ComparableEdge<V,E extends Comparable<E>> extends Edge<V,E> implements Comparable<ComparableEdge<V,E>>
{
    /**
     * Construct a (possibly directed) edge between two labeled
     * vertices.  When edge is directed, vtx1 specifies source.
     * When undirected, order of vertices is unimportant.  Label
     * on edge is any type, and may be null.
     * Edge is initially unvisited.
     *
     * @post edge associates vtx1 and vtx2. labeled with label
     *       directed if "directed" set true
     *
     * @param vtx1 The label of a vertex (source if directed).
     * @param vtx2 The label of another vertex (destination if directed).
     * @param label The label associated with the edge.
     * @param directed True iff this edge is directed.
     */
    public ComparableEdge(V vtx1, V vtx2, E label,
                boolean directed)
    {
        super(vtx1,vtx2,label,directed);
    }

    /**
     * Construct a (possibly directed) edge between two labeled
     * vertices.  When edge is directed, vtx1 specifies source.
     * When undirected, order of vertices is unimportant.  Label
     * on edge is any type, and may be null.
     * Edge is initially unvisited.
     *
     * @post edge associates vtx1 and vtx2. labeled with label.
     *       directed if "directed" set true
     *
     * @param e The edge to be used as the basis for a comparable edge
     */
    public ComparableEdge(Edge<V,E> e)
    {
        this(e.here(),e.there(),e.label(),e.isDirected());
    }

    /**
     * Compare edge, based on labels.
     *
     * @pre labels are Comparable
     * @post returns integer representing relation between labels
     */
    public int compareTo(ComparableEdge<V,E> other)
    {
        E thisLabel = label();
        E thatLabel = other.label();
        return thisLabel.compareTo(thatLabel);
    }

    /**
     * Construct a string representation of edge.
     *
     * @post returns string representation of edge
     * 
     * @return String representing edge.
     */
    public String toString()
    {
        StringBuffer s = new StringBuffer();
        s.append("<Edge:");
        if (visited) s.append(" visited");
        s.append(" "+here());
        if (directed) s.append(" <->");
        else s.append("->");
        s.append(" "+there()+">");
        return s.toString();
    }
}
