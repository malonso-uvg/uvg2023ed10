// The interface for Graphs.
// (c) 1998, 2001 duane a. bailey

package structure5;
import java.util.Iterator;
/**
 * The interface describing all Graph objects.  Graphs are collections
 * of vertices connected by a set of edges.  Edges may or may not be
 * directed.  Portions of the graph may be marked visited to support
 * iterative algorithms.  Iteration is provided over vertices, edges, and 
 * vertices adjacent to a particular vertex
 * <P>
 * Example usage:
 * <P>
 * To visit all of the vertices reachable from a given vertex we could use 
 * the following:
 * <P>
 * <pre>
 *   static void reachableFrom({@link Graph} g, Object vertexLabel)
 *   {
 *       g.{@link #visit(Object) visit(vertexLabel)};   // visit this vertex
 *
 *       // recursively visit unvisited neighbor vertices
 *       Iterator ni = g.{@link #neighbors(Object) neighbors(vertexLabel)};
 *       while (ni.hasNext())
 *       {
 *           Object neighbor = ni.next(); // adjacent node label
 *           if (!g.{@link #isVisited(Object) isVisited(neighbor)}x)
 *           {
 *               reachableFrom(g,neighbor); // depth-first search
 *           } 
 *       }
 *   }
 * </pre>  
 * @version $Id: Graph.java 22 2006-08-21 19:27:26Z bailey $
 * @author, 2001 duane a. bailey
 * @see structure.GraphList
 * @see structure.GraphMatrix
 */
public interface Graph<V,E> extends Structure<V>
{
    /**
     * Add a vertex to the graph
     *
     * @pre label is a non-null label for vertex
     * @post a vertex with label is added to graph
     *       if vertex with label is already in graph, no action
     * 
     * @param label Label of the vertex; should be non-null
     */
    public void add(V label);

    /**
     * Add an edge between two vertices within the graph.  Edge is directed
     * iff graph is directed.  Duplicate edges are silently replaced.
     * Labels on edges may be null.
     *
     * @pre vtx1 and vtx2 are labels of existing vertices
     * @post an edge (possibly directed) is inserted between
     *       vtx1 and vtx2.
     * 
     * @param vtx1 First (or source, if directed) vertex.
     * @param vtx2 Second (or destination, if directed) vertex.
     * @param label Label associated with the edge.
     */
    public void addEdge(V vtx1, V vtx2, E label);

    /**
     * Remove a vertex from the graph.  Associated edges are also 
     * removed.  Non-vertices are silently ignored.
     *
     * @pre label is non-null vertex label
     * @post vertex with "equals" label is removed, if found
     * 
     * @param label The label of the vertex within the graph.
     * @return The label associated with the vertex.
     */
    public V remove(V label);

    /**
     * Remove possible edge between vertices labeled vLabel1 and vLabel2.
     * Directed edges consider vLabel1 to be the source.
     *
     * @pre vLabel1 and vLabel2 are labels of existing vertices
     * @post edge is removed, its label is returned
     * @param vLabel1 First (or source, if directed) vertex.
     * @param vLabel2 Second (or destination, if directed) vertex.
     * @return The label associated with the edge removed.
     */
    public E removeEdge(V vLabel1, V vLabel2);

    /**
     * Get reference to actual label of vertex.  Vertex labels are matched
     * using their equals method, which may or may not test for actual
     * equivalence.  Result remains part of graph.
     *
     * @post returns actual label of indicated vertex
     * 
     * @param label The label of the vertex sought.
     * @return The actual label, or null if none is found.
     */
    public V get(V label);

    /**
     * Get reference to actual edge.  Edge is identified by
     * the labels on associated vertices.  If edge is directed, the
     * label1 indicates source.
     *
     * @post returns actual edge between vertices
     * 
     * @param label1 The first (or source, if directed) vertex.
     * @param label2 The second (or destination, if directed) vertex.
     * @return The edge, if found, or null.
     */
    public Edge<V,E> getEdge(V label1, V label2);

