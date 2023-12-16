/* WUGraph.java */

package graph;

import dict.HashTableChained;
import list.DList;
import list.DListNode;

import java.util.ArrayList;

/**
 * The WUGraph class represents a weighted, undirected graph.  Self-edges are
 * permitted.
 */

public class WUGraph {
  private int vertexCount;
  private int edgeCount;
  private HashTableChained vertexToDListVertex;
  private DList constructedVertexList;
  private HashTableChained edges;
  private HashTableChained edgesToWeights;
  private DList edgeList;



  /**
   * WUGraph() constructs a graph having no vertices or edges.
   * <p>
   * Running time:  O(1).
   */
  public WUGraph() {
    this.vertexCount = 0;
    this.edgeCount = 0;
    this.vertexToDListVertex = new HashTableChained();
    this.constructedVertexList = new DList();
    this.edges = new HashTableChained();
    this.edgesToWeights = new HashTableChained();
    this.edgeList = new DList();
  }

  private DListNode getVertexFromDList(Object vertex) {
    var foundConstructedVertex = this.vertexToDListVertex.find(vertex);
    // remove from doubly linked list
    var dlistVertex = (DListNode) foundConstructedVertex.value();
    return dlistVertex;
  }

  /**
   * vertexCount() returns the number of vertices in the graph.
   * <p>
   * Running time:  O(1).
   */
  public int vertexCount() {
    return this.constructedVertexList.length();
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
    var currNode = this.constructedVertexList.front();
    var objects = new ArrayList<Object>();
    while (currNode != null) {
      objects.add(((Vertex) currNode.item).name);
      currNode = this.constructedVertexList.next(currNode);

    }
    return objects.toArray();
  }

  /**
   * addVertex() adds a vertex (with no incident edges) to the graph.
   * The vertex's "name" is the object provided as the parameter "vertex".
   * If this object is already a vertex of the graph, the graph is unchanged.
   * <p>
   * Running time:  O(1).
   */
  public void addVertex(Object vertex) {
    // check for vertex exists
    if (this.vertexToDListVertex.find(vertex) != null) {
      return;
    }

    var constructedVertex = new Vertex(vertex);
    this.constructedVertexList.insertBack(constructedVertex);
    var dListVertex = this.constructedVertexList.back();
    this.vertexToDListVertex.insert(vertex, dListVertex);
  }

  /**
   * removeVertex() removes a vertex from the graph.  All edges incident on the
   * deleted vertex are removed as well.  If the parameter "vertex" does not
   * represent a vertex of the graph, the graph is unchanged.
   * <p>
   * Running time:  O(d), where d is the degree of "vertex".
   */
  public void removeVertex(Object vertex) {
    if (this.vertexToDListVertex.find(vertex) == null) {
      return;
    }

    var dListVertex = getVertexFromDList(vertex);
    // remove from doubly linked list
    this.constructedVertexList.remove(dListVertex);
    this.vertexToDListVertex.remove(vertex);

    //add edge handling
  }

  /**
   * isVertex() returns true if the parameter "vertex" represents a vertex of
   * the graph.
   * <p>
   * Running time:  O(1).
   */
  public boolean isVertex(Object vertex) {
    return this.vertexToDListVertex.find(vertex) != null;
  }

  /**
   * degree() returns the degree of a vertex.  Self-edges add only one to the
   * degree of a vertex.  If the parameter "vertex" doesn't represent a vertex
   * of the graph, zero is returned.
   * <p>
   * Running time:  O(1).
   */
  public int degree(Object vertex) {
    if (this.vertexToDListVertex.find(vertex) == null) {
      return 0;
    }
    return ((Vertex) getVertexFromDList(vertex).item).edges.length();
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
  public Neighbors getNeighbors(Object vertex) {
      var neighbors = new Neighbors();
      for (var edge : ((Vertex) getVertexFromDList(vertex).item).edges)
      return neighbors;
  }

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
    if (this.vertexToDListVertex.find(u) == null || this.vertexToDListVertex.find(v) == null) {
      return;
    }

    var vertexPair = new VertexPair(u, v);
    var edge = new Edge(vertexPair);

    var foundEdge = this.edges.find(vertexPair);
    DListNode insertedEdge = null;
    if (foundEdge == null) {
      // insert edge into both vertexes
      ((Vertex) getVertexFromDList(u).item).edges.insertBack(edge);
      insertedEdge = ((Vertex) getVertexFromDList(u).item).edges.back();
      edgeCount++;
      ((Vertex) getVertexFromDList(v).item).edges.insertBack(edge);
    }
    this.edgesToWeights.remove(vertexPair);
    this.edgesToWeights.insert(vertexPair, weight);
    this.edges.insert(vertexPair, insertedEdge);

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
    if (this.vertexToDListVertex.find(u) == null || this.vertexToDListVertex.find(v) == null) {
      return;
    }

    var vertexPair = new VertexPair(u, v);
    var foundEdge = this.edges.find(vertexPair);
    if (foundEdge == null) {
      return;
    }

    this.edgesToWeights.remove(vertexPair);
    this.edges.remove(vertexPair);
    ((Vertex) getVertexFromDList(u).item).edges.remove((DListNode) foundEdge.value());
    ((Vertex) getVertexFromDList(v).item).edges.remove((DListNode) foundEdge.value());

  }

  /**
   * isEdge() returns true if (u, v) is an edge of the graph.  Returns false
   * if (u, v) is not an edge (including the case where either of the
   * parameters u and v does not represent a vertex of the graph).
   *
   * Running time:  O(1).
   */
  public boolean isEdge(Object u, Object v) {
    return this.edges.find(new VertexPair(u, v))  != null || this.edges.find(new VertexPair(v, u)) != null;
  }

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
  public int weight(Object u, Object v) {
    var foundWeight = this.edgesToWeights.find(new VertexPair(u, v));
    return foundWeight == null ? 0 : Integer.valueOf(foundWeight.value().toString());
  }

}
