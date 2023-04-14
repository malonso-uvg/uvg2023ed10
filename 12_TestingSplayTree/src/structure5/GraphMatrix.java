// Graph, implemented with an adjacency matrix
// (c) 1998, 2001 duane a. bailey

package structure5;
import java.util.Iterator;

/**
 * Implementation of graph using adjacency matrices.
 * User must commit to maximum size of graph (in vertices); it may be smaller.
 * Edges are stored in matrix.  Not suitable for large graphs.
 * Class is abstract: you must use GraphMatrixDirected or 
 * GraphMatrixUndirected to construct particular instances of graphs.
 *
 * Typical usage:
 * <pre>
 *     Graph g = new GraphMatrixUndirected();
 *     g.add("harry");
 *     g.add("sally");
 *     g.addEdge("harry","sally","unfriendly");
 *     ...
 * </pre>
 *
 * @version $Id: GraphMatrix.java 35 2007-08-09 20:38:38Z bailey $
 * @author, 2001 duane a. bailey and kimberly tabtiang
 * @see GraphMatrixDirected
 * @see GraphMatrixUndirected
 * @see GraphList
 */
abstract public class GraphMatrix<V,E>
    extends AbstractStructure<V> implements Graph<V,E>
{
    /**
     * Number of vertices in graph.
     */
    protected int size;          // allocation size for graph
    /**
     * The edge data.  Every edge appears on one (directed)
     * or two (undirected) locations within graph.
     */
    protected Object data[][];     // matrix - array of arrays
    /**
     * Translation between vertex labels and vertex structures.
     */
    protected Map<V,GraphMatrixVertex<V>> dict;   // labels -> vertices
    /**
     * List of free vertex indices within graph.
     */
    protected List<Integer> freeList;    // available indices in matrix
    /**
     * Whether or not graph is directed.
     */
    protected boolean directed;  // graph is directed

    /**
     * Constructor of directed/undirected GraphMatrix. Protected constructor.
     *
     * @param size Maximum size of graph.
     * @param dir True if graph is to be directed.
     */
    protected GraphMatrix(int size, boolean dir)
    {
        this.size = size; // set maximum size
        directed = dir;   // fix direction of edges
        // the following constructs a size x size matrix
        data = new Object[size][size];
        // label to index translation table
        dict = new Hashtable<V,GraphMatrixVertex<V>>(size);
        // put all indices in the free list
        freeList = new SinglyLinkedList<Integer>();
        for (int row = size-1; row >= 0; row--)
            freeList.add(new Integer(row));
    }

    /**
     * Add a vertex to the graph.
     *
     * @pre label is a non-null label for vertex
     * @post a vertex with label is added to graph;
     *       if vertex with label is already in graph, no action
     * 
     * @param label Label of the vertex; should be non-null.
     */
    public void add(V label)
    {
        // if there already, do nothing
        if (dict.containsKey(label)) return;

        Assert.pre(!freeList.isEmpty(), "Matrix not full");
        // allocate a free row and column
        int row = freeList.removeFirst().intValue();
        // add vertex to dictionary
        dict.put(label, new GraphMatrixVertex<V>(label, row));
    }

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
    abstract public void addEdge(V v1, V v2, E label);

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
    public V remove(V label)
    {
        // find and extract vertex
        GraphMatrixVertex<V> vert;
        vert = dict.remove(label);
        if (vert == null) return null;
        // remove vertex from matrix
        int index = vert.index();
        // clear row and column entries
        for (int row=0; row<size; row++) {
            data[row][index] = null;
            data[index][row] = null;
        }
        // add node index to free list
        freeList.add(new Integer(index));
        return vert.label();
    }

    /**
     * Remove possible edge between vertices labeled vLabel1 and vLabel2.
     * Directed edges consider vLabel1 to be the source.
     *
     * @pre vLabel1 and vLabel2 are labels of existing vertices
     * @post edge is removed, its label is returned
     * 
     * @param vLabel1 First (or source, if directed) vertex.
     * @param vLabel2 Second (or destination, if directed) vertex.
     * @return The label associated with the edge removed.
     */
    abstract public E removeEdge(V vLabel1, V vLabel2);

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
    public V get(V label)
    {
        GraphMatrixVertex<V> vert;
        vert = dict.get(label);
        return vert.label();
    }

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
    @SuppressWarnings("unchecked")
    public Edge<V,E> getEdge(V label1, V label2)
    {
        int row,col;
        row = dict.get(label1).index();
        col = dict.get(label2).index();
        return (Edge<V,E>)data[row][col];
    }

    /**
     * Test for vertex membership.
     *
     * @post returns true iff vertex with "equals" label exists
     * 
     * @param label The label of the vertex sought.
     * @return True iff vertex with matching label is found.
     */
    public boolean contains(V label)
    {
        return dict.containsKey(label);
    }

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
    public boolean containsEdge(V vLabel1, V vLabel2)
    {
        GraphMatrixVertex<V> vtx1, vtx2;
        vtx1 = dict.get(vLabel1);
        vtx2 = dict.get(vLabel2);
        Assert.condition(vtx1 != null, "Vertex exists");
        Assert.condition(vtx2 != null, "Vertex exists");
        return data[vtx1.index()][vtx2.index()] != null;
    }

    /**
     * Test and set visited flag of vertex.
     *
     * @post sets visited flag on vertex, returns previous value
     * 
     * @param label Label of vertex to be visited.
     * @return Previous value of visited flag on vertex.
     */
    public boolean visit(V label)
    {
        Vertex<V> vert = dict.get(label);
        return vert.visit();
    }

    /**
     * Test and set visited flag of edge.
     *
     * @pre sets visited flag on edge; returns previous value
     * 
     * @param e Edge object that is part of graph.
     * @return Previous value of the Edge's visited flag.
     */
    public boolean visitEdge(Edge<V,E> e)
    {
        return e.visit();
    }

    /**
     * Return visited flag of vertex.
     *
     * @post returns visited flag on labeled vertex
     * 
     * @param label Label of vertex.
     * @return True if vertex has been visited.
     */
    public boolean isVisited(V label)
    {
        GraphMatrixVertex<V> vert;
        vert = dict.get(label);
        return vert.isVisited();
    }

    /**
     * Return visited flag of edge.
     *
     * @post returns visited flag on edge between vertices
     * 
     * @param e Edge of graph to be considered.
     * @return True if the edge has been visited.
     */
    public boolean isVisitedEdge(Edge<V,E> e)
    {
        return e.isVisited();
    }

    /**
     * Clear visited flags of edges and vertices.
     *
     * @post resets visited flags to false
     */
    @SuppressWarnings("unchecked")
    public void reset()
    {
        Iterator<GraphMatrixVertex<V>> it = dict.values().iterator();
        while (it.hasNext())
        {
            it.next().reset();
        }
        for (int row=0; row<size; row++)
            for (int col=0; col<size; col++) {
                Edge<V,E> e = (Edge<V,E>)data[row][col];
                if (e != null) e.reset();
            }
    }

    /**
     * Determine number of vertices within graph.
     *
     * @post returns the number of vertices in graph
     * 
     * @return The number of vertices within graph.
     */
    public int size()
    {
        return dict.size();
    }

    /**
     * Determine out degree of vertex.
     *
     * @pre label labels an existing vertex
     * @post returns the number of vertices adjacent to vertex
     *
     * @param label Label associated with vertex.
     * @return The number of edges with this vertex as source.
     */
    public int degree(V label)
    {
        // get index
        int row = dict.get(label).index();
        int col;
        int result = 0;
        // count non-null columns in row
        for (col = 0; col < size; col++)
        {
            if (data[row][col] != null) result++;
        }
        return result;
    }

    /**
     * Determine the number of edges in graph.
     *
     * @post returns the number of edges in graph
     * 
     * @return Number of edges in graph.
     */
    abstract public int edgeCount();

    /**
     * Construct vertex traversal.  Vertices are not visited in 
     * any guaranteed order.
     *
     * @post returns traversal across all vertices of graph
     * 
     * @return AbstractIterator traversing vertices in graph.
     */
    public Iterator<V> iterator()
    {
        return dict.keySet().iterator();
    }

    /**
     * Construct an adjacent vertex traversal.   Adjacent vertices
     * (those on destination of edge, if directed) are considered,
     * but not in any guaranteed order.
     *
     * @pre label is label of vertex in graph
     * @post returns traversal over vertices adj. to vertex
     *       each edge beginning at label visited exactly once
     * 
     * @param label Label of the vertex.
     * @return AbstractIterator traversing the adjacent vertices of labeled vertex.
     */
    @SuppressWarnings("unchecked")
    public Iterator<V> neighbors(V label)
    {
        GraphMatrixVertex<V> vert;
        vert = dict.get(label);
        List<V> list = new SinglyLinkedList<V>();
        for (int row=size-1; row>=0; row--)
        {
            Edge<V,E> e = (Edge<V,E>)data[vert.index()][row];
            if (e != null) {
                if (e.here().equals(vert.label()))
                     list.add(e.there());
                else list.add(e.here());
            }
        }
        return list.iterator();
    }
          
    /**
     * Construct an traversal over all edges.  Every directed/undirected
     * edge is considered exactly once.  Order is not guaranteed.
     *
     * @post returns traversal across edges of graph
     *       traversal returns edges; each edge visited once
     * 
     * @return AbstractIterator over edges.
     */
    abstract public Iterator<Edge<V,E>> edges();

    /**
     * Remove all vertices (and thus, edges) of the graph.
     *
     * @post removes all vertices from graph
     */
    public void clear()
    {
        dict.clear();
        for (int row=0; row<size; row++)
            for (int col=0; col<size; col++)
                data[row][col] = null;
        freeList = new SinglyLinkedList<Integer>();
        for (int row=size-1; row>=0; row--)
            freeList.add(new Integer(row));
    }

    /**
     * Determine if graph is empty.
     *
     * @post returns true if graph contains no vertices
     * 
     * @return True iff there are no vertices in graph.
     */
    public boolean isEmpty()
    {
      return dict.isEmpty();
    }

    /**
     * Determine if graph is directed.
     *
     * @post returns true if edges of graph are directed
     * 
     * @return True iff the graph is directed.
     */
    public boolean isDirected()
    {
        return directed;
    }
}