    /**
     * Test for vertex membership.
     *
     * @post returns true iff vertex with "equals" label exists
     * 
     * @param label The label of the vertex sought.
     * @return True iff vertex with matching label is found.
     */
    public boolean contains(V label);

    /**
     * Test for edge membership.  If edges are directed, vLabel1
     * indicates source.
     *
     * @post returns true iff edge with "equals" label exists
     * 
     * @param vLabel1 First (or source, if directed) vertex.
     * @param vLabel2 Second (or destination, if directed) vertex.
     * @return True iff the edge exists within the graph.
     */
    public boolean containsEdge(V vLabel1, V vLabel2);

    /**
     * Test and set visited flag of vertex
     *
     * @post sets visited flag on vertex, returns previous value
     * 
     * @param label Label of vertex to be visited.
     * @return Previous value of visited flag on vertex.
     */
    public boolean visit(V label);

    /**
     * Test and set visited flag of edge.
     *
     * @pre sets visited flag on edge; returns previous value
     * 
     * @param e Edge object that is part of graph.
     * @return Previous value of the Edge's visited flag.
     */
    public boolean visitEdge(Edge<V,E> e);

    /**
     * Return visited flag of vertex.
     *
     * @post returns visited flag on labeled vertex
     * 
     * @param label Label of vertex.
     * @return True if vertex has been visited.
     */
    public boolean isVisited(V label);

    /**
     * Return visited flag of edge.
     *
     * @post returns visited flag on edge between vertices
     * 
     * @param e Edge of graph to be considered.
     * @return True if the edge has been visited.
     */
    public boolean isVisitedEdge(Edge<V,E> e);

    /**
     * Clear visited flags of edges and vertices.
     *
     * @post resets visited flags to false
     */
    public void reset();

    /**
     * Determine number of vertices within graph.
     *
     * @post returns the number of vertices in graph
     * 
     * @return The number of vertices within graph.
     */
    public int size();

    /**
     * Determine out degree of vertex.
     *
     * @pre label labels an existing vertex
     * @post returns the number of vertices adjacent to vertex
     *
     * @param label Label associated with vertex.
     * @return The number of edges with this vertex as source.
     */
    public int degree(V label);

    /**
     * Determine the number of edges in graph.
     *
     * @post returns the number of edges in graph
     * 
     * @return Number of edges in graph.
     */
    public int edgeCount();

    /**
     * Construct vertex iterator.  Vertices are not visited in 
     * any guaranteed order.
     *
     * @post returns iterator across all vertices of graph
     * 
     * @return Iterator traversing vertices in graph.
     */
    public Iterator<V> iterator();

    /**
     * Construct an adjacent vertex iterator.   Adjacent vertices
     * (those on destination of edge, if directed) are considered,
     * but not in any guaranteed order.
     *
     * @pre label is label of vertex in graph
     * @post returns iterator over vertices adj. to vertex
     *       each edge beginning at label visited exactly once
     * 
     * @param label Label of the vertex.
     * @return Iterator traversing the adjacent vertices of labeled vertex.
     */
    public Iterator<V> neighbors(V label);

    /**
     * Construct an iterator over all edges.  Every directed/undirected
     * edge is considered exactly once.  Order is not guaranteed.
     *
     * @post returns iterator across edges of graph
     *       iterator returns edges; each edge visited once
     * 
     * @return Iterator over edges.
     */
    public Iterator<Edge<V,E>> edges();

    /**
     * Remove all vertices (and thus, edges) of the graph.
     *
     * @post removes all vertices from graph
     */
    public void clear();

    /**
     * Determine if graph is empty.
     *
     * @post returns true if graph contains no vertices
     * 
     * @return True iff there are no vertices in graph.
     */
    public boolean isEmpty();

    /**
     * Determine if graph is directed.
     *
     * @post returns true if edges of graph are directed
     * 
     * @return True iff the graph is directed.
     */
    public boolean isDirected();
}

