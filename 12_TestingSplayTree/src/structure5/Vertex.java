// Vertex base class, to be extended as needed.
// (c) 1998, 2001 duane a. bailey

package structure5;

/**
 * A private implementation of a vertex for use in graphs.  A vertex
 * is capable of holding a label and has a flag that can be set  
 * to mark it as visited.   
 * <P>
 * Typical Usage:
 * <P>
 * <pre>
 *     Vertex v = new {@link #Vertex(Object) Vertex(someLabel)};
 *     //...several graph related operations occur
 *     if(!v.{@link #isVisited()}){
 *         Object label = v.label();
 *         v.{@link #visit()};
 *     }
 * </pre>
 * @see GraphListVertex
 * @see GraphMatrixVertex
 *        
 * @version $Id: Vertex.java 22 2006-08-21 19:27:26Z bailey $
 * @author, 2001 duane a. bailey
 */
class Vertex<E>
{
    /**
     * A label associated with vertex.
     */
    protected E label;  // the user's label
    /**
     * Whether or not a vertex has been visited.
     */
    protected boolean visited;  // this vertex visited

    /**
     * Construct a vertex with an associated label.
     *
     * @post constructs unvisited vertex with label
     * 
     * @param label A label to be associated with vertex.
     */
    public Vertex(E label)
    {
        Assert.pre(label != null, "Vertex key is non-null");
        this.label = label;
        visited = false;
    }

    /**
     * Fetch the label associated with vertex.
     *
     * @post returns user label associated w/vertex
     * 
     * @return The label associated with vertex.
     */
    public E label()
    {
        return label;
    }

    /**
     * Test and set the visited flag.
     *
     * @post returns, then marks vertex as being visited
     * 
     * @return The value of the flag before marking
     */
    public boolean visit()
    {
        boolean was = visited;
        visited = true;
        return was;
    }

    /**
     * Determine if the vertex has been visited.
     *
     * @post returns true iff vertex has been visited
     * 
     * @return True iff the vertex has been visited.
     */
    public boolean isVisited()
    {
        return visited;
    }
 
    /**
     * Clears the visited flag.
     *
     * @post marks vertex unvisited
     */
    public void reset()
    {
        visited = false;
    }
    
    /**
     * Returns true iff the labels of two vertices are equal.
     *
     * @post returns true iff vertex labels are equal
     * 
     * @param o Another vertex.
     * @return True iff the vertex labels are equal.
     */
    public boolean equals(Object o)
    {
        Vertex<?> v = (Vertex<?>)o;
        return label.equals(v.label());
    }

    /**
     * Return a hashcode associated with the vertex.
     *
     * @post returns hash code for vertex
     * 
     * @return An integer for use in hashing values into tables.
     */
    public int hashCode()
    {
        return label.hashCode();
    }

    /**
     * Construct a string representing vertex.
     *
     * @post returns string representation of vertex
     * 
     * @return A string representing vertex.
     */
    public String toString()
    {
        return "<Vertex: "+label+">";
    }
}
  
