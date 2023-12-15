/* WUGraph.java */

package graph;

import dict.HashTableChained;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The WUGraph class represents a weighted, undirected graph.  Self-edges are
 * permitted.
 */

public class WUGraph {
  private int vertexCount;
  private int edgeCount;
  private List<Vertex> vertices;
  private List<Edge> edges;
  private HashTableChained vertexToIndex;
  private HashTableChained vertexToEdges;
  private HashTableChained vertexPairs;



  /**
   * WUGraph() constructs a graph having no vertices or edges.
   * <p>
   * Running time:  O(1).
   */
  public WUGraph() {
    this.vertexCount = 0;
    this.edgeCount = 0;
    this.vertices = new ArrayList<>();
    this.vertexToIndex = new HashTableChained();
  }

  /**
   * vertexCount() returns the number of vertices in the graph.
   * <p>
   * Running time:  O(1).
   */
  public int vertexCount() {
    return vertexCount;
  }

  /**
   * edgeCount() returns the total number of edges in the graph.
   * <p>
   * Running time:  O(1).
   */
  public int edgeCount() {
    return edgeCount;
  }

  /**
   * getVertices() returns an array containing all the objects that serve
   * as vertices of the graph.  The array's length is exactly equal to the
   * number of vertices.  If the graph has no vertices, the array has length
   * zero.
   * <p>
   * (NOTE:  Do not return any internal data structure you use to represent
   * vertices!  Return only the same objects that were provided by the
   * calling application in calls to addVertex().)
   * <p>
   * Running time:  O(|V|).
   */
  public Object[] getVertices() {
    List<Object> originalObjects = new ArrayList<>();
    for (var vertex : vertices) {
      originalObjects.add(vertex.name);
    }
    return new List[]{originalObjects};
  }

  /**
   * addVertex() adds a vertex (with no incident edges) to the graph.
   * The vertex's "name" is the object provided as the parameter "vertex".
   * If this object is already a vertex of the graph, the graph is unchanged.
   * <p>
   * Running time:  O(1).
   */
  public void addVertex(Object vertex) {
    if (this.vertexToIndex.find(vertex) != null) {
      return;
    }

    Vertex constructedVertex = new Vertex(vertex);
    this.vertices.add(constructedVertex);
    this.vertexToIndex.insert(vertex, vertices.size() - 1);
    this.vertexCount++;
  }

  /**
   * removeVertex() removes a vertex from the graph.  All edges incident on the
   * deleted vertex are removed as well.  If the parameter "vertex" does not
   * represent a vertex of the graph, the graph is unchanged.
   * <p>
   * Running time:  O(d), where d is the degree of "vertex".
   */
  public void removeVertex(Object vertex) {
    if (this.vertexToIndex.find(vertex) != null) {
      return;
    }
    int index = Integer.valueOf(this.vertexToIndex.find(vertex).value().toString());
    this.vertices.remove(index);
    this.vertexToIndex.remove(vertex);
    this.vertexCount--;
    //add edge handling
  }

  /**
   * isVertex() returns true if the parameter "vertex" represents a vertex of
   * the graph.
   * <p>
   * Running time:  O(1).
   */
  public boolean isVertex(Object vertex) {
    return (this.vertexToIndex.find(vertex) != null);
  }

  /**
   * degree() returns the degree of a vertex.  Self-edges add only one to the
   * degree of a vertex.  If the parameter "vertex" doesn't represent a vertex
   * of the graph, zero is returned.
   * <p>
   * Running time:  O(1).
   */
  public int degree(Object vertex) {
    return 0;
  }

  /**
   * getNeighbors() returns a new Neighbors object referencing two arrays.  The
   * Neighbors.neighborList array contains each object that is connected to the
   * input object by an edge.  The Neighbors.weightList array contains the
   * weights of the corresponding edges.  The length of both arrays is equal to
   * the number of edges incident on the input vertex.  If the vertex has
   * degree zero, or if the parameter "vertex" does not represent a vertex of
   * the graph, null is returned (instead of a Neighbors object).
   *
   * The returned Neighbors object, and the two arrays, are both newly created.
   * No previously existing Neighbors object or array is changed.
   *
   * (NOTE:  In the neighborList array, do not return any internal data
   * structure you use to represent vertices!  Return only the same objects
   * that were provided by the calling application in calls to addVertex().)
   *
   * Running time:  O(d), where d is the degree of "vertex".
   */
  public Neighbors getNeighbors(Object vertex);

  /**
   * addEdge() adds an edge (u, v) to the graph.  If either of the parameters
   * u and v does not represent a vertex of the graph, the graph is unchanged.
   * The edge is assigned a weight of "weight".  If the graph already contains
   * edge (u, v), the weight is updated to reflect the new value.  Self-edges
   * (where u.equals(v)) are allowed.
   * <p>
   * Running time:  O(1).
   */
  public void addEdge(Object u, Object v, int weight) {
    Vertex firstVertex = new Vertex(u);
    Vertex secondVertex = new Vertex(v);
    //convert objects into vertex
    if (this.vertexToIndex.find(firstVertex) == null || this.vertexToIndex.find(secondVertex) == null) {
      return;
    }

    int firstIndex = Integer.valueOf(this.vertexToIndex.find(firstVertex).value().toString());
    int secondIndex = Integer.valueOf(this.vertexToIndex.find(secondVertex).value().toString());
    //find index of each vertex
    Vertex firstExistingVertex = this.vertices.get(firstIndex);
    Vertex secondExistingVertex = this.vertices.get(secondIndex);
    VertexPair newPair = new VertexPair(firstExistingVertex, secondExistingVertex);
    this.vertexPairs.insert(newPair, weight);


/*    List<Edge> firstEdges = (List<Edge>) this.vertexToEdges.find(firstExistingVertex).value();
      for (var edge : firstEdges) {
      if (edge == )
    }*/

  }

  /**
   * removeEdge() removes an edge (u, v) from the graph.  If either of the
   * parameters u and v does not represent a vertex of the graph, the graph
   * is unchanged.  If (u, v) is not an edge of the graph, the graph is
   * unchanged.
   * <p>
   * Running time:  O(1).
   */
  public void removeEdge(Object u, Object v) {

  }

  /**
   * isEdge() returns true if (u, v) is an edge of the graph.  Returns false
   * if (u, v) is not an edge (including the case where either of the
   * parameters u and v does not represent a vertex of the graph).
   *
   * Running time:  O(1).
   */
  public boolean isEdge(Object u, Object v);

  /**
   * weight() returns the weight of (u, v).  Returns zero if (u, v) is not
   * an edge (including the case where either of the parameters u and v does
   * not represent a vertex of the graph).
   *
   * (NOTE:  A well-behaved application should try to avoid calling this
   * method for an edge that is not in the graph, and should certainly not
   * treat the result as if it actually represents an edge with weight zero.
   * However, some sort of default response is necessary for missing edges,
   * so we return zero.  An exception would be more appropriate, but also more
   * annoying.)
   *
   * Running time:  O(1).
   */
  public int weight(Object u, Object v);

}
