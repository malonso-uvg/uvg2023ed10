// Graph, implemented with an adjacency list
// (c) 1998, 2001 duane a. bailey

package structure5;
import java.util.Iterator;
/**
 * A GraphListDirected is a list-based graph representation that consists 
 * of a collection of vertices and directed edges.  Portions of the graph
 * may be marked visited to support iterative algorithms.  
 * Iteration is provided over vertices, edges, and vertices adjacent to a
 * particular vertex.
 * <P>
 * Example Usage: 
 * <P> 
 * To create a graph representation of the movie theaters nearest
 * the Williams College Department of Computer Science's unix laboratory, 
 * and to print these theaters out in order of increasing distance,
 * we could use the following:
 * <P>
 * <pre>
 *  public static void main(String[] argv){
 *      Graph theaters = new {@link #GraphListDirected()};
 *      FibHeap heap = new FibHeap();
 *      
 *      //instantiate array of locations 
 *      String[] locations = new String[]{"TCL 312", "Images Cinema", 
 *                                        "Movie Plex 3", "Cinema 1,2,&3", 
 *                                        "Cinema 7", "Berkshire Mall Cinemas"
 *                                        ,"Hathaway's Drive Inn Theatre",
 *                                        "Hollywood Drive-In Theatre"};
 *
 *      //instantiate array of distances between <code>location[0]</code> 
 *      //and movie theaters
 *      double[] distances =  new double[]{-1, 0.0, 12.6, 12.9, 12.9, 
 *                                         14.7, 16.5, 18.0};
 *      
 *      //build graph
 *      for(int i=0; i < locations.length; i++) theaters.add(locations[i]);
 *      for(int i=1; i < distances.length; i++){
 *        theaters.{@link #addEdge(Object, Object, Object) addEdge(locations[0],locations[i],new Double(distances[i]))};
 *      }
 *      
 *      //place neighbors of lab in into priority queue
 *      for(Iterator i=theaters.{@link #neighbors(Object) neighbors(locations[0])}; i.hasNext();){
 *          Object theater = i.next();
 *          Object distance = theaters.{@link #getEdge(Object,Object) getEdge(locations[0], theater).label()};
 *          heap.add(new ComparableAssociation((Comparable)distance,theater));
 *      }
 *      
 *      //print out theaters in order of distance
 *      while(!heap.isEmpty()){
 *          ComparableAssociation show = (ComparableAssociation)heap.remove();
 *          System.out.println(show.getValue()+" is "+show.getKey()+" miles away.");
 *      }
 *  }
 * </pre>
 * @version $Id: GraphListDirected.java 22 2006-08-21 19:27:26Z bailey $
 * @author, 2001 duane a. bailey and kimberly tabtiang
 * @see GraphList
 * @see GraphListUndirected
 * @see GraphMatrixDirected
 */
public class GraphListDirected<V,E> extends GraphList<V,E>
{

    /**
     * Construct a directed, adjacency-list based graph.
     *
     * @post constructs an directed graph
     */
    public GraphListDirected()
    {
        super(true);
    }

    /**
     * Add an edge between two vertices within the graph.  Edge is directed.
     * Duplicate edges are silently replaced.
     * Labels on edges may be null.
     *
     * @pre vLabel1 and vLabel2 are labels of existing vertices, v1 & v2
     * @post an edge is inserted between v1 and v2;
     *       if edge is new, it is labeled with label (can be null)
     * 
     * @param vLabel1 Source vertex.
     * @param vLabel2 Destination vertex.
     * @param label Label associated with the edge.
     */
    public void addEdge(V vLabel1, V vLabel2, E label)
    {
        GraphListVertex<V,E> v1 = dict.get(vLabel1);
        GraphListVertex<V,E> v2 = dict.get(vLabel2);
        Edge<V,E> e = new Edge<V,E>(v1.label(), v2.label(), label, true);
        v1.addEdge(e);
    }

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
        GraphListVertex<V,E> v = dict.get(label);

        Iterator<V> vi = iterator();
        while (vi.hasNext())
        {
            V v2 = vi.next();
            if (!label.equals(v2)) removeEdge(v2,label);
        }
        dict.remove(label);
        return v.label();
    }

    /**
     * Remove possible edge between vertices labeled vLabel1 and vLabel2.
     * vLabel1 is the source.
     *
     * @pre vLabel1 and vLabel2 are labels of existing vertices
     * @post edge is removed, its label is returned
     * 
     * @param vLabel1 Source vertex.
     * @param vLabel2 Destination vertex.
     * @return The label associated with the edge removed.
     */
    public E removeEdge(V vLabel1, V vLabel2)  
    {
        GraphListVertex<V,E> v1 = dict.get(vLabel1);
        GraphListVertex<V,E> v2 = dict.get(vLabel2);
        Edge<V,E> e = new Edge<V,E>(v1.label(), v2.label(), null, true);
        e = v1.removeEdge(e);
        if (e == null) return null;
        else return e.label();
    }

    /**
     * Determine the number of edges in graph.
     *
     * @post returns the number of edges in graph
     * 
     * @return Number of edges in graph.
     */
    public int edgeCount()
    {
        int count = 0;
        Iterator<GraphListVertex<V,E>> i = dict.values().iterator();
        while (i.hasNext())
            count += i.next().degree();
        return count;
    }

    /**
     * Construct a string representation of graph.
     *
     * @post returns string representation of graph
     * 
     * @return String representing graph.
     */
    public String toString()
    {

        return "<GraphListDirected: "+dict.toString()+">";
    }
}
